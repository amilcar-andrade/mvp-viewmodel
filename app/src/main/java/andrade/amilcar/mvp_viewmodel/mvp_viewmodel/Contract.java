package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.BasePresenter;
import andrade.amilcar.mvp_viewmodel.BaseView;
import andrade.amilcar.mvp_viewmodel.model.Repo;

public interface Contract {

    interface Presenter extends BasePresenter {
        // Some other presenter methods ...
    }

    interface View extends BaseView<Presenter> {
        // Some other view methods ...
        void showList(List<Repo> list);
    }
}
