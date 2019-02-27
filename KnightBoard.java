import java.util.ArrayList;
import java.util.Arrays;

public class KnightBoard{

  public static void main(String[] args) {
  KnightBoard board = new KnightBoard(5, 5);
  System.out.println(board.toString());
  System.out.println(board.solve(0, 0));
  System.out.println(board.toString());
}

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
    return solveHelper(startingRow, startingCol, 1);
  }

  public boolean solveHelper(int row, int col, int level){
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
      return false;
    }
    if (board[row][col] != 0){
      return false;
    }
    if (level == board.length * board[0].length){
      board[row][col] = level;
      return true;
    }
    for (int i = 0; i < 8; i++){
      board[row][col] = level;
      if (i == 0){
        if (solveHelper(row + 2, col + 1, level + 1)){
          return true;
        }
      }
      if (i == 1){
        if (solveHelper(row + 2, col - 1, level + 1)){
          return true;
        }
      }
      if (i == 2){
        if (solveHelper(row - 2, col - 1, level + 1)){
          return true;
        }
      }
      if (i == 3){
        if (solveHelper(row - 2, col + 1, level + 1)){
          return true;
        }
      }
      if (i == 4){
        if (solveHelper(row + 1, col + 2, level + 1)){
          return true;
        }
      }
      if (i == 5){
        if (solveHelper(row + 1, col - 2, level + 1)){
          return true;
        }
      }
      if (i == 6){
        if (solveHelper(row - 1, col + 2, level + 1)){
          return true;
        }
      }
      if (i == 7){
        if (solveHelper(row - 1, col - 2, level + 1)){
          return true;
        }
      }
      board[row][col] = 0;
    }
    return false;
  } // got help from Alex to restructure

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

  public int countSolutions(int startingRow, int startingCol) {
    for ( int row = startingRow; row < board.length; row++) {
      for (int col = startingCol; col < board[0].length; col++) {
        if (board[row][col] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    return countHelper(startingRow, startingCol, 1);
  }

  public int countHelper(int row, int col, int level) {
    if (level == ((board.length * board[0].length) + 1)) {
    return 1;
  }
  if ((row >= board.length) || (col >= board[0].length) || (row < 0) || (col < 0)) {
    return 0;
  }
  if (addKnight(row, col, level)) {
    total = total + countHelper(row + 2, col + 1, level + 1);
    total = total + countHelper(row + 2, col - 1, level + 1);
    total = total + countHelper(row + 1, col + 2, level + 1);
    total = total + countHelper(row - 1, col + 2, level + 1);
    total = total + countHelper(row - 2, col + 1, level + 1);
    total = total + countHelper(row - 2, col - 1, level + 1);
    total = total + countHelper(row + 1, col - 2, level + 1);
    total = total + countHelper(row - 1, col - 2, level + 1);
    removeKnight(row, col);
  }
  return total;
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
private boolean solveHelper(int row ,int col, int level)
// level is the # of the knight
*/
