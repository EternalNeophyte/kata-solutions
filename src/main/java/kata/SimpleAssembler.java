package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SimpleAssembler {


    public static Map<String, Integer> interpret(String[] program){
        Map<String, Integer> variables = new HashMap<>();
        List<Command> commands = Arrays.stream(program)
                .map(SimpleAssembler::toCommand)
                .toList();
        commands.forEach(command -> command.execute(variables, commands, commands.indexOf(command)));
        return variables;
    }

    private static Command toCommand(String statement) {
        String[] lexems = statement.split(" ");
        return switch(lexems[0]) {
            case "mov" -> (vars, commands, i) -> {
                int value = retrieveValue(vars, lexems[2]);
                vars.put(lexems[1], value);
                return value;
            };
            case "jnz" -> (vars, commands, i) -> {
                int target = i + Integer.parseInt(lexems[2]), result = retrieveValue(vars, lexems[1]);
                var command = commands.get(target);
                while(vars.get(lexems[1]) != 0)
                    //ToDo all commands in range target-this
                    result = command.execute(vars, commands, i);
                return result;
            };
            case "inc" -> (vars, commands, i) -> vars.merge(lexems[1], 1, Integer::sum);
            case "dec" -> (vars, commands, i) -> vars.merge(lexems[1], -1, Integer::sum);
            default -> throw new IllegalStateException("Unexpected statement: " + statement);
        };
    }

    private static int retrieveValue(Map<String, Integer> variables, String lexem) {
        return lexem.matches("-?\\d+") ? Integer.parseInt(lexem) : variables.get(lexem);
    }

    interface Command {
        int execute(Map<String, Integer> variables, List<Command> allCommands, int index);
    }

}
