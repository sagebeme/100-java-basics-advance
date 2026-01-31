# Day 64 - My Top 10 Movies Website

## 📚 Learning Objectives
- Build complete web application
- Integrate database
- Create movie listing
- Implement ranking system
- Build full-stack application

## 🎯 Topics Covered
- Full-stack development
- Database integration
- List management
- Ranking system
- CRUD operations
- User interface

## 📝 Step-by-Step Instructions

### Step 1: Movie Entity
Create Movie entity:

```java
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String director;
    private Integer year;
    private Integer rating;
    private Integer rank;
}
```

### Step 2: Movie Repository
Create repository:

```java
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByRankAsc();
    Optional<Movie> findByRank(Integer rank);
}
```

### Step 3: Controller
Create controller:

```java
@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    
    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getTop10Movies());
        return "movies";
    }
}
```

## 🎮 Project: Top 10 Movies Website

### Requirements
Create website with:
1. Movie database
2. Top 10 ranking
3. Add/edit movies
4. Update rankings
5. Beautiful UI

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Created movie entity
- [ ] Built repository
- [ ] Created controller
- [ ] Built UI
- [ ] Implemented ranking
- [ ] Completed Top 10 Movies
- [ ] Committed code to Git

## 💻 How to Run

**Run:** `mvn spring-boot:run` or run main Application from IDE. Open http://localhost:8080.

## 🚀 Next Steps
**Ready for Day 65?** You'll learn web design best practices!






