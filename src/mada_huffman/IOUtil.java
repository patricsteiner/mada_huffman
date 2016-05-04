package mada_huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    
    static byte[] binaryStringToByteArray(String binaryString) throws Exception {
        assert binaryString.matches("^[01]*$");
        binaryString += "1";
        //make sure string length is a multiple of 8 by appending 10* at the end
        //every string must end with 1 and then x*0 (empty string becomes = 10000000)
        if (binaryString.length() % 8 != 0) {
            for (int i = 8 - binaryString.length() % 8; i > 0; i--)
                binaryString += "0";
        }
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
        assert bytes.length > 0;
        assert bytes[bytes.length - 1] != 0;
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            for(int i = 0; i < 8; i++) {
                sb.append((b & 0b10000000) == 0b10000000 ? "1" : "0");
                b <<= 1;
            }
        }
        sb.delete(sb.lastIndexOf("1"), sb.length());
        return sb.toString();
    }
}
