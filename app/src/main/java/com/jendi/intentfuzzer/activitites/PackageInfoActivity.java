package com.jendi.intentfuzzer.activitites;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jendi.intentfuzzer.Helper;
import com.jendi.intentfuzzer.R;
import com.jendi.intentfuzzer.adapters.AppComponentAdapter;

import java.util.Arrays;

public class PackageInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private PackageInfo info;
    private Button buttonFuzzAll;
    private TextView textViewExceptionInfo;
    private int adapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_info);

        Intent intent = getIntent();
        info = intent.getParcelableExtra("info");

        ImageView packageLabelImageView = findViewById(R.id.imageView);
        TextView packageNameTextView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recyclerViewPackageInfo);
        buttonFuzzAll = findViewById(R.id.buttonFuzzAll);
        textViewExceptionInfo = findViewById(R.id.textViewExceptionContent);
        Spinner spinner = findViewById(R.id.spinner);

        packageLabelImageView.setImageDrawable(info.applicationInfo.loadIcon(getPackageManager()));
        packageNameTextView.setText(info.applicationInfo.loadLabel(getPackageManager()));

        textViewExceptionInfo.setMovementMethod(new ScrollingMovementMethod());
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                                R.array.app_components,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final Activity activity = this;
        buttonFuzzAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (adapterPosition) {
                    case 2:
                        ServiceInfo[] serviceInfos = info.services;
                        for (ServiceInfo info : serviceInfos) {
                            try {
                                Intent intentToSend = new Intent();
                                intentToSend.setClassName(info.applicationInfo.packageName,
                                                          info.name);
                                v.getContext().startService(intentToSend);
                            } catch (SecurityException e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            } catch (Exception e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            }
                        }
                        break;
                    case 3:
                        ActivityInfo[] receiverInfo = info.receivers;
                        for (ActivityInfo info : receiverInfo) {
                            try {
                                Intent intentToSend = new Intent();
                                intentToSend.setClassName(info.applicationInfo.packageName,
                                                          info.name);
                                v.getContext().startActivity(intentToSend);
                            } catch (SecurityException e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            } catch (Exception e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            }
                        }
                        break;
                    case 1:
                        ActivityInfo[] activityInfo = info.activities;
                        for (ActivityInfo info : activityInfo) {
                            try {
                                Intent intentToSend = new Intent();
                                intentToSend.setClassName(info.applicationInfo.packageName,
                                                          info.name);
                                v.getContext().startActivity(intentToSend);
                            } catch (SecurityException e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            } catch (Exception e) {
                                Helper.updateLogScreen(info, e.getMessage(), activity, textViewExceptionInfo);
                            }
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                adapterPosition = position;
                buttonFuzzAll.setEnabled(false);
                recyclerView.setAdapter(null);
                break;
            case 1:
            case 3:
                adapterPosition = position;
                buttonFuzzAll.setEnabled(true);
                ActivityInfo[] activityInfo;
                if (position == 1) {
                    activityInfo = info.activities;
                } else {
                    activityInfo = info.receivers;
                }
                if (activityInfo != null) {
                    AppComponentAdapter adapter = new AppComponentAdapter(Arrays.asList(activityInfo), this);
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setAdapter(null);
                }
                break;
            case 2:
                adapterPosition = position;
                buttonFuzzAll.setEnabled(true);
                ServiceInfo[] serviceInfo = info.services;
                if (serviceInfo != null) {
                    AppComponentAdapter adapter = new AppComponentAdapter(Arrays.asList(serviceInfo), this);
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setAdapter(null);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
