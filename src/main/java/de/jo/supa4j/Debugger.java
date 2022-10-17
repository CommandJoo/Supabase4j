package de.jo.supa4j;

public class Debugger {

    private static boolean log = true;

    public static void log(boolean state) {
        log = state;
    }

    public static void debug(String text, DebugType type) {
        if(log) {
            System.out.println(type.getColor()+text+ConsoleColors.RESET);
        }
    }

    public static enum DebugType {

        INFO(ConsoleColors.BLUE), WARNING(ConsoleColors.YELLOW_BRIGHT), ERROR(ConsoleColors.RED_BOLD)
        ;

        private String color;

        private DebugType(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

}
