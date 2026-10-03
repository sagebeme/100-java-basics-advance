import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

// The pieces of Spotify's "authorization code with PKCE" login that don't need the network.
public class SpotifyAuth {

    static final String AUTHORIZE = "https://accounts.spotify.com/authorize";
    static final String TOKEN = "https://accounts.spotify.com/api/token";

    private static String enc(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    // Step 1: send the user's browser here. They log in on Spotify's own page, never in your program.
    public static URI authorizeUrl(String clientId, String redirectUri, String scope, String state, String challenge) {
        return URI.create(AUTHORIZE + "?response_type=code"
                + "&client_id=" + enc(clientId)
                + "&scope=" + enc(scope)
                + "&redirect_uri=" + enc(redirectUri)
                + "&state=" + enc(state)
                + "&code_challenge_method=S256"
                + "&code_challenge=" + enc(challenge));
    }

    // Step 2: Spotify sends the browser back to redirectUri?code=...&state=... . Check the state is the one
    // we sent (so nobody can slip us their own code), then return the code.
    public static String codeFromCallback(String rawQuery, String expectedState) {
        // TODO: decode the query; throw IllegalStateException for an "error", a wrong state or no code
        return "";
    }

    // Step 3: swap the code (plus the verifier only we know) for an access token, by POSTing this form.
    public static String tokenRequestBody(String clientId, String code, String redirectUri, String verifier) {
        return "grant_type=authorization_code"
                + "&code=" + enc(code)
                + "&redirect_uri=" + enc(redirectUri)
                + "&client_id=" + enc(clientId)
                + "&code_verifier=" + enc(verifier);
    }
}
