package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class SimpleAssembler {

    interface Command {
        int execute(Map<String, Integer> variables, Command[] commands, int index);
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
            case "mov" -> (vars, commands, i) -> {
                vars.put(lexems[1], getOrParse(vars, lexems[2]));
                return ++i;
            };
            case "inc" -> (vars, commands, i) -> {
                vars.merge(lexems[1], 1, Integer::sum);
                return ++i;
            };
            case "dec" -> (vars, commands, i) -> {
                vars.merge(lexems[1], -1, Integer::sum);
                return ++i;
            };
            case "jnz" -> (vars, commands, i) -> {
                int target = i + Integer.parseInt(lexems[2]);
                if(target < i) {
                    while(operandIsNotZero(vars, lexems[1]))
                        executeAll(vars, commands, target, i);
                    return ++i;
                }
                else {
                    return operandIsNotZero(vars, lexems[1]) ? target : ++i;
                }
            };
            default -> throw new IllegalStateException("Unexpected statement: " + statement);
        };
    }

    private static int getOrParse(Map<String, Integer> variables, String lexem) {
        Integer value = variables.get(lexem);
        return (value != null) ? value : Integer.parseInt(lexem);
    }

    private static boolean operandIsNotZero(Map<String, Integer> variables, String lexem) {
        return getOrParse(variables, lexem) != 0;
    }

    private static void executeAll(Map<String, Integer> variables, Command[] commands, int index, int bound) {
        for( ; index < bound; index = commands[index].execute(variables, commands, index));
    }
}
