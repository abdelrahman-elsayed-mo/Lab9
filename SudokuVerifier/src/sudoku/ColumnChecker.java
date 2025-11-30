/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

/**
 *
 * @author DELL
 */


import java.util.List;
import java.util.Map;

public class ColumnChecker extends AbstractChecker {

    public ColumnChecker(int[][] board, Map<String, List<DuplicateInfo>> errors, int index) {
        super(board, errors, index);
    }

    @Override
    public void check() {
        int start = (index == 0) ? 0 : index - 1;
        int end = (index == 0) ? 9 : index;

        for (int c = start; c < end; c++) {
            int[] col = new int[9];
            for (int r = 0; r < 9; r++) {
                col[r] = board[r][c];
            }
            findDuplicates(col, "COL " + (c + 1), 1);
        }
    }
}