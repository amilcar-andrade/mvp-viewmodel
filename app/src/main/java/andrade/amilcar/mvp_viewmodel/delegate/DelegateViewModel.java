package andrade.amilcar.mvp_viewmodel.delegate;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

class DelegateViewModel extends ViewModel {

    private MutableLiveData<List<Repo>> reposLive = new MutableLiveData<>();

    private Disposable disposable;

    DelegateViewModel(@NonNull GithubRepository repository) {
        disposable = repository.getReposAsync().subscribe(new Consumer<List<Repo>>() {
            @Override
            public void accept(List<Repo> repos) throws Exception {
                reposLive.postValue(repos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // LATER: Send an actual error
                reposLive.postValue(Collections.<Repo>emptyList());
            }
        });
    }

    @Override
    protected void onCleared() {
        if (!(disposable.isDisposed())) {
            disposable.dispose();
        }
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final GithubRepository repository;

        Factory(@NonNull GithubRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DelegateViewModel(repository);
        }
    }

    LiveData<List<Repo>> getRepos() {
        return reposLive;
    }
}
