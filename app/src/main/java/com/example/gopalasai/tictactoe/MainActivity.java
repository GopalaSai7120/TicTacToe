package com.example.gopalasai.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private TicTacToe tttGame;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tttGame=new TicTacToe();
        buildBuiByCode();
    }
    private void buildBuiByCode(){
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w=size.x/TicTacToe.SIDE;
        GridLayout gridLayout=new GridLayout(this);
        gridLayout.setColumnCount(TicTacToe.SIDE);
        gridLayout.setRowCount(TicTacToe.SIDE+1);
        //create buttons
        buttons=new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        Buttonhandler bh=new Buttonhandler();

        for(int row=0;row<TicTacToe.SIDE;row++){
            for(int col=0;col<TicTacToe.SIDE;col++){
                buttons[row][col]=new Button(this);
                buttons[row][col].setTextSize((int)(w*.2));
                buttons[row][col].setOnClickListener(bh);
                gridLayout.addView(buttons[row][col],w,w);
            }//end for col
        }//end for row
        status=new TextView(this);
        GridLayout.Spec rowSpec=GridLayout.spec(TicTacToe.SIDE,1);
        GridLayout.Spec colSpec=GridLayout.spec(0,TicTacToe.SIDE);
        GridLayout.LayoutParams lpstatus=new GridLayout.LayoutParams(rowSpec,colSpec);
        status.setLayoutParams(lpstatus);
        //set the status text view characteristics
        status.setWidth(TicTacToe.SIDE*w);
        status.setHeight(w);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(w*.15));
        status.setText(tttGame.result());
        gridLayout.addView(status);
        setContentView(gridLayout);
    }//end bullid guicode

    public void update(int r,int c){
        int play=tttGame.play(r,c);
        if(play==1){
            buttons[r][c].setText("X");
        }
        else if(play==2){
            buttons[r][c].setText("O");
        }
        if(tttGame.gameIsOver()){
            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText(tttGame.result());
            showNewGameDialog();
        }
        //buttons[r][c].setText("X");
    }//end update
    public void enableButtons(boolean enabled){
        for(int row=0;row<TicTacToe.SIDE;row++){
            for(int col=0;col<TicTacToe.SIDE;col++){
                buttons[row][col].setEnabled(enabled);
            }
        }
    }
    public void resetButtons(){
        for (int row=0;row<TicTacToe.SIDE;row++){
            for(int col=0;col<TicTacToe.SIDE;col++){
                buttons[row][col].setText("");
            }
        }
    }
    //
    public void showNewGameDialog(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("This is fun");
        alert.setMessage("Play again");
        PlayDialog playAgain= new PlayDialog();
        alert.setPositiveButton("Yes",playAgain);
        alert.setNegativeButton("No",playAgain);
        alert.show();
    }
    /***************************************/
    private class Buttonhandler implements View.OnClickListener{
        @Override
        public void onClick(View view){
            for(int row=0;row<TicTacToe.SIDE;row++){
                for(int col=0;col<TicTacToe.SIDE;col++){
                    if(view==buttons[row][col]){
                        update(row,col);
                    }
                }
            }
        }
    }//end button handler
    private class PlayDialog implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==-1){
                tttGame.resetgame();//yes box
                enableButtons(true);
                resetButtons();
                status.setBackgroundColor(Color.GREEN);
                status.setText(tttGame.result());
            }
            //no button
            else if(i==-2){
                MainActivity.this.finish();//closes the app
            }
        }
    }
}
