package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int pointsA = 0;
    int pointsB = 0;
    int gameA = 0;
    int gameB = 0;
    TextView teamAPoints;
    TextView teamBPoints;
    TextView teamAGame;
    TextView teamBGame;
    TextView winnerText;
    TextView resetButton;
    String TEAM_A_SCORE = "TEAM A SCORE";
    String TEAM_B_SCORE = "TEAM B SCORE";
    String TEAM_A_GAME = "TEAM A GAME";
    String TEAM_B_GAME = "TEAM B GAME";
    String WINNER = "WINNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAPoints = findViewById(R.id.team_a_score);
        teamBPoints = findViewById(R.id.team_b_score);
        teamAGame = findViewById(R.id.gameA);
        teamBGame = findViewById(R.id.gameB);
        winnerText = findViewById(R.id.winner_text);
        resetButton = findViewById(R.id.reset_b);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Passing the saved state value to the variable
        displayPointsForTeamA(savedInstanceState.getString(TEAM_A_SCORE));
        displayPointsForTeamB(savedInstanceState.getString(TEAM_B_SCORE));
        displayGameForTeamA( Integer.parseInt(savedInstanceState.getString(TEAM_A_GAME)));
        displayGameForTeamB(Integer.parseInt(savedInstanceState.getString(TEAM_B_GAME)));
        displayWinner(savedInstanceState.getString(WINNER));

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        // Save the current value of the variable.
        savedInstanceState.putString(TEAM_A_SCORE, teamAPoints.getText().toString());
        savedInstanceState.putString(TEAM_B_SCORE, teamBPoints.getText().toString());
        savedInstanceState.putString(TEAM_A_GAME, teamAGame.getText().toString());
        savedInstanceState.putString(TEAM_B_GAME, teamBGame.getText().toString());
        savedInstanceState.putString(WINNER, winnerText.getText().toString());
        // Always call the super class so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method is called whenever team A scores a point
     * it takes every case for the pointsA value and modifies it accordingly
     *
     * @param view
     */
    public void winA(View view) {
        switch (pointsA) {
            case 0: {
                pointsA = 15;
                displayPointsForTeamA(pointsA + "");
                winnerText.setText("");
                break;
            }
            case 15: {
                pointsA = 30;
                displayPointsForTeamA(pointsA + "");
                break;
            }
            case 30: {
                pointsA = 40;
                displayPointsForTeamA(pointsA + "");
                break;
            }
            case 40: {
                if (pointsB < 40) {//when score would be, let's say, A:40-B:15 -> A wins the game -> +1 for game
                    pointsB = 0;
                    pointsA = 0;
                    gameA++;
                    displayPointsForTeamA(pointsA + "");
                    displayPointsForTeamB(pointsB + "");
                    displayGameForTeamA(gameA);
                }
                if (pointsB == 40) {//when score would be A:40-B:40 and the player would have to get an advantage
                    pointsA = 41;
                    displayPointsForTeamA("A");
                }
                if (pointsB == 41) {
                    pointsB = 40;
                    displayPointsForTeamB(pointsB + "");
                }
                break;
            }
            case 41: {//at the second advantage, when it gets a point, and the game is won
                pointsA = 0;
                pointsB = 0;
                gameA++;
                displayGameForTeamA(gameA);
                displayPointsForTeamA(pointsA + "");
                displayPointsForTeamB(pointsB + "");
            }

        }

        if (gameA == 6) {//the first one at six games wins
            reset(resetButton);
            displayWinnerA();
        }


    }

    /**
     * This method is called whenever team B scores a point
     * it takes every case for the pointsA value and modifies it accordingly
     *
     * @param view
     */

    public void winB(View view) {
        switch (pointsB) {
            case 0: {
                pointsB = 15;
                displayPointsForTeamB(pointsB + "");
                winnerText.setText("");

                break;
            }
            case 15: {
                pointsB = 30;
                displayPointsForTeamB(pointsB + "");
                break;
            }
            case 30: {
                pointsB = 40;
                displayPointsForTeamB(pointsB + "");
                break;
            }
            case 40: {
                if (pointsA < 40) {//when score would be, let's say, A:15-B:40 -> B wins the game -> +1 for game
                    pointsB = 0;
                    pointsA = 0;
                    gameB++;
                    displayPointsForTeamA(pointsA + "");
                    displayPointsForTeamB(pointsB + "");
                    displayGameForTeamB(gameB);
                }
                if (pointsA == 40) {//at the second advantage, when it gets a point
                    pointsB = 41;
                    displayPointsForTeamB("A");
                }
                if (pointsA == 41) {
                    pointsA = 40;
                    displayPointsForTeamA(pointsA + "");
                }
                break;
            }
            case 41: {//at the second advantage, when it gets a point, and the game is won
                pointsA = 0;
                pointsB = 0;
                gameB++;
                displayGameForTeamB(gameB);
                displayPointsForTeamA(pointsA + "");
                displayPointsForTeamB(pointsB + "");
            }

        }

        if (gameB == 6) {//the first one at six games wins
            reset(resetButton);
            displayWinnerB();
        }


    }

    /**
     * this method is automatically called by method winA whenever the points modify and they need to be updated
     *
     * @param s is a String because for advantages there has to be displayed "A", which is a string
     */

    public void displayPointsForTeamA(String s) {
        teamAPoints.setText(s);
    }

    /**
     * this method is automatically called by method winB whenever the points modify and they need to be updated
     *
     * @param s is a String because for advantages there has to be displayed "A", which is a string
     */

    public void displayPointsForTeamB(String s) {
        teamBPoints.setText(s);
    }

    /**
     * this method is automatically called by method winA whenever the game is won by A
     *
     * @param s is an int because for games there can only be numbers
     */

    public void displayGameForTeamA(int s) {
        teamAGame.setText(String.valueOf(s));
    }

    /**
     * this method is automatically called by method winB whenever the game is won by B
     *
     * @param s is an int because for games there can only be numbers
     */

    public void displayGameForTeamB(int s) {
        teamBGame.setText(String.valueOf(s));
    }

    /**
     * this method is also called in winA when A reaches 6 games, displaying A as the winner
     */

    public void displayWinnerA() {
        winnerText.setText(R.string.winnerA);
    }
    public void displayWinner(String s){
        winnerText.setText(s);
    }

    /**
     * this method is also called in winA when B reaches 6 games, displaying B as the winner
     */

    public void displayWinnerB() {
        winnerText.setText(R.string.winnerB);
    }

    /**
     * this method is called when the reset button is clicked. it restarts the entire scores and
     * empties the winner_text text view
     *
     * @param v
     */
    public void reset(View v) {
        pointsA = 0;
        pointsB = 0;
        gameA = 0;
        gameB = 0;
        displayPointsForTeamA(pointsA + "");
        displayPointsForTeamB(pointsB + "");
        displayGameForTeamA(gameA);
        displayGameForTeamB(gameB);
        winnerText.setText("");
    }
}