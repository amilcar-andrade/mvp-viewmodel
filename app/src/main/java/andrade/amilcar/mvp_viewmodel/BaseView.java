package andrade.amilcar.mvp_viewmodel;

public interface BaseView<T extends BasePresenter> {
    void createPresenter(T presenter);
}
