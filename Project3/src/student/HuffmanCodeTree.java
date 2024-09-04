//Cameron Nagle
//12/09/2023
//This program makes a HuffmanCodeTree
package student;

import provided.BinarySequence;

public class HuffmanCodeTree {
    /**
     * @param root the root of the code tree
     */
    private HuffmanNode root;

    /**
     * @param root setting the huffman code tree given one root node
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * @param codebook setting the huffman code tree given a codebook
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        this.root = new HuffmanNode(null, null);
        for (int i = 0; i < codebook.getLength(); ++i) {
            put(codebook.getSequence(i), codebook.getLetter(i));
        }
    }

    /**
     * @return if the huffman code tree is valid starting at the root and checking all the descendants
     */
    public boolean isValid() {
        return root.isValidTree();
    }

    /**
     * @param seq the binary sequence that will be run through
     * @param letter the char that will be put into the huffman code tree
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode newRootNode = root;
        for (int i = 0; i < seq.size(); i++) {
            if (seq.get(i)) {
                if (newRootNode.getOne() == null) {
                    newRootNode.setOne(new HuffmanNode(null, null));
                }
                newRootNode = newRootNode.getOne();
            } else {
                if (newRootNode.getZero() == null) {
                    newRootNode.setZero(new HuffmanNode(null, null));
                }
                newRootNode = newRootNode.getZero();
            }
        }
        newRootNode.setData(letter);
    }

    /**
     * @param s the binary sequence that will be run through
     * @return the string that is created from the binary sequence translated through the huffman code book data
     */
    public String decode(BinarySequence s) {
        HuffmanNode newRootNode = root;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.size(); ++i) {
            if (s.get(i)) {
                newRootNode = newRootNode.getOne();
            } else {
                newRootNode = newRootNode.getZero();
            }
            if (newRootNode.isLeaf()) {
                out.append(newRootNode.getData());
                newRootNode = root;
            }
        }
        return out.toString();
    }
}
