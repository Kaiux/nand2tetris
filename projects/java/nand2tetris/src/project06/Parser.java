package project06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yulikai@corp.netease.com
 * @date 2022/2/9 2:47 下午
 */
public class Parser implements AutoCloseable {

    private BufferedReader bufferedReader;

    private String currentCommand;

    private final String fileName;

    public Parser(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    public Boolean hasMoreCommands() {
        boolean ready = false;
        try {
            ready = bufferedReader.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ready;
    }

    public void advance() {
        try {
            currentCommand = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CommandType commandType() {
        if (currentCommand.startsWith("//")) {
            return CommandType.COMMENT;
        }
        if (currentCommand.startsWith("@")) {
            return CommandType.A_COMMAND;
        }
        if (currentCommand.startsWith("(")) {
            return CommandType.L_COMMAND;
        }
        if (currentCommand.contains("=") || currentCommand.contains(";")) {
            return CommandType.C_COMMAND;
        }
        return CommandType.NONE;
    }

    public String symbol() {
        if (null == currentCommand) {
            return null;
        }
        if (currentCommand.startsWith("@")) {
            return currentCommand.substring(1);
        }
        if (currentCommand.startsWith("(")) {
            return currentCommand.substring(1, currentCommand.length() - 1);
        }
        return null;
    }

    public String dest() {
        if (null == currentCommand) {
            return null;
        }
        if (currentCommand.contains("=")) {
            return currentCommand.substring(0, currentCommand.indexOf("="));
        }
        return null;
    }

    public String comp() {
        if (null == currentCommand) {
            return null;
        }
        if (currentCommand.contains("=")) {
            if (currentCommand.contains(";")) {
                return currentCommand.substring(currentCommand.indexOf("=") + 1, currentCommand.indexOf(";"));
            } else {
                return currentCommand.substring(currentCommand.indexOf("=") + 1);
            }
        }
        if (currentCommand.contains(";")) {
            return currentCommand.substring(0, currentCommand.indexOf(";"));
        } else {
            return currentCommand;
        }
    }

    public String jump() {
        if (null == currentCommand) {
            return null;
        }
        if (currentCommand.contains(";")) {
            return currentCommand.substring(currentCommand.indexOf(";") + 1);
        }
        return null;
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        try {
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}