package com.gk.recording.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gk.karaoke.R;
import com.gk.recording.database.RecordingAppDatabase;
import com.gk.recording.database.dao.RecordingsDao;
import com.gk.recording.database.model.Recording;
import com.gk.recording.utils.AudioRecorder;
import com.gk.recording.utils.Timer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.gk.recording.utils.FragmentUtils.changeCurrentFragment;
import static com.gk.recording.utils.FragmentUtils.popupToast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AudioRecordingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioRecordingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AudioRecorder recorder;
    private RecordingState recordingState;

    private Timer timer;
    private Long startPauseBreak;

    private AppCompatButton moveToRecordingsList = null;
    private AppCompatButton moveToVideoRecording = null;
    private TextView messageChange = null;
    private AppCompatButton recordingButton = null;
    private AppCompatButton saveButton = null;
    private AppCompatButton deleteButton = null;
    private AppCompatButton resetButton = null;

    public AudioRecordingFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_recording, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recorder = new AudioRecorder();
        recordingState = RecordingState.INITIAL;
        timer = new Timer();

        moveToRecordingsList = view.findViewById(R.id.go_to_recordings_list);
        moveToVideoRecording = view.findViewById(R.id.go_to_video_recording);
        messageChange = view.findViewById(R.id.message_change);
        recordingButton = view.findViewById(R.id.recording_button);
        saveButton = view.findViewById(R.id.save_button);
        deleteButton = view.findViewById(R.id.delete_button);
        resetButton = view.findViewById(R.id.reset_button);

        recordingButton.setOnClickListener(v -> {
            onClickRecordingButton();
        });

        saveButton.setOnClickListener(v -> {
            onSaveRecording();
        });

        deleteButton.setOnClickListener(v -> {
            onDeleteRecording();
        });

        resetButton.setOnClickListener(v -> {
            onResetRecorder();
        });

        moveToRecordingsList.setOnClickListener(v -> {
            changeCurrentFragment(this.requireActivity(), R.id.action_audioRecordingFragment_to_recordsFragment, R.string.try_again_text);
        });

        moveToVideoRecording.setOnClickListener(v -> {
            changeCurrentFragment(this.requireActivity(), R.id.action_audioRecordingFragment_to_videoRecordingFragment, R.string.try_again_text);
        });
    }

    @Override
    public void onResume() {
        if(recordingState == RecordingState.PAUSE){
            changeUIOnRecordingStateChange(R.color.recording_continue,R.string.recording_continue,R.drawable.recording_button_continue);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(recordingState == RecordingState.RECORDING){
            recordingState = RecordingState.PAUSE;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void onStartRecording() {
        timer.setStartTimer();
        recorder.startRecording();
        changeUIOnRecordingStateChange(R.color.pause_recording,R.string.recording_message_pause,R.drawable.recording_button_reset);
        changeButtonsState(false);
        recordingState = RecordingState.RECORDING;
    }

    private void onPauseRecording(){
        startPauseBreak = System.currentTimeMillis();
        timer.setFinishTimer();
        recorder.pauseRecording();
        changeUIOnRecordingStateChange(R.color.recording_continue,R.string.recording_continue,R.drawable.recording_button_continue);
        recordingState = RecordingState.PAUSE;
    }

    private void onResumeRecording(){
        timer.addPauseBreakToDurations(startPauseBreak - System.currentTimeMillis());
        recorder.resumeRecording();
        changeUIOnRecordingStateChange(R.color.pause_recording,R.string.recording_message_pause,R.drawable.recording_button_reset);
        recordingState = RecordingState.RECORDING;
    }

    private void onResetRecorder(){
        timer.setFinishTimer();
        timer.getFinalTimeDuration();
        CompletableFuture.runAsync(() -> {
            RecordingAppDatabase db = Room.databaseBuilder(getContext(),
                    RecordingAppDatabase.class, "recording_db").build();
            RecordingsDao recordingsDao = db.recordingsDao();
            recordingsDao.insertRecording(recording);
        });
        recordingState = RecordingState.INITIAL;
        changeUIOnRecordingStateChange(R.color.recording_button,R.string.recording_message_start,R.drawable.recording_button);
        changeButtonsState(true);
        recorder.resetRecorder();
    }

    private void onSaveRecording(){
        recorder.saveRecording();
        onResetRecorder();
        popupToast(getContext(),R.string.save_recording_toast);
    }

    private void onDeleteRecording(){
        recorder.deleteRecording();
        onResetRecorder();
        popupToast(getContext(),R.string.delete_recording_toast);
    }

    private void onClickRecordingButton(){
        if(recordingState == RecordingState.INITIAL) {
            onStartRecording();
        } else if(recordingState == RecordingState.RECORDING){
            onPauseRecording();
        } else if(recordingState == RecordingState.PAUSE){
            onResumeRecording();
        }
    }

    private void changeUIOnRecordingStateChange(int newMessageTextColor,int newMessageText,int newRecordingButtonColor){
        swapTextColor(messageChange,newMessageTextColor);
        changeTextMessage(messageChange,newMessageText);
        swapButtonColor(recordingButton,newRecordingButtonColor);
    }

    private void changeButtonsState(boolean isEnabled){
        changeButtonState(saveButton,isEnabled);
        changeButtonState(deleteButton,isEnabled);
    }

    private void swapButtonColor(AppCompatButton button, int newBackground){
        button.setBackgroundResource(newBackground);
    }

    private void swapTextColor(TextView text,int newColor){
        text.setTextColor(getResources().getColor(newColor));
    }

    private void changeTextMessage(TextView text,int newTextId){
       text.setText(newTextId);
    }

    private void changeButtonState(AppCompatButton button,boolean isEnabled){
        try {
            if (isEnabled) {
                button.setEnabled(false);
            } else {
                button.setEnabled(true);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    enum RecordingState{
        INITIAL,RECORDING,PAUSE,
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudioRecordingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudioRecordingFragment newInstance(String param1, String param2) {
        AudioRecordingFragment fragment = new AudioRecordingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}