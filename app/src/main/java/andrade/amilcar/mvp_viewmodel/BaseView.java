package andrade.amilcar.mvp_viewmodel;

public interface BaseView<T extends BasePresenter> {
    T createPresenter();
}
