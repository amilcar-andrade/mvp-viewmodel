package andrade.amilcar.mvp_viewmodel.delegate;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;

class DelegatePresenter implements DelegateContract.Presenter {

    private final DelegateViewModel viewModel;
    private final DelegateContract.View view;

    private Observer<List<Repo>> observer;

    DelegatePresenter(@NonNull DelegateContract.View view, @NonNull DelegateViewModel viewModel) {
        this.viewModel = viewModel;
        this.view = view;
    }

    @Override
    public void start() {
        observer = new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repositories) {
                view.showList(repositories);
            }
        };
        viewModel.getRepos().observeForever(observer);
    }

    @Override
    public void destroy() {
        viewModel.getRepos().removeObserver(observer);
    }
}
