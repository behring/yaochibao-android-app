package behring.android.yaochibao.data.source.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import behring.android.yaochibao.data.model.Food;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;


@Dao
public interface FoodDao {
    @Query("SELECT * FROM food")
    Observable<List<Food>> loadAllFoods();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFoods(Food... foods);
}