package behring.android.yaochibao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.data.source.DataSourceHiltModule;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;

import static org.mockito.Mockito.mock;

public class TestHelper {
    public static RemoteDataSource getRemoteDataSource(Context context) {
       return DataSourceHiltModule.provideRemoteDataSource(
                DataSourceHiltModule.provideRRetrofit(context,
                        DataSourceHiltModule.provideOkHttpClient(DataSourceHiltModule.provideHttpLoggingInterceptor())));
    }

    public static Food[] mockFoods(int mockCount) {
        List<Food> foods = new ArrayList<>();
        for (int i=0;i<mockCount;i++) {
            foods.add(new Food(String.valueOf(i)));
        }
        return foods.toArray(new Food[0]);
    }
}
