# Day 100 - Portfolio Project: Machine Learning Integration

## Learning Objectives
- Integrate machine learning with Java
- Build a regression model yourself, then know when to reach for a library (Weka, Deeplearning4j, TensorFlow Java)
- Build a predictive model
- Create a complete application
- Deploy ML models

## Topics Covered
- Machine Learning basics
- Model training and evaluation
- Data preprocessing
- Model deployment
- Performance optimization

## Project: Earnings Prediction System

Build a system that predicts earnings based on multiple variables using machine learning.

### Requirements
- Load and preprocess data
- Train a regression model
- Evaluate model performance
- Create predictions
- Build a REST API for predictions
- Create a web interface
- Deploy the application

### Features
- **Data Loading**: Load CSV/JSON datasets
- **Preprocessing**: Clean and normalize data
- **Model Training**: Train regression model
- **Prediction API**: REST endpoint for predictions
- **Web Interface**: User-friendly prediction form
- **Model Evaluation**: Display accuracy metrics

## Code Structure
```
day100/
├── README.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/learning/
    │   │   ├── Application.java
    │   │   ├── controller/
    │   │   │   ├── PredictionRestController.java
    │   │   │   └── PredictionWebController.java
    │   │   ├── model/
    │   │   │   ├── EarningsRecord.java
    │   │   │   ├── EarningsRequest.java
    │   │   │   ├── EvaluationMetrics.java
    │   │   │   └── PredictionResult.java
    │   │   └── service/
    │   │       ├── EarningsDataLoader.java
    │   │       ├── EarningsPredictionService.java
    │   │       ├── FeatureEncoder.java
    │   │       ├── LinearRegressionModel.java
    │   │       └── ModelEvaluator.java
    │   └── resources/
    │       ├── earnings-data.csv
    │       └── templates/prediction.html
    └── test/java/com/learning/   (the tests)
```

## Key Concepts
- **Machine Learning**: Algorithms that learn from data
- **Regression**: Predicting continuous values
- **Feature Engineering**: Creating meaningful features
- **Model Evaluation**: Metrics like R², MSE, MAE
- **Model Deployment**: Making models production-ready

## Example Prediction Request
```json
{
  "experience": 5,
  "education": "Bachelor",
  "location": "Urban",
  "industry": "Technology"
}
```

## Example Response
```json
{
  "predictedEarnings": 75000,
  "confidence": 0.85,
  "modelVersion": "1.0"
}
```

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project**, **Code Structure**, and **Key Concepts** above; use main README for structure. **Quick reference:** `mvn spring-boot:run`; POST to prediction API with JSON body; see Day 80 for ML/API pattern.

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/PredictionRestController.java` | `POST /api/predict` with JSON |
| `controller/PredictionWebController.java` | `GET /` shows the form, `POST /predict` shows the result |
| `model/EarningsRecord.java` | One training row |
| `model/EarningsRequest.java` | The inputs, for both the API and the form |
| `model/EvaluationMetrics.java` | R², MSE, MAE and the train/test sizes |
| `model/PredictionResult.java` | The predicted earnings, a confidence score and the model version |
| `service/EarningsDataLoader.java` | Loads the 150 rows of `earnings-data.csv` |
| `service/EarningsPredictionService.java` | Trains the model at start-up and makes predictions |
| `service/FeatureEncoder.java` | One-hot encodes education, location and industry |
| `service/LinearRegressionModel.java` | Multiple linear regression written from scratch, like Day 80's |
| `service/ModelEvaluator.java` | A repeatable train/test split, and R², MSE and MAE on the test set |

Also in `src/main/resources/`: `earnings-data.csv`, `templates/prediction.html`.

The model is written from scratch (no Weka, Deeplearning4j or TensorFlow), using the same linear regression as Day 80, plus one-hot encoding for the text fields. It's judged on a held-out test set.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day100
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day100
mvn test
```

Runs `EarningsPredictionServiceTest`, `FeatureEncoderTest`, `LinearRegressionModelTest`, `ModelEvaluatorTest`, `PredictionRestControllerTest` and `PredictionWebControllerTest`. A clean run ends with `BUILD SUCCESS`.

## Congratulations! 🎉

You've completed 100 days of Java! You should now:
- Have a strong foundation in Java
- Understand OOP principles
- Be able to build web applications
- Work with databases and APIs
- Deploy applications
- Integrate advanced features

## Next Steps
- Continue building projects
- Contribute to open source
- Learn advanced frameworks
- Specialize in areas of interest
- Build your portfolio

---

**Congratulations on completing 100 Days of Java! ☕**
