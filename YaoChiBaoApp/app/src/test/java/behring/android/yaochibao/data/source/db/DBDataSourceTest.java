package behring.android.yaochibao.data.source.db;

import android.content.Context;
import android.os.Build;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import behring.android.yaochibao.TestHelper;
import behring.android.yaochibao.data.model.Food;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@Config(sdk = Build.VERSION_CODES.P)
public class DBDataSourceTest {
    private FoodDao foodDao;
    private DBDataSource dbDataSource;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        dbDataSource = Room.inMemoryDatabaseBuilder(context, DBDataSource.class).build();
        foodDao = dbDataSource.foodDao();
    }

    @After
    public void closeDb() {
        dbDataSource.close();
    }

    /**
     * 此测试依赖于Fake的内存DB
     * */
    @Test
    public void should_return_foods_when_call_db_data_source_load_all_foods() {
        //given
        Food[] foods = TestHelper.mockFoods(10);
        //when
        foodDao.insertFoods(foods).subscribeOn(Schedulers.io()).blockingSubscribe();
        //then
        assertEquals(foods.length, foodDao.loadAllFoods().blockingFirst().size());
    }
}