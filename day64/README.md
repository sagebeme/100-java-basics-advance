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

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/MovieController.java` | `GET /movies` shows the top 10, `POST /movies` adds one |
| `model/Movie.java` | A JPA entity: title, director, year, rating and rank |
| `repository/MovieRepository.java` | Spring Data repository for movies |
| `service/MovieService.java` | Keeps the ranking: adds at the bottom, moves a movie to a new rank |

Also in `src/main/resources/`: `templates/movies.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day64
mvn spring-boot:run
```

Then open http://localhost:8080/movies. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day64
mvn test
```

Runs `MovieServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 65?** You'll learn web design best practices!
