package com.padcmyanmar.burpple.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.viewholders.BurppleExclusiveViewHolder;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class BurppleExclusiveAdapter extends RecyclerView.Adapter<BurppleExclusiveViewHolder> {

    private LayoutInflater mLayoutInflater;

    public BurppleExclusiveAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BurppleExclusiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_burpple_exclusive, parent, false);
        return new BurppleExclusiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BurppleExclusiveViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
