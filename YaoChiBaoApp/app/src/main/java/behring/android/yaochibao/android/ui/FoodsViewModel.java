package behring.android.yaochibao.android.ui;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import javax.inject.Inject;

import behring.android.yaochibao.android.ui.base.BaseViewModel;
import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.domain.FoodsPresenter;
import dagger.hilt.android.lifecycle.HiltViewModel;
import lombok.Getter;

/**
 * 餐品列表页ViewModel
 *
 * @since 2021-04-15
 */
@HiltViewModel
public class FoodsViewModel extends BaseViewModel {
    private final FoodsPresenter foodsPresenter;
    @Getter
    private List<Food> foods = new ArrayList<>();

    @Inject
    public FoodsViewModel(FoodsPresenter foodsPresenter) {
        this.foodsPresenter = foodsPresenter;
    }

    public void loadFoods(@Nullable BiConsumer<List<Food>, Throwable> consumer) {
        int skipCount = foods.size();
        addDisposable(foodsPresenter.getFoods(null, skipCount, 10)
                .subscribe((foods, throwable) -> {
                    if (throwable == null) {
                        this.foods = foods;
                    }
                    consumer.accept(foods, throwable);
                }));
    }
}
