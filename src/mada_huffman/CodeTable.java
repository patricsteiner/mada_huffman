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
    
    public CodeTable(String codeTable) {
        String symbol, code;
        for (String entry : codeTable.split("-")) {
            int ascii = Integer.valueOf(entry.substring(0, entry.indexOf(":")));
            symbol = Character.toString((char)ascii);
            code = entry.substring(entry.indexOf(":") + 1);
            put(symbol, code);
        }
    }
    
    public String getSymbolByCode(String code) {
        for (Entry<String, String> entry : entrySet()) {
            if (code.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String res = "";
        for (Entry<String, String> entry : entrySet()) {
            res += (int) entry.getKey().charAt(0) + ":" + entry.getValue() + "-";
        }
        if (res.length() > 0) res = res.substring(0, res.length() - 1);
        return res;
    }
}
