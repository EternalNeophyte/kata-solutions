package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class SimpleAssembler {

    interface Command {
        void execute(Map<String, Integer> variables, Command[] commands, int index);
    }

    public static Map<String, Integer> interpret(String[] program){
        Map<String, Integer> variables = new HashMap<>();
        Command[] commands = Arrays.stream(program)
                .map(SimpleAssembler::toCommand)
                .toArray(Command[]::new);
        executeAll(variables, commands, 0, commands.length);
        return variables;
    }

    private static Command toCommand(String statement) {
        String[] lexems = statement.split(" ");
        return switch(lexems[0]) {
            case "mov" -> (vars, commands, i) -> vars.put(lexems[1], getOrParse(vars, lexems[2]));
            case "inc" -> (vars, commands, i) -> vars.merge(lexems[1], 1, Integer::sum);
            case "dec" -> (vars, commands, i) -> vars.merge(lexems[1], -1, Integer::sum);
            case "jnz" -> (vars, commands, i) -> {
                int target = i + Integer.parseInt(lexems[2]);
                while(getOrParse(vars, lexems[1]) != 0)
                    executeAll(vars, commands, min(target, i), max(target, i));
            };
            default -> throw new IllegalStateException("Unexpected statement: " + statement);
        };
    }

    private static int getOrParse(Map<String, Integer> variables, String lexem) {
        Integer value = variables.get(lexem);
        return (value != null) ? value : Integer.parseInt(lexem);
    }

    private static void executeAll(Map<String, Integer> variables, Command[] commands, int index, int bound) {
        for( ; index < bound; index++)
            commands[index].execute(variables, commands, index);
    }
}
