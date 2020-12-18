package com.agtevoase;

public abstract class MusicalInstrument {
    public int getPrice() {
        return price;
    }

    public int getRange() {
        return range;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    private final int price;
    private final int range; // range in semitones; e.g. 87 for fortepiano
    private final String manufacturer;
    private final String name;

    public MusicalInstrument(String name, String manufacturer, int range, int price){
        this.name = name;
        this.manufacturer = manufacturer;
        this.range = range;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getClass().getName() +
                "{name='" + getName() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", range=" + getRange() +
                " semitones, price=$" + getPrice() +
                '}' + '\n';
    }
}
