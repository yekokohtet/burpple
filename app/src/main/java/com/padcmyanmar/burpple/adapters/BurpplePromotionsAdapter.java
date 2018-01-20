package com.padcmyanmar.burpple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.PromotionsVO;
import com.padcmyanmar.burpple.viewholders.BurpplePromotionsViewHolder;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class BurpplePromotionsAdapter extends BaseRecyclerAdapter<BurpplePromotionsViewHolder, PromotionsVO> {

    public BurpplePromotionsAdapter(Context context) {
        super(context);
    }

    @Override
    public BurpplePromotionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_burpple_promotions, parent, false);
        return new BurpplePromotionsViewHolder(view);
    }
}
