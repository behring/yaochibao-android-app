package behring.android.yaochibao.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import behring.android.yaochibao.android.ui.FoodsViewModel;
import behring.android.yaochibao.data.FoodsRepository;
import behring.android.yaochibao.data.model.Food;
import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FoodsPresenterTest {
    @Test
    public void should_return_foods_to_view_model_when_call_presenter() {
        //given
        FoodsRepository stubFoodsRepository = mock(FoodsRepository.class);
        when(stubFoodsRepository.getFoods(anyString(), anyInt(), anyInt()))
                .thenReturn(Single.just(Arrays.asList(mock(Food.class), mock(Food.class))));
        FoodsPresenter presenter = new FoodsPresenter(stubFoodsRepository);
        FoodsViewModel viewModel = new FoodsViewModel(presenter);
        //when
        List<Food> presentFoods = presenter.getFoods(anyString(), anyInt(), anyInt()).blockingGet();
        //then
        viewModel.loadFoods((foods, throwable) -> assertEquals(foods, presentFoods));
    }

    @Test
    public void should_call_repository_when_call_presenter_get_foods() {
        //given
        FoodsRepository mockFoodsRepository = mock(FoodsRepository.class);
        when(mockFoodsRepository.getFoods(anyString(), anyInt(), anyInt())).thenReturn(Single.just(new ArrayList<>()));
        FoodsPresenter presenter = new FoodsPresenter(mockFoodsRepository);
        //when
        presenter.getFoods(anyString(), anyInt(), anyInt()).blockingSubscribe();
        //then
        verify(mockFoodsRepository).getFoods(anyString(), anyInt(), anyInt());
    }
}