package behring.android.yaochibao.data.source;

import android.content.Context;

import javax.inject.Singleton;

import behring.android.yaochibao.R;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
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
@InstallIn(SingletonComponent.class) // Installs FooModule in the generate SingletonComponent.
final class DataSourceHiltModule {
    @Provides
    static RemoteDataSource provideRemoteDataSource(Retrofit retrofit) {
        return retrofit.create(RemoteDataSource.class);
    }

    @Singleton
    @Provides
    static Retrofit provideRRetrofit(
            @ApplicationContext Context appContext,
            OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(appContext.getString(R.string.base_url))
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}