package mada_huffman;

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanTree {
    
    Node root;
    
    class Node {
        String symbol;
        Node parent;
        Node child1;
        Node child2;
        public Node(Node parent, String symbol) { this.parent = parent; this.symbol = symbol; }
    }
   
    
    public HuffmanTree(TreeMap<String, Integer> frequencies) {
        buildTree(frequencies);
     
    }
    
    private void buildTree(TreeMap<String, Integer> frequencies) {  
        if (frequencies.size() > 1) {
            Entry<String, Integer> entry1 = frequencies.pollFirstEntry();
            Entry<String, Integer> entry2 = frequencies.pollFirstEntry();
            Node prevRoot = root;
            root = new Node(null , entry1.getKey() + entry2.getKey());
            if (root.symbol.length() > 2) {
                root.child1 = prevRoot;
            }
            root.child1 = prevRoot;//;new Node(root, entry1.getKey());
            root.child2 = new Node(root, entry2.getKey());
            frequencies.put(root.symbol, entry1.getValue() + entry2.getValue());
            
        
        }
    }
}


