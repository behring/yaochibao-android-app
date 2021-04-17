package behring.android.yaochibao.android.ui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

import javax.inject.Inject;

import behring.android.yaochibao.R;
import behring.android.yaochibao.android.ui.base.BaseActivity;
import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.databinding.ActivityFoodsBinding;
import behring.android.yaochibao.databinding.ItemFoodBinding;
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
        initRecyclerView(binding.foodsView);
        binding.setFoodsViewModel(new ViewModelProvider(this).get(FoodsViewModel.class));
        binding.getFoodsViewModel().loadFoods(null);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new FoodsAdapter());
    }

    @BindingAdapter("data")
    public static void loadRecyclerViewData(RecyclerView recyclerView, List<Food> foods) {
        if (recyclerView.getAdapter() instanceof FoodsAdapter) {
            ((FoodsAdapter)recyclerView.getAdapter()).setList(foods);
        }
    }

    public static class FoodsAdapter extends BaseQuickAdapter<Food, BaseDataBindingHolder<ItemFoodBinding>> {
        @Inject
        public FoodsAdapter() {
            super(R.layout.item_food);
        }

        @BindingAdapter("imageUrl")
        public static void loadImage(ImageView imageView, String imageUrl) {
            Glide.with(imageView).load(imageUrl).into(imageView);
        }

        @Override
        protected void convert(@NotNull BaseDataBindingHolder<ItemFoodBinding> bindingHolder, Food food) {
            ItemFoodBinding binding = bindingHolder.getDataBinding();
            if (food == null || binding == null) {
                return;
            }
            binding.setFood(food);
            binding.executePendingBindings();
        }
    }
}