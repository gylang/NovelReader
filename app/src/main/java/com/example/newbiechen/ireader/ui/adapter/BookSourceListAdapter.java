package com.example.newbiechen.ireader.ui.adapter;

import android.content.Context;

import com.example.newbiechen.ireader.ui.adapter.view.BookSourceHolder;
import com.example.newbiechen.ireader.ui.base.adapter.IViewHolder;
import com.example.newbiechen.ireader.widget.adapter.WholeAdapter;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

/**
 * Created by newbiechen on 17-5-1.
 */

public class BookSourceListAdapter extends WholeAdapter<CrawlerBookInfo> {
    public BookSourceListAdapter() {
    }

    public BookSourceListAdapter(Context context, Options options) {
        super(context, options);
    }

    @Override
    protected IViewHolder<CrawlerBookInfo> createViewHolder(int viewType) {
        return new BookSourceHolder();
    }
}
