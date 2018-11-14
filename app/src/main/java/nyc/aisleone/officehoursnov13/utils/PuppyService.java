package nyc.aisleone.officehoursnov13.utils;

import nyc.aisleone.officehoursnov13.models.PuppyItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PuppyService {

    @GET("breeds/image/random")
    Call<PuppyItem> getPuppyImage();

}
