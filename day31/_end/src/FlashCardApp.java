import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlashCardApp extends Application {
    private List<FlashCard> cards = new ArrayList<>();
    private int currentIndex = 0;
    private boolean showingAnswer = false;
    
    private Label cardLabel = new Label();
    private Button flipButton = new Button("Show Answer");
    private Button knownButton = new Button("I Know This");
    private Button unknownButton = new Button("I Don't Know");
    private Button nextButton = new Button("Next Card");
    private Button addButton = new Button("Add Card");
    private Label statusLabel = new Label();
    
    private TextField questionField = new TextField();
    private TextField answerField = new TextField();
    
    private static final String FILE_NAME = "flashcards.txt";
    
    @Override
    public void start(Stage stage) {
        cardLabel.setStyle("-fx-font-size: 18px; -fx-padding: 20; -fx-min-height: 100px;");
        cardLabel.setWrapText(true);
        
        flipButton.setOnAction(e -> flipCard());
        knownButton.setOnAction(e -> markKnown());
        unknownButton.setOnAction(e -> markUnknown());
        nextButton.setOnAction(e -> nextCard());
        addButton.setOnAction(e -> addCard());
        
        VBox cardBox = new VBox(10);
        cardBox.getChildren().addAll(
            cardLabel,
            flipButton,
            knownButton,
            unknownButton,
            nextButton,
            statusLabel
        );
        cardBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        VBox addBox = new VBox(10);
        addBox.getChildren().addAll(
            new Label("Question:"), questionField,
            new Label("Answer:"), answerField,
            addButton
        );
        addBox.setStyle("-fx-padding: 10;");
        
        Button loadButton = new Button("Load Cards");
        Button saveButton = new Button("Save Cards");
        loadButton.setOnAction(e -> loadCards());
        saveButton.setOnAction(e -> saveCards());
        
        VBox root = new VBox(10);
        root.getChildren().addAll(cardBox, addBox, loadButton, saveButton);
        
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Flash Card App");
        stage.setScene(scene);
        stage.show();
        
        loadCards();
        if (!cards.isEmpty()) {
            showCard();
        }
    }
    
    private void showCard() {
        if (cards.isEmpty()) {
            cardLabel.setText("No cards available. Add some cards!");
            return;
        }
        
        FlashCard card = cards.get(currentIndex);
        showingAnswer = false;
        cardLabel.setText("Question: " + card.question);
        flipButton.setText("Show Answer");
        statusLabel.setText("Card " + (currentIndex + 1) + " of " + cards.size());
    }
    
    private void flipCard() {
        if (cards.isEmpty()) return;
        
        FlashCard card = cards.get(currentIndex);
        showingAnswer = !showingAnswer;
        
        if (showingAnswer) {
            cardLabel.setText("Answer: " + card.answer);
            flipButton.setText("Show Question");
        } else {
            cardLabel.setText("Question: " + card.question);
            flipButton.setText("Show Answer");
        }
    }
    
    private void markKnown() {
        if (cards.isEmpty()) return;
        cards.get(currentIndex).known = true;
        statusLabel.setText("Marked as known");
    }
    
    private void markUnknown() {
        if (cards.isEmpty()) return;
        cards.get(currentIndex).known = false;
        statusLabel.setText("Marked as unknown");
    }
    
    private void nextCard() {
        if (cards.isEmpty()) return;
        currentIndex = (currentIndex + 1) % cards.size();
        showCard();
    }
    
    private void addCard() {
        String question = questionField.getText().trim();
        String answer = answerField.getText().trim();
        
        if (question.isEmpty() || answer.isEmpty()) {
            statusLabel.setText("Error: Both question and answer required!");
            return;
        }
        
        cards.add(new FlashCard(question, answer));
        questionField.clear();
        answerField.clear();
        statusLabel.setText("Card added! Total: " + cards.size());
        
        if (cards.size() == 1) {
            showCard();
        }
    }
    
    private void loadCards() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                statusLabel.setText("No saved cards found");
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            cards.clear();
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    FlashCard card = new FlashCard(parts[0], parts[1]);
                    if (parts.length > 2) {
                        card.known = Boolean.parseBoolean(parts[2]);
                    }
                    cards.add(card);
                }
            }
            reader.close();
            
            if (!cards.isEmpty()) {
                currentIndex = 0;
                showCard();
            }
            statusLabel.setText("Loaded " + cards.size() + " cards");
        } catch (IOException e) {
            statusLabel.setText("Error loading: " + e.getMessage());
        }
    }
    
    private void saveCards() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (FlashCard card : cards) {
                writer.println(card.question + "|" + card.answer + "|" + card.known);
            }
            writer.close();
            statusLabel.setText("Saved " + cards.size() + " cards");
        } catch (IOException e) {
            statusLabel.setText("Error saving: " + e.getMessage());
        }
    }
    
    private static class FlashCard {
        String question;
        String answer;
        boolean known = false;
        
        FlashCard(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

