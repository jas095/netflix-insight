package com.example.netflixapi.netflix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/netflix")
public class NetflixController {

    private NetflixService netflixService;

    @Autowired
    public NetflixController(NetflixService netflixService) {
        this.netflixService = netflixService;
    }

    @GetMapping("/insight/{id}")
    public Netflix findById(@PathVariable("id") Long id){
        return  netflixService.findById(id);
    }

    @GetMapping
    public List<Netflix> getNetflix(){
        return netflixService.All();
    }

    @RequestMapping("/fields/{id}")
    public ArrayList findByIDFilterByFields(@PathVariable("id") Long id, @RequestParam(value="type", required = false) String type,
                                            @RequestParam(value="title", required = false) String title, @RequestParam(value="director",required = false) String director,
                                            @RequestParam(value="cast",required = false) String cast, @RequestParam(value="country",required = false) String country,
                                            @RequestParam(value="release_year",required = false) String release_year, @RequestParam(value="rating",required = false) String rating,
                                            @RequestParam(value="duration",required = false) String duration, @RequestParam(value="listed_in",required = false) String listed_in){


        String[] key_fields = {type, title, director, cast, country, release_year, rating, duration, listed_in};
        return netflixService.findByIDFilterByFields(id,key_fields);
    }


    @RequestMapping("/insight/general/")
    public List<Netflix> generalSearchByFilters(@RequestParam(value="id", required=false) Long id, @RequestParam(value="type", required = false) String type,
                                                @RequestParam(value="title", required = false) String title, @RequestParam(value="director",required = false) String[] director,
                                                @RequestParam(value="cast",required = false) String[] cast, @RequestParam(value="country",required = false) String[] country,
                                                @RequestParam(value="release_year",required = false) Long release_year, @RequestParam(value="rating",required = false) String rating,
                                                @RequestParam(value="duration",required = false) Integer duration, @RequestParam(value="listed_in",required = false) String[] listed_in) {

        return netflixService.generalSearchByFilters(id, type,  title, director, cast,
                country, release_year, rating, duration, listed_in);
    }





}
