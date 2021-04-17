package behring.android.yaochibao.domain;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.data.FoodsRepository;
import behring.android.yaochibao.data.model.Food;
import io.reactivex.rxjava3.core.Single;

public class FoodsPresenter {
    private final FoodsRepository foodsRepository;

    @Inject
    public FoodsPresenter(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }

    public Single<List<Food>> getFoods(String searchString, int skipCount, int count) {
        return foodsRepository.getFoods(searchString, skipCount, count);
    }

    public Single<List<Food>> getFoodsFromCache() {
        return foodsRepository.getFoodsFromDB();
    }
}
