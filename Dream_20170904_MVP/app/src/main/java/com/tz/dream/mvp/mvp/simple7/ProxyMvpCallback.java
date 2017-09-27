package com.tz.dream.mvp.mvp.simple7;

/**
 * 作者: Dream on 2017/9/4 22:31
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//直接封装MVP实现
public class ProxyMvpCallback<V extends MvpView_7, P extends MvpPresenter_7<V>> implements MvpCallback_7<V, P> {

    //持有目标对象引用->Activity
    //mvpCallback->本质就是Actiivty
    private MvpCallback_7<V, P> mvpCallback;

    public ProxyMvpCallback(MvpCallback_7<V, P> mvpCallback){
        this.mvpCallback = mvpCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = mvpCallback.getPresneter();
        if (presenter == null) {
            presenter = mvpCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("Presenter is not null!");
        }

        // 绑定
        mvpCallback.setPresenter(presenter);
        return getPresneter();
    }

    @Override
    public V createView() {
        V view = mvpCallback.getMvpView();
        if (view == null) {
            view = mvpCallback.createView();
        }
        if (view == null) {
            throw new NullPointerException("Presenter is not null!");
        }

        // 绑定
        mvpCallback.setMvpView(view);
        return getMvpView();
    }

    @Override
    public void setMvpView(V view) {

    }

    /**
     * 获取presenter
     *
     * @return
     */
    @Override
    public P getPresneter() {
        P presenter = mvpCallback.getPresneter();
        if (presenter == null) {
            // 抛异常
            throw new NullPointerException("Presenter is not null!");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        mvpCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        return mvpCallback.getMvpView();
    }

    public void attachView(V view){
        mvpCallback.setMvpView(view);
    }

    public void detachView(){
        mvpCallback.setMvpView(null);
    }

}
