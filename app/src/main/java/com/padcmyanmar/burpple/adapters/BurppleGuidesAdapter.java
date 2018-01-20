package com.padcmyanmar.burpple.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.burpple.R;
import com.padcmyanmar.burpple.data.vo.GuidesVO;
import com.padcmyanmar.burpple.viewholders.BurppleGuidesViewHolder;

/**
 * Created by yekokohtet on 1/12/18.
 */

public class BurppleGuidesAdapter extends BaseRecyclerAdapter<BurppleGuidesViewHolder, GuidesVO> {

    public BurppleGuidesAdapter(Context context) {
        super(context);
    }

    @Override
    public BurppleGuidesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_burpple_guides, parent, false);
        return new BurppleGuidesViewHolder(view);
    }
}
