package behring.android.yaochibao.data.source;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import behring.android.yaochibao.BuildConfig;
import behring.android.yaochibao.data.source.db.DBDataSource;
import behring.android.yaochibao.data.source.db.FoodDao;
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
@Module
@InstallIn(SingletonComponent.class)
public final class BaseDataSourceHiltModule {

    @Provides
    public static DBDataSource provideDBDataSource(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(appContext, DBDataSource.class, "yaochibao-db").build();
    }

    @Provides
    public static FoodDao provideFoodDao(DBDataSource dbDataSource) {
        return dbDataSource.foodDao();
    }

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}