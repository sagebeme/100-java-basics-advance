package com.learning.ml;

import com.learning.model.HouseData;
import com.learning.model.HouseFeatures;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HousePriceModelTest {

    @Test
    void predictingBeforeTrainingThrows() {
        HousePriceModel model = new HousePriceModel();
        assertThrows(IllegalStateException.class, () -> model.predictPrice(new HouseFeatures(1500, 3, 2, 10)));
    }

    @Test
    void trainingOnAnEmptyDatasetThrows() {
        HousePriceModel model = new HousePriceModel();
        assertThrows(IllegalArgumentException.class, () -> model.trainModel(List.of()));
    }

    @Test
    void recoversTheExactCoefficientsOfANoiseFreeLinearDataset() {
        // price = 50000 + 100*squareFeet + 5000*bedrooms + 8000*bathrooms - 1000*ageYears
        List<HouseData> trainingData = new ArrayList<>();
        double[][] rows = {
                {1000, 2, 1, 5}, {1500, 3, 2, 10}, {2000, 4, 2, 0}, {1200, 2, 1, 20},
                {2500, 5, 3, 3}, {1800, 3, 2, 15}, {900, 1, 1, 30}, {3000, 5, 4, 1},
        };
        for (double[] row : rows) {
            HouseFeatures features = new HouseFeatures(row[0], (int) row[1], (int) row[2], row[3]);
            double price = 50000 + 100 * row[0] + 5000 * row[1] + 8000 * row[2] - 1000 * row[3];
            trainingData.add(new HouseData(features, price));
        }

        HousePriceModel model = new HousePriceModel();
        model.trainModel(trainingData);

        double predicted = model.predictPrice(new HouseFeatures(1700, 3, 2, 8));
        double expected = 50000 + 100 * 1700 + 5000 * 3 + 8000 * 2 - 1000 * 8;
        assertEquals(expected, predicted, 0.01);
    }

    @Test
    void largerHousesPredictHigherPricesThanSmallerOnesAllElseEqual() {
        // Every feature must vary on its own: if every row shared the same age, or bathrooms were
        // always bedrooms - 1, the model couldn't tell those effects apart, and training refuses.
        List<HouseData> trainingData = List.of(
                new HouseData(new HouseFeatures(1000, 2, 1, 12), 200000),
                new HouseData(new HouseFeatures(1500, 3, 1, 9), 270000),
                new HouseData(new HouseFeatures(2000, 3, 2, 15), 350000),
                new HouseData(new HouseFeatures(2500, 4, 2, 6), 410000),
                new HouseData(new HouseFeatures(3000, 4, 3, 20), 490000),
                new HouseData(new HouseFeatures(1800, 3, 2, 4), 320000)
        );

        HousePriceModel model = new HousePriceModel();
        model.trainModel(trainingData);

        double smaller = model.predictPrice(new HouseFeatures(1200, 3, 2, 10));
        double larger = model.predictPrice(new HouseFeatures(2800, 3, 2, 10));

        assertTrue(larger > smaller);
    }

    @Test
    void knowsWhichHousesAreInsideTheRangeItLearnedFrom() {
        HousePriceModel model = new HousePriceModel();
        List<HouseData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 1,000-1,900 sq ft, 1-3 bedrooms, 1-2 bathrooms, 0-27 years old, in a mixed order
            data.add(new HouseData(new HouseFeatures(1000 + 100 * i, 1 + i % 3, 1 + i % 2, (7 * i) % 30), 50_000 + 100 * i));
        }
        model.trainModel(data);

        assertTrue(model.isWithinTrainingRange(new HouseFeatures(1500, 2, 1, 10)));
        assertTrue(model.isWithinTrainingRange(new HouseFeatures(1950, 3, 2, 29)), "a little past the edge is allowed");
        assertFalse(model.isWithinTrainingRange(new HouseFeatures(1500, 2, 1, 400)));
        assertFalse(model.isWithinTrainingRange(new HouseFeatures(9000, 2, 1, 10)));
        assertFalse(model.isWithinTrainingRange(new HouseFeatures(1500, 9, 1, 10)));
    }

    @Test
    void refusesTrainingDataWhereTwoFeaturesAlwaysMoveTogether() {
        // Every house is 10 years old, so the model can't tell age's effect from the constant term.
        List<HouseData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new HouseData(new HouseFeatures(1000 + 100 * i, 1 + i % 3, 1 + i % 2, 10), 50_000 + 100 * i));
        }
        assertThrows(IllegalArgumentException.class, () -> new HousePriceModel().trainModel(data));
    }
}
