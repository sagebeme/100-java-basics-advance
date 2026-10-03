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
        String query = String.format(Locale.ROOT, "latitude=%.4f&longitude=%.4f", lat, lon)
                + "&hourly=precipitation_probability"
                + "&timezone=" + URLEncoder.encode(TIME_ZONE, StandardCharsets.UTF_8)
                + "&forecast_days=1";
        return URI.create(BASE + "?" + query);
    }

    public static List<HourlyRain> parse(String json) {
        JSONObject hourly = new JSONObject(json).getJSONObject("hourly");
        JSONArray times = hourly.getJSONArray("time");
        JSONArray chances = hourly.getJSONArray("precipitation_probability");
        List<HourlyRain> hours = new ArrayList<>();
        for (int i = 0; i < times.length(); i++) {
            int chance = chances.isNull(i) ? 0 : chances.getInt(i); // the API sends null for hours it can't forecast
            hours.add(new HourlyRain(LocalDateTime.parse(times.getString(i)), chance));
        }
        return hours;
    }

    // The worst hour from fromHour up to (not including) toHour, or null if none are in range.
    public static HourlyRain worstHour(List<HourlyRain> hours, int fromHour, int toHour) {
        HourlyRain worst = null;
        for (HourlyRain h : hours) {
            int hour = h.time().getHour();
            if (hour >= fromHour && hour < toHour && (worst == null || h.chance() > worst.chance())) {
                worst = h;
            }
        }
        return worst;
    }

    public static String verdict(List<HourlyRain> hours, int fromHour, int toHour) {
        HourlyRain worst = worstHour(hours, fromHour, toHour);
        if (worst == null) return "No forecast for those hours.";
        if (worst.chance() < 30) return "Hang it out.";
        if (worst.chance() < 60) return "Risky: hang it near the door.";
        return "Keep it inside.";
    }

    public static void main(String[] args) throws InterruptedException {
        double lat = args.length >= 2 ? Double.parseDouble(args[0]) : -1.2864;
        double lon = args.length >= 2 ? Double.parseDouble(args[1]) : 36.8172;

        try {
            List<HourlyRain> hours = parse(new WeatherClient().fetch(forecastUri(lat, lon)));
            HourlyRain worst = worstHour(hours, 8, 17);
            System.out.printf(Locale.ROOT, "Laundry forecast for %.2f, %.2f (%s), 8am to 5pm%n", lat, lon, TIME_ZONE);
            if (worst != null) {
                System.out.printf("Highest chance of rain: %d%% at %02d:00%n", worst.chance(), worst.time().getHour());
            }
            System.out.println(verdict(hours, 8, 17));
        } catch (IOException e) {
            System.out.println("Couldn't reach the weather service (" + e.getMessage() + "). Check your connection and try again.");
        } catch (ApiException e) {
            System.out.println("The weather service had a problem: " + e.getMessage());
        }
    }
}
