# Day 61 - Advanced Forms: Orders With Several Items, and File Uploads

> Real forms aren't a flat list of fields. An order has a customer inside it and a list of items, and sometimes it comes with a photo of a receipt. Today one form fills all of that in, and the upload can't be tricked into writing where it shouldn't.

## 📖 The story

Kerugoya Hardware takes orders by phone, and Njoroge writes them on whatever paper is nearest. He wants an order page on the shop's laptop:

- the **customer**: a name and an email;
- up to three **items**, each a product and a quantity, with empty rows ignored;
- a list of the orders so far;
- and a page to **upload** a photo of a receipt or a delivery note.

```
New order
Customer:  Name [Amina          ]  Email [amina@example.com]
Items:     [Cement 50kg ] [3]
           [Nails 3"    ] [2]
           [            ] [1]
[ Save order ]

Order for Amina saved.
```

The trick is that the form's fields are named after the path to each value: `customer.name`, `items[0].product`, `items[1].quantity`. Spring reads those names and builds the whole `Order`, with its `Customer` inside and its `List<OrderItem>`, before your method runs.

## 🎯 Learning Objectives
- Bind nested objects (`customer.name`) and lists (`items[0].product`) from one form
- Build indexed fields in Thymeleaf with `th:each` and `__${row.index}__`
- Clean up input before saving (drop empty rows), and re-show the form with a message when it's unusable
- Handle a type mismatch (a quantity of "many") through `BindingResult` instead of a 400 page
- Upload files with `multipart/form-data` and `MultipartFile`
- Never trust a file name from the browser: stop path traversal

## 🧰 What you'll use

**Nested and indexed fields.** `th:field="*{customer.name}"` names the input `customer.name`. For a list, the index has to be worked out first, which is what `__...__` (preprocessing) does:

```html
<div th:each="item, row : *{items}">
    <input th:field="*{items[__${row.index}__].product}">
    <input type="number" th:field="*{items[__${row.index}__].quantity}">
</div>
```

**Uploading.** The form needs `method="post"` and `enctype="multipart/form-data"`, and the controller takes a `MultipartFile`:

```java
@PostMapping("/upload")
public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirect) throws IOException {
    if (file.isEmpty()) { ... }
    String name = safeName(file.getOriginalFilename());
    file.transferTo(uploadDir.resolve(name));
    ...
}
```

**Path traversal.** The browser chooses the file name, so an attacker can choose `../../.bashrc`. `uploadDir.resolve("../../.bashrc")` points *outside* the upload folder, and `transferTo` would overwrite that file. `safeName` keeps only the part after the last `/` or `\`, and refuses `.` and `..`. The controller also checks that the final path's folder really is the upload folder. Exercises and real code alike: never build a path from user input without both checks.

## 🪜 Steps

**Project: Kerugoya Hardware orders** (the `day61/` folder is a finished version: build your own first, then compare).

1. `Customer`, `OrderItem` and `Order` (a customer and a list of items).
2. `GET /orders`: the form with three empty item rows, and the orders so far.
3. `POST /orders`: drop empty rows; if nothing's left, or a quantity isn't a number, show the form again with a message; otherwise save and redirect with a flash message.
4. `GET /upload` and `POST /upload`: the upload form, `safeName`, and a message for an empty file or a refused name.
5. Tests: nested and list binding, empty rows, the two re-shown forms, a normal upload, an empty one, and a `../../` file name that must land inside the upload folder.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day61
mvn spring-boot:run
```

Open http://localhost:8080/orders and http://localhost:8080/upload. Uploads go to a temporary folder that's created when the app starts. Stop it with Ctrl+C.

```bash
cd day61
mvn test
```

## 🆘 Stuck?

- **The list always arrives empty**: the field names don't have the index. Check the page source for `name="items[0].product"`.
- **`th:field="*{items[row.index].product}"` fails**: the index has to be worked out before the field is: `items[__${row.index}__]`.
- **A quantity of "many" gives a 400 page**: add a `BindingResult` parameter right after the `@ModelAttribute`, and check `hasErrors()`.
- **`MultipartFile` is always empty**: the form is missing `enctype="multipart/form-data"`.
- **`MaxUploadSizeExceededException`**: the default limit is 1MB. Raise it with `spring.servlet.multipart.max-file-size=5MB` if you need to.

## ✅ Checklist
- [ ] One form fills an object with an object and a list inside it
- [ ] Empty rows are dropped, and an unusable form is shown again with a message
- [ ] Files upload, and empty ones are refused
- [ ] A `../` file name can't write outside the upload folder, and a test proves it
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 62 moves data in and out in bulk: importing contacts from a CSV file, and exporting them as a download.
