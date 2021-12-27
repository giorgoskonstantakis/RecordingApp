package com.gk.recording.utils;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.gk.karaoke.R;

public class FragmentUtils {
    public static void changeCurrentFragment(FragmentActivity activity, int navigationAction, int messageId) {
        try {
            NavOptions.Builder navBuilder = new NavOptions.Builder();
            NavOptions navOptions = navBuilder.setPopUpTo(R.id.nav_graph, true).build();
            Navigation
                    .findNavController(activity, R.id.nav_host_fragment)
                    .navigate(navigationAction, new Bundle(), navOptions);
        } catch (Exception e) {
            e.printStackTrace();
            popupToast(activity.getApplicationContext(), messageId);
        }
    }

    public static void popupToast(Context context, int messageId) {
        try {
            Toast.makeText(context, context.getResources().getString(messageId), Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
