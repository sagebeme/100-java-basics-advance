import java.io.*;
import java.util.Properties;

public class Exercise2 {

    public static void save(String path, Properties props) throws IOException {
        try (OutputStream out = new FileOutputStream(path)) {
            props.store(out, "Exercise 2 properties");
        }
    }

    public static Properties load(String path) throws IOException {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(path)) {
            props.load(in);
        }
        return props;
    }

    public static String getOrDefault(Properties props, String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.setProperty("app.name", "MyApp");
        props.setProperty("app.version", "1.0");

        save("app-demo.properties", props);
        Properties loaded = load("app-demo.properties");

        System.out.println(getOrDefault(loaded, "app.name", "Unknown"));
        System.out.println(getOrDefault(loaded, "app.author", "Unknown"));
    }
}
