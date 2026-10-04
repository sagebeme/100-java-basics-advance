import java.util.regex.Pattern;

public class SpamFilter {

    private static final Pattern SPAMMY = Pattern.compile("(free|win|giveaway|promo|\\d{3,}|xx)", Pattern.CASE_INSENSITIVE);

    // Looks like a real person: has posted something, isn't an empty shell, and the name isn't an advert.
    public static boolean looksReal(Follower f) {
        return f.posts() >= 3 && f.followers() >= 20 && !SPAMMY.matcher(f.user()).find();
    }
}
