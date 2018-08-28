package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;

public class Presenter implements Contract.Presenter {

    @Nullable
    private final GithubRepository repository;
    private final ViewModel viewModel;

    private Contract.View view;
    private Observer<List<Repo>> observer;

    Presenter(@NonNull Contract.View view,
              @NonNull ViewModel viewModel) {
        this(view, viewModel, null);
    }

    Presenter(@NonNull Contract.View view,
              @NonNull ViewModel viewModel,
              @Nullable GithubRepository repository) {
        this.view = view;
        this.viewModel = viewModel;
        this.repository = repository;
    }

    @Override
    public void start() {
        if (viewModel instanceof ViewModelAndLiveData) {
            observer = new Observer<List<Repo>>() {
                @Override
                public void onChanged(@Nullable List<Repo> repositories) {
                    view.showList(repositories);
                }
            };
            ((ViewModelAndLiveData)viewModel).getReposAsLiveData().observeForever(observer);
            return;
        }

        if (viewModel instanceof ViewModelAsContainer && repository != null) {
            final ViewModelAsContainer container = (ViewModelAsContainer) viewModel;
            if (container.getRepos() != null) {
                view.showList(container.getRepos());
                return;
            }

            repository.getRepos(new GithubRepository.ReposCallback() {
                @Override
                public void onSuccess(@NonNull List<Repo> repos) {
                    ((ViewModelAsContainer) viewModel).setRepos(repos);
                    if (view != null) {
                        view.showList(repos);
                    }
                }

                @Override
                public void onFailure(@NonNull Throwable error) {
                    // LATER: Notify view
                }
            });
        }
    }

    @Override
    public void destroy() {
        view = null;
        if (viewModel instanceof ViewModelAndLiveData) {
            ((ViewModelAndLiveData)viewModel).getReposAsLiveData().removeObserver(observer);
        }
    }
}
