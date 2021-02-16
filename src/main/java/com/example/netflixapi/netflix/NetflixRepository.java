package com.example.netflixapi.netflix;

import java.util.ArrayList;

public interface NetflixRepository {

    public Netflix findById(Long id);

    public ArrayList<Netflix> All();

    public ArrayList<Netflix> generalSearchByFilters(Long id, String type, String title, String[] director, String[] cast,
                                                     String[] country,Long release_year, String rating, Integer duration,
                                                     String[] listed_in);

    public ArrayList findByIDFilterByFields(Long id, String[] str);
}
