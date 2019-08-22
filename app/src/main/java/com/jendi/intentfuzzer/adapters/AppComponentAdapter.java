package com.jendi.intentfuzzer.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ServiceInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jendi.intentfuzzer.Helper;
import com.jendi.intentfuzzer.R;
import com.jendi.intentfuzzer.viewholders.AppComponentViewHolder;

import java.util.List;

public class AppComponentAdapter extends RecyclerView.Adapter<AppComponentViewHolder> {

    private List<? extends ComponentInfo> componentInfo;
    private Activity activity;

    public AppComponentAdapter(List<? extends ComponentInfo> componentInfo, Activity activity) {
        this.componentInfo = componentInfo;
        this.activity = activity;
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
        appComponentViewHolder.getComponentName().setText(componentInfo.get(i).name);
        final int position = i;
        appComponentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (componentInfo.get(position) instanceof ActivityInfo) {
                    ActivityInfo info = (ActivityInfo) componentInfo.get(position);
                    Intent intent = new Intent();
                    intent.setClassName(info.applicationInfo.packageName,
                                        info.name);
                    try {
                        v.getContext().startActivity(intent);
                    } catch (SecurityException e) {
                        Helper.updateLogScreen(info, e.getMessage(), activity, (TextView) activity.findViewById(R.id.textViewExceptionContent));
                    } catch (Exception e) {
                        Helper.updateLogScreen(info, e.getMessage(), activity, (TextView) activity.findViewById(R.id.textViewExceptionContent));
                    }
                } else {
                    ServiceInfo info = (ServiceInfo) componentInfo.get(position);
                    Intent intent = new Intent();
                    intent.setClassName(info.applicationInfo.packageName,
                                        info.name);
                    try {
                        v.getContext().startService(intent);

                    } catch (SecurityException e) {
                        Helper.updateLogScreen(info, e.getMessage(), activity, (TextView) activity.findViewById(R.id.textViewExceptionContent));
                    } catch (Exception e) {
                        Helper.updateLogScreen(info, e.getMessage(), activity, (TextView) activity.findViewById(R.id.textViewExceptionContent));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentInfo.size();
    }
}
