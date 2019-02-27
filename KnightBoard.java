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

} // closing
