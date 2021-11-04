package com.example.newbiechen.ireader.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.newbiechen.ireader.R;
import com.example.newbiechen.ireader.model.bean.CollBookBean;
import com.example.newbiechen.ireader.presenter.crawler.BookSourcePresenter;
import com.example.newbiechen.ireader.presenter.crawler.contract.BookSourceContract;
import com.example.newbiechen.ireader.ui.adapter.BookSourceListAdapter;
import com.example.newbiechen.ireader.ui.base.BaseMVPActivity;
import com.example.newbiechen.ireader.widget.RefreshLayout;
import com.example.newbiechen.ireader.widget.adapter.WholeAdapter;
import com.example.newbiechen.ireader.widget.itemdecoration.DividerItemDecoration;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

import java.util.List;

import butterknife.BindView;

/**
 * @author gylang
 * data 2021/10/30
 */
public class BookSourceActivity extends BaseMVPActivity<BookSourceContract.Presenter>
        implements BookSourceContract.View {

    private static final String TAG = "BookSourceActivity";

    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.book_source_content)
    RecyclerView mRvContent;

    BookSourceListAdapter bookSourceListAdapter;
    private String name;
    private String author;
    private CollBookBean collBookBean;
    private boolean isCollected;
    private String bookId;
    private int requestRead;

    public static void startActivity(Context context, CollBookBean data, boolean isCollected, int requestRead) {
        Intent intent = new Intent(context, BookSourceActivity.class);
        intent.putExtra("isCollected", isCollected);
        intent.putExtra("collBookBean", data);
        intent.putExtra("requestRead", requestRead);
        context.startActivity(intent);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
       if (null == savedInstanceState) {
           collBookBean =  getIntent().getParcelableExtra("collBookBean");
           name = collBookBean.getTitle();
           author = collBookBean.getAuthor();
           bookId = collBookBean.get_id();
           requestRead = getIntent().getIntExtra("requestRead", 1);
       } else {
           collBookBean =  savedInstanceState.getParcelable("collBookBean");
           name = collBookBean.getTitle();
           author = collBookBean.getAuthor();
           bookId = collBookBean.get_id();
           requestRead = savedInstanceState.getInt("requestRead", 1);
       }
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_book_source;
    }

    @Override
    public void finishRefresh(List<CrawlerBookInfo> beans) {
        Log.i(TAG, "finishRefresh: " + JSON.toJSONString(beans));
        if (beans.isEmpty()){
            mRefreshLayout.showEmpty();
            return;
        }
        bookSourceListAdapter.refreshItems(beans);
        mRefreshLayout.showFinish();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mRefreshLayout.showLoading();
        bookSourceListAdapter = new BookSourceListAdapter(this, new WholeAdapter.Options());
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.addItemDecoration(new DividerItemDecoration(this));
        mRvContent.setAdapter(bookSourceListAdapter);
    }

    @Override
    protected void processLogic() {

        super.processLogic();
        mPresenter.loadBookList(name, author);
    }

    @Override
    protected void initClick() {

        bookSourceListAdapter.setOnItemClickListener(
                (view, pos) -> {
                    CrawlerBookInfo item = bookSourceListAdapter.getItem(pos);
                    collBookBean.setChapterUrl(item.getUrl());
                    // 跳转到阅读页面
                    startActivityForResult(new Intent(this, ReadActivity.class)
                            .putExtra(ReadActivity.EXTRA_IS_COLLECTED, isCollected)
                            .putExtra(ReadActivity.EXTRA_DOMAIN, item.getDomain())
                            .putExtra(ReadActivity.EXTRA_CHAPTER_URL, item.getUrl())
                            .putExtra(ReadActivity.EXTRA_COLL_BOOK, collBookBean), requestRead);
                }
        );

    }

    @Override
    public void showLoadError() {
        Log.i(TAG, "showLoadError: ");

        mRefreshLayout.showError();
    }

    @Override
    public void showError() {
        Log.i(TAG, "showLoadError: ");

        mRefreshLayout.showError();
    }

    @Override
    public void complete() {
        mRefreshLayout.showFinish();
    }

    @Override
    protected BookSourceContract.Presenter bindPresenter() {
        return new BookSourcePresenter();
    }
}
