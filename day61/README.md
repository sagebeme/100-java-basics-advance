# Day 61 - Building Advanced Forms

## 📚 Learning Objectives
- Build complex forms
- Handle multiple form fields
- Work with file uploads
- Implement form wizards
- Create dynamic forms

## 🎯 Topics Covered
- Complex form fields
- File uploads
- Form wizards
- Dynamic fields
- Nested objects
- Multi-step forms

## 📝 Step-by-Step Instructions

### Step 1: File Upload
Handle file uploads:

```java
@PostMapping("/upload")
public String handleFileUpload(@RequestParam("file") MultipartFile file) {
    if (!file.isEmpty()) {
        // Save file
        fileService.saveFile(file);
        return "redirect:/success";
    }
    return "redirect:/error";
}
```

### Step 2: Complex Forms
Handle nested objects:

```java
public class Order {
    private Customer customer;
    private List<OrderItem> items;
}

@PostMapping("/orders")
public String createOrder(@ModelAttribute Order order) {
    orderService.save(order);
    return "redirect:/orders";
}
```

## 🎮 Project: Advanced Registration Form

### Requirements
Create advanced form with:
1. Multiple sections
2. File upload
3. Dynamic fields
4. Validation
5. Progress indicator

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Can build complex forms
- [ ] Can handle file uploads
- [ ] Can work with nested objects
- [ ] Can create dynamic forms
- [ ] Completed advanced form
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/FileUploadController.java` | `POST /upload` saves an uploaded file |
| `controller/OrderController.java` | `POST /orders` accepts a nested form: a customer and a list of order items |
| `model/Customer.java` | The customer part of an order form |
| `model/Order.java` | An order: a customer and its items |
| `model/OrderItem.java` | One product and quantity |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day61
mvn spring-boot:run
```

It has no pages to open: its controllers take form posts (`POST /orders`, `POST /upload`). The tests show example requests. Stop it with Ctrl+C.

## 🧪 How to Test

```bash
cd day61
mvn test
```

Runs `FileUploadControllerTest` and `OrderControllerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 62?** You'll work with forms and CSV!
