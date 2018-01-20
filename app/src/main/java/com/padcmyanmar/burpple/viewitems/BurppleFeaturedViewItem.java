package com.padcmyanmar.burpple.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.FeaturedVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yekokohtet on 1/19/18.
 */

public class BurppleFeaturedViewItem extends FrameLayout {

    @BindView(R.id.iv_burpple_featured_image)
    ImageView ivBurppleFeaturedImage;

    @BindView(R.id.tv_burpple_featured_title)
    TextView tvBurppleFeaturedTitle;

    public BurppleFeaturedViewItem(@NonNull Context context) {
        super(context);
    }

    public BurppleFeaturedViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BurppleFeaturedViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(FeaturedVO data) {

        Glide
                .with(ivBurppleFeaturedImage.getContext())
                .load(data.getBurppleFeaturedImage())
                .into(ivBurppleFeaturedImage);

        tvBurppleFeaturedTitle.setText(data.getBurppleFeaturedImageTitle());
    }
}
