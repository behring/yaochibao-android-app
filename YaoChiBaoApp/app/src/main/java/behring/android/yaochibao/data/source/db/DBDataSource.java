package behring.android.yaochibao.data.source.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import behring.android.yaochibao.data.model.Food;

@Database(entities = {Food.class}, version = 1, exportSchema = false)
public abstract class DBDataSource extends RoomDatabase {
    public abstract FoodDao foodDao();
}
