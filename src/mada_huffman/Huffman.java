package mada_huffman;

import java.math.BigInteger;

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
    	 res += "1";
    	 for (int i = 8 - res.length() % 8; i > 0; i--)
    		 res += "0";
    	 return res;
     }
     
     static byte[] binaryStringToByteArray(String binaryString) throws Exception {
    	 assert binaryString.length() % 8 == 0;
    	 assert binaryString.matches("^[01]*$");
    	 byte[] bytes = new byte[binaryString.length()/8];
    	 String substring;
    	 byte b;
    	 for (int i = 0; i < bytes.length; i++) {
    		 //get next 8 chars
    		 substring = binaryString.substring(8*i, 8*i + 8);
    		 b = 0;
    		 for (int j = 0; j < 8; j++) {
    			 b |= (substring.charAt(j) == '0') ? 0 : 1;
    			 if (j < 7 ) b <<= 1; //else there is 1 shift too much
    		 }
    		 bytes[i] = b;
    	 }
    	 return bytes;
    	 
     }
     
     static String byteArrayToBinaryString(byte[] bytes) {
    	 String res = "";
    	 for (byte b : bytes) {
    		 for(int i = 0; i < 8; i++) {
    			 res += ((b & 1) == 1) ? "1" : "0";
    			 b >>= 1;
    		 }		 
    	 }
    	 return res;
     }
     
     static String decode(CodeTable codeTable, String encoded) {
    	 return null;
     }
     
     
     
    

}
