package com.padcmyanmar.burpple.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.FeaturedVO;
import com.padcmyanmar.burpple.viewitems.BurppleFeaturedViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yekokohtet on 1/13/18.
 */

public class BurppleFeaturedPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;

    private List<FeaturedVO> mData;

    public BurppleFeaturedPagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BurppleFeaturedViewItem itemView = (BurppleFeaturedViewItem) mLayoutInflater.inflate(R.layout.view_item_burpple_featured, container, false);
        itemView.setData(mData.get(position));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void appendNewData(List<FeaturedVO> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setNewData(List<FeaturedVO> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
