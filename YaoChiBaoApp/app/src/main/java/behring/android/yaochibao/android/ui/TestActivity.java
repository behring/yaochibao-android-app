package behring.android.yaochibao.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import behring.android.yaochibao.R;
import behring.android.yaochibao.foods.android.ui.FoodsActivity;

/**
 * 应用程序测试入口页
 *
 * @since 2021-04-15
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void startFoodsActivity(View view) {
        ActivityCompat.startActivity(this, new Intent(this, FoodsActivity.class), null);
    }
}