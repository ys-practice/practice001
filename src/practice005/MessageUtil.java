package practice005;

import java.text.MessageFormat;

public class MessageUtil {

    public static String getMessage(String msg, Object... args) {
        if (msg == null) {
            return null;
        }
        if (args.length == 0) {
            return msg;
        }
        return MessageFormat.format(msg, args);
    }

}
