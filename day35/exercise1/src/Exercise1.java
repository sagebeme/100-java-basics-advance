public class Exercise1 {

    /**
     * Reads an environment variable, falling back to defaultValue when it is not set.
     */
    public static String getEnvOrDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return value != null ? value : defaultValue;
    }

    /**
     * Reads an int environment variable, falling back to defaultValue when it is missing or
     * not a valid number.
     */
    public static int getEnvIntOrDefault(String name, int defaultValue) {
        String value = System.getenv(name);
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static void main(String[] args) {
        System.out.println("PORT: " + getEnvIntOrDefault("PORT", 8080));
        System.out.println("APP_NAME: " + getEnvOrDefault("APP_NAME", "MyApp"));
    }
}
