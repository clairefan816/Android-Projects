package claire.demoapps.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // 1 = yellow 0 = red
    int activePlayer = 0;

    boolean gameIsActive = true;

    // array to save the game state 2 = unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // check winning
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {6, 4, 2}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    public void dropIn(View view){
        ImageView counter = (ImageView)view;

        // get the counter's tag
//        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            //set the value of the tapped counter
            gameState[tappedCounter] = activePlayer;
            //move it up from screen
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                //set the counter to red
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                //set the counter to yellow
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            //bring it back again
            counter.animate().translationYBy(1000f).setDuration(300);

            // check the winning positions
            for ( int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameIsActive = false;

                    String winner = "Yellow";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Red";
                    }
                    //show message
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    return;
                }
            }
            boolean gameIsOver = true;
            for (int counterState: gameState){
                if (counterState == 2){
                    gameIsOver = false;
                }
            }

            if (gameIsOver){
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText("It's a draw!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }



        }

    }

    public void playAgain(View view){

        gameIsActive = true;

        //make grid disappear
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        //reset the game status
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        // this below doesn't work
        //GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}