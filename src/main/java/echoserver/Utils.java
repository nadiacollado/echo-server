package echoserver;

public class Utils {
    public static boolean quit(String keyword) {
        keyword = keyword.toLowerCase();
        return (keyword.equals("q") || keyword.equals("quit"));
    }

}
