package mada_huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for reading and writing files.
 */
public class IOUtil {
	
	static String readAsciiFile(String src) throws IOException {
    	return new String(Files.readAllBytes(Paths.get(src)), "ASCII");
    }
    
    static void writeToFile(String string, String dest) throws IOException {
        Files.write(Paths.get(dest), string.getBytes());
    }
    
    static byte[] readBytesFromFile(String src) throws IOException {
    	File file = new File(src);
    	byte[] bytes = new byte[(int) file.length()];
    	FileInputStream fis = new FileInputStream(file);
    	fis.read(bytes);
    	fis.close();
    	return bytes;
    }
    
    static void writeBytesToFile(byte[] bytes, String dest) throws IOException {
    	FileOutputStream fos = new FileOutputStream(new File(dest));
    	fos.write(bytes);
    	fos.close();
    }
    
    /**
     * Creates a byte array out of a string representation of a bits.
     * @param binaryString String containing only 0 and 1
     * @return A byte array representing the string. Always ends with a "1" followed by n "0".
     * @throws Exception if the binary String is not properly formated
     */
    static byte[] binaryStringToByteArray(String binaryString) throws Exception {
        assert binaryString.matches("^[01]*$");
        binaryString += "1";
        //make sure string length is a multiple of 8 by appending 10* at the end
        //every string must end with 1 and then x*0 (empty string becomes 10000000)
        if (binaryString.length() % 8 != 0) {
            for (int i = 8 - binaryString.length() % 8; i > 0; i--)
                binaryString += "0";
        }
        byte[] bytes = new byte[binaryString.length()/8];
        String substring;
        byte b;
        //process bitstring in chunks of length 8 (length of a byte)
        for (int i = 0; i < bytes.length; i++) {
            //get next 8 chars
            substring = binaryString.substring(8*i, 8*i + 8);
            b = 0; //create "empty" byte
            for (int j = 0; j < 8; j++) {
                b |= (substring.charAt(j) == '0') ? 0 : 1; //add 1 to the byte whenever the string contains 1 as well
                if (j < 7 ) b <<= 1; //shift byte to left
            }
            bytes[i] = b;
        }
        return bytes;
        
    }
    
    /**
     * The reverse function of binaryStringToByteArray()
     * @param bytes byte array. Last bits must be "1" followed by n "0".
     * @return String representation of given byte array containing only "1" and "0" (without the last "1" followed by n "0"!)
     */
    static String byteArrayToBinaryString(byte[] bytes) {
        assert bytes.length > 0;
        assert bytes[bytes.length - 1] != 0;
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
        	//process each bit in the current byte
            for(int i = 0; i < 8; i++) {
            	//add "1" to the result string if the MSB is 1
                sb.append((b & 0b10000000) == 0b10000000 ? "1" : "0"); //always check leftmost position in byte (MSB)
                b <<= 1; //then shift byte to the left
            }
        }
        sb.delete(sb.lastIndexOf("1"), sb.length()); //cut the last chunk ("1" followed by n "0")
        return sb.toString();
    }
}
