import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman_Compressor {

	static String[] codes = new String[256];
	
	public static void main(String[] args) {
		
		File f = new File("Compress.txt");
		Scanner s = new Scanner(System.in);
		String toCompress = "Seven of Claudio Monteverdi's operas are lost, apart from a few fragments, out of ten the Italian composer wrote in whole or in part between 1607 and 1643. A few librettos from these early baroque works have survived. Opera as a genre emerged during Monteverdi's creative lifetime, and he became a principal exponent of this new form, first at the Mantuan court and later as director of music at St Mark's Basilica in Venice. The loss of these works, written during a critical period of early opera history, has been much regretted by historians and musicologists, but reflects the habit of the times, when stage music was thought to have little relevance beyond its initial performance and often vanished quickly. Contemporary documents, including many letters written by Monteverdi, have provided most of the available information on the lost works, and have established that four of them were completed and performed in the composer's lifetime. Of the little music that has survived, the lamento from L'Arianna (1608) is frequently performed. (Full article...)";
		//toCompress = s.nextLine();
		int[] freqs = new int[256];
		
		for(char c:toCompress.toCharArray()){
			freqs[c]++;
		}
		
		Huffman_Tree tree = tree_Builder(freqs);
		
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");

        printCodes(tree, "");
        
        String compressed = convert(tree, toCompress);
        
        //System.out.println(compressed);
        
        
        
        
        
//      String deComp = "";
//		ArrayList<Character> toD = new ArrayList<Character>();
//		
//		for(int i = 0;i < compressed.length();i++){
//			toD.add(compressed.charAt(i));
//		}
//		
//		while(toD.size() > 0){
//			deComp += tree.interpret(toD);
//		}
//		
//		System.out.println(deComp);
        
        //compressCodes(tree);
        
        
        System.out.println(toCompress);
        System.out.println(forDeCon(freqs));
        System.out.println(compressed);
        System.out.println(compressed.length());
        
        try {
        	PrintWriter pw = new PrintWriter("Compress.txt", "UTF-8");
        	String temp = forDeCon(freqs); 
			pw.println(temp);
			pw.println(compressed);
			pw.println(compressed.length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
        
	}
	
	public static Huffman_Tree tree_Builder(int[] freq){
		PriorityQueue<Huffman_Tree> trees = new PriorityQueue<Huffman_Tree>();
        for (int i = 0; i < freq.length; i++){
            if (freq[i] > 0){
                trees.offer(new Huffman_Leaf(freq[i], (char)i));
            }
        }
        
        while(trees.size() > 1){
        	Huffman_Tree a = trees.poll();
        	Huffman_Tree b = trees.poll();
        	
        	trees.offer(new Huffman_Branch(a,b));
        }
        
        return trees.poll();
		
	}
	
	public static void printCodes(Huffman_Tree a, String pre){
		if(a instanceof Huffman_Leaf){
            System.out.println(((Huffman_Leaf) a).ch + "\t" + a.freq + "\t" + pre);
            codes[((Huffman_Leaf) a).ch] = pre;
		}else{
			pre += "0";
			printCodes(((Huffman_Branch)a)._0, pre);
            pre = pre.substring(0, pre.length()-1);
            

			pre += "1";
			printCodes(((Huffman_Branch)a)._1, pre);
			pre = pre.substring(0, pre.length()-1);
		}
	}
	
	public static void compressCodes(Huffman_Tree a){
		if(a instanceof Huffman_Leaf){
            System.out.print(((Huffman_Leaf) a).ch);
		}else{
			System.out.print("[");
			compressCodes(((Huffman_Branch)a)._0);
			System.out.print("|");
			compressCodes(((Huffman_Branch)a)._1);
			System.out.print("]");
		}
	}
	
	public static String convert(Huffman_Tree a, String tC){
		String compressed = "";
		for(int i = 0;i < tC.length();i++){
			compressed += codes[tC.charAt(i)];
		}
		return compressed;
	}
	
	public static String forDeCon(int[] freq){
		String tr = "";
		for(int i = 0;i < freq.length;i++){
			if(freq[i] != 0){
				tr += ";" + ((char) i) + freq[i];
			}
		}
		return tr;
	}
	
	public static String toHex(String comp){
		String tDo = "";
		int temp = 0;
		for(int i = 0;i < ((int) (comp.length()/4));i++){
			for(int k = 0;k < 4;k++){
				if(comp.charAt(k + i*4) == '0'){
				
				}else if(comp.charAt(k + i*4) == '1'){
					temp += Math.pow(2, 4 - k - 1);
				}
			}
			tDo += Integer.toHexString(temp);
		}
		return tDo;
		
	}

}
