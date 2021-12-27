package com.gk.recording.utils;

import android.media.MediaPlayer;
import android.media.MediaTimestamp;

import androidx.annotation.Nullable;

import java.io.IOException;

public class RecordingPlayer extends MediaPlayer {
    public RecordingPlayer() {
    }

    @Override
    public void prepare() throws IOException, IllegalStateException {
        super.prepare();
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
    }

    @Override
    public boolean isPlaying() {
        return super.isPlaying();
    }

    @Nullable
    @Override
    public MediaTimestamp getTimestamp() {
        return super.getTimestamp();
    }

    @Override
    public int getCurrentPosition() {
        return super.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return super.getDuration();
    }

    @Override
    public void release() {
        super.release();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public String toString() {
        return "RecordingPlayer{}";
    }
}
