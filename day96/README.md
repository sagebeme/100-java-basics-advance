# Day 96 - Portfolio Project: E-commerce with Payment

> A shop is where every mistake costs real money: a price stored as a decimal, a cart that charges the wrong total, an item sold twice, a customer seeing someone else's order. Today's shop gets each of those right.

## 📖 The story

Zawadi sells computer accessories from a stall at Luthuli Avenue, and her customers keep asking to order online. Her supplier quotes in US dollars, so for now her prices are in dollars too. She wants:

- a **catalogue** of products with prices and stock;
- a **cart** that remembers what you picked while you browse;
- a **checkout** that takes card details and charges the right amount;
- an **orders** page showing what you bought, and whether the payment went through.

Real card payments need a merchant account, so the shop uses a `PaymentGateway` interface with a `SimulatedPaymentGateway` behind it. It behaves like a payment provider's test mode: `4242 4242 4242 4242` is approved, `4000 0000 0000 0002` is declined, and a number that fails the card checksum or an expired date is refused. Swapping in a real provider later means writing one class.

## 🎯 Learning Objectives
- Store money as whole cents (`int`), never as `double`
- Keep a cart in the HTTP session, and recalculate the total on the server
- Hide the payment provider behind an interface, and test both approval and decline
- Validate card numbers with the Luhn checksum
- Check stock before charging, and reduce it after a sale, in one transaction
- Record declined orders too, and show each visitor only their own orders

## 🧰 What you'll use

**Money in cents.** `0.1 + 0.2` is `0.30000000000000004` as a `double`. Prices are `int priceCents`, totals are sums of ints, and they're only turned into `$89.99` for display.

**Never trust the browser's total.** The checkout form could be edited to say "charge $1". The service always charges `cart.getTotalCents()`, worked out on the server; a test sends a fake amount and checks it's ignored.

**The Luhn check** catches most typing mistakes in card numbers: from the right, double every second digit (subtracting 9 if it goes over 9), add everything up, and a valid number gives a multiple of 10.

**Stock, in a transaction.** The cart was filled from the session, maybe hours ago. Before charging, `checkout` reloads each product and refuses if there aren't enough ("Only 3 USB-C Hub left"). After an approved payment it takes them off the stock. `@Transactional` makes the stock change and the order one unit: both saved, or neither.

**Whose order is it?** There are no accounts, so the session remembers the ids of the orders it placed. `/orders` lists only those, and `/orders/{id}` for anyone else's order answers 404, exactly as if it didn't exist, so nobody can read other customers' emails by guessing ids.

**What's never stored:** the card number and CVV are used for the charge and then dropped. Orders keep only the result and a transaction id.

## 🪜 Steps

**Your brief** (the `day96/` folder is one finished version: build your own first, then compare).

1. `Product` (in cents, with stock), `Cart` and `CartItem`, with add, update, remove and clear.
2. `PaymentGateway`, `PaymentRequest`, `PaymentResult`, and the simulated gateway with Luhn and expiry checks.
3. `CustomerOrder`, `OrderLineItem`, `OrderStatus`, and `OrderService.checkout`: stock check, charge the server's total, record the order either way, reduce the stock and clear the cart only on approval.
4. The controllers and pages: catalogue, cart, checkout (with errors), my orders, and an order's detail.
5. Tests: the cart, the gateway's rules, checkout approved, declined, empty, over-stock and with a faked amount, and the whole flow through the web pages, including a stranger trying to see your order.
6. Make it yours: price in KES and add M-Pesa as a second gateway (an STK push request, then a callback; see Daraja's sandbox), email a receipt, or add an admin page for stock.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day96
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>, fill a cart, and pay with `4242 4242 4242 4242` (any future date, any CVV).

## 🆘 Stuck?

- **Totals like `$45.989999999`**: a price is a `double`. Use cents.
- **The cart empties on every page**: it must live in the `HttpSession`, not in a field of the controller (which every visitor shares).
- **A declined payment emptied the cart**: clear it only when the payment is approved.
- **Two customers bought the last item**: check stock inside the same transaction that reduces it. (Under heavy traffic you'd also lock the row, or use `@Version` for optimistic locking.)
- **Anyone can open `/orders/5`**: tie orders to the session (or, with accounts, to the user) and answer 404 for anything else.

## ✅ Checklist
- [ ] Money in cents everywhere
- [ ] A session cart, totals worked out on the server
- [ ] A payment gateway interface with approval and decline tested
- [ ] Stock checked before charging and reduced after
- [ ] Each visitor sees only their own orders
- [ ] Added KES and M-Pesa, receipts, or an admin page
- [ ] Committed code to Git

## ➡️ Next

Day 97 connects an app to WhatsApp: receiving messages through a webhook and replying automatically.
