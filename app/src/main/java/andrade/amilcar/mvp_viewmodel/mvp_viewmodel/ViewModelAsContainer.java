package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;

 class ViewModelAsContainer extends ViewModel {

    @Nullable
    private List<Repo> repos = null;

    @Nullable
    List<Repo> getRepos() {
        return repos;
    }

    void setRepos(@NonNull List<Repo> repos) {
        this.repos = repos;
    }
}
