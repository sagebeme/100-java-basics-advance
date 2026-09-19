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
        // ageYears must vary across rows - if every row shared the same age, that column would be
        // a constant multiple of the bias column, making the normal-equations matrix singular.
        List<HouseData> trainingData = List.of(
                new HouseData(new HouseFeatures(1000, 2, 1, 12), 200000),
                new HouseData(new HouseFeatures(1500, 3, 2, 9), 280000),
                new HouseData(new HouseFeatures(2000, 3, 2, 15), 350000),
                new HouseData(new HouseFeatures(2500, 4, 3, 6), 420000),
                new HouseData(new HouseFeatures(3000, 4, 3, 20), 490000),
                new HouseData(new HouseFeatures(1800, 3, 2, 4), 320000)
        );

        HousePriceModel model = new HousePriceModel();
        model.trainModel(trainingData);

        double smaller = model.predictPrice(new HouseFeatures(1200, 3, 2, 10));
        double larger = model.predictPrice(new HouseFeatures(2800, 3, 2, 10));

        assertTrue(larger > smaller);
    }
}
