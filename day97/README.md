# Day 97 - Portfolio Project: WhatsApp Integration

> In Kenya, customers don't email: they WhatsApp. Today's app plugs into the WhatsApp Cloud API's two halves: a webhook that receives messages, checked so nobody can fake them, and a client that sends replies.

## 📖 The story

Achieng runs a cake business in Kisumu. Orders arrive on WhatsApp at all hours, and she loses track of who asked for what. She wants her messages in a small web app: every incoming message saved, every reply sent from one page, and a history she can scroll.

The WhatsApp Cloud API works like this:

1. **Verification.** When you register your webhook URL, Meta calls `GET /webhook?hub.mode=subscribe&hub.verify_token=...&hub.challenge=...`. If the token matches yours, you echo the challenge back.
2. **Incoming messages.** Meta `POST`s JSON to your webhook for every message, signed with your app's secret.
3. **Sending.** You call Meta's API with the number and the text.

Sending for real needs a Meta business account and a verified number, so the app has a `WhatsAppClient` interface with a `SimulatedWhatsAppClient` that checks numbers exactly as the real API expects (E.164: `+254712345678`). The webhook side is real: its tests use JSON shaped like Meta's documented payloads, signed the way Meta signs them.

## 🎯 Learning Objectives
- Implement a webhook's verification handshake
- Verify an HMAC signature over the raw request body, in constant time
- Parse nested JSON with `JsonNode` without crashing on events that have no messages
- Handle redelivery: the same message may arrive twice, so save it once
- Hide an external API behind an interface, and record failed sends too
- Keep tokens and secrets in environment variables, never in Git

## 🧰 What you'll use

**Why signatures matter.** Your webhook URL is public, and anyone can `POST` JSON to it pretending to be a customer. Meta adds a header to every webhook:

```
X-Hub-Signature-256: sha256=<hex of HMAC-SHA256(raw body, your app secret)>
```

Only Meta and you know the secret, so only Meta can make a matching signature, and changing even one character of the body breaks it. The controller takes the body as raw bytes (`@RequestBody byte[]`, because re-serialised JSON wouldn't match byte for byte), computes the HMAC, and compares with `MessageDigest.isEqual`, which takes the same time however many characters match, so an attacker can't guess the signature one character at a time.

**Redelivery.** If your server is slow to answer, Meta sends the same webhook again. Each message has an id (`wamid....`), and the app skips one it has already saved.

**Secrets from the environment:**

```properties
whatsapp.webhook.verify-token=${WHATSAPP_VERIFY_TOKEN:test-verify-token-123}
whatsapp.app-secret=${WHATSAPP_APP_SECRET:dev-only-app-secret}
```

The defaults only exist so the app starts on your laptop. In production, set the real values as environment variables.

**Phone numbers in E.164:** a `+`, the country code, then the number, with no spaces or leading zero: `0712 345 678` becomes `+254712345678`.

## 🪜 Steps

**Your brief** (the `day97/` folder is one finished version: build your own first, then compare).

1. `WhatsAppMessage` (direction, numbers, body, status, external id, time) and its repository.
2. `WebhookController`: the GET handshake, and the POST with signature check, parsing and de-duplication.
3. `WhatsAppClient`, `SimulatedWhatsAppClient` with the E.164 check, and `MessagingService` (a failed send is saved as `FAILED`).
4. `MessagingController` and the page: a send form and the message history.
5. Tests: the handshake (right token, wrong token, wrong mode), a signed message, a status-only event, an unsigned post, a forged signature, a duplicate delivery, number validation, and the page.
6. Make it yours: auto-reply to "menu" with your price list, convert `07...` numbers to E.164 for the user, or (with a Meta developer account) write the real client against the Cloud API's test number.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day97
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>. To receive real webhooks, the app needs a public HTTPS address (for example a tunnel such as `cloudflared` or `ngrok` while developing) registered in Meta's dashboard, with `WHATSAPP_VERIFY_TOKEN` and `WHATSAPP_APP_SECRET` set.

## 🆘 Stuck?

- **Meta says verification failed**: the token in the dashboard must equal `WHATSAPP_VERIFY_TOKEN`, and the response body must be the challenge alone, as plain text.
- **Every real webhook gets 401**: the app secret is wrong (it's the *App Secret* from the app's basic settings, not an access token), or something changed the body before you hashed it.
- **The same message appears twice**: check the message id before saving.
- **`NullPointerException` on a delivery receipt**: status events have `statuses`, not `messages`. Check before reading.

## ✅ Checklist
- [ ] The verification handshake
- [ ] Signed webhooks only, compared in constant time
- [ ] Messages saved once, even when redelivered
- [ ] Sending behind an interface, with failures recorded
- [ ] Secrets only in environment variables
- [ ] Added auto-replies or number conversion
- [ ] Committed code to Git

## ➡️ Next

Day 98 builds a sales dashboard: totals, filters and charts from a year of sales, with CSV and JSON exports.
