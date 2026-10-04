import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

// The bot's decision, kept apart from the browser so it can be tested in milliseconds.
public class EventPicker {

    // The cheapest event on or after `from` that costs at most `budget` per ticket; the earliest wins a tie.
    public static Optional<EventCard> pick(List<EventCard> events, int budget, LocalDate from) {
        // TODO
        return Optional.empty();
    }
}
