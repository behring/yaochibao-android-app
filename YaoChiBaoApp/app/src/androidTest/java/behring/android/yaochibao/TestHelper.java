package behring.android.yaochibao;

import java.util.ArrayList;
import java.util.List;

import behring.android.yaochibao.data.model.Food;

public class TestHelper {
    public static Food mockFood(int id) {
        Food food = new Food(String.valueOf(id));
        food.setName("超级鸡排饭");
        food.setPriceCent(3200);
        return food;
    }

    public static Food[] mockFoods(int mockCount) {
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < mockCount; i++) {
            foods.add(mockFood(i));
        }
        return foods.toArray(new Food[0]);
    }
}
