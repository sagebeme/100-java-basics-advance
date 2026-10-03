# Day 45 - Exercise 1: PKCE

Write `newVerifier()` and `challenge(verifier)` in `src/Pkce.java`, following RFC 7636: the verifier is random URL-safe text, and the challenge is `base64url(SHA-256(verifier))` with no `=` padding. The test checks your challenge against the example printed in the standard itself.

## 📌 Stuck?

`Base64.getUrlEncoder().withoutPadding()`, `new SecureRandom().nextBytes(bytes)` and `MessageDigest.getInstance("SHA-256").digest(...)`. One answer is in `solution/Pkce.java`.
