package ir.mpkmro.imdb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import ir.mpkmro.imdb.pojo.searchID.Searchbyid;
import ir.mpkmro.imdb.pojo.searchName.SearchName;
import ir.mpkmro.imdb.retrofit.RetrofitAPIInterface;
import ir.mpkmro.imdb.retrofit.RetrofitServiceGenerator;
import ir.mpkmro.imdb.retrofitOnefilm.RetrofitAPIInterfaceOne;
import ir.mpkmro.imdb.retrofitOnefilm.RetrofitServiceGeneratorOne;
import ir.mpkmro.imdb.retrofitOnefilm.RetrofitAPIInterfaceOne;
import ir.mpkmro.imdb.retrofitOnefilm.RetrofitServiceGeneratorOne;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {
    public String IMDBID;
    ImageView des_Poster;
    TextView des_Title;
    TextView des_IMDB;
    TextView des_Year;
    TextView des_Director;
    TextView des_Actor;
    TextView des_Plot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        des_Poster = findViewById(R.id.des_Poster);
        des_Title = findViewById(R.id.des_Title);
        des_IMDB = findViewById(R.id.des_IMDB);
        des_Year = findViewById(R.id.des_Year);
        des_Director = findViewById(R.id.des_Director);
        des_Actor = findViewById(R.id.des_Actor);
        des_Plot = findViewById(R.id.des_Plot);


        getIncomingIntent();

        RetrofitAPIInterfaceOne client = RetrofitServiceGeneratorOne.createService(RetrofitAPIInterfaceOne.class);
        Call<Searchbyid> searchIDCall = client.searchIDCall(IMDBID, "77d67210");
        searchIDCall.enqueue(new Callback<Searchbyid>() {
            @Override
            public void onResponse(Call<Searchbyid> call, Response<Searchbyid> response) {


                String Title = response.body().getTitle();
                String Year = response.body().getYear();
                String Director = response.body().getDirector();
                String Actors = response.body().getActors();
                String IMDBRating = response.body().getImdbRating();
                String Poster = response.body().getPoster();
                String Plot = response.body().getPlot();


                Picasso.get().load(Poster).into(des_Poster);
                des_Title.setText(Title);
                des_IMDB.setText("IMDB: "+IMDBRating);
                des_Year.setText("Year: "+Year);
                des_Director.setText("Director: "+Director);
                des_Actor.setText("Actors: "+Actors);
                des_Plot.setText("Plot: "+Plot);


            }

            @Override
            public void onFailure(Call<Searchbyid> call, Throwable t) {
                Toast.makeText(DescriptionActivity.this, "Fail", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void getIncomingIntent() {
        if (getIntent().hasExtra("IMDBID")) {
            IMDBID = getIntent().getStringExtra("IMDBID");
        } else {
            Toast.makeText(this, "Your request is wrong, Please try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
