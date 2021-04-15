package behring.android.yaochibao.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.android.ui.base.BaseViewModel;
import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.domain.FoodsPresenter;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * 餐品列表页ViewModel
 *
 * @since 2021-04-15
 */
@HiltViewModel
public class FoodsViewModel extends BaseViewModel {
    private final FoodsPresenter foodsPresenter;
    private MutableLiveData<List<Food>> foods;

    @Inject
    public FoodsViewModel(FoodsPresenter foodsPresenter) {
        this.foodsPresenter = foodsPresenter;
    }

    public LiveData<List<Food>> getFoods() {
        if (foods == null) {
            foods = new MutableLiveData<>();
            loadFoods();
        }
        return foods;
    }

    private void loadFoods() {
        int skipCount = 0;
        if (foods.getValue() != null) {
            skipCount = foods.getValue().size();
        }
        addDisposable(foodsPresenter.getFoods(null, skipCount, 10)
                .subscribe((foods, throwable) -> {
                    if (throwable == null) {
                        this.foods.postValue(foods);
                    }
                }));
    }
}
