package behring.android.yaochibao.data;

import java.util.List;

import javax.inject.Inject;

import behring.android.yaochibao.data.source.db.DBDataSource;
import behring.android.yaochibao.data.source.model.Food;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FoodsRepository {
    private final RemoteDataSource remoteDataSource;
    private final DBDataSource dbDataSource;

    @Inject
    public FoodsRepository(RemoteDataSource remoteDataSource, DBDataSource dbDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.dbDataSource = dbDataSource;
    }

    public Single<List<Food>> getFoods(String searchString, int skip, int limit) {
        return remoteDataSource.getFoods(searchString, skip, limit).subscribeOn(Schedulers.io());
    }
}
