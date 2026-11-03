# Day 57 - Exercise 2: A Sacco member's page

A teachers' Sacco in Nyeri is moving its members' statements onto a web page. Different members see different things: the chair and treasurer get a line about what they're allowed to do, only members who've saved for six months can see how much they may borrow, and a new member's empty statement shouldn't show an empty table. One template, `templates/member.html`, handles all of it with Thymeleaf's conditions and loops.

`MemberController` (already written) puts a `member` in the model and has three members: `/members/1` (the chair, 30 months of saving), `/members/2` (the treasurer, 4 months) and `/members/3` (a new member, nothing yet). The heading is done. Add:

| What | Rule | Shows |
|---|---|---|
| Role | `th:switch` on `member.role` | `CHAIR`: `Chairperson: you can approve loans.` `TREASURER`: `Treasurer: you can record contributions.` Anything else: `Member` |
| Savings | always | `Savings: KES 84,000` |
| Loans | `monthsSaving` is 6 or more | `You can borrow up to KES 252,000.` (that's `member.loanLimit`) |
| | otherwise | `You can borrow after 6 months of saving. 2 to go.` |
| Statement | no entries | `No transactions yet.` and no table at all |
| | entries | a table with a row per entry: the row number (1, 2, 3…), date, what, and the amount with a comma. A row whose amount is negative gets `class="out"`, which the page colours red. |

## Files

- `src/main/resources/templates/member.html`: your code goes here.
- `MemberController.java`, `Member.java`, `Entry.java` and `static/css/kadi.css`: done.
- `src/test/java/com/learning/MemberControllerTest.java`: the tests.
- `solution/src/main/resources/templates/member.html`: one answer.

```bash
cd day57/exercise2
mvn test
```

## 📌 Stuck?

- `th:switch="${member.role}"` on an outer tag, then `th:case="'CHAIR'"` on each inner one. `th:case="*"` is the default.
- `th:if` and `th:unless` with the same condition give "one or the other".
- `th:each="entry, row : ${member.statement}"` also gives you `row`, the loop status: `row.count` starts at 1, `row.index` at 0.
- `th:classappend="${entry.shillings < 0} ? 'out'"` adds the class only when the condition holds.
- `${#lists.isEmpty(member.statement)}` is true for an empty list.
