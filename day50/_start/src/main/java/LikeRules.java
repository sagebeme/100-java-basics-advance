import java.util.Locale;
import java.util.Set;

public class LikeRules {

    // Like a post only when it's from someone you follow, mentions one of your interests, isn't yours,
    // and isn't already liked. A bot that likes everything isn't being social, it's being noise.
    public static boolean shouldLike(Post post, String me, Set<String> following, Set<String> interests) {
        // TODO
        return false;
    }
}
