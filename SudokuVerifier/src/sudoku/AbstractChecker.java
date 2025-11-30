/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

/**
 *
 * @author DELL
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractChecker implements Checker {
  protected int[][] board;
    protected Map<String, List<DuplicateInfo>> errors;
    protected int index; 

    public AbstractChecker(int[][] board, Map<String, List<DuplicateInfo>> errors, int index) {
        this.board = board;
        this.errors = errors;
        this.index = index;
    }

    @Override
    public void run() {
        check();
    }

    protected void findDuplicates(int[] arr, String key, int offset) {
        Map<Integer, List<Integer>> freq = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            freq.computeIfAbsent(num, k -> new ArrayList<>()).add(i + offset);
        }

        List<DuplicateInfo> infos = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : freq.entrySet()) {
            if (entry.getValue().size() > 1) {
                DuplicateInfo info = new DuplicateInfo(entry.getKey());
                info.positions.addAll(entry.getValue());
                infos.add(info);
            }
        }

        if (!infos.isEmpty()) {
            errors.put(key, infos);
        }
    }
}
