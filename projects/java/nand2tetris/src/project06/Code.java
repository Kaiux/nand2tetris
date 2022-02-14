package project06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yulikai@corp.netease.com
 * @date 2022/2/9 3:27 下午
 */
public class Code {

    private static final Map<String, String> COMP_CODE_MAP = new HashMap<>();

    static {
        COMP_CODE_MAP.put("0", "0101010");
        COMP_CODE_MAP.put("1", "0111111");
        COMP_CODE_MAP.put("-1", "0111010");
        COMP_CODE_MAP.put("D", "0001100");
        COMP_CODE_MAP.put("A", "0110000");
        COMP_CODE_MAP.put("!D", "0001101");
        COMP_CODE_MAP.put("!A", "0110001");
        COMP_CODE_MAP.put("-D", "0001111");
        COMP_CODE_MAP.put("-A", "0110011");
        COMP_CODE_MAP.put("D+1", "0011111");
        COMP_CODE_MAP.put("A+1", "0110111");
        COMP_CODE_MAP.put("D-1", "0001110");
        COMP_CODE_MAP.put("A-1", "0110010");
        COMP_CODE_MAP.put("D+A", "0000010");
        COMP_CODE_MAP.put("D-A", "0010011");
        COMP_CODE_MAP.put("A-D", "0000111");
        COMP_CODE_MAP.put("D&A", "0000000");
        COMP_CODE_MAP.put("D|A", "0010101");
        COMP_CODE_MAP.put("M", "1110000");
        COMP_CODE_MAP.put("!M", "1110001");
        COMP_CODE_MAP.put("-M", "1110011");
        COMP_CODE_MAP.put("M+1", "1110111");
        COMP_CODE_MAP.put("M-1", "1110010");
        COMP_CODE_MAP.put("D+M", "1000010");
        COMP_CODE_MAP.put("D-M", "1010011");
        COMP_CODE_MAP.put("M-D", "1000111");
        COMP_CODE_MAP.put("D&M", "1000000");
        COMP_CODE_MAP.put("D|M", "1010101");
    }

    public String dest(String mnemonic) {
        int[] dest = new int[3];
        if (null != mnemonic) {
            for (char c : mnemonic.toCharArray()) {
                switch (c) {
                    case 'A':
                        dest[0] = 1;
                        break;
                    case 'D':
                        dest[1] = 1;
                        break;
                    case 'M':
                        dest[2] = 1;
                        break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(dest[i]);
        }
        return stringBuilder.toString();
    }

    public String comp(String mnemonic) {
        return COMP_CODE_MAP.get(mnemonic);
    }

    public String jump(String mnemonic) {
        if (null == mnemonic) {
            return "000";
        }
        switch (mnemonic) {
            case "JGT":
                return "001";
            case "JEQ":
                return "010";
            case "JGE":
                return "011";
            case "JLT":
                return "100";
            case "JNE":
                return "101";
            case "JLE":
                return "110";
            case "JMP":
                return "111";
            default:
                return "000";
        }
    }
}