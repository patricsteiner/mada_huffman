package mada_huffman;

import static org.junit.Assert.*;

import org.junit.Test;

public class HuffmanTest {

	@Test
	public void testByteArrayToBinaryString() {
		assertEquals("", Huffman.byteArrayToBinaryString(new byte[] {}));
		assertEquals("0", Huffman.byteArrayToBinaryString(new byte[] {0}));
		assertEquals("1", Huffman.byteArrayToBinaryString(new byte[] {1}));
		assertEquals("10", Huffman.byteArrayToBinaryString(new byte[] {2}));
		assertEquals("10", Huffman.byteArrayToBinaryString(new byte[] {1, 0}));
		assertEquals("1011111111100", Huffman.byteArrayToBinaryString(new byte[] {2,1,127,4}));
		assertEquals("10111111110001", Huffman.byteArrayToBinaryString(new byte[] {0b101, 0b111, 0b1111000, 0b1}));
	}
	
	@Test
	public void testBinaryStringToByteArray() throws Exception {
		try {
			Huffman.binaryStringToByteArray("01");
			fail();
		}
		catch (AssertionError a) {  }
		try {
			Huffman.binaryStringToByteArray("0101010a");
			fail();
		}
		catch (AssertionError a) {  }
		
		assertArrayEquals(new byte[] {}, Huffman.binaryStringToByteArray(""));
		assertArrayEquals(new byte[] {(byte) 0b11110000}, Huffman.binaryStringToByteArray("11110000"));
		assertArrayEquals(new byte[] {(byte) 0b11110000, (byte) 0b11110000}, Huffman.binaryStringToByteArray("1111000011110000"));
		assertArrayEquals(new byte[] {(byte) 0b11111111, (byte) 0b10000001, (byte) 0b10101010}, Huffman.binaryStringToByteArray("111111111000000110101010"));
		byte[] b = Huffman.binaryStringToByteArray("10101010");
		System.out.println(b.length);
		System.out.println(Huffman.byteArrayToBinaryString(b));
		assertEquals("10101010", Huffman.byteArrayToBinaryString(Huffman.binaryStringToByteArray("10101010")));
	}

}
