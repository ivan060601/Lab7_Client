package ClientStuff.Checkers;

import CityStructure.City;

import java.text.ParseException;

public interface Checker{
    public void checkEverything(City city, CheckParameter parameter) throws ParseException;
    public void checkEverything(City city) throws ParseException;
}

