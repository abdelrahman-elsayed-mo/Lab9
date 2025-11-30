/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckerFactory {

    public static List<Runnable> createCheckers(int[][] board, int mode, Map<String, List<DuplicateInfo>> errors) {
        List<Runnable> checkers = new ArrayList<>();

        if (mode == 0 || mode == 3) {
           
            if (mode == 0) {
                
                checkers.add(new RowChecker(board, errors, 0)); 
                checkers.add(new ColumnChecker(board, errors, 0)); 
                checkers.add(new BoxChecker(board, errors, 0)); 
            } else { 
                checkers.add(new RowChecker(board, errors, 0));
                checkers.add(new ColumnChecker(board, errors, 0));
                checkers.add(new BoxChecker(board, errors, 0));
            }
        } else if (mode == 27) {
            
            for (int i = 0; i < 9; i++) {
                checkers.add(new RowChecker(board, errors, i + 1)); 
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