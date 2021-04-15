package behring.android.yaochibao.android.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

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
    @Inject
    FoodsAdapter foodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFoodsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_foods);
        binding.foodsView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.foodsView.setAdapter(foodsAdapter);
        FoodsViewModel foodsViewModel = new ViewModelProvider(this).get(FoodsViewModel.class);
        foodsViewModel.getFoods().observe(this, foods -> {
            foodsAdapter.setList(foods);
        });
    }

    public static class FoodsAdapter extends BaseQuickAdapter<Food, BaseViewHolder> {
        private ItemFoodBinding binding;

        @Inject
        public FoodsAdapter() {
            super(R.layout.item_food);
        }

        @Override
        protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
            binding = ItemFoodBinding.bind(viewHolder.itemView);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder viewHolder, Food item) {
            if (item == null || binding == null) {
                return;
            }
            binding.name.setText(item.getName());
            binding.priceCent.setText(String.format("%s%s", item.getPriceCent() / 100.0, getContext().getString(R.string.yuan)));
            Glide.with(getContext()).load(item.getImageUrl()).into(binding.image);
            binding.executePendingBindings();
        }
    }
}