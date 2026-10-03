import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazoeziTest {

    private static final LocalDate SAT = LocalDate.of(2026, 10, 3);
    private WorkoutDb db;

    @BeforeEach
    void freshDatabase() throws SQLException {
        db = new WorkoutDb("jdbc:h2:mem:test" + System.nanoTime()); // a new, empty database for every test
        db.createTable();
    }

    @AfterEach
    void closeIt() throws SQLException {
        db.close();
    }

    @Test
    void parsesSeveralWorkoutsWithCalories() {
        List<Workout> w = Activities.parse("run 30, Skipping 15", SAT);
        assertEquals(2, w.size());
        assertEquals("skipping", w.get(1).activity());
        assertEquals(343, w.get(0).kcal());   // 9.8 × 70 kg × 0.5 h
        assertEquals(215, w.get(1).kcal());   // 12.3 × 70 × 0.25
    }

    @Test
    void explainsWhatsWrongWithBadInput() {
        assertTrue(assertThrows(IllegalArgumentException.class, () -> Activities.parse("netball 30", SAT)).getMessage().contains("Unknown activity"));
        assertThrows(IllegalArgumentException.class, () -> Activities.parse("run thirty", SAT));
        assertThrows(IllegalArgumentException.class, () -> Activities.parse("run", SAT));
        assertThrows(IllegalArgumentException.class, () -> Activities.parse("run 0", SAT));
    }

    @Test
    void createTableIsSafeToRunTwice() throws SQLException {
        db.createTable();
    }

    @Test
    void savedWorkoutsGetAnIdAndCanBeReadBack() throws SQLException {
        Workout saved = db.add(Workout.unsaved(SAT, "run", 30, 343));
        assertTrue(saved.id() > 0);
        assertEquals(List.of(saved), db.forDay(SAT));
        assertTrue(db.forDay(SAT.plusDays(1)).isEmpty());
    }

    @Test
    void totalsAddUpPerActivityWithinTheDates() throws SQLException {
        db.add(Workout.unsaved(LocalDate.of(2026, 9, 28), "run", 45, 515));
        db.add(Workout.unsaved(LocalDate.of(2026, 9, 30), "run", 45, 514));
        db.add(Workout.unsaved(SAT, "skipping", 15, 215));
        db.add(Workout.unsaved(LocalDate.of(2026, 9, 27), "run", 60, 686)); // last week
        List<ActivityTotal> week = db.totalsBetween(LocalDate.of(2026, 9, 28), LocalDate.of(2026, 10, 4));
        assertEquals(List.of(new ActivityTotal("run", 90, 1029), new ActivityTotal("skipping", 15, 215)), week);
    }

    @Test
    void deletesOneWorkout() throws SQLException {
        Workout w = db.add(Workout.unsaved(SAT, "walk", 20, 82));
        assertTrue(db.delete(w.id()));
        assertFalse(db.delete(w.id()));
        assertTrue(db.forDay(SAT).isEmpty());
    }

    @Test
    void theDatabaseRefusesZeroMinutesEvenIfTheJavaCheckIsSkipped() {
        assertThrows(SQLException.class, () -> db.add(Workout.unsaved(SAT, "run", 0, 0)));
    }
}
