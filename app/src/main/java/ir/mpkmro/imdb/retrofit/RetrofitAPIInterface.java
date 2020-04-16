package ir.mpkmro.imdb.retrofit;

import ir.mpkmro.imdb.pojo.searchName.SearchName;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPIInterface {


    @GET("/")
    Call<SearchName> searchNameCall(@Query("s") String movieName, @Query("apikey") String apiKey);

}
