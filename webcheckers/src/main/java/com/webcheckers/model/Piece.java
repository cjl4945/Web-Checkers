package com.webcheckers.model;

public class Piece {
    public enum type {SINGLE, KING};
    public enum color {RED, WHITE};

    public type pieceType;
    public color pieceColor;

     /**
     * Creates new checker piece with a type and color
     * @param pieceType 
     * The type of piece this checker is (single or king)
     * @param pieceColor
     * The type of color this checker is (red or white)
     */
    public Piece(type pieceType, color pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }
    /**
     * @return The type of this checker piece
     */
    public type getType() {
        return this.pieceType;
    }

    /**
     * @return The color of this checker piece
     */
    public color getColor() {
        return this.pieceColor;
    }
}