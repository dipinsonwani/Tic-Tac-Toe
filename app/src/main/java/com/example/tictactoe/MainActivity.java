package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1_turn = true;
    private int roundCount;
    private int Player1Points;
    private int Player2Points;

    private TextView textviewplayer1;
    private TextView textviewplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewplayer1 = findViewById(R.id.text_p1);
        textviewplayer2 = findViewById(R.id.text_p2);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }


    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals(""))
            return;

        if (player1_turn) {
            ((Button) v).setText("X");
        } else
            ((Button) v).setText("O");

        roundCount++;

        if (checkForWin()) {
            if (player1_turn)
                player1Wins();
            else
                player2Wins();
        } else if (roundCount == 9)
            draw();
        else
            player1_turn = !player1_turn;

    }


    private boolean checkForWin() {

        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0;
             i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }

        }
        for (
                int i = 0;
                i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }

        }
        for (
                int i = 0;
                i < 3; i++) {
            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
                return true;
            }

        }
        for (
                int i = 0;
                i < 3; i++) {
            if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
                return true;
            }

        }
        return false;
    }


    private void player1Wins() {
        Player1Points++;
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }


    private void player2Wins() {
        Player2Points++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        ;
        updatePointsText();
        resetBoard();

    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textviewplayer1.setText("Player1: " + Player1Points);
        textviewplayer2.setText("Player2: " + Player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1_turn = true;

    }

    private void resetGame() {
        Player1Points = 0;
        Player2Points = 0;
        resetBoard();
        updatePointsText();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount );
        outState.putInt("player1Points",Player1Points);
        outState.putInt("player2Points",Player2Points);
        outState.putBoolean("Player1turn",player1_turn);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount=savedInstanceState.getInt("roundCount");
        Player1Points=savedInstanceState.getInt("Player1points");
        Player2Points=savedInstanceState.getInt("Player2points");
        player1_turn=savedInstanceState.getBoolean("Player1turn");
    }
}

