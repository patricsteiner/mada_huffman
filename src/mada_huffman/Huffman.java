package mada_huffman;

/**
 * Contains methods for Huffman related operations
 */
public class Huffman {

	/**
	 * @param asciistring
	 * @return int array with length 128 containing the character counts in the given string
	 */
     static int[] getCharacterFrequency(String asciistring) {
         int[] frequencies = new int[128];
         for (char c : asciistring.toCharArray()) {
             frequencies[c]++;
         }
         return frequencies;
     }
     
     /**
      * Formats frequencies nicely
      * Used to show in GUI
      */
     static String frequenciesToString(int[] frequencies) {
    	 StringBuilder sb = new StringBuilder();
    	 for (int i = 0; i < frequencies.length; i++) {
    		if (frequencies[i] != 0)
    			sb.append((char)i + " (" + String.format("%1$03d", i) +"): " + frequencies[i] + "\n");
		}
		return sb.toString();
     }
     
     
     /**
      * encode given text with given codeTable
      * @param codeTable
      * @param text
      * @return encoded text (String containing only 0 and 1)
      */
     static String encode(CodeTable codeTable, String text) {
    	 String res = "";
    	 for (int i = 0; i < text.length(); i++)
    		 res += codeTable.get(text.substring(i, i+1));
    	 return res;
     }
     
     /**
      * decode given bitstring
      * @param codeTable
      * @param encoded the encoded String (containing only 0 and 1)
      * @return decoded text
      */
     static String decode(CodeTable codeTable, String encoded) {
    	 String res = "";
    	 int start = 0, end = 0; //two pointers to operate on the encoded string
    	 while (end < encoded.length()) {
    		 //search for a matching code in the codetable by increasing the length of the substring.
    		 //because not every code has the same length we need to try and error
        	 while (codeTable.getSymbolFromCode(encoded.substring(start, end)) == null) end++;
        	 res += codeTable.getSymbolFromCode(encoded.substring(start, end));
        	 start = end;
    	 }
    	 return res;
     }
}
