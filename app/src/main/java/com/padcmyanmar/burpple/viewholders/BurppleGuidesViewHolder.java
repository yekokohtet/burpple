package com.padcmyanmar.burpple.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.GuidesVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class BurppleGuidesViewHolder extends BaseViewHolder<GuidesVO> {

    @BindView(R.id.iv_burpple_guides_image)
    ImageView ivBurppleGuidesImage;

    @BindView(R.id.tv_burpple_guides_title)
    TextView tvBurppleGuidesTitle;

    public BurppleGuidesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(GuidesVO data) {

        Glide
                .with(itemView.getContext())
                .load(data.getBurppleGuideImage())
                .into(ivBurppleGuidesImage);

        tvBurppleGuidesTitle.setText(data.getBurppleGuideTitle());
    }

    @Override
    public void onClick(View view) {

    }
}
