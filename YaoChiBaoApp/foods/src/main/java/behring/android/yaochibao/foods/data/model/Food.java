package behring.android.yaochibao.foods.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("priceCent")
    private long priceCent;
    @Ignore
    private Restaurant restaurant;
}
