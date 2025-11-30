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

public class BoxChecker extends AbstractChecker {

    public BoxChecker(int[][] board, Map<String, List<DuplicateInfo>> errors, int index) {
        super(board, errors, index);
    }

    @Override
    public void check() {
        int start = (index == 0) ? 0 : index - 1;
        int end = (index == 0) ? 9 : index;

        for (int b = start; b < end; b++) {
            int[] box = new int[9];
            int boxRow = (b / 3) * 3;
            int boxCol = (b % 3) * 3;
            int idx = 0;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    box[idx++] = board[boxRow + r][boxCol + c];
                }
            }
            // Positions in box: flatten to 1-9, but as per output, it's [1,2,3,...] for duplicates
            findDuplicates(box, "BOX " + (b + 1), 1);
        }
    }
}
