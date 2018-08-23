package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;

class PresenterAsObserver implements MvpViewModelContract.Presenter {

    private final MvpViewModel viewModel;
    private final MvpViewModelContract.View view;

    private Observer<List<Repo>> observer;

    PresenterAsObserver(@NonNull MvpViewModelContract.View view, @NonNull MvpViewModel viewModel) {
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
