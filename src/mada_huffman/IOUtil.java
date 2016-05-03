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
}
