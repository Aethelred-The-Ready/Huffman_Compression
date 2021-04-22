import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman_Decompressor {

	public static void main(String[] args) {
		File f = new File("Compress.txt");
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
		
		
		//String temp = "";
		
		//for(int i = 0;i < tD.length();i++){
		//	temp += ((int) tD.charAt(i)) + "";
		//}
		
		tD = toBin(tD);
		//System.out.println(tD.replace(""," "));
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
			//System.out.print(temp);
			//try {
			//	Thread.sleep(10);
			//} catch (Exception e) {}
			deComp += temp;
		}
		
		System.out.println("\n" + (System.nanoTime() - time));
		
		
		System.out.println(deComp);
		
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
	
	static String toBin(String s) {
		String tr = "";
		int t = 0;
		for(int i = 0;i < s.length();i++){
			t = ((int) s.charAt(i)) - 32;
			//System.out.println(t);
			if(t > 31)
				tr += toBinStr(t);
			else
				tr += "0" + toBinStr(t);
			t = 0;
		}
		return tr;
	}
	
	private static String toBinStr(int a){
		if(a > 1){
			return toBinStr((int) a/2) + (a%2);
		}else if(a == 1){
			return "1";
		}else{
			return "0";
		}
	}
	

}