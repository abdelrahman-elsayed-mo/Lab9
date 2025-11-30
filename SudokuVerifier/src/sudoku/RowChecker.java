/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author DELL
 */

package sudoku;
import java.util.List;
import java.util.Map;

public class RowChecker extends AbstractChecker {

    public RowChecker(int[][] board, Map<String, List<DuplicateInfo>> errors, int index) {
        super(board, errors, index);
    }

    @Override
    public void check() {
        int start = (index == 0) ? 0 : index - 1;
        int end = (index == 0) ? 9 : index;

        for (int r = start; r < end; r++) {
            int[] row = board[r];
            findDuplicates(row, "ROW " + (r + 1), 1); // positions 1-9
        }
    }
}