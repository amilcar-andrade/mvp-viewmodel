package andrade.amilcar.mvp_viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import andrade.amilcar.mvp_viewmodel.mvp_viewmodel.MvpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPresenterActivity(@NonNull View view) {
        if (R.id.button_presenter_as_observer == view.getId()) {
            MvpActivity.startActivityPresenterAsObserver(this);
        } else {
            MvpActivity.startActivityViewModelAsContainer(this);
        }
    }
}
