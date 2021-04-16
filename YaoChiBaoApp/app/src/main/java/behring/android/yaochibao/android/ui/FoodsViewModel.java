package behring.android.yaochibao.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

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

    public void loadFoods() {
        int skipCount = foods.size();
        addDisposable(foodsPresenter.getFoods(null, skipCount, 10)
                .subscribe((foods, throwable) -> {
                    if (throwable == null) {
                        this.foods = foods;
                    }
                }));
    }
}
