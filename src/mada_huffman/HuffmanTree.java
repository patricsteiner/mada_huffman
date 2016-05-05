package mada_huffman;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A Huffman tree can be built out of a character frequency array.
 * It's used to "compute" the huffman code for each character (=leaf)
 */
public class HuffmanTree {
    
    private Node root;
    private Set<Node> nodes = new HashSet<Node>();
    
    public Node getRoot() { return root; }
    public Set<Node> getNodes() { return nodes; }
    
    public HuffmanTree(int[] frequencies) {
    	buildTree(frequencies);
    }
    
    /**
     * Build a Huffman tree out of given character frequencies.
     * There must be at least 2 different characters.
     * @param frequencies
     */
    private void buildTree(int[] frequencies) {
    	//Using a PriorityQueue for the symbol-count-pairs ordered by frequency
    	//because we want to process the symbols with the lowest frequency first
    	PriorityQueue<Pair> queue = new PriorityQueue<Pair>((a, b) -> a.count - b.count);
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) queue.add(new Pair(String.valueOf((char)i), frequencies[i]));
        }
        
        Node parent = null;
        while (queue.size() > 1) { //as long as there are 2 or more nodes to be processed
            Pair elem1 = queue.poll();
            Pair elem2 = queue.poll();
            parent = new Node(null , elem1.symbol + elem2.symbol);
            //create new node by merging the two nodes with the lowest character frequency
            
            //if there is no node with the given symbol: create new node
            if (!nodeExists(elem1.symbol)) {
            	parent.child1 = new Node(parent, elem1.symbol);
            	nodes.add(parent.child1);
            }
            //otherwise connect the two nodes
            else {
                parent.child1 = getNode(elem1.symbol);
                parent.child1.parent = parent;
            }
            
            if (!nodeExists(elem2.symbol)) {
            	parent.child2 = new Node(parent, elem2.symbol);
            	nodes.add(parent.child2);
            }
            else {
                parent.child2 = getNode(elem2.symbol);
                parent.child2.parent = parent;	
            }
            nodes.add(parent); //add the new node to "global node list"
            //combine the previous elements to one and add it to the queue
            queue.add(new Pair(parent.symbol, elem1.count + elem2.count)); 
        }
        root = parent;
    }
	
    /**
     * Find a node by its symbol
     * @param symbol
     * @return Node or null if not found
     */
	private Node getNode(String symbol) {
		try {
			return nodes.stream().filter(n -> n.symbol.equals(symbol)).collect(Collectors.toList()).get(0);
		} catch (Exception e) { return null; }
	}
	
	private boolean nodeExists(String symbol) {
		return getNode(symbol) != null;
	}
	
	/**
	 * Get every node that has no children
	 */
	public Set<Node> getLeaves() {
		return nodes.stream().filter(n -> n.child1 == null && n.child2 == null).collect(Collectors.toSet());
	}
	
	/**
	 * Represents a Node in the tree.
	 */
    static class Node {
    	
        String symbol;
        Node parent;
        Node child1;
        Node child2;
        
        public Node(Node parent, String symbol) { 
        	this.parent = parent; 
        	this.symbol = symbol; 
        }
        
        /**
         * Get the code of a Node which is its parent code + a "0" if its the left child or a "1" if its the right child
         */
        public String getCode() {
    		if (parent == null) return "";
    		return parent.getCode() + (parent.child1 == this ? "0" : "1");
        }
        
        // merely for debugging
        @Override
        public String toString() {
        	if (child1 == null) return symbol + "(_,_)";
        	return symbol + "(" + child1.symbol + "," + child2.symbol +")";
        }
    }
    
    /**
     * Used as entries in the PriorityQueue (more convenient than just having an array).
     */
    static class Pair {

    	String symbol;
    	int count;
    	
    	public Pair(String symbol, int count) {
    		this.symbol = symbol;
    		this.count = count;
		}
		
    	// merely for debugging
		@Override
		public String toString() {
			return symbol + ": " + count;
		}
    }
}


