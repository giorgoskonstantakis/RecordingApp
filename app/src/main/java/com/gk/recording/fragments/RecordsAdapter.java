package com.gk.recording.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gk.karaoke.R;
import com.gk.recording.utils.adapter_models.Record;

import java.util.ArrayList;

public class RecordsAdapter extends ArrayAdapter<Record> {

    private static class ViewHolder {
        TextView recordId;
        TextView recordName;
        TextView recordTimeDuration;
        TextView recordType;
        AppCompatButton recordPreview;
    }

    public RecordsAdapter(@NonNull Context context, @NonNull ArrayList<Record> records) {
        super(context, R.layout.fragment_record, records);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_record, parent, false);
            viewHolder.recordId = (TextView) convertView.findViewById(R.id.record_id);
            viewHolder.recordName = (TextView) convertView.findViewById(R.id.record_name);
            viewHolder.recordTimeDuration = (TextView) convertView.findViewById(R.id.record_time_duration);
            viewHolder.recordType = (TextView) convertView.findViewById(R.id.record_type);
            viewHolder.recordPreview = (AppCompatButton) convertView.findViewById(R.id.record_preview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.recordId.setText(record.idToString());
        viewHolder.recordName.setText(record.getName());
        viewHolder.recordTimeDuration.setText(record.timeDurationToString());
        viewHolder.recordType.setText(record.getRecordType());
        viewHolder.recordPreview.setText("PREVIEW");
        // Return the completed view to render on screen
        return convertView;
    }
}