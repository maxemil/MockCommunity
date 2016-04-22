package reader;

import java.lang.String;

/**
 * representation of a single nucleotide
 */
public class Nucleotide {
    private String accepted = "atgcnyrswkm-ATGCNYRSWKM";
    private char i;

    public Nucleotide(char i) throws NucleotideException {
        if (accepted.contains(String.valueOf(i))) {
            this.i = i;
        } else {
            throw new NucleotideException("Wrong/Unknown Nucleotide in Sequence");
        }
    }

    public String toString() {
        return String.valueOf(i);
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public char getI() {
        return i;
    }

    public void setI(char i) {
        this.i = i;
    }
}
