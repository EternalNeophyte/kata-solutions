package kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SimpleAssembler {


    public static Map<String, Integer> interpret(String[] program){
        Map<String, Integer> vars = new HashMap<>();
        List<Command> commands = Arrays.stream(program)
                .map(SimpleAssembler::toCommand)
                .toList();
        for(int i = 0; i < commands.size(); i++)
            commands.get(i).execute(vars, commands, i);
        return vars;
    }

    private static Command toCommand(String statement) {
        String[] lexems = statement.split(" ");
        return switch(lexems[0]) {
            case "mov" -> (vars, commands, i) -> {
                int value = lexems[2].matches("-?\\d+") ? Integer.parseInt(lexems[2]) : vars.get(lexems[2]);
                vars.put(lexems[1], value);
            };
            case "jnz" -> (vars, commands, i) -> {
                int target = i + Integer.parseInt(lexems[2]);
                var loop = (target < i)
                        ? commands.subList(target, i)
                        : commands.subList(i, target);
                while(vars.get(lexems[1]) != 0)
                    loop.forEach(command -> command.execute(vars, commands, i));
            };
            case "inc" -> (vars, commands, i) -> vars.merge(lexems[1], 1, Integer::sum);
            case "dec" -> (vars, commands, i) -> vars.merge(lexems[1], -1, Integer::sum);
            default -> throw new IllegalStateException("Unexpected statement: " + statement);
        };
    }

    interface Command {
        void execute(Map<String, Integer> variables, List<Command> allCommands, int index);
    }

}
