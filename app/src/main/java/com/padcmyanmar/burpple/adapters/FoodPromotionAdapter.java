package com.padcmyanmar.burpple.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.viewholders.FoodPromotionViewHolder;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class FoodPromotionAdapter extends RecyclerView.Adapter<FoodPromotionViewHolder> {

    private LayoutInflater mLayoutInflater;

    public FoodPromotionAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FoodPromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_food_promotions, parent, false);
        return new FoodPromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodPromotionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
