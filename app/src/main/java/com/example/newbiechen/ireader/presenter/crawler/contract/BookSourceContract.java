package com.example.newbiechen.ireader.presenter.crawler.contract;

import com.example.newbiechen.ireader.model.bean.BookListBean;
import com.example.newbiechen.ireader.model.flag.BookListType;
import com.example.newbiechen.ireader.presenter.contract.BookListContract;
import com.example.newbiechen.ireader.ui.base.BaseContract;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

import java.util.List;

/**
 * @author gylang
 * data 2021/11/1
 */
public interface BookSourceContract {

    interface View extends BaseContract.BaseView{
        void finishRefresh(List<CrawlerBookInfo> beans);
        void finishLoading(List<CrawlerBookInfo> beans);
        void showLoadError();
    }

    interface Presenter extends BaseContract.BasePresenter<BookSourceContract.View>{
        void refreshBookList(BookListType type, String tag, int start, int limited);
        void loadBookList(BookListType type, String tag,int start, int limited);
    }
}
