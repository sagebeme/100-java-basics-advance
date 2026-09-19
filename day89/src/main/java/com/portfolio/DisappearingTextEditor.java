package com.portfolio;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The core rule of the app: if you stop typing for longer than idleTimeout, whatever you haven't
 * saved disappears. Depends on an injected Clock rather than reading the system clock directly so
 * idle behavior can be tested deterministically instead of relying on real sleeps.
 *
 * setContent is meant to be called with the editor's full current text on every keystroke (e.g.
 * from a text field's change listener), not appended piece by piece - that keeps this class in
 * sync with whatever the UI is actually showing.
 */
public class DisappearingTextEditor {

    private final Clock clock;
    private final Duration idleTimeout;
    private final List<SavedEntry> savedEntries = new ArrayList<>();
    private String content = "";
    private Instant lastActivity;

    public DisappearingTextEditor(Clock clock, Duration idleTimeout) {
        this.clock = clock;
        this.idleTimeout = idleTimeout;
        this.lastActivity = clock.instant();
    }

    public void setContent(String text) {
        content = text == null ? "" : text;
        lastActivity = clock.instant();
    }

    public String getContent() {
        return content;
    }

    public boolean isIdle() {
        return Duration.between(lastActivity, clock.instant()).compareTo(idleTimeout) >= 0;
    }

    public Duration timeUntilDisappears() {
        Duration elapsed = Duration.between(lastActivity, clock.instant());
        Duration remaining = idleTimeout.minus(elapsed);
        return remaining.isNegative() ? Duration.ZERO : remaining;
    }

    /**
     * If the editor has gone idle, clears the content and reports that it disappeared. Call this
     * periodically (e.g. from a UI timer tick) rather than automatically on every read, so the
     * "disappearing" moment is an explicit, observable event.
     */
    public boolean clearIfIdle() {
        if (isIdle() && !content.isEmpty()) {
            content = "";
            return true;
        }
        return false;
    }

    /**
     * Saves the current content as a permanent entry. Saving counts as activity, since deciding
     * to save is itself engaging with the app.
     */
    public SavedEntry save() {
        if (content.isEmpty()) {
            throw new IllegalStateException("Nothing to save");
        }
        SavedEntry entry = new SavedEntry(content, clock.instant());
        savedEntries.add(entry);
        lastActivity = clock.instant();
        return entry;
    }

    public List<SavedEntry> getSavedEntries() {
        return Collections.unmodifiableList(savedEntries);
    }

    public void clear() {
        content = "";
        lastActivity = clock.instant();
    }
}
