/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

/**
 *
 * @author DELL
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SudokuVerifier {

    private static final int SIZE = 9;
    private int[][] board;
    private int mode;

    public SudokuVerifier(String filePath, int mode) throws IOException {
        this.mode = mode;
        this.board = readBoardFromCSV(filePath);
    }

    private int[][] readBoardFromCSV(String filePath) throws IOException {
        int[][] board = new int[SIZE][SIZE];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < SIZE) {
                String[] values = line.split(",");
                for (int col = 0; col < SIZE; col++) {
                    board[row][col] = Integer.parseInt(values[col].trim());
                }
                row++;
            }
        }
        return board;
    }

    public void verify() throws InterruptedException {
        Map<String, List<DuplicateInfo>> errors = new ConcurrentHashMap<>();

        List<Runnable> checkers = CheckerFactory.createCheckers(board, mode, errors);

        if (mode == 0) {
            // Sequential: run in main thread
            for (Runnable checker : checkers) {
                checker.run();
            }
        } else {
            // Parallel: use threads
            ExecutorService executor = Executors.newFixedThreadPool(checkers.size());
            for (Runnable checker : checkers) {
                executor.submit(checker);
            }
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }

        printResult(errors);
    }

    private void printResult(Map<String, List<DuplicateInfo>> errors) {
        if (errors.isEmpty()) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");

            // Print ROWs
            printSection(errors, "ROW");
            System.out.println("------------------------------------------");

            // Print COLs
            printSection(errors, "COL");
            System.out.println("------------------------------------------");

            // Print BOXs
            printSection(errors, "BOX");
        }
    }

    private void printSection(Map<String, List<DuplicateInfo>> errors, String type) {
        for (int i = 1; i <= SIZE; i++) {
            String key = type + " " + i;
            List<DuplicateInfo> infos = errors.get(key);
            if (infos != null) {
                for (DuplicateInfo info : infos) {
                    System.out.println(key + ", #" + info.number + ", " + Arrays.toString(info.positions.toArray()));
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar SudokuVerifier.jar <csv-filepath> <mode>");
            System.exit(1);
        }

        String filePath = args[0];
        int mode;
        try {
            mode = Integer.parseInt(args[1]);
            if (mode != 0 && mode != 3 && mode != 27) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            System.err.println("Invalid mode. Must be 0, 3, or 27.");
            System.exit(1);
            return;
        }

        try {
            SudokuVerifier verifier = new SudokuVerifier(filePath, mode);
            verifier.verify();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}