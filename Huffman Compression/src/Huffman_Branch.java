import java.util.ArrayList;

public class Huffman_Branch extends Huffman_Tree {

	public Huffman_Tree _0, _1;
	
	public Huffman_Branch(Huffman_Tree l, Huffman_Tree r) {
		super(l.freq + r.freq);
		_0 = l;
		_1 = r;

	}

	public String interpret(ArrayList<Character> tI) {
		if(tI.get(0) == '0'){
			tI.remove(0);
			return _0.interpret(tI);
		}else{
			tI.remove(0);
			return _1.interpret(tI);
		}
		
		
	}

}
