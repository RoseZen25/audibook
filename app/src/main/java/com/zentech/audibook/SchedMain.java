package com.zentech.audibook;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static com.zentech.audibook.ui.AddEditAlarmActivity.ADD_ALARM;
import static com.zentech.audibook.ui.AddEditAlarmActivity.buildAddEditAlarmActivityIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zentech.audibook.activities.MainActivity;
import com.zentech.audibook.adapters.EventAdapter;
import com.zentech.audibook.database.DatabaseClass;
import com.zentech.audibook.database.EntityClass;
import com.zentech.audibook.util.AlarmUtils;

import java.util.List;

public class SchedMain extends AppCompatActivity implements View.OnClickListener {
    ImageView createEvent;
    EventAdapter eventAdapter;
    RecyclerView recyclerview;
    DatabaseClass databaseClass;
    private ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule);
        createEvent = findViewById(R.id.btn_createEvent);
        recyclerview = findViewById(R.id.recyclerviewSched);
        createEvent.setOnClickListener(this);
        databaseClass = DatabaseClass.getDatabase(getApplicationContext());

        button = (ImageView) findViewById(R.id.HomeSched);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        final FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener((View view) -> {
            AlarmUtils.checkAlarmPermissions(this);
            Intent intent =new Intent(buildAddEditAlarmActivityIntent(this, ADD_ALARM));
            startActivity(intent);
            overridePendingTransition(0, 0);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

    }

    private void setAdapter() {
        List<EntityClass> classList = databaseClass.EventDao().getAllData();
        eventAdapter = new EventAdapter(getApplicationContext(), classList);
        recyclerview.setAdapter(eventAdapter);
    }

    @Nullable
    @Override
    public void onClick(View view) {
        if (view == createEvent) {
            goToCreateEventActivity();
        }
    }

    private void goToCreateEventActivity() {
        Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
        startActivity(intent);
    }
    public  void openMainActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
