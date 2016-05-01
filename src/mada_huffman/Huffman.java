package mada_huffman;

import java.util.HashMap;

public class Huffman {

     static int[] getCharacterFrequency(String asciistring) {
         int[] frequencies = new int[128];
         for (char c : asciistring.toCharArray()) {
             frequencies[c]++;
         }
         return frequencies;
     }
     
     static String encode(String text) {
    	 return null;
     }
     
     static String decode(HashMap<String, String> codeMap, String encoded) {
    	 return null;
     }
     
     
     
    

}
