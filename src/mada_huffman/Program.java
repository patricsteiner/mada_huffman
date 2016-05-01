package mada_huffman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Program {

    public static void main(String[] args) throws IOException {
        
        HuffmanTree tree = new HuffmanTree(Huffman.getCharacterFrequency("aaaaaaaaaaaaaaaaaahelloowood"));
        System.out.println(tree.createCodeMap());
        writeCodeMapToFile(tree.createCodeMap(), new File("dec_tab.txt"));
        
        
    }
    
    static void writeCodeMapToFile(HashMap<String, String> codeMap, File file) throws IOException {
	    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	    Iterator<Entry<String, String>> iterator = codeMap.entrySet().iterator();
	    Entry<String, String> entry;
    	while (iterator.hasNext()) {
    		entry = iterator.next();
			bw.write((int)entry.getKey().charAt(0) + ":" + entry.getValue());
			if (iterator.hasNext()) bw.write("-");
		}
    	bw.close();
    }
}
