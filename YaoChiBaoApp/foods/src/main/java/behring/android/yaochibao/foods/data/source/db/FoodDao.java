package behring.android.yaochibao.foods.data.source.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import behring.android.yaochibao.foods.data.model.Food;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    Single<List<Food>> loadAllFoods();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertFoods(Food... foods);
}