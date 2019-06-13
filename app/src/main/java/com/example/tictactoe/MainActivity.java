package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to see whose turn it is.
    int count = 0;

    // Used to determine winner, 0 for yellow and 1 for red
    int grid[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // Winner var
    String winner = "";

    public void drop(View v) {
        // The image
        ImageView i = (ImageView) v;

        // Animations for dropping a piece.
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(v, "translationY", -10f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(v, "translationY", 10f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(v, "rotation", 40);
        AnimatorSet set1 = new AnimatorSet();
        AnimatorSet set2 = new AnimatorSet();

        // Logic for checking if piece is already at that location
        int location = Integer.parseInt(v.getTag().toString());
        if(winner != "") {
            // do nothing
        } else if(grid[location] == 2){
            grid[location] = count;

            // Logic for determining turn
            if(count==0) {
                i.setImageResource(R.drawable.yellow);
                count = 1;
            } else {
                i.setImageResource(R.drawable.red);
                count = 0;
            }

            // Plays animations.
            i.setTranslationY(-1000f);
            i.animate().translationYBy(1000f).setDuration(300);
            set1.play(anim1).before(anim2);
            set1.play(anim2).with(anim3);
            set1.start();
        } else {
            set1.play(anim1).before(anim2);
            set1.play(anim2).with(anim3);
            set1.setDuration(100);
            set1.start();
        }

        // Check for winner or tie
        boolean tie = true;
        for(int x=0; x < grid.length; x++){
            if(grid[x] == 2){
                tie = false;
            }
        }
        if(
                grid[0]==1 && grid[1]==1 && grid[2]==1 ||
                grid[0]==1 && grid[4]==1 && grid[8]==1 ||
                grid[0]==1 && grid[3]==1 && grid[6]==1 ||
                grid[1]==1 && grid[4]==1 && grid[7]==1 ||
                grid[2]==1 && grid[4]==1 && grid[6]==1 ||
                grid[2]==1 && grid[5]==1 && grid[8]==1 ||
                grid[3]==1 && grid[4]==1 && grid[5]==1 ||
                grid[6]==1 && grid[7]==1 && grid[8]==1
        ) {
            winner = "Red";
        } else if (
                grid[0]==0 && grid[1]==0 && grid[2]==0 ||
                grid[0]==0 && grid[4]==0 && grid[8]==0 ||
                grid[0]==0 && grid[3]==0 && grid[6]==0 ||
                grid[1]==0 && grid[4]==0 && grid[7]==0 ||
                grid[2]==0 && grid[4]==0 && grid[6]==0 ||
                grid[2]==0 && grid[5]==0 && grid[8]==0 ||
                grid[3]==0 && grid[4]==0 && grid[5]==0 ||
                grid[6]==0 && grid[7]==0 && grid[8]==0
        ) {
            winner = "Yellow";
        } else if(tie){
            winner = "No one, it's a Tie!";
        }

        if(winner != ""){
            TextView message = (TextView)findViewById(R.id.winnerTxt);
            message.setText("The winner is: " + winner);
            LinearLayout layout = (LinearLayout)findViewById(R.id.winnerLayout);
            layout.setVisibility(View.VISIBLE);
        }

    }

    // Restart the game
    public void restart(View v){
        // Set layout to invisible
        LinearLayout layout = (LinearLayout)findViewById(R.id.winnerLayout);
        layout.setVisibility(View.INVISIBLE);

        // Reset board
        for(int i=0; i < 9; i++){
            grid[i] = 2;
        }

        ImageView img;
        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView2);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView3);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView4);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView5);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView6);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView7);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView8);
        img.setImageResource(0);
        img = (ImageView)findViewById(R.id.imageView9);
        img.setImageResource(0);

        // Reset winner var
        winner = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
