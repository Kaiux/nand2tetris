package project06;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author yulikai@corp.netease.com
 * @date 2022/2/9 2:47 下午
 */
public class Assembler {

    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        String currentPath = Objects.requireNonNull(Assembler.class.getResource("")).getPath();
        if (!fileName.startsWith("/")) {
            fileName = currentPath + "/" + fileName;
        }

        String outputFilePath = fileName.replace(".asm", ".hack");
        OutputStream os = new FileOutputStream(outputFilePath);

        Parser parser = new Parser(fileName);
        Code code = new Code();
        SymbolTable symbolTable = new SymbolTable();

        int codeAddress = 0;

        while (parser.hasMoreCommands()) {
            parser.advance();
            switch (parser.commandType()) {
                case A_COMMAND:
                case C_COMMAND:
                    codeAddress++;
                    break;
                case L_COMMAND:
                    symbolTable.addEntry(parser.symbol(), codeAddress);
                    break;
            }
        }

        parser.reset();

        while (parser.hasMoreCommands()) {
            parser.advance();
            switch (parser.commandType()) {
                case A_COMMAND:
                    String symbol = parser.symbol();
                    if (symbol.matches("^[0-9]+$")) {
                        os.write((getCodeFromSymbol(symbol) + "\n").getBytes());
                    } else {
                        if (!symbolTable.contains(symbol)) {
                            symbolTable.addEntry(symbol, symbolTable.getNextAvailableAddress());
                        }
                        os.write((getCodeFromSymbol(symbolTable.getAddress(symbol)) + "\n").getBytes());
                    }
                    break;
                case C_COMMAND:
                    os.write(("111" + code.comp(parser.comp()) + code.dest(parser.dest()) + code.jump(parser.jump()) + "\n").getBytes());
                    break;
            }
        }

        parser.close();
        os.close();

    }


    public static String getCodeFromSymbol(String symbol) {
        return getCodeFromSymbol(Integer.parseInt(symbol));
    }

    public static String getCodeFromSymbol(int value) {
        int[] binary = new int[16];
        for (int i = 15; i >= 0; i--) {
            binary[i] = value % 2;
            value /= 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(binary[i]);
        }
        return sb.toString();
    }

}