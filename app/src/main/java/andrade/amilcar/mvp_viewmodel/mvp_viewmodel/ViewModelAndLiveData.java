package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

class ViewModelAndLiveData extends android.arch.lifecycle.ViewModel {

    private MutableLiveData<List<Repo>> reposLive = new MutableLiveData<>();
    private Disposable disposable;

    ViewModelAndLiveData(@NonNull GithubRepository repository) {
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
        public <T extends android.arch.lifecycle.ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ViewModelAndLiveData(repository);
        }
    }

    @NonNull
    LiveData<List<Repo>> getReposAsLiveData() {
        return reposLive;
    }
}
