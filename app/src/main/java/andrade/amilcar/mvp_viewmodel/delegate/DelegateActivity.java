package andrade.amilcar.mvp_viewmodel.delegate;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.R;
import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;

public class DelegateActivity extends AppCompatActivity implements DelegateContract.View {

    private DelegateContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate);
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
    public DelegateContract.Presenter createPresenter() {
        final DelegateViewModel.Factory factory = new DelegateViewModel.Factory(GithubRepository.INSTANCE);
        return new DelegatePresenter(this, ViewModelProviders.of(this, factory).get(DelegateViewModel.class));
    }

    @Override
    public void showList(List<Repo> list) {
    }
}
