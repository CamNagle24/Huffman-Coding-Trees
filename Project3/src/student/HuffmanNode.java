//Cameron Nagle
//12/09/2023
//This program makes a HuffmanNode for a HuffmanCodeTree
package student;

public class HuffmanNode {
    /**
     * @param zero the zero child node of the node
     */
    private HuffmanNode zero;
    /**
     * @param one the one child node of the node
     */
    private HuffmanNode one;
    /**
     * @param data the data Character stored in the node
     */
    private Character data;

    /**
     * @param zero setting the zero child node of the node
     * @param one setting the one child node of the node
     */

    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        this.data = null;
    }

    /**
     * @param data setting the char data going into the new created node
     */
    public HuffmanNode(char data) {
        this.zero = null;
        this.one = null;
        this.data = data;
    }

    /**
     * @return the zero child of the node
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * @param zero setting the zero child of the node
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * @return the one child of the node
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * @param one setting the one child of the node
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * @return the character in the node
     */
    public Character getData() {
        return data;
    }

    /**
     *
     * @param data setting the data char in the node
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * @return if the node is a leaf or not
     */
    public boolean isLeaf() {
        if (this.data != null && this.zero == null && this.one == null) {
            return true;
        }
        return false;
    }

    /**
     * @return if the node is valid or not
     */
    public boolean isValidNode() {
        if (this.isLeaf() || (this.data == null  && this.zero != null && this.one != null)) {
            return true;
        }
        return false;
    }

    /**
     * @return if this node and all descendant nodes are valid for a huffman coding tree
     */
    public boolean isValidTree() {

        if (isLeaf()) {
            return true;
        } else if (this.data == null  && this.zero != null && this.one != null) {
            if (this.isValidNode() && this.zero.isValidTree() && this.one.isValidTree()) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
