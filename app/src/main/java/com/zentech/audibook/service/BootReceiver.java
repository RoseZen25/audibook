package com.zentech.audibook.service;

import static android.content.Intent.ACTION_BOOT_COMPLETED;

import static com.zentech.audibook.service.AlarmReceiver.setReminderAlarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zentech.audibook.data.DatabaseHelper;
import com.zentech.audibook.model.Alarm;

import java.util.List;
import java.util.concurrent.Executors;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Executors.newSingleThreadExecutor().execute(() -> {
                final List<Alarm> alarms = DatabaseHelper.getInstance(context).getAlarms();
                setReminderAlarms(context, alarms);
            });
        }
    }

}