package behring.android.yaochibao.data;

import javax.inject.Inject;

import behring.android.yaochibao.data.source.db.DBDataSource;
import behring.android.yaochibao.data.source.remote.RemoteDataSource;

public class FoodsRepository {
    private final RemoteDataSource remoteDataSource;
    private final DBDataSource dbDataSource;

    @Inject
    public FoodsRepository(RemoteDataSource remoteDataSource, DBDataSource dbDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.dbDataSource = dbDataSource;
    }
}
