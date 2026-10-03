import java.nio.file.Path;

public class SmsAlerts {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java SmsAlerts <phone> <message>");
            return;
        }
        try {
            AppConfig config = AppConfig.load(System.getenv(), AppConfig.readFile(Path.of("config.properties")))
                    .require("sms.username", "sms.apiKey");
            System.out.println("Config: " + config.describe());

            SmsGateway gateway = new ConsoleSmsGateway(); // swap in a real provider's gateway here
            gateway.send(Phone.normalise(args[0]), args[1]);
        } catch (ConfigException | IllegalArgumentException e) {
            System.out.println("Can't send: " + e.getMessage());
        }
    }
}
