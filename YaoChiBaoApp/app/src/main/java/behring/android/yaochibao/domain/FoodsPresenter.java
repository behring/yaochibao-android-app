package behring.android.yaochibao.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.data.FoodsRepository;
import behring.android.yaochibao.data.model.Food;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.core.Single;

public class FoodsPresenter {
    private final FoodsRepository foodsRepository;
    private final Context context;

    @Inject
    public FoodsPresenter(@ApplicationContext Context context, FoodsRepository foodsRepository) {
        this.context = context;
        this.foodsRepository = foodsRepository;
    }

    public Single<List<Food>> getFoods(String searchString, int skipCount, int count) {
        if (isNetworkAvailable()) {
            return foodsRepository.getFoods(searchString, skipCount, count);
        } else {
            return foodsRepository.getFoodsFromDB();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isAvailable();
    }
}
