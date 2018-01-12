package com.padcmyanmar.burpple.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.adapters.BurppleExclusiveAdapter;
import com.padcmyanmar.burpple.adapters.FoodPromotionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BurppleFoodPlacesActivity extends AppCompatActivity {

    @BindView(R.id.rv_promotions)
    RecyclerView rvPromotions;

    @BindView(R.id.rv_burpple_guides)
    RecyclerView rvBurppleGuides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burpple_food_places);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        rvPromotions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        FoodPromotionAdapter foodPromotionAdapter = new FoodPromotionAdapter(getApplicationContext());
        rvPromotions.setAdapter(foodPromotionAdapter);

        rvBurppleGuides.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        BurppleExclusiveAdapter burppleExclusiveAdapter = new BurppleExclusiveAdapter(getApplicationContext());
        rvBurppleGuides.setAdapter(burppleExclusiveAdapter);
    }
}
