package com.example.netflixapi.netflix;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class NetflixService implements NetflixRepository {
    private final ArrayList<Netflix> nett;

    public NetflixService() {
        nett = new ArrayList();
    }

    private void ingest_transforms_store_data(){
        //String dataSource = System.getProperty("user.dir")+"/netflix_db.csv";
        String dataSource = "src/main/resources/netflix_db.csv";
        Reader in; String line="";
        String[] listed_in,director, cast, country;
        Integer duration;

        try {
            in = new FileReader(dataSource);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                director = record.get(2).split(",");
                cast = record.get(3).split(",");
                country = record.get(4).split(",");
                listed_in = record.get(8).split(",");

                for (int i=0; i < director.length; i++) director[i] = director[i].trim();
                for (int i=0; i < cast.length; i++) cast[i] = cast[i].trim();
                for (int i=0; i < country.length; i++) country[i] = country[i].trim();
                for (int i = 0; i < listed_in.length; i++) listed_in[i] = listed_in[i].trim();


                duration = Integer.valueOf(record.get(7).replaceAll("[^0-9]",""));
                Netflix netflix = new Netflix(record.get(0), record.get(1), director, cast, country, Long.parseLong(record.get(5)), record.get(6), duration, listed_in);
                nett.add(netflix);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("This file is not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nett.isEmpty()!=true){
        transformDataSourceToJson(nett);}else{
            throw new IllegalStateException("This file is empty");
        }
    }

    private void transformDataSourceToJson(ArrayList<Netflix> nett){
        ObjectMapper mapper = new ObjectMapper();
        try {
            //for window c://temp/employee.json
            //mapper.writeValue(new File(System.getProperty("user.dir")+"/netflix_db.json"), nett);
            mapper.writeValue(new File("src/main/resources/netflix_db.json"), nett);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Netflix> All() {
        ingest_transforms_store_data();
        return nett;
    }

    @Override
    public Netflix findById(Long id){
        Netflix netflixListID = null;
        //File jsonSource = new File(System.getProperty("user.dir")+"/netflix_db.json");
        File jsonSource = new File("src/main/resources/netflix_db.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            if (Double.NaN == id || id < 1 ){
                throw new IllegalStateException("This is not valid Id");
            }

            ArrayList<Netflix> netflixListObj = mapper.readValue(jsonSource, new TypeReference<ArrayList<Netflix>>(){});

            for (Netflix netflix : netflixListObj) {
                if (netflix.getId().equals(id)) {
                    netflixListID = netflix;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return netflixListID;
    }

    @Override
    public ArrayList findByIDFilterByFields(Long id, String[] str) {
        Netflix id_fields = findById(id);
        ArrayList result = new ArrayList<>();

        for (String s :str){
            if (s!=null){
                switch(s) {
                    case "type": result.add(id_fields.getType()); break;
                    case "title": result.add(id_fields.getTitle()); break;
                    case "director":result.add(id_fields.getDirector()); break;
                    case "cast": result.add(id_fields.getCast()); break;
                    case "country": result.add(id_fields.getCountry()); break;
                    case "release_year": result.add(id_fields.getRelease_year()); break;
                    case "rating": result.add(id_fields.getRating()); break;
                    case "duration": result.add(id_fields.getDuration()); break;
                    case "listed_in": result.add(id_fields.getListed_in()); break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + s);
                }
            }
        }


        return result;
    }


    @Override
    public ArrayList<Netflix> generalSearchByFilters(Long id, String type, String title, String[] director, String[] cast,
                                                     String[] country,Long release_year, String rating, Integer duration, String[] listed_in) {

        String conditionST;
        ArrayList<Netflix> netflixListObj;
        ArrayList<Netflix> netflixListAPI = new ArrayList<>();
        //File jsonSource = new File(System.getProperty("user.dir")+"/netflix_db.json");
        File jsonSource = new File("src/main/resources/netflix_db.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            netflixListObj = mapper.readValue(jsonSource, new TypeReference<ArrayList<Netflix>>(){});

            for (Netflix netflix : netflixListObj) {
                if (netflix.getId().equals(id) || netflix.getType().equals(type) || netflix.getTitle().equals(title) ||
                            Arrays.equals(director,netflix.getDirector()) || Arrays.equals(netflix.getCast(),cast) ||
                            Arrays.equals(country, netflix.getCountry()) || netflix.getRelease_year().equals(release_year) ||
                            netflix.getRating().equals(rating) || netflix.getDuration().equals(duration) ||
                            Arrays.equals(listed_in,netflix.getListed_in())) {
                        netflixListAPI.add(netflix);
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return netflixListAPI;
    }

}
