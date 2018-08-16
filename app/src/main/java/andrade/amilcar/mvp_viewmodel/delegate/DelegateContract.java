package andrade.amilcar.mvp_viewmodel.delegate;

import andrade.amilcar.mvp_viewmodel.BasePresenter;
import andrade.amilcar.mvp_viewmodel.BaseView;

public interface DelegateContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
