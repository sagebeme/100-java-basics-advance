# Day 40 - Solution

The finished Safari Club, checked by `SafariClubTest.java`.

Things worth noticing:

- **Most of the code is reused.** Eight classes came from Day 39 and three from Day 32 without changes. Small, focused classes travel well.
- **`Member` can't be invalid.** Its compact constructor checks the name, email and wishlist, and tidies the email, so `MemberStore` never has to.
- **`send` searches each destination once** and shares the results among all members.
- **`emailFor` returns `Optional<Email>`**, so "don't email this person" is a value the caller has to handle, not a `null` to forget.
