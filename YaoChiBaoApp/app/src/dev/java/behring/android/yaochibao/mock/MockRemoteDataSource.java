package behring.android.yaochibao.mock;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import behring.android.yaochibao.data.model.Food;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
import io.reactivex.rxjava3.core.Single;
import timber.log.Timber;

public class MockRemoteDataSource implements RemoteDataSource {
    private final Context context;

    public MockRemoteDataSource(Context context) {
        this.context = context;
    }

    @Override
    public Single<List<Food>> getFoods(String searchString, int skip, int limit) {
        return Single.just(new Gson().fromJson(getJsonStrForAssetsMockApi("getFoods"),
                new TypeToken<ArrayList<Food>>() {}.getType()));
    }

    private String getJsonStrForAssetsMockApi(String apiFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(String.format("mock/api/%s.json", apiFileName));

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            Timber.e(e);
        }
        return sb.toString();
    }
}
