package com.jendi.intentfuzzer.viewholders;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jendi.intentfuzzer.R;
import com.jendi.intentfuzzer.activitites.PackageInfoActivity;

import java.util.List;

public class PackageInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView packageName;
    List<PackageInfo> packageInfos;
    Context context;

    public PackageInfoViewHolder(View packageInfoView, List<PackageInfo> packageInfos, Context context) {
        super(packageInfoView);
        packageInfoView.setOnClickListener(this);
        this.packageName = packageInfoView.findViewById(R.id.package_name);
        this.packageInfos = packageInfos;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        int index = getLayoutPosition();
        Intent intent = new Intent(context, PackageInfoActivity.class);
        intent.putExtra("info", packageInfos.get(index));
        context.startActivity(intent);
    }

    public TextView getPackageName() {
        return packageName;
    }
}
