package behring.android.yaochibao.data.source.remote;

import java.util.List;

import behring.android.yaochibao.data.source.model.Food;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络数据获取接口
 *
 * @since 2021-04-15
 */
public interface RemoteDataSource {
    @GET("customer/foods")
    Single<List<Food>> getFoods(@Path("searchString") String searchString,
                                @Path("skip") int skip,
                                @Path("limit") int limit);
}
