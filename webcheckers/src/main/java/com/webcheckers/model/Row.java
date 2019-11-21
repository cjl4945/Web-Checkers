package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *  Row is the data representation of each board row, they is made up of 8 spaces
 *
 *  @Author Jonah Waltz-Rieber and Joe Talbot
 */
public class Row implements Iterable<Space> {

    protected List<Space> spaces;

    private int index;

    /**
     *  This method is the constructor for the row.
     *  It creates Row with spaces 0 to 7 for normal orientation
     *  7 to 0 for flipped orientation
     * @param flipped whether the board is upside down
     * @param index the row index
     */
    public Row(int index, boolean flipped){
        this.index = index;
        spaces = new ArrayList<Space>();
        for(int i = 0; i < 8; i++){
            if (flipped){
                spaces.add(new Space(7-i,index));
            }
            else {
                spaces.add(new Space(i,index));
            }
        }
    }
    /**
     * @return returns the current row index
     */
    public int getIndex() {
        return this.index;
    }
    /**
     * @return returns the iterator of spaces
     */
    @Override
    public Iterator<Space> iterator(){
        return spaces.iterator();
    }



}