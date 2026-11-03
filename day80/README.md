# Day 80 - Capstone Project: Predict House Prices

> Twenty houses that have sold, and a buyer asking "what's this one worth?" Today you train a model yourself, with no machine learning library, and serve its answers through a web page and a JSON API, and you teach it to say "I don't know".

## 📖 The story

Nyokabi is an estate agent in Nakuru. She has records of 20 houses she has sold: their size, bedrooms, bathrooms, age and price in shillings. Buyers ask her every week what a house is worth, and she answers from memory. She wants a page where she types in the details and gets an estimate based on her real sales.

That's **linear regression**: find the numbers (weights) that best turn the features into a price:

```
price ≈ w0 + w1·squareFeet + w2·bedrooms + w3·bathrooms + w4·ageYears
```

"Best" means the smallest total of squared errors over all 20 sales, and there's a formula for that: the **normal equations**. The app solves them when it starts, then answers:

```
POST /api/predict/house-price   {"squareFeet":1800,"bedrooms":3,"bathrooms":2,"ageYears":10}
→ 200 {"price":1.530967851602312E7}

Web page: Predicted price: KES 15,309,679
```

But ask about a 400-year-old house, and a straight line drawn through houses of 1 to 40 years old predicts a **negative price**. So the model also remembers the range it learned from, and refuses to guess outside it:

```
→ 422 "This model learned from houses of 900-3,000 sq ft, 1-5 bedrooms, 1-4 bathrooms
       and 1-40 years old, so it can't price this one."
```

## 🎯 Learning Objectives
- Train a multiple linear regression from scratch with the normal equations
- Solve a small system of equations with Gaussian elimination
- Load training data from a CSV on the classpath when the app starts
- Serve predictions as a JSON API and as a Thymeleaf form, with validation on both
- Spot when a model is being asked about something it never saw, and refuse
- Notice training data that can't be solved, and test the maths with a known answer

## 🧰 What you'll use

**The normal equations.** Put the features in a matrix `X` (one row per house, with a 1 at the front for `w0`) and the prices in `y`. The best weights solve:

```
(Xᵀ X) w = Xᵀ y
```

`XᵀX` is only 5×5, whatever the number of houses, so plain loops and Gaussian elimination solve it in no time. Libraries like scikit-learn do the same maths for problems this small.

**Training at startup.** `TrainingDataLoader` is a `CommandLineRunner`: Spring calls its `run` method once the app has started, and it reads `training-data.csv` and trains the model.

**Testing maths with a known answer.** Make up data from an exact formula (`price = 50000 + 100·sqft + ...`), train on it, and check the model finds that formula again. If it doesn't, the maths is wrong.

**Extrapolation.** Inside the range of the training data, a straight line is a fair guess. Far outside it, it's nonsense. The model records the lowest and highest value of each feature, allows 10% of the range either side, and `isWithinTrainingRange` says whether a house is close enough to the ones it learned from.

**Features that move together.** If every house in the data had bathrooms = bedrooms − 1, the model couldn't tell which one adds the value. The equations have no single answer, and the solver finds a zero where it needs to divide. It now refuses with an `IllegalArgumentException` instead of returning nonsense weights.

## 🪜 Steps

**Project: Nyokabi's price estimator** (the `day80/` folder is a finished version: build your own first, then compare).

1. `HouseFeatures` (with validation), `HouseData` and `Prediction` records.
2. `HousePriceModel.trainModel`: build `XᵀX` and `Xᵀy`, solve them, remember each feature's range.
3. `predictPrice`, `isWithinTrainingRange` and `describeTrainingRange`.
4. `TrainingDataLoader`: read the CSV and train at startup.
5. `PredictionController` (`POST /api/predict/house-price`): 400 for invalid input, 422 for a house outside the range.
6. `WebController` and `predict-form.html`: the same, as a form, with the price in KES.
7. Tests: the exact formula recovered, bigger houses cost more, untrained and empty models, the range check, unsolvable data, and the API and form.
8. Measure how good it is: train on 15 houses, predict the other 5, and print how far off each guess is.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day80
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>, or call the API:

```bash
curl -X POST localhost:8080/api/predict/house-price \
     -H "Content-Type: application/json" \
     -d '{"squareFeet":1800,"bedrooms":3,"bathrooms":2,"ageYears":10}'
```

## 🆘 Stuck?

- **The weights are `NaN` or huge**: two features move together in your training data, or one never changes. Look at the columns; the model now says so when it happens.
- **The recovered weights are slightly off**: floating point. Compare with a tolerance (`0.01`), not `==`.
- **`Model must be trained before it can predict`**: training happens in the `CommandLineRunner`. Check `training-data.csv` is in `src/main/resources`.
- **A price like `1.53E7` in the JSON**: that's a normal way to write 15,300,000 in JSON. Format it for people on the page, not in the API.
- **A sensible house is refused**: it's more than 10% outside a range in the training data. Add more varied sales, or widen `RANGE_MARGIN`, knowing the guesses get worse.

## ✅ Checklist
- [ ] Linear regression trained from a CSV with the normal equations
- [ ] Tested with data from a known formula
- [ ] A JSON API and a web form, both validated
- [ ] Houses outside the training range are refused with a reason
- [ ] Training data that can't be solved is refused
- [ ] Measured the error on houses the model didn't train on
- [ ] Committed code to Git

## ➡️ Next

Day 81 starts the portfolio projects: twenty apps of your own, beginning with a Morse code converter.
