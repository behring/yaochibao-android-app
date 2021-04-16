package behring.android.yaochibao.data;


import android.content.Context;
import android.os.Build;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import behring.android.yaochibao.TestHelper;
import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.data.source.db.FoodDao;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@Config(sdk = Build.VERSION_CODES.P)
public class FoodsRepositoryTest {
    /**
     * 此测试依赖于Fake的BFF，在运行测试前确保启动BFF服务器。
     * */
    @Test
    public void should_return_foods_when_call_remote_data_source_get_foods() {
        //given
        Context stubContext = mock(Context.class);
        doReturn("http://localhost:3000").when(stubContext).getString(anyInt());
        RemoteDataSource remoteDataSource = TestHelper.getRemoteDataSource(stubContext);
        //when
        List<Food> foods = remoteDataSource.getFoods(null, 0, 0).blockingGet();
        //then
        assertEquals(20, foods.size());
    }

    @Test
    public void should_call_remote_data_source_when_call_foods_repository_get_foods() {
        //given
        RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
        doReturn(Single.just(new ArrayList<>())).when(remoteDataSource).getFoods(anyString(), anyInt(), anyInt());
        FoodsRepository foodsRepository = new FoodsRepository(remoteDataSource, mock(FoodDao.class));
        //when
        foodsRepository.getFoods(anyString(), anyInt(), anyInt()).blockingSubscribe();
        //then
        verify(remoteDataSource).getFoods(anyString(), anyInt(), anyInt());
    }
}