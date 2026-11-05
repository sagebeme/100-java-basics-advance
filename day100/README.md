# Day 100 - Portfolio Project: Machine Learning Integration

> The last day puts everything together: data, a model you write yourself, honest testing, a web form, a JSON API, and the judgement to say "I don't know" when a question is outside what the model learned.

## 📖 The story

Chebet runs the careers office at a college in Eldoret. Every term, students ask the same thing: "If I study this, what will I earn?" She has a spreadsheet of 150 people's monthly pay, with their years of experience, education, whether they work in a town, a suburb or the countryside, and their industry. She wants a tool where a student picks those four things and gets a figure, with a straight answer about how far to trust it.

| Field | Values in the data |
|---|---|
| experience | 0 to 25 years |
| education | HighSchool, Bachelor, Master, PhD |
| location | Rural, Suburban, Urban |
| industry | Education, Finance, Healthcare, Retail, Technology |
| earnings | KES 39,889 to 177,895 a month |

(The data is made up for practice, so the model fits it unusually well. Real pay data is much messier.)

With the bundled data, 5 years, a Bachelor's, Urban, Technology gives about **KES 104,254** a month; 3 years, HighSchool, Rural, Retail about **KES 36,836**. On 30 people it never saw while training, the model was off by **KES 3,599** on average (R² 0.98).

## 🎯 Learning Objectives
- Turn text categories into numbers with one-hot encoding, and avoid the dummy-variable trap
- Train multiple linear regression from scratch (the normal equations, as on Day 80)
- Judge a model on a held-out test set: R², MSE and MAE
- Refuse questions the model can't answer: unknown categories and inputs beyond the data
- Serve one model through both a web form and a JSON API, with validation

## 🧰 What you'll use

**One-hot encoding.** A model can only multiply numbers, so "Master" becomes columns of 0s and 1s, one per value. Leave one value out of each field (the *reference*, here the first alphabetically): its effect is folded into the bias. Keeping every column makes them add up to the bias column, the matrix can't be solved, and that's called the *dummy-variable trap*.

```
education = Master   →  [Bachelor=0 is the reference] HighSchool=0, Master=1, PhD=0
```

**Unknown values.** If a request says `"education": "Diploma"`, every education column is 0, which is exactly how the reference category looks. The model would quietly answer for a Bachelor's. So the encoder refuses values it hasn't seen, and the API answers:

```json
{ "status": 422, "title": "Unprocessable Content",
  "detail": "Unknown education 'Diploma'. Choose one of: Bachelor, HighSchool, Master, PhD." }
```

**Beyond the data.** A straight line keeps going forever: ask about 60 years of experience and it will answer. Nobody in the data has more than 25, so the app says so instead: "This model learned from people with 0-25 years of experience, so it can't predict 60 years."

**Train, then test on data it hasn't seen.** A seeded shuffle splits the 150 rows 120/30. A model trained on the 120 is scored on the 30:

- **MAE**: the average miss, in shillings. The easiest to explain.
- **MSE**: the average squared miss, which punishes big misses more.
- **R²**: how much of the variation the model explains, from 0 (no better than guessing the average) to 1.

Then the model used for predictions is trained on all 150, since the testing is done. The page shows the test scores and calls the figure "a typical figure for people like this in the data, not a promise". R² is one number for the whole model, so the API calls it `modelR2`, not a per-prediction "confidence".

**Collinear features.** If one column can be worked out from others, the normal equations have no single answer. The solver checks each pivot and throws a clear error instead of dividing by almost zero.

## 🪜 Steps

**Your brief** (the `day100/` folder is one finished version: build your own first, then compare).

1. `EarningsRecord`, the loader and `earnings-data.csv`.
2. `FeatureEncoder`: fit on the data, encode with a bias and reference categories, refuse unknown values.
3. `LinearRegressionModel`: the normal equations with partial pivoting and a collinearity check.
4. `ModelEvaluator`: the seeded split, and R², MSE and MAE.
5. `EarningsPredictionService`: train at start-up, check each request, predict.
6. `EarningsRequest` with validation, `POST /api/predict` (400 for a blank field, 422 for a question it can't answer), and the form at `/`.
7. Tests: exact weights on noise-free data, collinear data, the encoder, the split, the metrics, sensible directions (more experience, more pay), and both controllers, including refusals.
8. Make it yours: show a range (prediction ± the MAE) instead of one number, add a field and see whether the test score improves, or retrain from an uploaded CSV.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day100
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>, or call the API:

```bash
curl -s -H 'Content-Type: application/json' \
  -d '{"experience":5,"education":"Bachelor","location":"Urban","industry":"Technology"}' \
  localhost:8080/api/predict
```

```json
{"predictedEarnings":104254.14246402957,"modelR2":0.9821202104911996,"modelVersion":"1.0"}
```

## 🆘 Stuck?

- **Weights come out as `NaN` or huge numbers**: a column for every category (the dummy-variable trap) or a duplicated feature. Drop one category per field.
- **The test score is perfect**: you scored the model on the rows it trained on. Split first, and fit the model on the training part only.
- **The score changes every run**: the shuffle isn't seeded. Use `new Random(42)`.
- **An unknown value gets a confident answer**: check it against the categories the encoder learned before encoding.
- **The form loses its dropdowns after an error**: add the options to the model on every path, not only on `GET`.

## ✅ Checklist
- [ ] One-hot encoding with a reference category
- [ ] Linear regression from scratch, with a collinearity check
- [ ] Scores on a held-out test set
- [ ] Unknown values and out-of-range input refused, not guessed
- [ ] A form and a JSON API on one model
- [ ] Added a range, a new field or retraining from an upload
- [ ] Committed code to Git

## 🎉 You made it

A hundred days ago this course started with `System.out.println`. Since then you've written classes and collections, threads and streams, web apps with databases and logins, tests for all of it, Docker images, and a model you understand line by line.

What next:

- **Polish three projects** into your portfolio: a README with screenshots, tests that pass, and a link that works. Days 80, 96 and 98 make a good spread.
- **Build something for someone you know**: a chama's contributions tracker, a shop's stock list, a school's fees records. Real users find real bugs.
- **Read other people's code**: pick a small open-source Java project, fix a typo or a test, and open a pull request.
- **Go deeper in one direction**: Spring Security and OAuth, PostgreSQL and query plans, Kubernetes, or Android with Kotlin.

Hongera, and keep building.

## ➡️ Next: Part 2

**[Box Office in 30 Days](https://github.com/sagebeme/box-office-30-days)** picks up at Day 101. Instead of a new project every day, you build one real app for 30 days: an offline-first gate app for a weekend event at Ngong Racecourse. It keeps selling when the Wi-Fi drops, never half-saves a sale when the power goes, checks tickets in with a USB scanner, and later syncs to a server and takes M-Pesa. Every day ships with tests, and the app still runs at the end of each one.

Start on [the course website](https://sagebeme.github.io/box-office-30-days/site/) or straight from the repo.
