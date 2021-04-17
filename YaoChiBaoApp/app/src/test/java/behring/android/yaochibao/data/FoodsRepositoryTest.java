package behring.android.yaochibao.data;


import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.data.source.db.DBDataSource;
import behring.android.yaochibao.data.source.db.FoodDao;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@Config(sdk = Build.VERSION_CODES.P)
public class FoodsRepositoryTest {

    @Test
    public void should_call_remote_data_source_when_call_foods_repository_get_foods() {
        //given
        RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
        doReturn(Single.just(new ArrayList<>())).when(remoteDataSource).getFoods(anyString(), anyInt(), anyInt());
        FoodsRepository foodsRepository = spy(new FoodsRepository(remoteDataSource, mock(FoodDao.class)));
        doNothing().when(foodsRepository).saveFoodsToDB(any());
        //when
        foodsRepository.getFoods(anyString(), anyInt(), anyInt()).blockingSubscribe();
        //then
        verify(remoteDataSource).getFoods(anyString(), anyInt(), anyInt());
    }

    @Test
    public void should_call_food_dao_when_call_foods_repository_get_foods_from_db() {
        //given
        FoodDao foodDao = mock(FoodDao.class);
        doReturn(Flowable.just(new ArrayList<>())).when(foodDao).loadAllFoods();
        FoodsRepository foodsRepository = new FoodsRepository(mock(RemoteDataSource.class), foodDao);
        //when
        foodsRepository.getFoodsFromDB().blockingSubscribe();
        //then
        verify(foodDao).loadAllFoods();
    }
}