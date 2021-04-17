package behring.android.yaochibao.domain;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    @Mock
    FoodsRepository mockFoodsRepository;
    FoodsPresenter foodsPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        foodsPresenter = spy(new FoodsPresenter(mock(Context.class), mockFoodsRepository));
    }

    @Test
    public void should_return_foods_to_view_model_when_call_presenter_get_foods_and_network_available() {
        //given
        when(mockFoodsRepository.getFoods(anyString(), anyInt(), anyInt()))
                .thenReturn(Single.just(Arrays.asList(mock(Food.class), mock(Food.class))));
        doReturn(true).when(foodsPresenter).isNetworkAvailable();
        FoodsViewModel viewModel = new FoodsViewModel(foodsPresenter);
        //when
        List<Food> presentFoods = foodsPresenter.getFoods(anyString(), anyInt(), anyInt()).blockingGet();
        //then
        viewModel.loadFoods((foods, throwable) -> assertEquals(foods, presentFoods));
    }

    @Test
    public void should_call_repository_get_foods_when_call_presenter_get_foods_and_network_available() {
        //given
        when(mockFoodsRepository.getFoods(anyString(), anyInt(), anyInt())).thenReturn(Single.just(new ArrayList<>()));
        doReturn(true).when(foodsPresenter).isNetworkAvailable();
        //when
        foodsPresenter.getFoods(anyString(), anyInt(), anyInt()).blockingSubscribe();
        //then
        verify(mockFoodsRepository).getFoods(anyString(), anyInt(), anyInt());
    }
}