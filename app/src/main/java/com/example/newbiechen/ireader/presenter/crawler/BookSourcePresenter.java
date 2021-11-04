package com.example.newbiechen.ireader.presenter.crawler;

import android.support.annotation.NonNull;
import android.util.Log;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Singleton;

import com.alibaba.fastjson.JSON;
import com.example.newbiechen.ireader.presenter.crawler.contract.BookSourceContract;
import com.example.newbiechen.ireader.ui.base.RxPresenter;
import com.example.newbiechen.ireader.utils.RxUtils;
import com.gylang.novel.domain.AjaxResult;
import com.gylang.novel.domain.bean.crawler.CrawlerBookInfo;
import com.gylang.novel.handler.SimpleNovelHandlerManager;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * @author gylang
 * data 2021/11/1
 */
public class BookSourcePresenter extends RxPresenter<BookSourceContract.View> implements BookSourceContract.Presenter {


    @Override
    public void loadBookList(String name, String author) {

        // 请求第三方爬取数据接口

        Disposable disposable = Observable.<List<CrawlerBookInfo>>create(emitter ->
                {
                    SimpleNovelHandlerManager simpleNovelHandlerManager = Singleton.get(SimpleNovelHandlerManager.class);
                    List<AjaxResult<CrawlerBookInfo>> ajaxResults = simpleNovelHandlerManager.allNodeBookSearchByNameAndAuthor(name, author);
                    Log.i("BookSourcePresenter", "loadBookList size: " + ajaxResults.size());
                    Log.i("BookSourcePresenter", "loadBookList : " + JSON.toJSONString(ajaxResults));
                    List<CrawlerBookInfo> bookInfos = CollUtil.map(ajaxResults, AjaxResult::getData, true);
                    Log.i("BookSourcePresenter", "bookInfos : " + JSON.toJSONString(bookInfos));
                    emitter.onNext(bookInfos);
                    emitter.onComplete();//结束异步任务
                }
        )
                .compose(upstream -> upstream.observeOn(AndroidSchedulers.mainThread()))
                .subscribeOn(Schedulers.io())//异步任务在IO线程执行
                .observeOn(AndroidSchedulers.mainThread())//执行结果在主线程运行
                .subscribe(crawlerBookInfos -> {
                    mView.finishRefresh(crawlerBookInfos);
                    mView.complete();
                });
//        Disposable disposable = Flowable.create(new FlowableOnSubscribe<List<CrawlerBookInfo>>() {
//            @Override
//            public void subscribe(@NonNull FlowableEmitter<List<CrawlerBookInfo>> emitter) throws Exception {
//
//                SimpleNovelHandlerManager simpleNovelHandlerManager = Singleton.get(SimpleNovelHandlerManager.class);
//                List<CrawlerBookInfo> crawlerBookInfoList = simpleNovelHandlerManager.allNodeBookSearchByNameAndAuthor(name, author);
//                emitter.onNext(crawlerBookInfoList);
//            }
//        }, BackpressureStrategy.BUFFER)
//                .compose(upstream -> upstream.observeOn(AndroidSchedulers.mainThread()))
//                .subscribe(crawlerBookInfos -> mView.finishRefresh(crawlerBookInfos));
        addDisposable(disposable);
    }
}
