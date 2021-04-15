package behring.android.yaochibao.data.source.remote;

import java.util.List;

import behring.android.yaochibao.data.model.Food;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 网络数据获取接口
 *
 * @since 2021-04-15
 */
public interface RemoteDataSource {
    @GET("customer/foods")
    Single<List<Food>> getFoods(@Query("searchString") String searchString,
                                @Query("skip") int skip,
                                @Query("limit") int limit);
}
