package mada_huffman;

import java.util.HashMap;

import mada_huffman.HuffmanTree.Node;

public class CodeTable extends HashMap<String, String> {
    
    private static final long serialVersionUID = -1337184040582587871L;

    public CodeTable(HuffmanTree huffmanTree) {
        for (Node leaf : huffmanTree.getLeaves()) {
           put(leaf.symbol, leaf.getCode());
        }
    }
    
    @Override
    public String toString() {
        String res = "";
        for (Entry<String, String> entry : entrySet()) {
            res += (int) entry.getKey().charAt(0) + ":" + entry.getValue() + "-";
        }
        if (res.length()>0) res = res.substring(0,res.length() - 2);
        return res;
    }
}
