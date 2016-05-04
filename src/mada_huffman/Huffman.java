package mada_huffman;

public class Huffman {

     static int[] getCharacterFrequency(String asciistring) {
         int[] frequencies = new int[128];
         for (char c : asciistring.toCharArray()) {
             frequencies[c]++;
         }
         return frequencies;
     }
     
     static String encode(CodeTable codeTable, String text) {
    	 String res = "";
    	 for (int i = 0; i < text.length(); i++)
    		 res += codeTable.get(text.substring(i, i+1));
    	 return res;
     }
     
     static String decode(CodeTable codeTable, String encoded) {
    	 return null;
     }
     
     
     
    

}
