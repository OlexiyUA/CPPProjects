package com.agtevoase;

public class FootballClub {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String name;
    private final int year;
    private String city;

    public FootballClub(String name, String city, int year) {
        this.name = name;
        this.year = year;
        this.city = city;
    }

    public String toString() {
        return name + " " + city + "(" + year + ")";
    }

    public boolean equals(Object rhs) {
        if (!(rhs instanceof FootballClub))
            return false;
        FootballClub rhsc = (FootballClub) rhs;
        return this.name.equals(rhsc.name) && this.city.equals(rhsc.city) && (this.year == rhsc.year);
    }

}
