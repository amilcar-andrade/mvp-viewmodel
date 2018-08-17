package andrade.amilcar.mvp_viewmodel.service;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import andrade.amilcar.mvp_viewmodel.model.Repo;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GithubRepository {

    public static final GithubRepository INSTANCE = new GithubRepository();

    private final GithubService service;

    private GithubRepository() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(GithubService.class);
    }

    // Callback approach
    public void getRepos(@NonNull ReposCallback callback) {
        final WeakReference<ReposCallback> weakReference = new WeakReference<>(callback);
        Call<List<Repo>> listCall = service.listRepos();
        listCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                final ReposCallback reposCallback = weakReference.get();
                final List<Repo> body = response.body();
                if (reposCallback != null && body != null) {
                    reposCallback.onSuccess(body);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                final ReposCallback reposCallback = weakReference.get();
                if (reposCallback != null) {
                    reposCallback.onFailure(t);
                }
            }
        });
    }

    public Single<List<Repo>> getReposAsync() {
        return service.listReposAsSingle().subscribeOn(Schedulers.io());
    }

    interface GithubService {

        @GET("orgs/hulu/repos")
        Call<List<Repo>> listRepos();

        @GET("orgs/hulu/repos")
        Single<List<Repo>> listReposAsSingle();
    }

    public interface ReposCallback {

        void onSuccess(@NonNull List<Repo> repos);

        void onFailure(@NonNull Throwable error);
    }
}
