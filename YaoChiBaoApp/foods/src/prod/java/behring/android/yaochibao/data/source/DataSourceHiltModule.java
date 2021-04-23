package behring.android.yaochibao.foods.data.source;

import behring.android.yaochibao.foods.data.source.remote.RemoteDataSource;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

/**
 * 网络数据获取接口
 *
 * @since 2021-04-15
 */
@Module(includes = {BaseDataSourceHiltModule.class})
@InstallIn(SingletonComponent.class)
public final class DataSourceHiltModule {
    @Provides
    public static RemoteDataSource provideRemoteDataSource(Retrofit retrofit) {
        return retrofit.create(RemoteDataSource.class);
    }
}