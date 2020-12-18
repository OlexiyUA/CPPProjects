package com.agtevoase;

import java.util.*;

public class MusicStoreManager {
    enum Type {
        WIND,
        PERCUSSION,
        STRING
    }

    public static class NameComparator implements Comparator<MusicalInstrument> {
        @Override
        public int compare(MusicalInstrument o1, MusicalInstrument o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public class ManufacturerAndNameComparator implements Comparator<MusicalInstrument> {
        @Override
        public int compare(MusicalInstrument o1, MusicalInstrument o2) {
            if (o1.getManufacturer().equals(o2.getManufacturer())) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return o1.getManufacturer().compareTo(o2.getManufacturer());
            }
        }
    }



    public List<MusicalInstrument> FindInstrumentsByType (List <MusicalInstrument> list, Type type) {
        List<MusicalInstrument> found = new ArrayList<>();
        for (MusicalInstrument current : list) {
            switch (type) {
                case WIND:
                    if (current instanceof WindInstrument) {
                        found.add(current);
                    }
                    break;
                case PERCUSSION:
                    if (current instanceof PercussionInstrument) {
                        found.add(current);
                    }
                    break;
                case STRING:
                    if (current instanceof StringInstrument) {
                        found.add(current);
                    }
                    break;
            }
        }
        return found;
    }

    public List<MusicalInstrument> FindBandInstruments (List <MusicalInstrument> list) {
        List<MusicalInstrument> band = new ArrayList<>();
        band.add(list.stream()
                .filter(instrument -> instrument instanceof PercussionInstrument)
                .findAny()
                .orElse(null));

        band.add(list.stream()
                .filter(instrument -> instrument instanceof WindInstrument)
                .findAny()
                .orElse(null));

        band.add(list.stream()
                .filter(instrument -> instrument instanceof StringInstrument)
                .findAny()
                .orElse(null));

        return band;
    }

    public List<MusicalInstrument> SortByName(List<MusicalInstrument> list, boolean ascending) {
        List<MusicalInstrument> copy = new ArrayList<>(list);
        if (ascending) {
            copy.sort(new NameComparator());
        } else {
            copy.sort(Collections.reverseOrder(new NameComparator()));
        }
        return copy;
    }

    public List<MusicalInstrument> SortByManufacturerAndName(List<MusicalInstrument> list, boolean ascending) {
        List<MusicalInstrument> copy = new ArrayList<>(list);
        if (ascending) {
            copy.sort(new ManufacturerAndNameComparator());
        } else {
            copy.sort(Collections.reverseOrder(new ManufacturerAndNameComparator()));
        }
        return copy;
    }

    public List<MusicalInstrument> SortByPrice(List<MusicalInstrument> list, boolean ascending) {
        List<MusicalInstrument> copy = new ArrayList<>(list);
        copy.sort(new Comparator<>() {
            @Override
            public int compare(MusicalInstrument o1, MusicalInstrument o2) {
                int res = Integer.compare(o1.getPrice(), o2.getPrice());
                if (ascending) {
                    return res;
                } else {
                    return -res;
                }
            }
        });
        return copy;
    }

    public List<MusicalInstrument> SortByRange(List<MusicalInstrument> list, boolean ascending) {
        List<MusicalInstrument> copy = new ArrayList<>(list);
        copy.sort((o1, o2) -> {
            int res = Integer.compare(o1.getRange(), o2.getRange());
            if (ascending) {
                return res;
            } else {
                return -res;
            }
        });
        return copy;
    }
}
