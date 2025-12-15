# Day 45 - Create Spotify Playlist API Integration

## 📚 Learning Objectives
- Work with Spotify Web API
- Authenticate with OAuth
- Create and manage playlists
- Search for songs
- Build music automation tools

## 🎯 Topics Covered
- Spotify Web API
- OAuth 2.0 authentication
- API requests and responses
- Playlist management
- Song search
- API rate limiting

## 📝 Step-by-Step Instructions

### Step 1: Spotify API Setup
Register application:

1. Go to [Spotify Developer Dashboard](https://developer.spotify.com/dashboard)
2. Create app
3. Get Client ID and Client Secret
4. Set redirect URI

### Step 2: OAuth Authentication
Authenticate with Spotify:

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SpotifyAuth {
    private String clientId;
    private String clientSecret;
    
    public String getAccessToken() {
        String credentials = clientId + ":" + clientSecret;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://accounts.spotify.com/api/token"))
            .header("Authorization", "Basic " + encoded)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
            .build();
        
        // Parse response and return access token
    }
}
```

### Step 3: Search Songs
Search for tracks:

```java
public class SpotifyAPI {
    private String accessToken;
    
    public List<Track> searchTracks(String query) {
        String url = "https://api.spotify.com/v1/search?q=" + 
                     query.replace(" ", "%20") + 
                     "&type=track&limit=10";
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer " + accessToken)
            .GET()
            .build();
        
        // Parse JSON response
        // Return list of tracks
    }
}
```

### Step 4: Create Playlist
Create and add songs:

```java
public void createPlaylist(String name, List<String> trackUris) {
    // Create playlist
    String playlistId = createPlaylistRequest(name);
    
    // Add tracks
    addTracksToPlaylist(playlistId, trackUris);
}
```

## 💻 Exercises

### Exercise 1: API Authentication
Practice with:
- OAuth flow
- Token management
- Token refresh
- Error handling

### Exercise 2: API Calls
Make requests for:
- Search songs
- Get user playlists
- Create playlists
- Add tracks

### Exercise 3: Playlist Management
Build features:
- Create playlists
- Add/remove songs
- Update playlist details
- Get playlist info

## 🎮 Project: Music Time Machine

### Requirements
Create a playlist generator:
1. Input a date (e.g., "2000-01-01")
2. Search for top songs from that time
3. Create Spotify playlist
4. Add songs to playlist
5. Share playlist link

### Example Output
```
Music Time Machine

Enter a date (YYYY-MM-DD): 2000-01-01

Searching for top songs from 2000...
Found 100 songs

Creating playlist: "Top Songs from 2000"...
Adding songs...
✓ Playlist created successfully!

Playlist URL: https://open.spotify.com/playlist/abc123
```

### Starter Code
```java
public class MusicTimeMachine {
    private SpotifyAPI spotify;
    
    public void createTimeMachinePlaylist(String date) {
        // Search for songs from that time
        // Create playlist
        // Add songs
    }
}
```

## 📚 Resources

### Official Documentation
- [Spotify Web API](https://developer.spotify.com/documentation/web-api/)
- [Spotify API Reference](https://developer.spotify.com/documentation/web-api/reference/)
- [OAuth 2.0](https://oauth.net/2/)

### Tutorials
- [Spotify API Tutorial](https://developer.spotify.com/documentation/web-api/quick-start/)
- [OAuth in Java](https://www.baeldung.com/java-oauth)

### Video Resources
- [Spotify API Tutorial](https://www.youtube.com/results?search_query=spotify+api+java)
- [OAuth Java](https://www.youtube.com/results?search_query=oauth+java+tutorial)

### Practice Platforms
- [Spotify Developer](https://developer.spotify.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| OAuth 2.0 | Authentication | Client credentials flow |
| Access Token | API authentication | Bearer token |
| API Endpoints | Service URLs | `/v1/search`, `/v1/playlists` |
| Rate Limiting | Request limits | Handle 429 responses |
| JSON Parsing | Extract data | Parse API responses |

## ✅ Checklist
- [ ] Understand OAuth
- [ ] Can authenticate with Spotify
- [ ] Can search songs
- [ ] Can create playlists
- [ ] Can add tracks
- [ ] Completed Music Time Machine
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 45, you should be able to:
- Work with OAuth APIs
- Integrate music services
- Build automation tools
- Create playlist generators

**Ready for Day 46?** You'll build an Automated Price Tracker!



