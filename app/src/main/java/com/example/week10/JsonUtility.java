package com.example.week10;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtility {

    public static List<Movie> loadMovies(Context context) throws IOException, JSONException {
        List<Movie> movies = new ArrayList<>();

        InputStream is = context.getAssets().open("movies.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                Movie movie = parseMovie(obj);
                movies.add(movie);
            } catch (BadMovieDataException e) {
                Log.e("JsonUtility", "Skipping bad movie: " + e.getMessage());
            } catch (JSONException e) {
                Log.e("JsonUtility", "Malformed movie entry, skipping.");
            }
        }

        return movies;
    }

    private static Movie parseMovie(JSONObject obj) throws BadMovieDataException {
        String title = obj.optString("title", null);
        String genre = obj.optString("genre", null);
        String poster = obj.optString("poster", null);
        int year;

        try {
            year = obj.getInt("year");
        } catch (Exception e) {
            throw new BadMovieDataException("Invalid or missing year");
        }

        return new Movie(title, year, genre, poster);
    }
}

