package yaochibao.data.source.remote;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import behring.android.yaochibao.foods.data.model.Food;
import behring.android.yaochibao.foods.data.source.DataSourceHiltModule;
import behring.android.yaochibao.foods.data.source.remote.RemoteDataSource;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@Config(sdk = Build.VERSION_CODES.P)
public class RemoteDataSourceTest {
    /**
     * 工序1 通过fake Mobile BFF，实现RemoteDataSource调用BFF并返回结果到Repository
     *
     * 此测试依赖于Fake的BFF，在运行测试前确保启动BFF服务器
     * */
    @Test
    public void should_return_foods_when_call_remote_data_source_get_foods() {
        //given
        RemoteDataSource remoteDataSource = DataSourceHiltModule.provideRemoteDataSource(ApplicationProvider.getApplicationContext());
        //when
        List<Food> foods = remoteDataSource.getFoods(null, 0, 0).blockingGet();
        //then
        assertEquals(11, foods.size());
    }
}