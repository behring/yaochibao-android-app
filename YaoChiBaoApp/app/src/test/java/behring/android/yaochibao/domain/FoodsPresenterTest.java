package behring.android.yaochibao.domain;

import android.content.Context;

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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FoodsPresenterTest {
    @Test
    public void should_return_foods_to_view_model_when_call_presenter_get_foods_and_network_available() {
        //given
        FoodsRepository stubFoodsRepository = mock(FoodsRepository.class);
        when(stubFoodsRepository.getFoods(anyString(), anyInt(), anyInt()))
                .thenReturn(Single.just(Arrays.asList(mock(Food.class), mock(Food.class))));
        FoodsPresenter spyPresenter = spy(new FoodsPresenter(mock(Context.class), stubFoodsRepository));
        doReturn(true).when(spyPresenter).isNetworkAvailable();
        FoodsViewModel viewModel = new FoodsViewModel(spyPresenter);
        //when
        List<Food> presentFoods = spyPresenter.getFoods(anyString(), anyInt(), anyInt()).blockingGet();
        //then
        viewModel.loadFoods((foods, throwable) -> assertEquals(foods, presentFoods));
    }

    @Test
    public void should_call_repository_get_foods_when_call_presenter_get_foods_and_network_available() {
        //given
        FoodsRepository mockFoodsRepository = mock(FoodsRepository.class);
        when(mockFoodsRepository.getFoods(anyString(), anyInt(), anyInt())).thenReturn(Single.just(new ArrayList<>()));
        FoodsPresenter spyPresenter = spy(new FoodsPresenter(mock(Context.class), mockFoodsRepository));
        doReturn(true).when(spyPresenter).isNetworkAvailable();
        //when
        spyPresenter.getFoods(anyString(), anyInt(), anyInt()).blockingSubscribe();
        //then
        verify(mockFoodsRepository).getFoods(anyString(), anyInt(), anyInt());
    }
}