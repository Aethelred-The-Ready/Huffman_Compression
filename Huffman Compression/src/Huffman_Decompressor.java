import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman_Decompressor {

	public static void main(String[] args) {
		File f = new File("deCompress.txt");
		String treeS = "";
		String tD = "";
		int l = 0;
		try {
			Scanner s = new Scanner(f);
			treeS = s.nextLine();
			tD = s.nextLine();
			l = s.nextInt();
		} catch (Exception e) {}
		
		long time = System.nanoTime();
		
		//tD = hexToBin(tD);
		//tD = tD.substring(0, l);
		
		String deComp = "";
		ArrayList<Character> toD = new ArrayList<Character>();
		
		Huffman_Tree tree = treeGen(treeS);
		
		for(int i = 0;i < tD.length();i++){
			toD.add(tD.charAt(i));
		}
		
		String temp = "";
		
		while(toD.size() > 0){
			temp = tree.interpret(toD);
			System.out.print(temp);
			//try {
			//	Thread.sleep(10);
			//} catch (Exception e) {}
			deComp += temp;
		}
		
		System.out.print("\n" + (System.nanoTime() - time));
		
		//System.out.println(deComp);
		
	}
	
	public static Huffman_Tree treeGen(String s){
		PriorityQueue<Huffman_Tree> trees = new PriorityQueue<Huffman_Tree>();
		
		s = s.substring(1);
		
		
		String[] ch = s.split(";");

		for(int i = 0;i < ch.length;i++){
			trees.offer(new Huffman_Leaf(Integer.parseInt(ch[i].substring(1)),ch[i].charAt(0)));
		}
		
		while(trees.size() > 1){
			Huffman_Tree a = trees.poll();
	        Huffman_Tree b = trees.poll();
	        	
	        trees.offer(new Huffman_Branch(a,b));
	    }
	        
	    return trees.poll();
		
	}
	
	static String hexToBin(String s) {
		return new BigInteger(s, 16).toString(2);
	}

}