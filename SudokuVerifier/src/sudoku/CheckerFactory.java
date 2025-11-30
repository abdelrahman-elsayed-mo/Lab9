/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;  // If needed elsewhere, but for errors

public class CheckerFactory {

    public static List<Runnable> createCheckers(int[][] board, int mode, Map<String, List<DuplicateInfo>> errors) {
        List<Runnable> checkers = new ArrayList<>();

        if (mode == 0 || mode == 3) {
            // One checker per type
            if (mode == 0) {
                // For mode 0, we still create them but run sequentially
                checkers.add(new RowChecker(board, errors, 0)); // 0 means all rows
                checkers.add(new ColumnChecker(board, errors, 0)); // all columns
                checkers.add(new BoxChecker(board, errors, 0)); // all boxes
            } else { // mode 3
                checkers.add(new RowChecker(board, errors, 0));
                checkers.add(new ColumnChecker(board, errors, 0));
                checkers.add(new BoxChecker(board, errors, 0));
            }
        } else if (mode == 27) {
            // One per row/column/box
            for (int i = 0; i < 9; i++) {
                checkers.add(new RowChecker(board, errors, i + 1)); // index 1-9
            }
            for (int i = 0; i < 9; i++) {
                checkers.add(new ColumnChecker(board, errors, i + 1));
            }
            for (int i = 0; i < 9; i++) {
                checkers.add(new BoxChecker(board, errors, i + 1));
            }
        }

        return checkers;
    }
}