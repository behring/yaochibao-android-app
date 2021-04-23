package behring.android.yaochibao.foods.data.source;

import android.content.Context;

import behring.android.yaochibao.foods.data.source.remote.RemoteDataSource;
import behring.android.yaochibao.mock.MockRemoteDataSource;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * 网络数据获取接口
 *
 * @since 2021-04-15
 */
@Module(includes = {BaseDataSourceHiltModule.class})
@InstallIn(SingletonComponent.class)
public final class DataSourceHiltModule {
    @Provides
    public static RemoteDataSource provideRemoteDataSource(@ApplicationContext Context appContext) {
        return new MockRemoteDataSource(appContext);
    }
}