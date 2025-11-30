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
import java.util.List;

public class DuplicateInfo {
    int number;
    List<Integer> positions;

    public DuplicateInfo(int number) {
        this.number = number;
        this.positions = new ArrayList<>();
    }
}