package mada_huffman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Huffman {

    static HashSet<HuffmanTree> leafes;
     static int[] getCharacterFrequency(String asciistring) {
         int[] frequencies = new int[128];
         for (char c : asciistring.toCharArray()) {
             frequencies[c]++;
         }
         return frequencies;
     }
     
     static int[] createCode(int[] frequencies) {
         TreeMap<Character, Integer> tree = new TreeMap<Character, Integer>();
         for (int i = 0; i < frequencies.length; i++) {
             if (frequencies[i] > 0) map.put((char) i, frequencies[i]);
         }
         HashMap<Character, String> code = new HashMap<Character, String>();
         
         Entry<Character, Integer> left;
         Entry<Character, Integer> right;
         while (tree.size() > 1) {
             left = tree.pollFirstEntry();
             right = tree.pollFirstEntry();
             code.put(left.getKey(), left.getValue() + "0");
             code.put(right.getKey(), right.getValue() + "1");
             tree.put('*', left.getValue() + right.getValue());
         }
         
         
         
        
         
         
     }
     
    

}
