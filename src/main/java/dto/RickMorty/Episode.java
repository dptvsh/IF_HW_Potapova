package dto.RickMorty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Episode{
    public int id;
    public String name;
    @JsonProperty("air_date")
    public String airDate;
    public String episode;
    public ArrayList<String> characters;
    public String url;
    public Date created;
}