# Day 67 - Blog Capstone Part 3: RESTful Routing

## 📚 Learning Objectives
- Add REST API to blog
- Create API endpoints
- Integrate API with frontend
- Build API-first application
- Complete blog functionality

## 🎯 Topics Covered
- REST API integration
- Frontend-backend separation
- API endpoints
- JSON responses
- API testing

## 📝 Step-by-Step Instructions

### Step 1: Blog API
Create REST endpoints:

```java
@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(postService.save(post));
    }
}
```

## 🎮 Project: Blog REST API

### Requirements
Add REST API to blog:
1. GET /api/posts
2. GET /api/posts/{id}
3. POST /api/posts
4. PUT /api/posts/{id}
5. DELETE /api/posts/{id}

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Created REST endpoints
- [ ] Integrated with frontend
- [ ] Tested API
- [ ] Added error handling
- [ ] Completed Blog REST API
- [ ] Committed code to Git

## 💻 How to Run

**Run:** `mvn spring-boot:run` or run main Application from IDE. Open http://localhost:8080.

## 🚀 Next Steps
**Ready for Day 68?** You'll learn authentication with Spring Security!






