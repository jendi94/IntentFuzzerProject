package com.jendi.intentfuzzer;

import android.content.pm.ComponentInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AppComponentAdapter extends RecyclerView.Adapter<AppComponentViewHolder> {

    public List<ComponentInfo> componentInfos;

    public AppComponentAdapter(List<? extends ComponentInfo> componentInfos) {
        this.componentInfos = (List<ComponentInfo>) componentInfos;
    }

    @NonNull
    @Override
    public AppComponentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View componentInfoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_component_info, viewGroup, false);

        return new AppComponentViewHolder(componentInfoView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppComponentViewHolder appComponentViewHolder, int i) {
        appComponentViewHolder.componentName.setText(componentInfos.get(i).name);
    }

    @Override
    public int getItemCount() {
        return componentInfos.size();
    }
}
