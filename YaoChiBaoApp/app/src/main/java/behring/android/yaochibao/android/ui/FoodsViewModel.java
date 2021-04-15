package behring.android.yaochibao.android.ui;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import behring.android.yaochibao.domain.FoodsPresenter;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FoodsViewModel extends ViewModel {
    private final FoodsPresenter foodsPresenter;

    @Inject
    public FoodsViewModel(FoodsPresenter foodsPresenter) {
        this.foodsPresenter = foodsPresenter;
    }
}
