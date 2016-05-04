package mada_huffman;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
//must have at least 2 symbols
public class HuffmanTree {
    
    private Node root;
    private Set<Node> nodes = new HashSet<Node>();
    
    public Node getRoot() { return root; }
    public Set<Node> getNodes() { return nodes; }
    
    public HuffmanTree(int[] frequencies) {
    	buildTree(frequencies);
    }
    
    private void buildTree(int[] frequencies) {
    	PriorityQueue<Pair> queue = new PriorityQueue<Pair>((a, b) -> a.count - b.count);
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) queue.add(new Pair(String.valueOf((char)i), frequencies[i]));
        }
        
        Node parent = null;
        while (queue.size() > 1) {
            Pair elem1 = queue.poll();
            Pair elem2 = queue.poll();
            parent = new Node(null , elem1.symbol + elem2.symbol);
            
            if (!nodeExists(elem1.symbol)) {
            	parent.child1 = new Node(parent, elem1.symbol);
            	nodes.add(parent.child1);
            }
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
            nodes.add(parent);
            queue.add(new Pair(parent.symbol, elem1.count + elem2.count));
        }
        root = parent;
    }
	
	private Node getNode(String symbol) {
		try {
			return nodes.stream().filter(n -> n.symbol.equals(symbol)).collect(Collectors.toList()).get(0);
		} catch (Exception e) { return null; }
	}
	
	private boolean nodeExists(String symbol) {
		return getNode(symbol) != null;
	}
	
	public Set<Node> getLeaves() {
		return nodes.stream().filter(n -> n.child1 == null && n.child2 == null).collect(Collectors.toSet());
	}
	
    static class Node {
    	
        String symbol;
        Node parent;
        Node child1;
        Node child2;
        
        public Node(Node parent, String symbol) { 
        	this.parent = parent; 
        	this.symbol = symbol; 
        }
        
        public String getCode() {
    		if (parent == null) return "";
    		return parent.getCode() + (parent.child1 == this ? "0" : "1");
        }
        
        @Override
        public String toString() {
        	if (child1 == null) return symbol + "(_,_)";
        	return symbol + "(" + child1.symbol + "," + child2.symbol +")";
        }
    }
    
    static class Pair {

    	String symbol;
    	int count;
    	
    	public Pair(String symbol, int count) {
    		this.symbol = symbol;
    		this.count = count;
		}
		
		@Override
		public String toString() {
			return symbol + ": " + count;
		}
    }
}


