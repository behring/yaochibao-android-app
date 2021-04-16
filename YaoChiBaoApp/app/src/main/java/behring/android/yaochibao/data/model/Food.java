package behring.android.yaochibao.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * 餐品信息
 *
 * @since 2021-04-15
 */

@Data
@Entity
public class Food {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String imageUrl;
    private long priceCent;
    @Ignore
    private Restaurant restaurant;
}
