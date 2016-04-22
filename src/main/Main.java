package main;

import mock.Distribution;
import mock.SequenceGenerator;
import org.apache.commons.cli.*;
import reader.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoen on 4/21/16.
 */
public class Main {
    private static final String CLASS_NAME = "MockCom";
    private static final int communitySize = 10000;

    public static void main(String[] args) {

        Options helpOptions = new Options();
        helpOptions.addOption("h", "help", false, "show this help page");

        Options options = new Options();
        options.addOption("h", "help", false, "show this help page");
        options.addOption("i", "input", true, "the input fasta file");
        options.addOption("d", "distribution", true, "The file containing a length distribution as given by fastqc");
        options.addOption("o", "output", true, "the output fasta file");

        HelpFormatter helpformatter = new HelpFormatter();
        CommandLineParser parser = new BasicParser();

        FastaReader reader;

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')) {
                helpformatter.printHelp(CLASS_NAME, options);
                System.exit(0);
            }
            reader = new FastaReader(cmd.getOptionValue("input"));
            List<Sequence> sequences = reader.getSequences();
            Distribution distribution = new Distribution(cmd.getOptionValue("distribution"));
            SequenceGenerator generator = new SequenceGenerator(sequences, distribution);

            ArrayList<Sequence> mockCommunity = new ArrayList<>();

            for (int i = 0; i < communitySize; i++) {
                mockCommunity.add(generator.createSequence());
            }
            reader.setSequences(mockCommunity);

            reader.write(cmd.getOptionValue("output"));

        } catch (Exception e) {
            System.out.println("File is missing or in a wrong format...");
            e.printStackTrace();
            System.exit(-1);
        }


    }
}
