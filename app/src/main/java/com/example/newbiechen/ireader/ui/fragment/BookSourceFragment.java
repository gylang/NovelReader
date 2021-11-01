package com.example.newbiechen.ireader.ui.fragment;

import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.example.newbiechen.ireader.R;
import com.example.newbiechen.ireader.presenter.contract.BookListContract;
import com.example.newbiechen.ireader.presenter.crawler.contract.BookSourceContract;
import com.example.newbiechen.ireader.ui.base.BaseFragment;
import com.example.newbiechen.ireader.ui.base.BaseMVPFragment;
import com.example.newbiechen.ireader.widget.RefreshLayout;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

import java.util.List;

/**
 * @author gylang
 * data 2021/10/30
 */
public class BookSourceFragment extends BaseMVPFragment<BookListContract.Presenter>
        implements BookSourceContract.View {

    @BindView(R.id.book_source_content)
    RecyclerView bookSourceContent;


    @Override
    protected int getContentId() {
        return R.layout.fragment_book_source;
    }

    @Override
    public void finishRefresh(List<CrawlerBookInfo> beans) {

    }

    @Override
    public void finishLoading(List<CrawlerBookInfo> beans) {

    }

    @Override
    public void showLoadError() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    protected BookListContract.Presenter bindPresenter() {
        return null;
    }
}
