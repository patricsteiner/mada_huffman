package mada_huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Program {

    public static void main(String[] args) throws IOException {
        
        HuffmanTree tree = new HuffmanTree(Huffman.getCharacterFrequency("ASas"));
        System.out.println(tree.createCodeMap());
        exportCodeMap(tree.createCodeMap(), "dec_tab.txt");
        System.out.println(readAsciiFile("text.txt"));
        
    }
    
    static String readAsciiFile(String src) throws IOException {
    	return new String(Files.readAllBytes(Paths.get(src)), "ASCII");
    }
    
    static void exportCodeMap(HashMap<String, String> codeMap, String dest) throws IOException {
	    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dest)));
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
