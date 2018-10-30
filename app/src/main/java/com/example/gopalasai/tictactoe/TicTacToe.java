package com.example.gopalasai.tictactoe;

public class TicTacToe {
    public static final int SIDE=3;
    private int turn;//1 is player 1 and 2 is player 2
    private int [][] game;//2d array that will represent the game board

    public TicTacToe(){
        game=new int[SIDE][SIDE];
        resetgame();
    }

    public void resetgame() {
        for(int row=0;row<SIDE;row++){
            for (int col=0;col<SIDE;col++){
                game[row][col]=0;
            }//end col
        }//end row
        turn=1;
    }//ned reset
    public int play(int row,int col){
        //if play is legal and board cell is m=not taken
        int curreentTurn=turn;
        if(row>=0&&col>=0&&row<SIDE&&col<SIDE&&game[row][col]==0){
            game[row][col]=turn;
            if(turn==1)
                turn=2;
            else
                turn=1;
            return curreentTurn;

        }
        else{
            return 0;
        }
    }//end play
    protected int checkRows(){
        for(int row=0;row<SIDE;row++){
            if(game[row][0]!=0&&game[row][0]==game[row][1]&&game[row][1]==game[row][2]){
                return game[row][0];
            }
        }
        return 0;
    }//end check rows
    protected int checkColumns(){
        for(int col=0;col<SIDE;col++){
            if(game[0][col]!=0&&game[0][col]==game[1][col]&&game[1][col]==game[2][col]){
                return game[0][col];
            }
        }
        return 0;
    }//end checkforcolumns
    protected int checkDiagonals(){
        if(game[0][0]!=0&&game[0][0]==game[1][1]&&game[1][1]==game[2][2]){
            return game[0][0];
        }

        else if(game[0][2]!=0&&game[0][2]==game[1][1]&&game[1][1]==game[2][0]){
            return game[0][2];
        }
            return 0;
    }//end check diagonals
    public int whoWon(){
        int rows=checkRows();
        if(rows>0)
            return rows;
        int cols=checkColumns();
        if(cols>0)
            return cols;
        int diags=checkDiagonals();
        if(diags>0)
            return diags;
        return 0;
    }
    public boolean cannotPlay(){
        boolean result=true;
        for (int row=0;row<SIDE;row++){
            for(int col=0;col<SIDE;col++){
                if(game[row][col]==0){
                    result=false;
                }
            }// end col
        }//end row
        return result;
    }
    public boolean gameIsOver(){
        return cannotPlay()|| (whoWon()>0);
    }
    public String result(){
        //eventually will show the current state of the game on the bottom of screen
        if(whoWon()>0){
            return "Player"+whoWon()+"won";
        }
        else if(cannotPlay()){
            return "Tie game,no winner";
        }
        else{
            return "play";
        }
    }
}
