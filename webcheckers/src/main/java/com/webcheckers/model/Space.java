package com.webcheckers.model;
/**
 *  Space is the data representation of each board space, they can have a piece on them or not
 *
 *  @Author Jonah Waltz-Rieber and Joe Talbot
 */
public class Space{
    private int cellIdx;
    private int index;
    private boolean hasPiece;
    private Piece piece;
    /**
     *  This method is the constructor for the space.
     *  The space is either initialized with or without a piece on it
     *
     * @param cellIdx the current column of each space
     * @param index the row index
     */
    public Space(int cellIdx, int index){
        this.cellIdx = cellIdx;
        this.index = index;
        this.hasPiece = false;
        //If bottom 3 rows of board
        if(index < 3){
            //if even row
            if (index % 2==0){
                //if odd column
                if(getCellIdx() % 2==1){
                    //put white piece
                    this.hasPiece = true;
                    this.piece = new Piece(Piece.type.SINGLE, Piece.color.WHITE);
                }
                //if odd row
            }else{
                //if even column
                if(getCellIdx() % 2 ==0){
                    //put red piece
                    this.hasPiece = true;
                    this.piece = new Piece(Piece.type.SINGLE, Piece.color.WHITE);
                }
            }
        }
        //If top 3 rows of board
        else if(index > 4){
            //if even row
            if (index % 2==0){
                //if odd column
                if(getCellIdx() % 2==1){
                    //put red piece
                    this.hasPiece = true;
                    this.piece = new Piece(Piece.type.SINGLE, Piece.color.RED);
                }
                //if odd row
            }else{
                //if even column
                if(getCellIdx() % 2 ==0){
                    //place red piece
                    this.hasPiece = true;
                    this.piece = new Piece(Piece.type.SINGLE, Piece.color.RED);
                }
            }
        }
    }
    /**
     *
     * @return the current column number
     */
    public int getCellIdx() {
        return this.cellIdx;
    }
    /**
     *  This method determines whether a space is a valid move aka a black space without a piece
     * @return whether the space is a valid move
     */
    public boolean isValid(){
        //if odd row
        if(index%2==1){
            //if even column
            if(getCellIdx()%2==0){
                //if no piece
                if(getPiece()==null){
                    return true;
                }
            }
            //if even row
        } else {
            //if odd column
            if (getCellIdx()% 2 ==1) {
                //if no piece
                if (getPiece() == null) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *  This method returns a piece if the space contains one
     * @return piece that the space contains or null
     */
    public Piece getPiece(){
        if(hasPiece){
            return this.piece;
        }
        return null;
    }
}
