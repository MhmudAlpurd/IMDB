package ir.mpkmro.imdb.recycler;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.mpkmro.imdb.R;
import ir.mpkmro.imdb.pojo.searchName.Search;
import ir.mpkmro.imdb.pojo.searchName.SearchName;
import ir.mpkmro.imdb.retrofit.RetrofitAPIInterface;
import ir.mpkmro.imdb.retrofit.RetrofitServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    Button btn;
    String MovieName;
    EditText editText;
    RecyclerView myRecycler;

    private SearchAdapter mySearchAdapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        String MovieName = getIntent().getStringExtra("MovieName");


        RetrofitAPIInterface client = RetrofitServiceGenerator.createService(RetrofitAPIInterface.class);
        Call<SearchName> searchNameCall = client.searchNameCall(MovieName, "77d67210");
        searchNameCall.enqueue(new Callback<SearchName>() {
            @Override
            public void onResponse(Call<SearchName> call, Response<SearchName> response) {

                List<Search> searchList = response.body().getSearch();
                if (searchList != null) {
                    setupRecyclerView(searchList);
                } else {
                    Toast.makeText(RecyclerViewActivity.this, "We dont find any reslut for your request.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<SearchName> call, Throwable t) {
                Toast.makeText(RecyclerViewActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void setupRecyclerView(List<Search> searchList) {
        myRecycler = findViewById(R.id.rv_film);
        mySearchAdapter = new SearchAdapter(searchList, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myRecycler.setAdapter(mySearchAdapter);
        myRecycler.setLayoutManager(linearLayoutManager);
        myRecycler.setItemAnimator(new DefaultItemAnimator());
    }
}
