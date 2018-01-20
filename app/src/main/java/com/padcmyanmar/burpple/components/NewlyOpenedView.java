package com.padcmyanmar.burpple.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.padcmyanmar.burpple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yekokohtet on 1/16/18.
 */

public class NewlyOpenedView extends CardView {

    @BindView(R.id.iv_food_item_1)
    ImageView ivFoodItem1;

    @BindView(R.id.tv_food_shop_1)
    TextView tvFoodShop1;

    @BindView(R.id.tv_distance_shop_1)
    TextView tvDistanceShop1;

    @BindView(R.id.iv_food_item_2)
    ImageView ivFoodItem2;

    @BindView(R.id.tv_food_shop_2)
    TextView tvFoodShop2;

    @BindView(R.id.tv_distance_shop_2)
    TextView tvDistanceShop2;

    @BindView(R.id.iv_food_item_3)
    ImageView ivFoodItem3;

    @BindView(R.id.tv_food_shop_3)
    TextView tvFoodShop3;

    @BindView(R.id.tv_distance_shop_3)
    TextView tvDistanceShop3;

    public NewlyOpenedView(@NonNull Context context) {
        super(context);
    }

    public NewlyOpenedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewlyOpenedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }
}
