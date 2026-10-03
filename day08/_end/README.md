# Day 8 - Solution

`src/FarmerPayout.java` is one finished version, checked by `src/FarmerPayoutTest.java`.

Things worth noticing:

- **`netPay` builds on `grossPay`**, and `payslip` builds on both. Each number is worked out in exactly one place.
- **`Math.max(0, ...)`** is a one-line guard: whatever the advance, nobody is paid a negative amount.
- **`fillPayouts` returns nothing**, but its test still sees the results. The caller's array is changed through the reference it passed in.
- **`%,.2f`** adds thousands separators, so `44200` prints as `44,200.00`.
