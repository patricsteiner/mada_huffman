package mada_huffman;

public class Program {

    public static void main(String[] args) {
        
        HuffmanTree tree = new HuffmanTree(Huffman.getCharacterFrequency("asdfasdfaaaaaadddddddd"));
        System.out.println(Huffman.createCodeMap(tree));
        
        
    }

}
