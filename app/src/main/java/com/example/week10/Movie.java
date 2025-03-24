package com.example.week10;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String poster;

    public Movie(String title, int year, String genre, String poster) throws BadMovieDataException {
        if (title == null || title.trim().isEmpty()) {
            throw new BadMovieDataException("Missing or empty title");
        }
        if (year < 1888 || year > 2100) {
            throw new BadMovieDataException("Invalid year: " + year);
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new BadMovieDataException("Missing or empty genre");
        }

        this.title = title;
        this.year = year;
        this.genre = genre;
        this.poster = (poster == null || poster.trim().isEmpty()) ? "placeholder" : poster;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }
}

