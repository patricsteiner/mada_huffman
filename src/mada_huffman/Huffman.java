package mada_huffman;

import java.util.HashMap;

import mada_huffman.HuffmanTree.Node;

public class Huffman {

     static int[] getCharacterFrequency(String asciistring) {
         int[] frequencies = new int[128];
         for (char c : asciistring.toCharArray()) {
             frequencies[c]++;
         }
         return frequencies;
     }
     
     static HashMap<String, String> createCodeMap(HuffmanTree huffmanTree) {
         HashMap<String, String> codeMap = new HashMap<String, String>();
         for (Node leaf : huffmanTree.getLeaves()) {
        	 codeMap.put(leaf.symbol, huffmanTree.getCode(leaf));
         }
         return codeMap;
     }
     

     
    

}
