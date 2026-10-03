import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class LaundryForecast {

    static final String BASE = "https://api.open-meteo.com/v1/forecast";
    static final String TIME_ZONE = "Africa/Nairobi";

    public static URI forecastUri(double lat, double lon) {
        // TODO: latitude, longitude, hourly=precipitation_probability, an encoded timezone, forecast_days=1
        return URI.create(BASE);
    }

    public static List<HourlyRain> parse(String json) {
        List<HourlyRain> hours = new ArrayList<>();
        // TODO: read hourly.time and hourly.precipitation_probability side by side
        return hours;
    }

    // The worst hour from fromHour up to (not including) toHour, or null if none are in range.
    public static HourlyRain worstHour(List<HourlyRain> hours, int fromHour, int toHour) {
        // TODO
        return null;
    }

    public static String verdict(List<HourlyRain> hours, int fromHour, int toHour) {
        // TODO: under 30% "Hang it out.", under 60% "Risky: hang it near the door.", else "Keep it inside."
        return "";
    }

    public static void main(String[] args) throws InterruptedException {
        double lat = args.length >= 2 ? Double.parseDouble(args[0]) : -1.2864;
        double lon = args.length >= 2 ? Double.parseDouble(args[1]) : 36.8172;

        // TODO: fetch, parse and print the verdict for 8am to 5pm
        // TODO: catch IOException and ApiException and print a friendly message
    }
}
