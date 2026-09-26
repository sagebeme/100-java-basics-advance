# Day 97 - Portfolio Project: WhatsApp Integration

## 📚 Learning Objectives
- Integrate WhatsApp API
- Send messages programmatically
- Build messaging features
- Handle WhatsApp webhooks
- Create messaging app

## 🎯 Project Requirements
Create WhatsApp Integration:
1. Send messages
2. Receive messages
3. Handle webhooks
4. User interface
5. Message management

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; WhatsApp Business API; env vars for credentials.

## ✅ Checklist
- [ ] Integrated WhatsApp
- [ ] Can send messages
- [ ] Can receive messages
- [ ] Created UI
- [ ] Completed integration
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/MessagingController.java` | `GET /` shows the conversation, `POST /messages/send` sends a message |
| `model/MessageDirection.java` | Incoming or outgoing |
| `model/MessageStatus.java` | Sent, failed or received |
| `model/WhatsAppMessage.java` | A JPA entity |
| `repository/WhatsAppMessageRepository.java` | Spring Data repository |
| `send/SendResult.java` | The API's answer |
| `send/SimulatedWhatsAppClient.java` | A stand-in for the real API, which needs a Meta developer account |
| `send/WhatsAppClient.java` | What sending a WhatsApp message needs, as an interface |
| `service/MessagingService.java` | Sends a message and records it, even when sending fails |
| `webhook/WebhookController.java` | The real WhatsApp Cloud API webhook: the verification handshake (GET) and incoming messages (POST) |

Also in `src/main/resources/`: `templates/index.html`.

`SimulatedWhatsAppClient` stands in for the real WhatsApp Cloud API, which needs a Meta developer account. The webhook's verify token is in `application.properties`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day97
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day97
mvn test
```

Runs `MessagingFlowTest`, `SimulatedWhatsAppClientTest` and `WebhookControllerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 98?** You'll build a Data Analysis Dashboard!
