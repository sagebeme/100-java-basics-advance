# Day 80 - Capstone Project: Predict House Prices

## 📚 Learning Objectives
- Build complete ML application
- Integrate machine learning
- Create prediction system
- Build full-stack application
- Deploy ML model

## 🎯 Topics Covered
- Machine learning integration
- Model training
- Prediction API
- Full-stack development
- Model deployment

## 📝 Step-by-Step Instructions

### Step 1: Load and Train Model
Train prediction model:

```java
public class HousePriceModel {
    public void trainModel(List<HouseData> trainingData) {
        // Fit a linear regression to the training data (this project writes its own; Weka is one library that does it for you)
    }
    
    public double predictPrice(HouseFeatures features) {
        // Make prediction
    }
}
```

### Step 2: Create API
Build prediction API:

```java
@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    
    @PostMapping("/house-price")
    public ResponseEntity<Prediction> predictPrice(@RequestBody HouseFeatures features) {
        double price = model.predictPrice(features);
        return ResponseEntity.ok(new Prediction(price));
    }
}
```

## 🎮 Project: House Price Predictor

### Requirements
Build complete system:
1. Load training data
2. Train model
3. Create prediction API
4. Build web interface
5. Deploy application

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- Re-read **Step 1** (model training, prediction); **Step 2** (REST controller, @RequestBody, ResponseEntity)
- **Related days:** Day 79 (testing); Day 81 (portfolio). **Quick reference:** `mvn spring-boot:run`; POST to `/api/predict/house-price` with JSON body

## ✅ Checklist
- [ ] Trained ML model
- [ ] Created prediction API
- [ ] Built web interface
- [ ] Deployed application
- [ ] Completed house price predictor
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/PredictionController.java` | `POST /api/predict/house-price` returns a price as JSON |
| `controller/WebController.java` | `GET /` shows the form, `POST /predict` shows the prediction |
| `ml/HousePriceModel.java` | Multiple linear regression written from scratch (the normal equations, solved by Gaussian elimination). No ML library |
| `ml/TrainingDataLoader.java` | Trains the model from `training-data.csv` when the app starts |
| `model/HouseData.java` | One training row |
| `model/HouseFeatures.java` | The inputs: square feet, bedrooms, bathrooms, age |
| `model/HouseFeaturesForm.java` | The same inputs as a form-binding bean |
| `model/Prediction.java` | The predicted price |

Also in `src/main/resources/`: `templates/predict-form.html`, `training-data.csv`.

The model is trained from scratch, with no ML library, so you can see every step. Weka and similar libraries do the same job for bigger models.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day80
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day80
mvn test
```

Runs `HousePriceModelTest`, `PredictionControllerTest` and `WebControllerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Congratulations!** You've completed the Advanced section!

**Ready for Day 81?** You'll start portfolio projects!
