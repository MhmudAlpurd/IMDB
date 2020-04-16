package ir.mpkmro.imdb;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ir.mpkmro.imdb.recycler.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_Title;
    Button btn_Movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Title = findViewById(R.id.et_Title);
        btn_Movie=findViewById(R.id.btn_Movie);


        btn_Movie.setOnClickListener(v -> {

            String MovieName = et_Title.getText().toString();

            if (!(MovieName.isEmpty() || MovieName.length() == 0 || MovieName.equals("") || MovieName == null)) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                intent.putExtra("MovieName", MovieName);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Insert Correct Text!", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
