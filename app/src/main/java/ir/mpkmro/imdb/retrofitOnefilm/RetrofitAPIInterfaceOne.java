package ir.mpkmro.imdb.retrofitOnefilm;

import ir.mpkmro.imdb.pojo.searchID.Searchbyid;
import ir.mpkmro.imdb.pojo.searchID.Searchbyid;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPIInterfaceOne {

    @GET("/")
    Call<Searchbyid> searchIDCall(@Query("i") String IMDBID, @Query("apikey") String apiKey);


}
