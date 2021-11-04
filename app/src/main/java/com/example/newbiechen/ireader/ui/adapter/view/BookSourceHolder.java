package com.example.newbiechen.ireader.ui.adapter.view;

import android.widget.TextView;

import com.example.newbiechen.ireader.R;
import com.example.newbiechen.ireader.ui.base.adapter.ViewHolderImpl;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

public class BookSourceHolder extends ViewHolderImpl<CrawlerBookInfo> {

    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvNode;
    private TextView mTvLastChapter;

    @Override
    public void initView() {
        mTvTitle = findById(R.id.book_source_tv_title);
        mTvAuthor = findById(R.id.book_source_tv_author);
        mTvNode = findById(R.id.book_source_tv_node);
        mTvLastChapter = findById(R.id.book_source_tv_last_chapter);

    }

    @Override
    public void onBind(CrawlerBookInfo data, int pos) {
        mTvTitle.setText(data.getName());
        mTvAuthor.setText(data.getAuthor());
        mTvNode.setText(data.getDomain());
        mTvLastChapter.setText(data.getLastChapter());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_book_source;
    }
}
