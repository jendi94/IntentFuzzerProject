package com.jendi.intentfuzzer.activitites;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jendi.intentfuzzer.AppComponentAdapter;
import com.jendi.intentfuzzer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PackageInfo info;
    private TextView packageNameTextView;
    private ImageView packageLabelImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_info);

        Intent intent = getIntent();
        info = intent.getParcelableExtra("info");

        packageLabelImageView = findViewById(R.id.imageView);
        packageLabelImageView.setImageDrawable(info.applicationInfo.loadIcon(getPackageManager()));
        packageNameTextView = findViewById(R.id.textView);
        packageNameTextView.setText(info.applicationInfo.loadLabel(getPackageManager()));
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recyclerViewPackageInfo);


        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                                                R.array.app_components,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                recyclerView.setAdapter(null);
                break;
            case 1:
                ActivityInfo[] activityInfos = info.activities;
                if (activityInfos != null) {
                    AppComponentAdapter adapter = new AppComponentAdapter(Arrays.asList(activityInfos));
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setAdapter(null);
                }
                break;

            case 2:
                ServiceInfo[] serviceInfos = info.services;
                if (serviceInfos != null) {
                    AppComponentAdapter adapter = new AppComponentAdapter(Arrays.asList(serviceInfos));
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setAdapter(null);
                }
                break;

            case 3:
                ProviderInfo[] componentInfos = info.providers;
                if (componentInfos != null) {
                    AppComponentAdapter adapter = new AppComponentAdapter(Arrays.asList(componentInfos));
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
