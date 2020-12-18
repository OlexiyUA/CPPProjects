package com.agtevoase;

import com.agtevoase.monitor.ThreadMonitor;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final String directory = "C:\\Users\\andro\\IdeaProjects\\CrossPlatform4\\stuff\\";
    public static final int matrixDimension = 5000;

    public static void main(String[] args) {
        var blockMatrix = new BlockMatrix();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter matrix size: ");
        blockMatrix.generate(scanner.nextInt());

        System.out.print("Enter amount of threads: ");
        int threadCount = scanner.nextInt();
        scanner.nextLine();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; ++i) {
            threads.add(new Thread(()-> {
                try {
                    while (true) {
                        if (blockMatrix.nextBlockSolve()) {
                            return;
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }));
        }
        ThreadMonitor monitor = new ThreadMonitor(threads);


        JFrame frame = new JFrame("CPP4");
        JTextArea area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);
        frame.add(pane);
        monitor.startDisplaying(area, 10);

        System.out.print("Use ExecutorService (Y/N)? ");
        String str = scanner.nextLine();
        long startTime = System.nanoTime();
        if (str.equals("Y")) {
            ExecutorService executor = Executors.newFixedThreadPool(threads.size());
            for (var thread: threads) {
                executor.submit(thread);
            }
        } else {
            for (var thread: threads) {
                thread.start();
            }
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 500);


        var timer = new java.util.Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (blockMatrix.allBlocksSolved()) {
                    timer.cancel();
                    monitor.stopDisplaying();
                    long endTime = System.nanoTime();
                    double timeElapsed = (endTime - startTime) / 1E6;

                    System.out.println("Matrix solution took " + timeElapsed + "ms");
                    System.out.print("Save solved matrix (Y/N)? ");
                    String str = scanner.nextLine();
                    if (str.equals("N")) {
                        return;
                    }
                    System.out.print("Enter filename: ");
                    str = scanner.nextLine();
                    try {
                        blockMatrix.writeToFile(directory + str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(task, 0, 1);
    }

}
