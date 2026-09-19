package com.learning.ml;

import com.learning.model.HouseData;
import com.learning.model.HouseFeatures;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingDataLoader implements CommandLineRunner {

    private final HousePriceModel housePriceModel;

    public TrainingDataLoader(HousePriceModel housePriceModel) {
        this.housePriceModel = housePriceModel;
    }

    @Override
    public void run(String... args) throws IOException {
        housePriceModel.trainModel(loadTrainingData("training-data.csv"));
    }

    public List<HouseData> loadTrainingData(String classpathFile) throws IOException {
        List<HouseData> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource(classpathFile).getInputStream(), StandardCharsets.UTF_8))) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                HouseFeatures features = new HouseFeatures(
                        Double.parseDouble(parts[0]),
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]));
                double price = Double.parseDouble(parts[4]);
                data.add(new HouseData(features, price));
            }
        }
        return data;
    }
}
