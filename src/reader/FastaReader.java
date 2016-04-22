package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for parsing and writing an Alignment file in FastA format
 */
public class FastaReader {
    private List<Sequence> sequences;

    public FastaReader(String file) throws IOException, NucleotideException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        read(reader);
        reader.close();
    }

    public void read(BufferedReader reader) throws IOException, NucleotideException {
        sequences = new ArrayList<Sequence>();
        String id = "";
        String seq = "";
        int count = -1;
        while (reader.ready()) {
            reader.mark(1);
            if (reader.read() == '>') {
                if (count > -1) {
                    sequences.add(new Sequence(seq, id));
                    id = "";
                    seq = "";
                }
                count++;
                id = reader.readLine();
            } else if (seq == "") {
                reader.reset();
                seq = reader.readLine();
            } else {
                reader.reset();
                seq = seq + reader.readLine();
            }
        }
        sequences.add(new Sequence(seq, id));
        return;
    }

    public void write(String out) throws IOException {
        Writer writer = new FileWriter(out);
        for (Sequence seq : sequences) {
            writer.write(seq.toString());
        }
        writer.close();
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }
}
