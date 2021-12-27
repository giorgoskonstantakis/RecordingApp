package com.gk.recording.utils;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    private List<Long> durations;
    private Long startTimer;
    private Long finishTimer;

    public void setStartTimer() {
        this.startTimer = this.getCurrentTime();
    }

    public void setFinishTimer() {
        this.finishTimer = this.getCurrentTime();
    }

    public Timer() {
        this.durations = new ArrayList<>();
    }

    private Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public void addPauseBreakToDurations(Long pauseBreak) {
        this.durations.add(pauseBreak);
    }

    public Long getFinalTimeDuration() {
        int i;
        Long totalTimeDuration = this.finishTimer - this.startTimer;
        Long finalTimeDuration = totalTimeDuration;
        int durationsSize = durations.size();
        if (durationsSize > 0) {
            for (i = 0; i < durationsSize; i++) {
                finalTimeDuration = finalTimeDuration - durations.get(i);
            }
        }
        return finalTimeDuration;
    }
}
