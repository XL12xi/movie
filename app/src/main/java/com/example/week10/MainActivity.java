package com.example.week10;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        try {
            List<Movie> movieList = JsonUtility.loadMovies(this);
            MovieAdapter adapter = new MovieAdapter(movieList);
            recyclerViewMovies.setAdapter(adapter);
        } catch (IOException e) {
            showToast("Movie data file not found.");
            Log.e("MainActivity", "IOException: " + e.getMessage());
        } catch (JSONException e) {
            showToast("Error parsing movie data.");
            Log.e("MainActivity", "JSONException: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

