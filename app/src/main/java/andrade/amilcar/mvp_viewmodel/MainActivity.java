package andrade.amilcar.mvp_viewmodel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import andrade.amilcar.mvp_viewmodel.delegate.DelegateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startDelegateActivity(View view) {
        startActivity(new Intent(this, DelegateActivity.class));
    }
}
