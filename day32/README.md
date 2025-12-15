# Day 32 - Email (JavaMail) & Date Management

## 📚 Learning Objectives
- Send emails using JavaMail API
- Work with dates and times
- Format dates
- Schedule tasks
- Automate email sending

## 🎯 Topics Covered
- JavaMail API
- Email sending (SMTP)
- Date and time classes
- Date formatting
- Scheduling
- Automation

## 📝 Step-by-Step Instructions

### Step 1: JavaMail Setup
Add JavaMail dependency:

**pom.xml:**
```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>
```

### Step 2: Send Email
Send email programmatically:

```java
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String to, String subject, String body) {
        String from = "your-email@gmail.com";
        String password = "your-app-password";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);
            
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
```

### Step 3: Date and Time
Work with dates:

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Current date
LocalDate today = LocalDate.now();

// Specific date
LocalDate birthday = LocalDate.of(1990, 5, 15);

// Format date
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String formatted = today.format(formatter);

// Parse date
LocalDate parsed = LocalDate.parse("2023-12-25", formatter);
```

### Step 4: Date Operations
Manipulate dates:

```java
// Add days
LocalDate future = today.plusDays(30);

// Check if before/after
boolean isBefore = today.isBefore(birthday);

// Get day of week
DayOfWeek day = today.getDayOfWeek();
```

## 💻 Exercises

### Exercise 1: Email Sending
Create programs that:
- Send simple emails
- Send HTML emails
- Send emails with attachments
- Send to multiple recipients

### Exercise 2: Date Operations
Practice with:
- Date formatting
- Date parsing
- Date calculations
- Date comparisons

### Exercise 3: Automation
Create automated systems:
- Scheduled email sending
- Birthday reminders
- Date-based triggers
- Recurring tasks

## 🎮 Project: Automated Birthday Wisher

### Requirements
Create a birthday reminder system:
1. Read birthdays from CSV file
2. Check if today is someone's birthday
3. Send birthday email automatically
4. Track sent emails
5. Support multiple recipients

### Example CSV (birthdays.csv)
```csv
name,email,birthday
Alice,alice@example.com,1990-05-15
Bob,bob@example.com,1985-12-25
```

### Starter Code
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BirthdayWisher {
    public static void main(String[] args) {
        List<Person> people = loadBirthdays();
        LocalDate today = LocalDate.now();
        
        for (Person person : people) {
            if (isBirthdayToday(person, today)) {
                sendBirthdayEmail(person);
            }
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [JavaMail API - Oracle Docs](https://javaee.github.io/javameail/)
- [Date and Time - Oracle Docs](https://docs.oracle.com/javase/tutorial/datetime/)
- [LocalDate - Java API](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)

### Tutorials
- [JavaMail Tutorial - Baeldung](https://www.baeldung.com/java-email)
- [Java Date Time - W3Schools](https://www.w3schools.com/java/java_date.asp)
- [JavaMail Example - GeeksforGeeks](https://www.geeksforgeeks.org/sending-email-java/)

### Video Resources
- [JavaMail Tutorial](https://www.youtube.com/results?search_query=java+send+email+tutorial)
- [Java Date Time Tutorial](https://www.youtube.com/results?search_query=java+date+time+tutorial)

### Practice Platforms
- [Email Projects](https://github.com/karan/Projects#email)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| SMTP | Email protocol | `smtp.gmail.com` |
| JavaMail | Email API | `javax.mail` package |
| LocalDate | Date class | `LocalDate.now()` |
| DateTimeFormatter | Date formatting | `DateTimeFormatter.ofPattern()` |
| Automation | Scheduled tasks | Check dates and send emails |

## ✅ Checklist
- [ ] Can send emails
- [ ] Understand JavaMail API
- [ ] Can work with dates
- [ ] Can format dates
- [ ] Can automate tasks
- [ ] Completed Birthday Wisher
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 32, you should be able to:
- Send emails programmatically
- Work with dates effectively
- Automate tasks
- Build notification systems

**Ready for Day 33?** You'll learn about REST API endpoints and HTTP clients!



