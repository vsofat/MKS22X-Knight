public class Move implements Comparable{

  int row;
  int col;
  int possibleMoves;

  public move(int rowInput, int colInput){
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
