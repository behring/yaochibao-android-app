package behring.android.yaochibao.android.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import behring.android.yaochibao.R;
import behring.android.yaochibao.android.ui.base.BaseActivity;
import behring.android.yaochibao.databinding.ActivityFoodsBinding;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * 餐品列表页
 *
 * @since 2021-04-15
 */
@AndroidEntryPoint
public class FoodsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFoodsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_foods);
        setContentView(R.layout.activity_foods);
        binding.setFoodsViewModel(new ViewModelProvider(this).get(FoodsViewModel.class));
    }
}