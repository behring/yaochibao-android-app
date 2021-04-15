package behring.android.yaochibao.data.source.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 餐品信息
 *
 * @since 2021-04-15
 */
@RequiredArgsConstructor
@Getter
public class Food {
    private final String id;
    private final String name;
    private final String releaseDate;
    private final Restaurant restaurant;
}
