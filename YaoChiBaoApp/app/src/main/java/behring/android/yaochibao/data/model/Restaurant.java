package behring.android.yaochibao.data.model;

import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 餐饮商家信息
 *
 * @since 2021-04-15
 */
@RequiredArgsConstructor
@Getter
public class Restaurant {
    private final String id;
    private final String name;
    @Nullable
    private final String homePage;
    @Nullable
    private final String phone;
}
