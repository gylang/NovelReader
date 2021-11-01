package com.example.newbiechen.ireader.ui.adapter;

import android.content.Context;
import com.example.newbiechen.ireader.model.bean.BookListBean;
import com.example.newbiechen.ireader.ui.adapter.view.BookListHolder;
import com.example.newbiechen.ireader.ui.base.adapter.IViewHolder;
import com.example.newbiechen.ireader.widget.adapter.WholeAdapter;

/**
 * Created by newbiechen on 17-5-1.
 */

public class BookSourceListAdapter extends WholeAdapter<BookListBean> {
    public BookSourceListAdapter() {
    }

    public BookSourceListAdapter(Context context, Options options) {
        super(context, options);
    }

    @Override
    protected IViewHolder<BookListBean> createViewHolder(int viewType) {
        return new BookListHolder();
    }
}
