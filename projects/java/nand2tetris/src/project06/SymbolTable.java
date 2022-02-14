package project06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yulikai@corp.netease.com
 * @date 2022/2/11 11:26 上午
 */
public class SymbolTable {

    private static final Map<String, Integer> PREDEFINED_SYMBOL_MAP = new HashMap<>();

    private final Map<String, Integer> symbolTable = new HashMap<>();

    private static int nextAvailableAddress = 16;

    static {
        PREDEFINED_SYMBOL_MAP.put("SP", 0);
        PREDEFINED_SYMBOL_MAP.put("LCL", 1);
        PREDEFINED_SYMBOL_MAP.put("ARG", 2);
        PREDEFINED_SYMBOL_MAP.put("THIS", 3);
        PREDEFINED_SYMBOL_MAP.put("THAT", 4);
        PREDEFINED_SYMBOL_MAP.put("SCREEN", 16384);
        PREDEFINED_SYMBOL_MAP.put("KBD", 24576);
        PREDEFINED_SYMBOL_MAP.put("R0", 0);
        PREDEFINED_SYMBOL_MAP.put("R1", 1);
        PREDEFINED_SYMBOL_MAP.put("R2", 2);
        PREDEFINED_SYMBOL_MAP.put("R3", 3);
        PREDEFINED_SYMBOL_MAP.put("R4", 4);
        PREDEFINED_SYMBOL_MAP.put("R5", 5);
        PREDEFINED_SYMBOL_MAP.put("R6", 6);
        PREDEFINED_SYMBOL_MAP.put("R7", 7);
        PREDEFINED_SYMBOL_MAP.put("R8", 8);
        PREDEFINED_SYMBOL_MAP.put("R9", 9);
        PREDEFINED_SYMBOL_MAP.put("R10", 10);
        PREDEFINED_SYMBOL_MAP.put("R11", 11);
        PREDEFINED_SYMBOL_MAP.put("R12", 12);
        PREDEFINED_SYMBOL_MAP.put("R13", 13);
        PREDEFINED_SYMBOL_MAP.put("R14", 14);
        PREDEFINED_SYMBOL_MAP.put("R15", 15);
    }

    public void addEntry(String symbol, Integer address) {
        symbolTable.put(symbol, address);
    }

    public Integer getAddress(String symbol) {
        if (PREDEFINED_SYMBOL_MAP.containsKey(symbol)) {
            return PREDEFINED_SYMBOL_MAP.get(symbol);
        }
        return symbolTable.get(symbol);
    }

    public boolean contains(String symbol) {
        return PREDEFINED_SYMBOL_MAP.containsKey(symbol) || symbolTable.containsKey(symbol);
    }

    public Integer getNextAvailableAddress() {
        //无法处理@数字冲突的问题
        return nextAvailableAddress++;
    }

}