import java.util.ArrayList;
import java.util.Arrays;

public class KnightBoard{

  private int[][] board;
  private int total = 0;

  public KnightBoard(int rows, int cols){
    if (rows <= 0 || cols <= 0){
      throw new IllegalArgumentException("Both rows and columns must have a value greater than 0.");
    }
    board = new int[rows][cols];
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[0].length; c++){
        board[r][c] = 0;
      }
    }
  }

  public String toString(){
    String result = "";
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[0].length; col++){
        if (board[row][col] % 10 == board[row][col]){
          result += "  " + board[row][col];
        } else {
          result += " " + board[row][col];
        }
      }
      result += "\n";
    }
    return result;
  }

  public boolean solve(int startingRow, int startingCol){
    if (startingRow < 0 || startingRow >= board.length || startingCol < 0 || startingCol >= board[0].length){
      throw new IllegalArgumentException("The values for starting row and starting column must be greater than zero.");
    }
    for (int row = 0; row < board.length; row++){
      for (int col = 0; col < board[0].length; col++){
        if (board[row][col] != 0){
          throw new IllegalArgumentException("Board can only contain 0");
        }
      }
    }
    return solveHelperelpe r(startingRow, startingCol, 1);
  }

  public boolean solveHelperelper(int row, int col, int level){
    if (level == ((board.length *  board[0].length)+1)){
      return true;
    }
    if ((row >= board.length) || (col >= board[0].length) || (row < 0) || (col < 0)) {
      return false;
    }
    if (addKnight(row,col,level)){
      if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
        return false;
      }
      if (board[row][col] != 0){
        return false;
      } // checking for value at location
      if (solveHelperelper(row + 2, col + 1, level + 1)){
        return true;
      }
      if (solveHelperelper(row + 2, col - 1, level + 1)){
        return true;
      }
      if (solveHelperelper(row + 1, col + 2, level + 1)){
        return true;
      }
      if (solveHelperelper(row - 1, col + 2, level + 1)){
        return true;
      }
      if (solveHelperelper(row - 2, col + 1, level + 1)){
        return true;
      }
      if (solveHelperelper(row - 2, col - 1, level + 1)){
        return true;
      }
      if (solveHelperelper(row + 1, col - 2, level + 1)){
        return true;
      }
      if (solveHelperelper(row - 1, col - 2, level + 1)){
        return true;
      }
    }
  }

  public boolean addKnight(int row, int col, int level) {
    if (board[row][col] != 0) {
      return false;
    }
    board[row][col] = level;
    return true;
  }

  public boolean removeKnight (int row, int col){ // might have to add a level paramater here as well
    if (board[row][col] != 0){
      board[row][col] = 0;
      return true;
    }
    else{
      return false;
    }
  }

  public int countSolutions throws IllegalStateException(int startingRow, int startingCol) {
    for (int startingRow = 0; startingRow < board.length; startingRow++) {
      for (int startingCol = 0; startingCol < board[0].length; startingCol++) {
        if (board[startingRow][startingCol] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    return countHelper(startingRow, startingCol);
  }

  public int countHelper(int row, int col) {
    if (level == ((board.length * board[0].length) + 1)) {
      return true;
    }
    if ((row >= board.length) || (col >= board[0].length) || (row < 0) || (col < 0)) {
      return false;
    }
    if (placeKnight(row, col, level)) {
      if (solveHelper(row + 2, col + 1, level + 1)) {
        return true;
      }
      if (solveHelper(row + 2, col - 1, level + 1)) {
        return true;
      }
      if (solveHelper(row + 1, col + 2, level + 1)) {
        return true;
      }
      if (solveHelper(row - 1, col + 2, level + 1)) {
        return true;
      }
      if (solveHelper(row - 2, col + 1, level + 1)) {
        return true;
      }
      if (solveHelper(row - 2, col - 1, level + 1)) {
        return true;
      }
      if (solveHelper(row + 1, col - 2, level + 1)) {
        return true;
      }
      if (solveHelper(row - 1, col - 2, level + 1)) {
        return true;
      }
      removeKnight(row, col);
    }
    return false;
  }

} // closing

/*
KnightBoard has 3 public methods and a constructor, a private helper is needed as well.

@throws IllegalArgumentException when either parameter is negative.
public KnightBoard(int startingRows,int startingCols)

Initialize the board to the correct size and make them all 0's
public String toString()
see format for toString below
blank boards display 0's as underscores
you get a blank board if you never called solve or
when there is no solution

@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
or out of bounds.
public boolean solve(int startingRow, int startingCol)

@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
or out of bounds.
public int countSolutions(int startingRow, int startingCol)

Suggestion:
private boolean solveHelperelper(int row ,int col, int level)
// level is the # of the knight
*/
