package com.padcmyanmar.burpple.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.PromotionsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class BurpplePromotionsViewHolder extends BaseViewHolder<PromotionsVO> {

    @BindView(R.id.iv_burpple_promotion_image)
    ImageView ivBurpplePromotionImage;

    @BindView(R.id.tv_burpple_promotion_title)
    TextView tvBurpplePromotionTitle;

    @BindView(R.id.tv_burpple_promotion_until)
    TextView getTvBurpplePromotionUntil;

    @BindView(R.id.tv_burpple_promotion_shop_name)
    TextView tvBurpplePromotionShopName;

    @BindView(R.id.tv_burpple_promotion_shop_area)
    TextView tvBurpplePromotionShopArea;

    public BurpplePromotionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionsVO data) {

        Glide
                .with(itemView.getContext())
                .load(data.getBurpplePromotionImage())
                .into(ivBurpplePromotionImage);

        tvBurpplePromotionTitle.setText(data.getBurpplePromotionTitle());
        getTvBurpplePromotionUntil.setText(data.getBurpplePromotionUntil());
        tvBurpplePromotionShopName.setText(data.getBurpplePromotionShop().getBurppleShopName());
        tvBurpplePromotionShopArea.setText(data.getBurpplePromotionShop().getBurppleShopArea());

    }

    @Override
    public void onClick(View view) {

    }
}
