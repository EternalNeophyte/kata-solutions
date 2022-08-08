package kata;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://www.codewars.com/kata/526156943dfe7ce06200063e/java">Kata details</a>
 */
public final class BrainLuck {

    private static final int MEMORY_SIZE = 4000;

    private final String code;
    private final byte[] memory;
    private int pointer;

    public BrainLuck(String code) {
        this.code = code;
        this.memory = new byte[MEMORY_SIZE];
        this.pointer = 0;
    }

    public String process(String input) {
        char[] inputArr = input.toCharArray();
        return "";
    }

    private void operate(char symbol) {
        switch (symbol) {
            case '>' -> pointer++;
            case '<' -> pointer--;
            case '+' -> memory[pointer]++;
            case '-' -> memory[pointer]--;
            case ',' -> memory[pointer] = (byte) symbol;
        }
    }

    public static List<Loop> buildLoops(char[] input) {
        int balance = 0, highest = 0, startPos = 0;
        var loops = new LinkedList<Loop>();
        for(int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case '[' -> {
                    if(balance == 0) startPos = i;
                    balance++;
                    if(highest < balance) highest = balance;
                }
                case ']' -> {
                    balance--;
                    char[] body = Arrays.copyOfRange(input, startPos + 1, i);
                    if(balance == 0) {
                        var loop = new Loop(startPos, body, new LinkedList<>());
                        loops.add(loop);
                        //ToDo replace inner loops in body with marks (int) char
                        if(highest > 1) {
                            var nested = buildLoops(body);
                            loop.nestedLoops.addAll(nested);
                            highest = 0;
                        }
                    }
                }
            }
        }
        return loops;
    }

    static record Loop(int startPos, char[] body, List<Loop> nestedLoops) {

        void run(BrainLuck context) {
            while (context.memory[startPos] != 0) {
                for(char ch : body) context.operate(ch);
            }
        }
    }
}
