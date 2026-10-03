# Day 12 - Solution

`src/FarmerRecord.java` and `src/RecordsOffice.java` are one finished version, checked by `src/FarmerRecordTest.java`.

Things worth noticing:

- **There's no setter for `deliveredKg`.** The only way to change it is `addDelivery`, so the "no negative deliveries" rule can't be skipped.
- **`final` on `name` and `memberCode`.** Once a record is opened, nobody can rename it to someone else's.
- **`this.name = name`.** Inside the constructor, the parameter `name` hides the field `name`. `this.` reaches past it to the field.
- **`recordsOpened` is `static`**, so it's one counter shared by every record, not one per record.
- **The test checks the field really is private**, so a later "quick fix" that makes it public would fail.
