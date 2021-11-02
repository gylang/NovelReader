package com.example.newbiechen.ireader.presenter.crawler.contract;

import com.example.newbiechen.ireader.ui.base.BaseContract;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;

import java.util.List;

/**
 * @author gylang
 * data 2021/11/1
 */
public interface BookSourceContract {

    interface View extends BaseContract.BaseView {
        /**
         * 加载各个节点的数据
         *
         * @param beans
         */
        void finishRefresh(List<CrawlerBookInfo> beans);

        /**
         * 展示错误新
         */
        void showLoadError();
    }

    interface Presenter extends BaseContract.BasePresenter<BookSourceContract.View> {

        void loadBookList(String name, String author);
    }
}
