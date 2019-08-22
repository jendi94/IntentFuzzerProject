package com.jendi.intentfuzzer.activitites;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.jendi.intentfuzzer.R;
import com.jendi.intentfuzzer.adapters.PackageInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private List<PackageInfo> packageInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText2);

        PackageManager packageManager = getPackageManager();
        packageInfos = packageManager.getInstalledPackages(PackageManager.GET_SERVICES
                                                                                     | PackageManager.GET_ACTIVITIES
                                                                                     | PackageManager.GET_RECEIVERS);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PackageInfoAdapter(packageInfos));

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editText.getText().toString().isEmpty()) {
                    List<PackageInfo> packageInfosFiltered = new ArrayList<>();
                    String filter = editText.getText().toString();
                    for (PackageInfo info : packageInfos) {
                        if (info.applicationInfo.loadLabel(getPackageManager()).toString().toLowerCase().contains(filter)) {
                            packageInfosFiltered.add(info);
                        }
                    }
                    recyclerView.setAdapter(new PackageInfoAdapter(packageInfosFiltered));
                } else {
                    recyclerView.setAdapter(new PackageInfoAdapter(packageInfos));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
