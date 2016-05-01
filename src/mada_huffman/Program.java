package mada_huffman;

public class Program {

    public static void main(String[] args) {
        
        HuffmanTree tree = new HuffmanTree(Huffman.getCharacterFrequency("helloowood"));
        System.out.println(tree.createCodeMap());
        
        
    }

}
