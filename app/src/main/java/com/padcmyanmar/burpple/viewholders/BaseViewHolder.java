package com.padcmyanmar.burpple.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yekokohtet on 1/18/18.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);

}
