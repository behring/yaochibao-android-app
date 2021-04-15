package behring.android.yaochibao;

import android.content.Context;

import behring.android.yaochibao.data.source.DataSourceHiltModule;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;

public class TestHelper {
    public static RemoteDataSource getRemoteDataSource(Context context) {
       return DataSourceHiltModule.provideRemoteDataSource(
                DataSourceHiltModule.provideRRetrofit(context,
                        DataSourceHiltModule.provideOkHttpClient(DataSourceHiltModule.provideHttpLoggingInterceptor())));
    }
}
