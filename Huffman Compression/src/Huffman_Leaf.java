import java.util.ArrayList;

public class Huffman_Leaf extends Huffman_Tree {

	public char ch;
	
	public Huffman_Leaf(int fr, char c) {
		super(fr);
		ch = c;
	}

	public String interpret(ArrayList<Character> tI) {
		return ch + "";
	}

}
