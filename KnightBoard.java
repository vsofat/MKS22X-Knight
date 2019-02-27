public class KnightBoard{

  private int[][] board;

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
      result += "/n";
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
    return solveHelper(startingRow, startingCol);
  }

  public boolean solveHelper(int row, int col){
    if (row > board.length || col > board[0].length){
      return false;
    }
    board[row][col] = numKnights;
    numKnights++;
    return solveHelper(row + 2, col + 1) || solveHelper(row - 2, col + 1) || solveHelper(row + 2, col - 1) || solveHelper(row - 2, col - 1)
    || solveHelper(row + 1, col + 2) || solveHelper(row - 1, col + 2) || solveHelper(row + 1, col - 2) || solveHelper(row - 1, col - 2);
  }

} // closing
