package com.portfolio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityLog {

    private final List<String> entries = new ArrayList<>();

    public void record(String message) {
        entries.add(LocalDateTime.now() + " - " + message);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
