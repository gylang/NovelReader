package com.example.newbiechen.ireader.presenter.crawler;

import android.support.annotation.NonNull;
import cn.hutool.core.lang.Singleton;
import com.example.newbiechen.ireader.presenter.crawler.contract.BookSourceContract;
import com.example.newbiechen.ireader.ui.base.RxPresenter;
import com.example.newbiechen.ireader.utils.RxUtils;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;
import com.gylang.novel.handler.SimpleNovelHandlerManager;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.List;

/**
 * @author gylang
 * data 2021/11/1
 */
public class BookSourcePresenter extends RxPresenter<BookSourceContract.View> implements BookSourceContract.Presenter {


    @Override
    public void loadBookList(String name, String author) {

        // 请求第三方爬取数据接口

        Flowable.create(new FlowableOnSubscribe<List<CrawlerBookInfo>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<List<CrawlerBookInfo>> emitter) throws Exception {

                SimpleNovelHandlerManager simpleNovelHandlerManager = Singleton.get(SimpleNovelHandlerManager.class);
                List<CrawlerBookInfo> crawlerBookInfoList = simpleNovelHandlerManager.allNodeBookSearchByNameAndAuthor(name, author);
                emitter.onNext(crawlerBookInfoList);
            }
        }, BackpressureStrategy.BUFFER)
                .compose(upstream -> upstream.observeOn(AndroidSchedulers.mainThread()));
    }
}
