import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 
 * @author wongt1
 *
 */
public class POS_Tagging {
	
	/**
	 * Main Method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		start();
	}
	
	/**
	 * Scans through the text file
	 * @throws IOException
	 */
	private static void start() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/greenEggs.txt"));
		HashSet<String> text = new HashSet<>();
		String line = "";
		
		//Creates a HashSet of the unique words in the text file
		while ((line = br.readLine()) != null){
			String[] sentence_array = line.split(" ");
			
			for(int i = 0; i < sentence_array.length; i++){
				String word = sentence_array[i];
				word = cleanUpString(word);
				
				text.add(word);
			}
		}
		
		//Used to alphabetize the HashSet, not particularly useful (?)
//		Iterator<String> iterator = text.iterator(); 
//		ArrayList<String> list = new ArrayList<>();
//		while(iterator.hasNext()){
//			list.add(iterator.next());
//		}
//		Collections.sort(list);
//		for(String s : list){
//			System.out.println(s);
//		}
		
		br.close();
		parse(text);
	}
	
	/**
	 * Reads from the parse file
	 * hs is the HashSet of the unique words from the text file
	 * @param hs
	 */
	private static void parse(HashSet<String> hs) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("src/words.txt"));
		HashMap<String, String> parse = new HashMap<>();
		String line = "";
		
		while((line = br.readLine()) != null){
			//Tabs, not white space
			String[] sentence_array = line.split("	");
			
			//sentence_array[0] is the word, sentence_array[sentence_array.length - 1] is the part of speech
			if(hs.contains(sentence_array[0])){
				parse.put(sentence_array[0], sentence_array[sentence_array.length - 1]);
			}
		}
		
		br.close();
		tag(parse);
	}
	
	/**
	 * With the text HashSet and parse HashMap, you use the two to tag part of speech
	 * @param hm
	 * @throws IOException
	 */
	private static void tag(HashMap<String, String> hm) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("src/greenEggs.txt"));
		String line = "";
		
		while ((line = br.readLine()) != null){
			String[] sentence_array = line.split(" ");
			
			for(int i = 0; i < sentence_array.length; i++){
				String word = sentence_array[i];
				word = cleanUpString(word);
				
				if(hm.get(word) != null){
					word = sentence_array[i] + " (" + hm.get(word) + ") ";
					System.out.print(word);	
				}
			}
			System.out.println();
		}
		
		br.close();
	}
	
	/**
	 * Method to clean up the String.
	 * @param s
	 * @return
	 */
	private static String cleanUpString(String s) {
		s = s.toLowerCase();
		s = s.replaceAll("[!?]", "");
		s = s.replaceAll("[,.-]", "");
		s = s.replaceAll("’s", "");
		s = s.replaceAll("'s", "");
		s = s.replaceAll("[â€˜™]", "");
		s = s.replaceAll("[0123456789]", "");
		s = s.replaceAll("\\s+", "");
		s = s.trim();
		return s;
	}
}
