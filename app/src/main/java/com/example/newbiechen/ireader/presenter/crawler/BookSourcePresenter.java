package com.example.newbiechen.ireader.presenter.crawler;

import com.example.newbiechen.ireader.model.flag.BookListType;
import com.example.newbiechen.ireader.presenter.crawler.contract.BookSourceContract;
import com.example.newbiechen.ireader.ui.base.RxPresenter;

/**
 * @author gylang
 * data 2021/11/1
 */
public class BookSourcePresenter extends RxPresenter<BookSourceContract.View> implements BookSourceContract.Presenter {

    @Override
    public void refreshBookList(BookListType type, String tag, int start, int limited) {

    }

    @Override
    public void loadBookList(BookListType type, String tag, int start, int limited) {

    }


}
