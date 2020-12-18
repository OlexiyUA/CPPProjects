package com.agtevoase;

public class StringInstrument extends MusicalInstrument {
    enum Type {
        LUTE,
        HARP,
        ZITHER
    }

    public Type getType() {
        return type;
    }

    public int getStringCount() {
        return stringCount;
    }

    private final Type type;

    private final int stringCount;

    public StringInstrument(String name, String manufacturer, int range, int price, Type type, int stringCount){
        super(name, manufacturer, range, price);
        this.type = type;
        this.stringCount = stringCount;
    }

    public String toString() {
        return this.getClass().getName() +
                "{name='" + getName() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", range=" + getRange() +
                " semitones, price=$" + getPrice() +
                ", type='" + type + '\'' +
                ", strings='" + stringCount + '\'' +
                '}' + '\n';
    }
}
