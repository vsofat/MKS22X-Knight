public class Move implements Comparable<Move>{

  int row;
  int col;
  int possibleMoves;

  public Move(int rowInput, int colInput){
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

  public int compareTo(Move other){
    return this.possibleMoves - other.possibleMoves;
  }

}
