import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreakGardenTest {

    private static final LocalDate TODAY = LocalDate.of(2026, 10, 3); // a Saturday

    private static Habit habit(String... days) {
        Habit h = new Habit("Java practice");
        for (String d : days) h.markDone(LocalDate.parse(d));
        return h;
    }

    @Test
    void countsTheCurrentStreakIncludingToday() {
        assertEquals(3, habit("2026-10-01", "2026-10-02", "2026-10-03").currentStreak(TODAY));
    }

    @Test
    void notDoneYetTodayDoesNotBreakTheStreak() {
        assertEquals(2, habit("2026-10-01", "2026-10-02").currentStreak(TODAY));
    }

    @Test
    void aMissedDayYesterdayEndsTheStreak() {
        assertEquals(0, habit("2026-10-01").currentStreak(TODAY));
    }

    @Test
    void findsTheLongestRunAnywhere() {
        Habit h = habit("2026-09-01", "2026-09-02", "2026-09-03", "2026-09-04", "2026-09-10", "2026-09-30", "2026-10-01");
        assertEquals(4, h.longestStreak());
        assertEquals(0, new Habit("x").longestStreak());
    }

    @Test
    void ratesTheLastThirtyDays() {
        Habit h = new Habit("x");
        for (int i = 0; i < 24; i++) h.markDone(TODAY.minusDays(i));
        h.markDone(TODAY.minusDays(40)); // outside the window
        assertEquals(80, h.rate(TODAY, 30));
    }

    @Test
    void drawsWeeksAsColumnsWithFutureDaysBlank() {
        Habit h = habit("2026-09-28", "2026-09-30", "2026-10-03"); // Mon, Wed and Sat of this week
        String garden = Garden.render(h, TODAY, 2);
        String[] rows = garden.split("\n");
        assertEquals(7, rows.length);
        assertEquals("     Mon  · ✿", rows[0]);
        assertEquals("     Tue  · ·", rows[1]);
        assertEquals("     Wed  · ✿", rows[2]);
        assertEquals("     Sat  · ✿", rows[5]);
        assertEquals("     Sun  ·", rows[6]); // tomorrow is blank, and trailing spaces are trimmed
    }

    @Test
    void savesAndLoadsHabits() throws IOException {
        Path file = Files.createTempFile("habits", ".json");
        Map<String, Habit> habits = new LinkedHashMap<>();
        habits.put("Java practice", habit("2026-10-01", "2026-10-02"));
        habits.put("Read 10 pages", new Habit("Read 10 pages"));
        HabitStore.save(habits, file);

        Map<String, Habit> loaded = HabitStore.load(file);
        assertEquals(2, loaded.size());
        assertTrue(loaded.get("Java practice").isDone(LocalDate.of(2026, 10, 2)));
        assertEquals(0, loaded.get("Read 10 pages").days().size());
        Files.delete(file);
    }

    @Test
    void aMissingFileMeansNoHabitsYet() throws IOException {
        assertTrue(HabitStore.load(Path.of("no-such-file-" + System.nanoTime() + ".json")).isEmpty());
    }

    @Test
    void aHabitNeedsAName() {
        assertThrows(IllegalArgumentException.class, () -> new Habit("  "));
    }
}
