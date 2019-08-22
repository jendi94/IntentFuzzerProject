package com.jendi.intentfuzzer.adapters;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jendi.intentfuzzer.R;
import com.jendi.intentfuzzer.viewholders.PackageInfoViewHolder;

import java.util.List;

public class PackageInfoAdapter extends RecyclerView.Adapter<PackageInfoViewHolder> {

    private List<PackageInfo> packageInfo;

    public PackageInfoAdapter(List<PackageInfo> packageInfo) {
        this.packageInfo = packageInfo;
    }

    @NonNull
    @Override
    public PackageInfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View packageInfoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_package_info, viewGroup, false);
        return new PackageInfoViewHolder(packageInfoView, packageInfo);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInfoViewHolder packageInfoViewHolder, int i) {
        packageInfoViewHolder.getPackageName().setText(packageInfo.get(i).applicationInfo.loadLabel(packageInfoViewHolder.itemView.getContext().getPackageManager()).toString());
    }

    @Override
    public int getItemCount() {
        return packageInfo.size();
    }

}
