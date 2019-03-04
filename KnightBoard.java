import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KnightBoard{

  public static void main(String[] args) {
    /*  KnightBoard board = new KnightBoard(5, 5);
    System.out.println(board.toString());
    System.out.println(board.solve(0, 0));
    System.out.println(board.toString()); */

  for (int index = 0; index < 6; index ++){
    runTest(index);
  }

//  KnightBoard board = new KnightBoard(8,8);

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
ArrayList<Move> moveList = new ArrayList<Move>(8); //moves based off sample board

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
      e.printStackTrace();
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
  if (possible(new Move(startingRow, startingCol)) == false){
    throw new IllegalArgumentException("There are no possible moves from given row and column. Please try another set of coordinates.");
  }
  if (startingRow < 0 || startingRow >= board.length || startingCol < 0 || startingCol >= board[0].length){
    throw new IllegalArgumentException("The values for starting row and starting column must be greater than zero.");
  }
  for (int row = 0; row < board.length; row++){
    for (int col = 0; col < board[0].length; col++){
      if (board[row][col] != 0){
        throw new IllegalArgumentException("Board can only contain 0s.");
      }
    }
  }
  return solveHelper(new Move (startingRow, startingCol), 1);
}

public boolean solveHelper( Move current, int level){
  addKnight(current, level += 1);
  ArrayList<Move> nextMove = possibleMovesFromCurrent(current);
  if(nextMove.size() == 0){
    return level == rowLen * colLen + 1; // went through everything
  }
  else{
    for(int index = 0; index < nextMove.size(); index++){
      if(solveHelper(nextMove.get(index),level)){
        return true;
      }
      else{
        removeKnight(current);
      }
    }
  }
  return false;
}

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

private ArrayList<Move> possibleMovesFromCurrent(Move current){
  ArrayList<Move> moves = new ArrayList<>(8);
  int row = current.getRow();
  int col = current.getCol();
  int[] possibleCoordinates = new int[]{row - 2, col + 1, row - 2, col - 1, row - 1, col - 2, row - 1, col + 2, row + 2, col + 1, row + 2, col - 1, row + 1, col - 2, row + 1, col + 2};
  for (int location = 0; location < possibleCoordinates.length; location += 2){
    Move moveAtCurrentLocation = new Move(possibleCoordinates[location],possibleCoordinates[location+1]);
    if (possible(moveAtCurrentLocation)){
      moves.add(moveAtCurrentLocation);
    }
  }
  return moves;
}

private boolean possible(Move current){
  int currentRow = current.getRow();
  int currentCol = current.getCol();
  if (currentRow >= 0 && currentCol >= 0 && currentRow < rowLen && currentCol < colLen){
    if(board[currentRow][currentCol] == 0){
      return true;
    }
  }
  else{
    return false;
  }
  return false;
}

private void removeKnight(Move move){
  board[move.getRow()][move.getCol()] = 0;
}

public int countSolutions(int startingRow, int startingCol) {
  if (possible(new Move(startingRow, startingCol)) == false){
    throw new IllegalArgumentException("No moves possible from given coordinates.");
  }
  for ( int row = startingRow; row < board.length; row++) {
    for (int col = startingCol; col < board[0].length; col++) {
      if (board[row][col] != 0) {
        throw new IllegalStateException("All values on board should be 0.");
      }
    }
  }
  if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
    throw new IllegalArgumentException("The given coordinates do not meet the restrictions for the program.");
  }

  this.board = new int[rowLen][colLen];

  return countHelper(new Move (startingRow, startingCol) , 1);

}

private ArrayList<Move> sortedMoves (Move current){
    ArrayList<Move> possibleMoves = possibleMovesFromCurrent(current);
    for(int i = 0; i < possibleMoves.size(); i++){
    possibleMoves.get(i).setPossibleMoves(possibleMovesFromCurrent(possibleMoves.get(i)).size());
    }
    Collections.sort(possibleMoves);
    return possibleMoves;
}

public int countHelper(Move current, int level) {
  int total = 0;
  addKnight(current, level += 1);
  ArrayList<Move> movesFromCurrent = sortedMoves(current);
  if (movesFromCurrent.size() == 0){
    if (level == colLen * rowLen + 1){
      removeKnight(current);
      return 1;
    }
    else{
      removeKnight(current);
      return 0;
    }
  }
  else{
    for(int i = 0; i < movesFromCurrent.size(); i++){
      total += countHelper(movesFromCurrent.get(i), level);
    }
  }
  removeKnight(current);
  return total;
}

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

// George helped me on 2/28 at the Cs Dojo -- forgot to add this comment earlier

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
