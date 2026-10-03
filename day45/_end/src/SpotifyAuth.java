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
        Map<String, String> params = new HashMap<>();
        if (rawQuery != null) {
            for (String pair : rawQuery.split("&")) {
                String[] kv = pair.split("=", 2);
                params.put(URLDecoder.decode(kv[0], StandardCharsets.UTF_8),
                        kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "");
            }
        }
        if (params.containsKey("error")) {
            throw new IllegalStateException("Login refused: " + params.get("error"));
        }
        if (!expectedState.equals(params.get("state"))) {
            throw new IllegalStateException("The state doesn't match. Ignoring this callback.");
        }
        String code = params.get("code");
        if (code == null || code.isBlank()) throw new IllegalStateException("No code in the callback");
        return code;
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
