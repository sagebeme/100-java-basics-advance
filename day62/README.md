# Day 62 - Spring Boot, Forms, and CSV

## 📚 Learning Objectives
- Export form data to CSV
- Import CSV data via forms
- Process CSV files
- Generate CSV reports
- Build data import/export

## 🎯 Topics Covered
- CSV generation
- CSV parsing
- File downloads
- Data export
- Data import
- Report generation

## 📝 Step-by-Step Instructions

### Step 1: Export to CSV
Generate CSV from data:

```java
@GetMapping("/users/export")
public void exportUsers(HttpServletResponse response) throws IOException {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=users.csv");
    
    List<User> users = userService.getAllUsers();
    PrintWriter writer = response.getWriter();
    
    writer.println("Name,Email,Phone");
    for (User user : users) {
        writer.println(String.format("%s,%s,%s", 
            user.getName(), user.getEmail(), user.getPhone()));
    }
}
```

### Step 2: Import from CSV
Upload and parse CSV:

```java
@PostMapping("/users/import")
public String importUsers(@RequestParam("file") MultipartFile file) {
    try {
        List<User> users = csvService.parseCSV(file);
        userService.saveAll(users);
        return "redirect:/users?imported=true";
    } catch (Exception e) {
        return "redirect:/users?error=import";
    }
}
```

## 🎮 Project: Data Import/Export System

### Requirements
Create system for:
1. Export data to CSV
2. Import data from CSV
3. Validate CSV data
4. Handle errors
5. Generate reports

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Can export to CSV
- [ ] Can import from CSV
- [ ] Can validate data
- [ ] Can handle errors
- [ ] Completed import/export system
- [ ] Committed code to Git

## 💻 How to Run

**Run:** `mvn spring-boot:run` or run main Application from IDE. Open http://localhost:8080.

## 🚀 Next Steps
**Ready for Day 63?** You'll learn databases with JPA and Hibernate!






