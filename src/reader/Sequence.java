package reader;

import java.lang.String;

/**
 * representation of an RNA/DNA (nucleotide) Sequence
 */
public class Sequence {
    private String id;
    private Nucleotide[] seq;
    private int sequenceLength;

    public Sequence(String sequence, String identifier) throws NucleotideException {
        id = identifier;
        seq = new Nucleotide[sequence.length()];
        sequenceLength = sequence.length();
        for (int i = 0; i < sequence.length(); i++) {
            seq[i] = new Nucleotide(sequence.charAt(i));
        }
    }

    public String print_positions(int start, int end) {
        String part = "";
        for (int i = start; i < end; i++) {
            part = part + seq[i].toString();
        }
        return part;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append('>');
        buffer.append(id);
        buffer.append('\n');
        int count = 0;
        for (Nucleotide nucl : seq) {
            buffer.append(nucl.toString());
            count++;
            if (count > 79) {
                buffer.append('\n');
                count = 0;
            }
        }
        buffer.append('\n');
        return buffer.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Nucleotide[] getSeq() {
        return seq;
    }

    public void setSeq(Nucleotide[] seq) {
        this.seq = seq;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public void setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }
}

