package com.gnuey.one.utils;



import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * https://juejin.im/entry/58ff2e26a0bb9f0065d2c5f2
 */


public class RxBus {

    private final FlowableProcessor<Object> mBus;
    private ConcurrentHashMap<Object, List<FlowableProcessor>> processorMapper = new ConcurrentHashMap<>();

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    private static class Holder {
        private static RxBus instance = new RxBus();
    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }
    public void post(@NonNull Object tag,@NonNull Object content){
        List<FlowableProcessor> processorList = processorMapper.get(tag);
        if(!processorList.isEmpty()){
            for(FlowableProcessor flowableProcessor:processorList){
                flowableProcessor.onNext(content);
            }
        }
    }

    public <T> Flowable<T> register(Class<T> clz) {
        return mBus.ofType(clz);
    }
    public <T> Flowable<T> register(Object tag,Class<T> clz){
        List<FlowableProcessor> processorList = processorMapper.get(tag);
        if(null==processorList){
            processorList = new ArrayList<>();
            processorMapper.put(tag,processorList);
        }
        FlowableProcessor<Object> processor = PublishProcessor.create().toSerialized();
        processor.onBackpressureLatest();
        processorList.add(processor);

        return processor.ofType(clz);
    }

    public void unregisterAll() {
        //会将所有由mBus 生成的 Flowable 都置  completed 状态  后续的 所有消息  都收不到了
        mBus.onComplete();
    }
    public void unRegister(Object tag){
        List<FlowableProcessor> processorList = processorMapper.get(tag);
        if(null != processorList){
            for(FlowableProcessor processor : processorList){
                processor.onComplete();
            }
            processorList.clear();
            if(processorList.isEmpty()){
                processorMapper.remove(tag);
            }
        }
    }
    public void unRegisterEach(Object tag){
        List<FlowableProcessor> processorList = processorMapper.get(tag);
        if(null != processorList){
            processorList.get(processorList.size()-1).onComplete();
            processorList.remove(processorList.size()-1);
            if(processorList.isEmpty()){
                processorMapper.remove(tag);
            }
        }
    }
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
