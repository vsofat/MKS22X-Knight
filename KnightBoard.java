import java.util.ArrayList;
import java.util.Arrays;

public class move{

  int row;
  int col;
  int possibleMoves;

  private move(int rowInput, int colInput){
    row = rowInput;
    col = colInput;
  }

  public int getCol(){
    return this.col;
  }

  public int getRow(){
    return this.row;
  }

  public int getPossibleMoves(){
    return possibleMoves;
  }

  public void setPossibleMoves(int possibleMoves){
    this.possibleMoves = possibleMoves;
  }

}

public class KnightBoard{

  public static void main(String[] args) {
    /*  KnightBoard board = new KnightBoard(5, 5);
    System.out.println(board.toString());
    System.out.println(board.solve(0, 0));
    System.out.println(board.toString()); */
    /*  for (int index = 0; index < 6; index ++){
    runTest(index);
  }*/

  KnightBoard board = new KnightBoard(8,8);

  // testing model board
  /*
  board.BoardModeler();
  int moves[][] = board.model;
  String result = "";
  for (int row = 0; row < board.board.length; row++){
    for (int col = 0; col < board.board[0].length; col++){
      result += moves[row][col];
      result += " ";
    }
    result += "\n";
  }

  System.out.println(result);
  */

  // to test the model board
  //System.out.println(" \n 2 3 4 4 4 4 3 2 \n 3 4 6 6 6 6 4 3 \n 4 6 8 8 8 8 6 4 \n 4 6 8 8 8 8 6 4 \n 3 4 6 6 6 6 4 3 \n 2 3 4 4 4 4 3 2");

}

private int[][] board;
private int total = 0;
private int[][] model; // model board
private int rowLen;
private int colLen;

ArrayList<move> moveList = new ArrayList<move>(8); //moves based off sample board

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
  model = new int[rows][cols];
  rowLen = rows;
  colLen = cols;
}

public static void runTest(int i){ // from Mr. K's code

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}

public String toString(){
  String total = "";
  for (int row = 0; row < board.length; row++){
    for (int col = 0; col < board[0].length; col++){
      if (board[row][col] % 10 == board[row][col]){
        total += "  " + board[row][col];
      } else {
        total += " " + board[row][col];
      }
    }
    total += "\n";
  }
  return total;
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

private void addKnight(Move move, int level){
  board[move.getRow()][move.getCol()] = level;
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

private void removeKinght(Move move){
  board[move.getRow()][move.getCol()] = 0;
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
  int total = 0;
  if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
    return 0;
  }
  if (board[row][col] != 0){
    return 0;
  }
  if (level == board.length * board[0].length){
    return 1;
  }
  for (int i = 0; i < 8; i++){
    board[row][col] = level;
    if (i == 0){
      total += countHelper(row + 2, col + 1, level + 1);
    }
    if (i == 1){
      total += countHelper(row + 2, col - 1, level + 1);
    }
    if (i == 2){
      total += countHelper(row - 2, col - 1, level + 1);
    }
    if (i == 3){
      total += countHelper(row - 2, col + 1, level + 1);
    }
    if (i == 4){
      total += countHelper(row + 1, col + 2, level + 1);
    }
    if (i == 5){
      total += countHelper(row + 1, col - 2, level + 1);
    }
    if (i == 6){
      total += countHelper(row - 1, col + 2, level + 1);
    }
    if (i == 7){
      total += countHelper(row - 1, col - 2, level + 1);
    }
    board[row][col] = 0;
  }
  return total;
} // Alex helped me change this part too

// Went to CS Dojo 02/27/19 --  William helped me

/*

The pattern for BoardModeler:

2 3 4 4 4 4 3 2
3 4 6 6 6 6 4 3
4 6 8 8 8 8 6 4
4 6 8 8 8 8 6 4
3 4 6 6 6 6 4 3
2 3 4 4 4 4 3 2

*/

private int[][] BoardModeler(){
  //model = new int[rowLen][colLen];
  for (int row = 0; row < rowLen; row++){
    for (int col = 0; col < colLen; col++){
      if (row == 0 || row == rowLen - 1){
        if (col == 0 || col == colLen - 1){
          model[row][col] = 2;
        }
        else if (col == 1 || col == colLen - 2){
          model[row][col] = 3;
        }
        else{
          model[row][col] = 4;
        }
      }

      else if(row == 1 || row == rowLen - 2){
        if (col == 0 || col == colLen - 1){
          model[row][col] = 3;
        }
        else if (col == 1 || col == colLen - 2){
          model[row][col] = 4;
        }
        else{
          model[row][col] = 6;
        }
      }

      else{
        if (col == 0 || col == colLen - 1){
          model[row][col] = 4;
        }
        else if (col == 1 || col == colLen - 2){
          model[row][col] = 6;
        }
        else{
          model[row][col] = 8;
        }

      }
    }
  }
  return model;
}

private void clear(){
  for (int row=0; row< board.length; row++){
    for (int col=0; col< board[0].length; col++){
      board[row][col] = 0;
    }
  }
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
