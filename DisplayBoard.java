public class DisplayBoard {
    private int[][] board = new int[64][32];

    public DisplayBoard(){
        for(int i =0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public void clearBoard(){
        for(int i =0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public int getPixel(int x, int y){
        return board[x][y];
    }

    public void setPixel(int x, int y){
        board[x][y] ^=1;
    }

    public int[][] getBoard(){
        return board;
    }
}
