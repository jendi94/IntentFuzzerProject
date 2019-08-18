package com.jendi.intentfuzzer.activitites;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jendi.intentfuzzer.PackageInfoAdapter;
import com.jendi.intentfuzzer.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PackageInfoAdapter packageInfoAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(PackageManager.GET_SERVICES
                                                                                     | PackageManager.GET_ACTIVITIES
                                                                                     | PackageManager.GET_RECEIVERS);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        packageInfoAdapter = new PackageInfoAdapter(packageInfos, this);
        recyclerView.setAdapter(packageInfoAdapter);
    }

}
