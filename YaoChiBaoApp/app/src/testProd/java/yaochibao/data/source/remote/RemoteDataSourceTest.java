package behring.android.yaochibao.data.source.remote;

import android.content.Context;

import org.junit.Test;

import java.util.List;

import behring.android.yaochibao.TestHelper;
import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.data.source.BaseDataSourceHiltModule;
import behring.android.yaochibao.data.source.DataSourceHiltModule;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RemoteDataSourceTest {
    /**
     * 工序1 通过fake Mobile BFF，实现RemoteDataSource调用BFF并返回结果到Repository
     *
     * 此测试依赖于Fake的BFF，在运行测试前确保启动BFF服务器
     * */
    @Test
    public void should_return_foods_when_call_remote_data_source_get_foods() {
        //given
        Context stubContext = mock(Context.class);
        doReturn("http://localhost:3000").when(stubContext).getString(anyInt());
        RemoteDataSource remoteDataSource = getRemoteDataSource(stubContext);
        //when
        List<Food> foods = remoteDataSource.getFoods(null, 0, 0).blockingGet();
        //then
        assertEquals(20, foods.size());
    }

    private RemoteDataSource getRemoteDataSource(Context context) {
        return DataSourceHiltModule.provideRemoteDataSource(
                BaseDataSourceHiltModule.provideRRetrofit(context,
                        BaseDataSourceHiltModule.provideOkHttpClient(BaseDataSourceHiltModule.provideHttpLoggingInterceptor())));
    }
}