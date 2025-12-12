# Day 53 - Automated Data Entry

## 📚 Learning Objectives
- Automate data entry tasks
- Work with forms and databases
- Use Selenium for form filling
- Handle data validation
- Build data entry automation

## 🎯 Topics Covered
- Form automation
- Data processing
- Database integration
- Validation
- Error handling
- Batch processing

## 📝 Step-by-Step Instructions

### Step 1: Data Source
Load data to enter:

```java
public class DataLoader {
    public List<EntryData> loadFromCSV(String filename) {
        List<EntryData> entries = new ArrayList<>();
        
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                
                EntryData entry = new EntryData(
                    values[0], // name
                    values[1], // email
                    values[2], // phone
                    values[3]  // address
                );
                entries.add(entry);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return entries;
    }
}
```

### Step 2: Form Automation
Fill forms automatically:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormFiller {
    private WebDriver driver;
    
    public void fillForm(EntryData data) {
        // Navigate to form
        driver.get("https://example.com/form");
        
        // Fill fields
        driver.findElement(By.id("name")).sendKeys(data.getName());
        driver.findElement(By.id("email")).sendKeys(data.getEmail());
        driver.findElement(By.id("phone")).sendKeys(data.getPhone());
        driver.findElement(By.id("address")).sendKeys(data.getAddress());
        
        // Submit
        driver.findElement(By.id("submit")).click();
    }
}
```

### Step 3: Data Validation
Validate before entry:

```java
public class DataValidator {
    public boolean validate(EntryData data) {
        // Validate email
        if (!isValidEmail(data.getEmail())) {
            return false;
        }
        
        // Validate phone
        if (!isValidPhone(data.getPhone())) {
            return false;
        }
        
        // Check required fields
        if (data.getName().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean isValidPhone(String phone) {
        return phone.matches("^\\+?[1-9]\\d{1,14}$");
    }
}
```

### Step 4: Batch Processing
Process multiple entries:

```java
public class DataEntryBot {
    public void processEntries(List<EntryData> entries) {
        int success = 0;
        int failed = 0;
        
        for (EntryData entry : entries) {
            if (validator.validate(entry)) {
                try {
                    formFiller.fillForm(entry);
                    success++;
                    
                    // Log success
                    logEntry(entry, "SUCCESS");
                    
                    // Rate limiting
                    Thread.sleep(2000);
                } catch (Exception e) {
                    failed++;
                    logEntry(entry, "FAILED: " + e.getMessage());
                }
            } else {
                failed++;
                logEntry(entry, "VALIDATION FAILED");
            }
        }
        
        System.out.println("Processed: " + entries.size());
        System.out.println("Success: " + success);
        System.out.println("Failed: " + failed);
    }
}
```

## 💻 Exercises

### Exercise 1: Data Loading
Load data from:
- CSV files
- JSON files
- Databases
- Excel files

### Exercise 2: Form Automation
Automate:
- Text inputs
- Dropdowns
- Checkboxes
- File uploads

### Exercise 3: Validation
Implement:
- Email validation
- Phone validation
- Required fields
- Format checking

## 🎮 Project: Automated Data Entry System

### Requirements
Create data entry automation:
1. Load data from CSV/JSON
2. Validate data
3. Fill forms automatically
4. Handle errors
5. Log results
6. Generate reports

### Example Output
```
Data Entry Bot

Loading data from entries.csv...
Found 100 entries

Validating data...
✓ 95 entries valid
✗ 5 entries invalid

Processing entries...
Entry 1/95: ✓ Success
Entry 2/95: ✓ Success
Entry 3/95: ✗ Failed: Invalid email

Summary:
- Total: 100
- Valid: 95
- Processed: 95
- Success: 90
- Failed: 5
```

### Starter Code
```java
public class DataEntryBot {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        DataValidator validator = new DataValidator();
        FormFiller filler = new FormFiller();
        
        // Load data
        List<EntryData> entries = loader.loadFromCSV("entries.csv");
        
        // Process
        for (EntryData entry : entries) {
            if (validator.validate(entry)) {
                filler.fillForm(entry);
            }
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [CSV Processing](https://www.baeldung.com/java-csv)

### Tutorials
- [Form Automation](https://www.selenium.dev/documentation/webdriver/elements/)
- [Data Validation](https://www.baeldung.com/java-validation)

### Video Resources
- [Data Entry Automation](https://www.youtube.com/results?search_query=data+entry+automation+java)
- [Selenium Form Filling](https://www.youtube.com/results?search_query=selenium+form+filling)

### Practice Platforms
- [Automation Projects](https://github.com/karan/Projects#automation)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Data Loading | Read from sources | CSV, JSON, database |
| Form Automation | Fill forms | Selenium WebDriver |
| Validation | Check data | Email, phone, format |
| Batch Processing | Process multiple | Loop through entries |
| Error Handling | Handle failures | Try-catch, logging |

## ✅ Checklist
- [ ] Can load data from files
- [ ] Can validate data
- [ ] Can automate forms
- [ ] Can handle errors
- [ ] Can generate reports
- [ ] Completed Data Entry Bot
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 53, you should be able to:
- Automate data entry
- Process batch data
- Validate information
- Build automation tools

**Ready for Day 54?** You'll learn Spring Boot (already exists, but we'll continue with 55-58)!

