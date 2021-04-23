package behring.android.yaochibao.foods.data;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.foods.data.model.Food;
import behring.android.yaochibao.foods.data.source.db.FoodDao;
import behring.android.yaochibao.foods.data.source.remote.RemoteDataSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FoodsRepository {
    private final RemoteDataSource remoteDataSource;
    private final FoodDao foodDao;

    @Inject
    public FoodsRepository(RemoteDataSource remoteDataSource, FoodDao foodDao) {
        this.remoteDataSource = remoteDataSource;
        this.foodDao = foodDao;
    }

    public Single<List<Food>> getFoods(String searchString, int skip, int limit) {
        return remoteDataSource.getFoods(searchString, skip, limit).subscribeOn(Schedulers.io())
                .doAfterSuccess(this::saveFoodsToDB);
    }

    public void saveFoodsToDB(List<Food> foods) {
        foodDao.insertFoods(foods.toArray(new Food[0])).subscribe();
    }

    public Single<List<Food>> getFoodsFromDB() {
        return foodDao.loadAllFoods().subscribeOn(Schedulers.io());
    }
}
