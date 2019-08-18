package com.jendi.intentfuzzer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PackageInfoAdapter extends RecyclerView.Adapter<PackageInfoViewHolder> {

    public List<PackageInfo> packageInfos;
    private Context context;

    public PackageInfoAdapter(List<PackageInfo> packageInfos, Context context){
        this.packageInfos = packageInfos;
        this.context = context;
    }

    @NonNull
    @Override
    public PackageInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View packageInfoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_package_info, viewGroup, false);

        return new PackageInfoViewHolder(packageInfoView, packageInfos, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInfoViewHolder packageInfoViewHolder, int i) {
        packageInfoViewHolder.packageName.setText(packageInfos.get(i).applicationInfo.loadLabel(context.getPackageManager()).toString());
    }

    @Override
    public int getItemCount() {
        return packageInfos.size();
    }

}
