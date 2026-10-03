import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

// The window. All the rules live in QuizLogic; this class only shows them and passes on clicks.
public class QuizApp extends Application {

    private QuizLogic quiz;
    private final Label progress = new Label("Loading questions…");
    private final Label score = new Label("Score 0");
    private final Label category = new Label();
    private final Label question = new Label();
    private final Button trueButton = new Button("True");
    private final Button falseButton = new Button("False");
    private final Button again = new Button("Play again");
    private final VBox card = new VBox(18);

    @Override
    public void start(Stage stage) {
        Label title = new Label("Quiz Night");
        title.getStyleClass().add("title");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        score.getStyleClass().add("score");
        HBox top = new HBox(12, title, spacer, score);
        top.setAlignment(Pos.CENTER_LEFT);

        progress.getStyleClass().add("progress");
        category.getStyleClass().add("category");
        question.getStyleClass().add("question");
        question.setWrapText(true);
        card.getStyleClass().add("card");
        card.getChildren().addAll(category, question);
        VBox.setVgrow(card, Priority.ALWAYS);

        trueButton.getStyleClass().addAll("answer", "true");
        falseButton.getStyleClass().addAll("answer", "false");
        trueButton.setOnAction(e -> choose(true));
        falseButton.setOnAction(e -> choose(false));
        again.getStyleClass().add("again");
        again.setOnAction(e -> loadQuestions());
        again.setVisible(false);
        again.setManaged(false);
        HBox answers = new HBox(14, trueButton, falseButton, again);
        answers.setAlignment(Pos.CENTER);
        HBox.setHgrow(trueButton, Priority.ALWAYS);
        HBox.setHgrow(falseButton, Priority.ALWAYS);
        trueButton.setMaxWidth(Double.MAX_VALUE);
        falseButton.setMaxWidth(Double.MAX_VALUE);

        Label hint = new Label("Keys: T for true, F for false");
        hint.getStyleClass().add("hint");

        VBox root = new VBox(16, top, progress, card, answers, hint);
        root.getStyleClass().add("root-pane");
        Scene scene = new Scene(root, 560, 520);
        scene.getStylesheets().add(getClass().getResource("/quiz.css").toExternalForm());
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.T) choose(true);
            if (e.getCode() == KeyCode.F) choose(false);
        });

        stage.setTitle("Quiz Night");
        stage.setScene(scene);
        stage.show();
        loadQuestions();
    }

    // The network call runs off the UI thread, so the window never freezes while it waits.
    private void loadQuestions() {
        setButtonsEnabled(false);
        again.setVisible(false);
        again.setManaged(false);
        progress.setText("Loading questions…");
        Task<List<Question>> task = new Task<>() {
            @Override
            protected List<Question> call() {
                return TriviaSource.load();
            }
        };
        task.setOnSucceeded(e -> {
            quiz = new QuizLogic(task.getValue());
            score.setText("Score 0");
            showQuestion();
        });
        Thread loader = new Thread(task, "trivia-loader");
        loader.setDaemon(true);
        loader.start();
    }

    private void showQuestion() {
        card.getStyleClass().removeAll("right", "wrong");
        if (quiz.isFinished()) {
            category.setText("Final score");
            question.setText(quiz.score() + " / " + quiz.total() + "\n" + quiz.verdict());
            progress.setText("All done");
            setButtonsEnabled(false);
            trueButton.setVisible(false);
            falseButton.setVisible(false);
            again.setVisible(true);
            again.setManaged(true);
            return;
        }
        trueButton.setVisible(true);
        falseButton.setVisible(true);
        category.setText(quiz.current().category().toUpperCase());
        question.setText(quiz.current().text());
        progress.setText(quiz.progress());
        setButtonsEnabled(true);
    }

    private void choose(boolean guess) {
        if (quiz == null || quiz.isFinished() || trueButton.isDisabled()) return;
        boolean right = quiz.answer(guess);
        score.setText("Score " + quiz.score());
        card.getStyleClass().add(right ? "right" : "wrong");
        setButtonsEnabled(false);
        PauseTransition pause = new PauseTransition(Duration.millis(700));
        pause.setOnFinished(e -> showQuestion());
        pause.play();
    }

    private void setButtonsEnabled(boolean enabled) {
        trueButton.setDisable(!enabled);
        falseButton.setDisable(!enabled);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
