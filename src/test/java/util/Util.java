package util;

public class Util {

    public static String makeUnique(String name) {
        int min = 1000;
        int max = 9999;
        String suffix = "_" + (int)(Math.random() * (max - min));
        return name + suffix;
    }
}
