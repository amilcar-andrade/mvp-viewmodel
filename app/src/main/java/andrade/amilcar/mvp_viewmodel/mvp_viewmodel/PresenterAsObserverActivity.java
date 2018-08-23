package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.R;
import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;
import andrade.amilcar.mvp_viewmodel.ui.ReposAdapter;

public class PresenterAsObserverActivity extends AppCompatActivity implements MvpViewModelContract.View {

    private MvpViewModelContract.Presenter presenter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate);
        recyclerView = findViewById(R.id.rv);
        presenter = createPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public MvpViewModelContract.Presenter createPresenter() {
        final MvpViewModel.Factory factory = new MvpViewModel.Factory(GithubRepository.INSTANCE);
        return new PresenterAsObserver(this, ViewModelProviders.of(this, factory).get(MvpViewModel.class));
    }

    @Override
    public void showList(List<Repo> list) {
        recyclerView.setAdapter(new ReposAdapter(list));
    }
}
