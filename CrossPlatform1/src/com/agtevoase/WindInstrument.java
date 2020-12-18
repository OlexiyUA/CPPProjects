package com.agtevoase;

public class WindInstrument extends MusicalInstrument{
    enum Type {
        BRASS,
        WOODWIND
    }

    public Type getType(){
        return type;
    }

    public String getMaterial() {
        return material;
    }

    private final Type type;

    private final String material;

    public WindInstrument(String name, String manufacturer, int range, int price, Type type, String material){
        super(name, manufacturer, range, price);
        this.type = type;
        this.material = material;
    }

    public String toString() {
        return this.getClass().getName() +
                "{name='" + getName() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", range=" + getRange() +
                " semitones, price=$" + getPrice() +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                '}' + '\n';
    }
}
