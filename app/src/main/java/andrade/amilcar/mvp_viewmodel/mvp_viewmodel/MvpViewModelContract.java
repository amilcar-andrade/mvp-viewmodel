package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.BasePresenter;
import andrade.amilcar.mvp_viewmodel.BaseView;
import andrade.amilcar.mvp_viewmodel.model.Repo;

interface MvpViewModelContract {

    interface Presenter extends BasePresenter {
        // Some other presenter methods
    }

    interface View extends BaseView<Presenter> {

        void showList(List<Repo> list);
    }
}
