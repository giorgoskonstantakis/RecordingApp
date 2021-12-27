package com.gk.recording.utils;

import android.media.MediaRecorder;
import java.io.IOException;

public class AudioRecorder {

    private MediaRecorder audioRecorder;

    public AudioRecorder() {
    }

    private void newMediaRecorderInstance() {
        if (this.audioRecorder == null) {
            this.audioRecorder = new MediaRecorder();
        }
    }

    public void resetRecorder() {
        try {
            stopRecording();
            this.audioRecorder.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRecording() {
        try {
            this.newMediaRecorderInstance();
            this.audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            this.audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            try {
                this.audioRecorder.setOutputFile(this.getFilename("", ""));
            } catch (Exception e){
                e.printStackTrace();
            }
            this.audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                this.audioRecorder.prepare();
                this.audioRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void pauseRecording(){
        try{
            this.audioRecorder.pause();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resumeRecording(){
        try{
            this.audioRecorder.resume();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        try {
            this.audioRecorder.stop();
            this.audioRecorder.release();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private String getFilename(String directory,String filename){
        String fileName = directory + "/" + filename;
        return fileName;
    }

    public void saveRecording(){
    }

    public void deleteRecording(){
        this.resetRecorder();
    }

}
