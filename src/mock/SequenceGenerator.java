package mock;

import reader.NucleotideException;
import reader.Sequence;

import java.util.List;
import java.util.Random;

/**
 * Created by schoen on 4/21/16.
 */
public class SequenceGenerator {
    private List<Sequence> sequences;
    private Distribution distribution;

    public SequenceGenerator(List<Sequence> sequences, Distribution distribution) {
        this.sequences = sequences;
        this.distribution = distribution;
    }

    public Sequence createSequence() throws NucleotideException {
        Random rand = new Random();
        int randomLength = distribution.drawFromDistribution();
        int seqLength = 0;
        Sequence selectedSequence = null;
        while (seqLength < randomLength - 1) {
            int randomSequence = rand.nextInt(sequences.size() - 1);
            selectedSequence = sequences.get(randomSequence);
            seqLength = selectedSequence.getSequenceLength();
        }
        int randomStartPosition = rand.nextInt(seqLength - randomLength);
        String newID = selectedSequence.getId() + ":" + randomStartPosition + "-" + (randomLength + randomStartPosition);
        String newSeq = selectedSequence.print_positions(randomStartPosition, randomLength + randomStartPosition);
        return new Sequence(newSeq, newID);
    }
}
