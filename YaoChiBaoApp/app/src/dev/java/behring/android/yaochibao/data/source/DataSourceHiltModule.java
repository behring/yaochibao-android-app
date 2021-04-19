package behring.android.yaochibao.data.source;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import behring.android.yaochibao.R;
import behring.android.yaochibao.data.source.db.DBDataSource;
import behring.android.yaochibao.data.source.db.FoodDao;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
import behring.android.yaochibao.mock.MockRemoteDataSource;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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