package com.portfolio;

import java.util.List;

public interface Strategy {
    Move nextMove(List<RoundResult> history);
}
