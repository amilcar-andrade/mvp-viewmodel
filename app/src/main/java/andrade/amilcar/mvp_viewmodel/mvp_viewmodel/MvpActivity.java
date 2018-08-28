package andrade.amilcar.mvp_viewmodel.mvp_viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.R;
import andrade.amilcar.mvp_viewmodel.model.Repo;
import andrade.amilcar.mvp_viewmodel.service.GithubRepository;
import andrade.amilcar.mvp_viewmodel.ui.ReposAdapter;

public class MvpActivity extends AppCompatActivity implements Contract.View {
    private static final String INTENT_EXTRA_MODE = "INTENT_EXTRA_MODE";
    private static final int MODE_PRESENTER_AS_OBSERVER = 0;
    private static final int MODE_VM_AS_CONTAINER = 1;

    private Contract.Presenter presenter;

    private RecyclerView recyclerView;

    private int mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = getIntent().getIntExtra(INTENT_EXTRA_MODE, MODE_PRESENTER_AS_OBSERVER);
        setContentView(R.layout.activity_list);
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
    public Contract.Presenter createPresenter() {
        if (mode == MODE_PRESENTER_AS_OBSERVER) {
            final ViewModelAndLiveData.Factory factory = new ViewModelAndLiveData.Factory(GithubRepository.INSTANCE);
            return new Presenter(this, ViewModelProviders.of(this, factory).get(ViewModelAndLiveData.class));
        }
        return new Presenter(this, ViewModelProviders.of(this).get(ViewModelAsContainer.class), GithubRepository.INSTANCE);
    }

    @Override
    public void showList(List<Repo> list) {
        recyclerView.setAdapter(new ReposAdapter(list));
    }

    public static void startActivityPresenterAsObserver(@NonNull Context context) {
        Intent i = new Intent(context, MvpActivity.class);
        i.putExtra(INTENT_EXTRA_MODE, MODE_PRESENTER_AS_OBSERVER);
        context.startActivity(i);
    }

    public static void startActivityViewModelAsContainer(@NonNull Context context) {
        Intent i = new Intent(context, MvpActivity.class);
        i.putExtra(INTENT_EXTRA_MODE, MODE_VM_AS_CONTAINER);
        context.startActivity(i);
    }
}
