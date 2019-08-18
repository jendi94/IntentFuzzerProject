package com.jendi.intentfuzzer.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jendi.intentfuzzer.R;

public class AppComponentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView componentName;

    public AppComponentViewHolder(View componentInfoView) {
        super(componentInfoView);
        componentInfoView.setOnClickListener(this);
        this.componentName = componentInfoView.findViewById(R.id.component_name);
    }

    @Override
    public void onClick(View v) {
        //tutaj napisac fuzzing komponentow
    }

    public TextView getComponentName() {
        return componentName;
    }
}
