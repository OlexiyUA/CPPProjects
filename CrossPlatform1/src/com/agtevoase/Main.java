package com.agtevoase;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<MusicalInstrument> storage = new ArrayList<>();
        storage.add(new StringInstrument("Guitar", "Gibson", 28, 4000,
                StringInstrument.Type.LUTE, 6));
        storage.add(new StringInstrument("Piano", "Pearl River", 87, 3500,
                StringInstrument.Type.ZITHER, 230));
        storage.add(new WindInstrument("Alto sax", "Stagg", 18, 400,
                WindInstrument.Type.WOODWIND, "brass"));
        storage.add(new WindInstrument("Trombone", "Startone", 28, 200,
                WindInstrument.Type.BRASS, "brass"));
        storage.add(new AuxiliaryPercussionInstrument("Drum set", "Tama", 800));
        storage.add(new TunedPercussionInstrument("Xylophone", "Maxtone", 36, 800));
        System.out.println("Before sorts:");
        System.out.println(storage.toString());
        MusicStoreManager manager = new MusicStoreManager();
        System.out.println("Searching all string instruments:");
        System.out.println(manager.FindInstrumentsByType(storage, MusicStoreManager.Type.STRING));
        System.out.println("Searching for instruments for band:");
        System.out.println(manager.FindBandInstruments(storage));
        System.out.println("Sorting all instruments by name (using static inner class):");
        System.out.println(manager.SortByName(storage, true));
        System.out.println("Sorting all instruments by manufacturer and name, descending (using inner class):");
        System.out.println(manager.SortByManufacturerAndName(storage, false));
        System.out.println("Sorting all instruments by price (using anonymous inner class):");
        System.out.println(manager.SortByPrice(storage, true));
        System.out.println("Sorting all instruments by note range, descending (using inner class):");
        System.out.println(manager.SortByRange(storage, false));

    }
}
