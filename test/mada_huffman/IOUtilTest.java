package mada_huffman;
import static org.junit.Assert.*;
import static mada_huffman.IOUtil.*;

import org.junit.Test;

/**
 * Some Tests for the complicated methods (mainly because TDD)
 * Make sure to enable Assertions in the JVM! (parameter "-ea" must be set)
 */
public class IOUtilTest {

	@Test
	public void testByteArrayToBinaryString() {
	    try {
	        byteArrayToBinaryString(new byte[] {});
            fail();
        }
        catch (AssertionError a) {  }
        try {
            byteArrayToBinaryString(new byte[] {0});
            fail();
        }
        catch (AssertionError a) {  }
        
		assertEquals("0000000", byteArrayToBinaryString(new byte[] {1}));
		assertEquals("000000", byteArrayToBinaryString(new byte[] {2}));
		assertEquals("000000010000000", byteArrayToBinaryString(new byte[] {1, 1}));
		assertEquals("0000010001111111000001", byteArrayToBinaryString(new byte[] {4, 127 ,6}));
	}
	
	@Test
	public void testBinaryStringToByteArray() throws Exception {
		try {
			binaryStringToByteArray("01");
			fail();
		}
		catch (AssertionError a) {  }
		try {
			binaryStringToByteArray("0101010a");
			fail();
		}
		catch (AssertionError a) {  }
		
		assertArrayEquals(new byte[] {(byte) 0b10000000 }, binaryStringToByteArray(""));
		assertArrayEquals(new byte[] {(byte) 0b11110010 }, binaryStringToByteArray("111100"));
		assertArrayEquals(new byte[] {(byte) 0b11110000, (byte) 0b11110001}, binaryStringToByteArray("111100001111000"));
		assertArrayEquals(new byte[] {(byte) 0b11111111, (byte) 0b10000001, (byte) 0b10101010, (byte) 0b10000000 }, binaryStringToByteArray("111111111000000110101010"));
		
		assertEquals("1010101", byteArrayToBinaryString(binaryStringToByteArray("1010101")));
	}

}
