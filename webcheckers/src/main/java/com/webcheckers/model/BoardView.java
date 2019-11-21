package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  BoardView is the data representation of the board, it is made up of 8 rows
 *
 *  @Author Jonah Waltz-Rieber and Joe Talbot
 */
public class BoardView implements Iterable<Row>{
  //flipped is red checkers on bottom
    private boolean flipped;
    private List<Row> rows;

    /**
     *  This method is the constructor for the board view.
     *  It creates a board with rows 0 to 7 for normal orientation
     *  7 to 0 for flipped orientation
     * @param flipped whether the board is upside down
     *
     */
    public BoardView(boolean flipped){
        this.flipped = flipped;
        rows = new ArrayList<Row>();
        for(int index = 0; index < 8; index++){
            int rowNumber = 0;
            if (flipped) {
                rowNumber = 7-index;
            }
            else {
                rowNumber = index;
            }
            rows.add(new Row(rowNumber, flipped));
        }
    }
    /**
     *
     * @return return the iterator of rows
     *
     */
    @Override
    public Iterator<Row> iterator(){
        return rows.iterator();
    };


}
