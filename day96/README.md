# Day 96 - Portfolio Project: E-commerce with Payment

## 📚 Learning Objectives
- Build e-commerce platform
- Integrate payment gateway
- Handle transactions
- Create shopping cart
- Build complete store

## 🎯 Project Requirements
Create E-commerce Site:
1. Product catalog
2. Shopping cart
3. Checkout process
4. Payment integration
5. Order management

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; a simulated payment gateway stands in for Stripe/PayPal; JPA for orders (Day 63).

## ✅ Checklist
- [ ] Created catalog
- [ ] Added cart
- [ ] Integrated payment
- [ ] Handles orders
- [ ] Completed store
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `config/DataSeeder.java` | Adds sample products when the app starts |
| `controller/CartController.java` | View the cart, change quantities, remove items |
| `controller/CartSessionSupport.java` | Gets the cart from the session |
| `controller/CatalogController.java` | `GET /` shows products, `POST /cart/add/{productId}` adds one |
| `controller/CheckoutController.java` | The checkout form and payment |
| `controller/CheckoutForm.java` | The checkout form, with validation |
| `controller/OrderController.java` | Your orders, and one order's details |
| `model/Cart.java` | The cart, kept in the session |
| `model/CartItem.java` | A product and quantity |
| `model/CustomerOrder.java` | A JPA entity (not `Order`: that's a reserved SQL word) |
| `model/OrderLineItem.java` | One line of an order |
| `model/OrderStatus.java` | Paid or declined |
| `model/Product.java` | A JPA entity, prices in cents |
| `payment/PaymentGateway.java` | What a card processor does, as an interface |
| `payment/PaymentRequest.java` | What's charged |
| `payment/PaymentResult.java` | Approved or declined, with a transaction id |
| `payment/SimulatedPaymentGateway.java` | A stand-in for Stripe or PayPal: approves every card except numbers ending in 0002, which always decline |
| `repository/OrderRepository.java` | Spring Data repository |
| `repository/ProductRepository.java` | Spring Data repository |
| `service/OrderService.java` | Charges the cart and records the order, whether the payment worked or not |

Also in `src/main/resources/`: `templates/cart.html`, `templates/catalog.html`, `templates/checkout.html`, `templates/order-detail.html`, `templates/orders.html`.

No real money moves: `SimulatedPaymentGateway` stands in for Stripe or PayPal, which need a merchant account. Swapping in a real one means implementing `PaymentGateway`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day96
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day96
mvn test
```

Runs `CartTest`, `OrderServiceTest`, `ShoppingFlowTest` and `SimulatedPaymentGatewayTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 97?** You'll build WhatsApp Integration!
