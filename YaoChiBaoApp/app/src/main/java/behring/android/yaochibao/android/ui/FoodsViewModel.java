package behring.android.yaochibao.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.android.ui.base.BaseViewModel;
import behring.android.yaochibao.data.source.model.Food;
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

    public LiveData<List<Food>> getFoods(String searchString) {
        if (foods == null) {
            foods = new MutableLiveData<>();
            loadFoods(searchString);
        }
        return foods;
    }

    private void loadFoods(String searchString) {
        addDisposable(foodsPresenter.getFoods(searchString, foods.getValue().size(), 10)
                .subscribe((foods, throwable) -> {
                    if (throwable == null) {
                        this.foods.postValue(foods);
                    }
                }));
    }
}
