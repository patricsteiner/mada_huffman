package mada_huffman;

import java.util.PriorityQueue;

public class HuffmanTree {
    
    Node root;
    
    public HuffmanTree(int[] frequencies) {
    	buildTree(frequencies);
    }
    
    private void buildTree(int[] frequencies) {
    	PriorityQueue<Pair> queue = new PriorityQueue<Pair>((a, b) -> a.count > b.count ? 1 : -1);
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) queue.add(new Pair(String.valueOf((char)i), frequencies[i]));
        }
        
        while (queue.size() > 1) {
            Pair elem1 = queue.poll();
            Pair elem2 = queue.poll();
            Node newRoot = new Node(null , elem1.symbol + elem2.symbol);
            
            if (root == null) {
            	newRoot.child1 = new Node(newRoot, elem1.symbol);
            	newRoot.child2 = new Node(newRoot, elem2.symbol);
            }
            else {
            	newRoot.child1 = root;
            	newRoot.child2 = new Node(newRoot, root.symbol.equals(elem1.symbol) ? elem2.symbol : elem1.symbol);
            }
            queue.add(new Pair(newRoot.symbol, elem1.count + elem2.count));
            root = newRoot;
        }
    }
    
    public void print() {
    	root.print();
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
        
        @Override
        public String toString() {
        	if (child1 == null)
        		return symbol + "(_,_)";
        	return symbol + "(" + child1.symbol + "," + child2.symbol +")";
        }
        
        public void print() {
            print("");
        }

        private void print(String prefix) {
            System.out.println(prefix + "|-- " + symbol);
            if (child1 != null ) {
            	child1.print(prefix + "   ");
                child2.print(prefix + "   ");
            }
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


