package com.agtevoase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<FootballClub> list = listFromFile("C:\\Users\\andro\\Desktop\\nulp\\labs\\CP\\cp2\\main.txt");
        System.out.println("Original list:\n" + list.toString());
        menu:
        while (true)
        {
            System.out.println("\n\n1. Make a map with producers' cities keys and football clubs lists values.\n" +
                    "   Print first n positions from each list.\n" +
                    "2. Determine how many football clubs share same name.\n" +
                    "3. Read 2 input club information from 2 different files.\n" +
                    "   Make collection consisting only of mutual lists members.\n" +
                    "4. Exit.");
            int res = scanner.nextInt();
            switch (res){
                case 1: System.out.println("Enter n: ");
                        int n = scanner.nextInt();
                        mapAndOutput(list, n);
                    break;

                case 2: System.out.println("Show unique names(Y/N)? ");
                        String ch = scanner.next();
                        countRepetitionsAndOutput(list, ch.equals("Y"));
                    break;

                case 3: List<FootballClub> a = listFromFile("C:\\Users\\andro\\Desktop\\nulp" +
                            "\\labs\\CP\\cp2\\part1.txt");
                        List<FootballClub> b = listFromFile("C:\\Users\\andro\\Desktop\\nulp" +
                            "\\labs\\CP\\cp2\\part2.txt");
                        intersectAndOutput(a, b);
                    break;

                case 4:
                    break menu;

                default:
                    break;
            }

        }
    }

    public static List<FootballClub> listFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        List<FootballClub> list = new ArrayList<>();
        FootballClub club;

        while (scanner.hasNextLine()) {
            String[] strs = scanner.nextLine().split(",");
            club = new FootballClub(strs[0], strs[1], Integer.parseInt(strs[2]));
            list.add(club);
        }

        return list;
    }

    public static void mapAndOutput(List<FootballClub> list, int n) {
        Map<String, List<FootballClub>> map = list.stream().collect(Collectors.groupingBy(FootballClub::getCity));
        System.out.println("Generated map: \n");
        map.forEach((city, clubs) -> {
            System.out.println("'" + city + "': " +
                    clubs.stream().limit(n).collect(Collectors.toList()).toString());
        });
    }

    public static void countRepetitionsAndOutput(List<FootballClub> list, boolean showSingleCases) {
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(FootballClub::getName, Collectors.counting()));
        map.forEach((name, count) -> {
            if (showSingleCases || count > 1) {
                System.out.println("'" + name + "': " + count);
            }
        });
    }

    public static void intersectAndOutput(List<FootballClub> a, List<FootballClub> b) {
        List<FootballClub> list = a.stream().distinct().filter(b::contains).collect(Collectors.toList());
        System.out.println("First list: " + a.toString() + "\n");
        System.out.println("Second list: " + b.toString() + "\n");
        System.out.println("Intersection list: " + list.toString() + "\n\n");
    }
}
