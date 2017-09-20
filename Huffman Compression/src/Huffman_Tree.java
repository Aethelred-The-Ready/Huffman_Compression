import java.util.ArrayList;

public abstract class Huffman_Tree implements Comparable<Huffman_Tree> {
	public int freq;
	
	public Huffman_Tree(int fr){
		freq = fr;
	}
	
	public int compareTo(Huffman_Tree o){
		return freq - o.freq;
	}
	
	public abstract String interpret(ArrayList<Character> tI);
}
