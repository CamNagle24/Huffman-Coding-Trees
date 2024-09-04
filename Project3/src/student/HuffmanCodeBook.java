//Cameron Nagle
//12/09/2023
//This program makes a HuffmanCodeBook
package student;

import provided.BinarySequence;

public class HuffmanCodeBook {
    /**
     * @param length the length of the char and seq arrays
     */
    private int length = 0;
    /**
     * @param Maxlength the max length that the char and seq arrays could be until expanded
     */
    private int maxLength = 0;
    /**
     * @param charArray the array storing all the char data in the code book
     */
    private char[] charArray = new char[maxLength];
    /**
     * @param BinarySequence the array storing all the binary sequences data in the code book
     */
    private BinarySequence[] seqArray = new BinarySequence[maxLength];

    /**
     * Empty constructor set the values elsewhere.
     */
    public HuffmanCodeBook() {
    }

    /**
     * @return the length of the charArray a seqArray
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @param c the char adding to the codeBook
     * @param seq the BinarySequence adding to the CodeBook
     */
    public void addSequence(char c, BinarySequence seq) {
        if (this.length == maxLength) {
            if (this.length == 0) {
                maxLength = 1;
                charArray = new char[maxLength];
                seqArray = new BinarySequence[maxLength];
            } else {
                maxLength *= 2;
                char[] tempCharArray = new char[maxLength];
                BinarySequence[] tempSeqArray = new BinarySequence[maxLength];
                for (int i = 0; i < charArray.length; ++i) {
                    tempCharArray[i] = charArray[i];
                    tempSeqArray[i] = seqArray[i];
                }
                charArray = new char[maxLength];
                seqArray = new BinarySequence[maxLength];
                for (int i = 0; i < tempCharArray.length; ++i) {
                    charArray[i] = tempCharArray[i];
                    seqArray[i] = tempSeqArray[i];
                }
            }
        }
        if (this.length == 0) {
            this.charArray[0] = c;
            this.seqArray[0] = seq;
        } else {
            int i = 0;
            int shifted = length;
            while (i < this.length) {
                if (this.charArray[i] >= c) {
                    shifted = i;
                    i = this.length;
                }
                ++i;
            }
            for (i = this.length; i > shifted; --i) {
                this.charArray[i] = this.charArray[i - 1];
                this.seqArray[i] = this.seqArray[i - 1];
            }
            this.charArray[shifted] = c;
            this.seqArray[shifted] = seq;
        }
        length++;
    }

    /**
     * @param letter the char letter being looked for in the codebook
     * @return true if the char is in the codebook or false if not
     */
    public boolean contains(char letter) {
        if (this.length > 0) {
            int l = 0;
            int r = this.length - 1;
            int m;
            while (l <= r) {
                m = (l + r) / 2;
                if (charArray[m] == letter) {
                    return true;
                } else if (charArray[m] < letter) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * @param letters the string of letters that you will look if are in the codebook
     * @return true if all the letters are in the codebook and false if not
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); ++i) {
            if (!contains(letters.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param c the char to look for what the sequence is
     * @return the binary sequence at char cor null if you could not find
     */
    public BinarySequence getSequence(char c) {
        int l = 0;
        int r = this.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (charArray[m] == c) {
                return seqArray[m];
            } else if (charArray[m] < c) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return null;
    }

    /**
     * @param i the int to look for the spot in the binary sequence array
     * @return the sequence array value at i
     */
    public BinarySequence getSequence(int i) {
        return seqArray[i];
    }

    /**
     * @param i the int to look for the spot in the char array
     * @return the char value at i
     */
    public char getLetter(int i) {
        return charArray[i];
    }

    /**
     * @param s the string to encode into a binary sequence using the codebook values
     * @return the binary sequence that represents the string
     */
    public BinarySequence encode(String s) {
        BinarySequence out = new BinarySequence();
        for (int i = 0; i < s.length(); ++i) {
            out.append(getSequence(s.charAt(i)));
        }
        return out;
    }
}
