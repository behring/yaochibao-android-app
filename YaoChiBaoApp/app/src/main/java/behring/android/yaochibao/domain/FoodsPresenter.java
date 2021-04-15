package behring.android.yaochibao.domain;

import javax.inject.Inject;

import behring.android.yaochibao.data.FoodsRepository;

public class FoodsPresenter {
    private final FoodsRepository foodsRepository;

    @Inject
    public FoodsPresenter(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }
}
