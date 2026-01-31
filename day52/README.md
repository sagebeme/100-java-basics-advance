# Day 52 - Instagram Automation Bot

## 📚 Learning Objectives
- Understand Instagram automation
- Work with Instagram APIs
- Automate interactions
- Handle authentication
- Build social media tools

## 🎯 Topics Covered
- Instagram Basic Display API
- OAuth authentication
- Post automation
- Like and follow automation
- Rate limiting
- Best practices

## 📝 Step-by-Step Instructions

### Step 1: Instagram API Setup
Set up Instagram API:

```java
public class InstagramAPI {
    private String accessToken;
    private String clientId;
    private String clientSecret;
    
    public String getAccessToken() {
        // OAuth flow to get access token
        String url = "https://api.instagram.com/oauth/access_token";
        
        // Exchange code for token
        // Return access token
    }
}
```

### Step 2: Get User Media
Fetch user posts:

```java
public List<Media> getUserMedia(String userId) {
    String url = String.format(
        "https://graph.instagram.com/%s/media?access_token=%s",
        userId, accessToken
    );
    
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();
    
    // Parse JSON response
    // Return list of media
}
```

### Step 3: Automate Interactions
Like and comment:

```java
public class InstagramBot {
    private InstagramAPI api;
    
    public void likePost(String mediaId) {
        String url = String.format(
            "https://graph.instagram.com/%s/likes?access_token=%s",
            mediaId, api.getAccessToken()
        );
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();
        
        // Send request
    }
    
    public void commentOnPost(String mediaId, String comment) {
        String url = String.format(
            "https://graph.instagram.com/%s/comments?access_token=%s&message=%s",
            mediaId, api.getAccessToken(), comment
        );
        
        // Post comment
    }
}
```

### Step 4: Hashtag Search
Search by hashtag:

```java
public List<Media> searchByHashtag(String hashtag) {
    // Note: Instagram Graph API requires business account
    String url = String.format(
        "https://graph.instagram.com/hashtag_search?user_id=%s&q=%s&access_token=%s",
        userId, hashtag, accessToken
    );
    
    // Fetch and return media
}
```

## 💻 Exercises

### Exercise 1: API Integration
Practice with:
- OAuth authentication
- API requests
- Response parsing
- Error handling

### Exercise 2: Automation
Implement:
- Like posts
- Follow users
- Comment on posts
- Search hashtags

### Exercise 3: Rate Limiting
Handle:
- API rate limits
- Request delays
- Error recovery
- Best practices

## 🎮 Project: Instagram Automation Bot

### Requirements
Create Instagram automation bot:
1. Authenticate with Instagram API
2. Search posts by hashtag
3. Like relevant posts
4. Follow users
5. Comment on posts (optional)
6. Respect rate limits
7. Log activities

### Example Output
```
Instagram Bot Started

Searching for posts with #java...
Found 50 posts

Liking posts...
✓ Liked post 1
✓ Liked post 2
✓ Liked post 3
...

Following users...
✓ Followed @user1
✓ Followed @user2

Activity Summary:
- Posts liked: 25
- Users followed: 10
- Comments posted: 5
```

### Starter Code
```java
public class InstagramBot {
    private InstagramAPI api;
    
    public void automate(String hashtag, int maxPosts) {
        // Search hashtag
        List<Media> posts = api.searchByHashtag(hashtag);
        
        // Like and interact
        for (int i = 0; i < Math.min(maxPosts, posts.size()); i++) {
            api.likePost(posts.get(i).getId());
            
            // Rate limiting
            try {
                Thread.sleep(5000); // 5 seconds between actions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [Instagram Basic Display API](https://developers.facebook.com/docs/instagram-basic-display-api)
- [Instagram Graph API](https://developers.facebook.com/docs/instagram-api)
- [OAuth 2.0](https://oauth.net/2/)

### Tutorials
- [Instagram API Guide](https://developers.facebook.com/docs/instagram-api/getting-started)
- [OAuth Implementation](https://www.baeldung.com/java-oauth)

### Video Resources
- [Instagram API Tutorial](https://www.youtube.com/results?search_query=instagram+api+java)
- [Social Media Automation](https://www.youtube.com/results?search_query=instagram+automation+java)

### Practice Platforms
- [Instagram Developer](https://developers.facebook.com/docs/instagram)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| OAuth | Authentication | Instagram OAuth flow |
| Graph API | Instagram API | Media, users, hashtags |
| Rate Limiting | API restrictions | Delay between requests |
| Automation | Automated actions | Like, follow, comment |
| Best Practices | Ethical automation | Respect limits, terms |

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- **OAuth:** Re-read **Step 1** — Instagram/Facebook Graph API OAuth flow — see **Step 2**
- **API:** Re-read **Step 2** — use Graph API endpoints; rate limits — see **Step 2**
- **Automation:** Re-read **Step 3** — respect ToS; delays; avoid bans — see **Step 3**
- **Main day README** → **Step-by-Step Instructions** and **Key Concepts**

**Related days:** Day 45 (OAuth); Day 50 (social APIs); Day 35 (env). **Quick reference:** Env for client ID/secret; run main class from IDE

## 💻 How to Run

**Java + Instagram API** — set env vars for Instagram/Facebook app credentials. Run main class from IDE or Maven exec.

## ✅ Checklist
- [ ] Understand Instagram API
- [ ] Can authenticate
- [ ] Can fetch media
- [ ] Can automate interactions
- [ ] Can handle rate limits
- [ ] Completed Instagram Bot
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 52, you should be able to:
- Work with Instagram API
- Automate social interactions
- Handle OAuth
- Build social media tools

**Ready for Day 53?** You'll build an Automated Data Entry system!






