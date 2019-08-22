package com.jendi.intentfuzzer.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jendi.intentfuzzer.R;

public class AppComponentViewHolder extends RecyclerView.ViewHolder {

    private TextView componentName;

    public AppComponentViewHolder(View componentInfoView) {
        super(componentInfoView);
        this.componentName = componentInfoView.findViewById(R.id.component_name);
    }

    public TextView getComponentName() {
        return componentName;
    }
}
