package space.devport.wertik.conditionaltext.system.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import space.devport.wertik.conditionaltext.ConditionalTextPlugin;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Log
@UtilityClass
public class ParseUtil {

    @NotNull
    public Object parseObject(@NotNull String input) {

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ignored) {
            // Not an integer
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ignored) {
            // Not a double
        }
        try {
            return LocalTime.parse(input, ConditionalTextPlugin.getInstance().getTimeFormatter());
        } catch (DateTimeParseException ignored) {
            // Not a date
        }

        return input;
    }
}