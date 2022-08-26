package kata;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created on 26.08.2022
 *
 * @author alexandrov
 */
public class PnoneDir {

    public static String phone(String strng, String num) {
        Pattern namePattern = Pattern.compile("<\\.+>");
        var list = Arrays.stream(strng.split("\n"))
                .filter(s -> s.contains(num))
                .map(s -> {
                    String name = namePattern.matcher(s).group();
                    String address = s.replaceFirst(num, "").replaceFirst(name, "").trim();
                    return "Phone => %s, Name => %s, Address => %s".formatted(num, name, address);
                })
                .toList();
        return switch (list.size()) {
            case 0 -> "Error => Not found: " + num;
            case 1 -> list.get(0);
            default -> "Error => Too many people: " + num;
        };
    }
}
