package com.example.wlarts;

import static com.example.wlarts.Game.players;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wlarts.Dialogs.BackToStartDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.media.MediaPlayer;

public class GameActivity extends AppCompatActivity {

    ConstraintLayout score_1, score_2, score_3, score_4, score_5, score_6, score_7, score_8, score_9, score_0, no_score, confirm, revert, delete;
    TextView score;
    ArrayList<String> players_names;
    LinearLayout dashboard;


    private SoundPool soundPool;
    private int[] soundIDs;
    private int loadedSounds = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            players_names = extras.getStringArrayList("players_names");
            //The key argument here must match that used in the other activity;
        }

        // Initialize the SoundPool
        soundPool = new SoundPool.Builder().build();

        // Load the sound files
        soundIDs = new int[180];
        for (int i = 0; i < 180; i++) {
            int resourceId = getResources().getIdentifier("wlad_sound_" + (i + 1), "raw", getPackageName());
            soundIDs[i] = soundPool.load(this, resourceId, 1);
            soundPool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
                loadedSounds++;
                if (loadedSounds == 200) {

                }
            });
        }





        // Init Game
        Game game = new Game();
        for (int i = 0; i < players_names.size(); i++) {
            players.add(new Player(i, players_names.get(i), 501));
        }
        // Choose random player to start
        setBeginnerPlayer();
        createLayout(players.size());
        Game.current_player.setPlayersTurn();


        ///////////////////////////////////////////////////////////////////////////////////7

        score = (TextView) findViewById(R.id.score);
        score_1 = (ConstraintLayout) findViewById(R.id.score_1);
        score_2 = (ConstraintLayout) findViewById(R.id.score_2);
        score_3 = (ConstraintLayout) findViewById(R.id.score_3);
        score_4 = (ConstraintLayout) findViewById(R.id.score_4);
        score_5 = (ConstraintLayout) findViewById(R.id.score_5);
        score_6 = (ConstraintLayout) findViewById(R.id.score_6);
        score_7 = (ConstraintLayout) findViewById(R.id.score_7);
        score_8 = (ConstraintLayout) findViewById(R.id.score_8);
        score_9 = (ConstraintLayout) findViewById(R.id.score_9);
        score_0 = (ConstraintLayout) findViewById(R.id.score_0);
        no_score = (ConstraintLayout) findViewById(R.id.no_score);
        confirm = (ConstraintLayout) findViewById(R.id.confirm);
        revert = (ConstraintLayout) findViewById(R.id.revert);
        delete = (ConstraintLayout) findViewById(R.id.delete);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String score_value = score.getText().toString();
                if (score_value.equalsIgnoreCase("")) {
                    Toast.makeText(GameActivity.this, "Please enter your Points!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int points = Integer.parseInt(score_value);
                int points_left = Game.current_player.getPointsLeft();

                if (points > 180)
                    Toast.makeText(GameActivity.this, "180 is max", Toast.LENGTH_SHORT).show();
                else if (points == points_left)
                    //TODO gebonnen
                    System.out.println("");
                else {
                    game.enterScore(points);
                    try {
                        soundPool.play(soundIDs[points - 1], 1.0f, 1.0f, 1, 0, 1.0f);

                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }

                }
                score.setText("");

            }
        });

        no_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.enterScore(0);
            }
        });

        score_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "1";
                score.setText(new_value);
            }
        });

        score_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "2";
                score.setText(new_value);
            }
        });

        score_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "3";
                score.setText(new_value);
            }
        });

        score_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "4";
                score.setText(new_value);
            }
        });

        score_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "5";
                score.setText(new_value);
            }
        });

        score_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "6";
                score.setText(new_value);
            }
        });

        score_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "7";
                score.setText(new_value);
            }
        });

        score_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "8";
                score.setText(new_value);
            }
        });

        score_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "9";
                score.setText(new_value);
            }
        });

        score_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score_previous = score.getText().toString();
                String new_value = score_previous + "0";
                score.setText(new_value);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score.setText("");
            }
        });

        revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.revert();
                score.setText("");
            }
        });


    }

    private void setBeginnerPlayer() {

        Random rand = new Random();
        int position = rand.nextInt(players.size());
        Game.current_player = players.get(position);
    }

    @Override
    public void onBackPressed() {

        BackToStartDialog dialog = new BackToStartDialog();
        dialog.show(getSupportFragmentManager(), "exit");
    }

    private void createLayout(int number_of_players) {

        dashboard = findViewById(R.id.dashboard_placeholder);

        TextView player_name_1, player_score_1, player_last_Score_1, player_average_1;
        TextView player_name_2, player_score_2, player_last_Score_2, player_average_2;
        TextView player_name_3, player_score_3, player_last_Score_3, player_average_3;
        TextView player_name_4, player_score_4, player_last_Score_4, player_average_4;
        LinearLayout player_dashboard_1, player_dashboard_2, player_dashboard_3, player_dashboard_4;


        switch (number_of_players) {
            case 1:
                LayoutInflater layoutInflater1 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = layoutInflater1.inflate(R.layout.player_dashboard, dashboard);

                //Player 1
                player_name_1 = (TextView) v.findViewById(R.id.players_name);
                players.get(0).initNameDisplay(player_name_1);
                player_score_1 = (TextView) v.findViewById(R.id.player_score);
                players.get(0).initScore_display(player_score_1);
                player_last_Score_1 = (TextView) v.findViewById(R.id.last_throw);
                players.get(0).initLast_throw_display(player_last_Score_1);
                player_average_1 = (TextView) v.findViewById(R.id.player_avg);
                players.get(0).initAverage_display(player_average_1);
                player_dashboard_1 = v.findViewById(R.id.dashboard);
                players.get(0).initDashboard(player_dashboard_1);

                break;

            case 2:
                LayoutInflater layoutInflater2 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //left
                LinearLayout left_layout2 = new LinearLayout(getApplicationContext());
                left_layout2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                left_layout2.setOrientation(LinearLayout.VERTICAL);

                //right
                LinearLayout right_layout2 = new LinearLayout(getApplicationContext());
                right_layout2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                right_layout2.setOrientation(LinearLayout.VERTICAL);


                dashboard.addView(left_layout2);
                dashboard.addView(right_layout2);


                layoutInflater2.inflate(R.layout.player_dashboard, left_layout2);
                layoutInflater2.inflate(R.layout.player_dashboard, right_layout2);

                // Get and initialize TextViews
                //Player 1
                player_name_1 = (TextView) left_layout2.findViewById(R.id.players_name);
                players.get(0).initNameDisplay(player_name_1);
                player_score_1 = (TextView) left_layout2.findViewById(R.id.player_score);
                players.get(0).initScore_display(player_score_1);
                player_last_Score_1 = (TextView) left_layout2.findViewById(R.id.last_throw);
                players.get(0).initLast_throw_display(player_last_Score_1);
                player_average_1 = (TextView) left_layout2.findViewById(R.id.player_avg);
                players.get(0).initAverage_display(player_average_1);
                player_dashboard_1 = left_layout2.findViewById(R.id.dashboard);
                players.get(0).initDashboard(player_dashboard_1);

                //Player 2
                player_name_2 = (TextView) right_layout2.findViewById(R.id.players_name);
                players.get(1).initNameDisplay(player_name_2);
                player_score_2 = (TextView) right_layout2.findViewById(R.id.player_score);
                players.get(1).initScore_display(player_score_2);
                player_last_Score_2 = (TextView) right_layout2.findViewById(R.id.last_throw);
                players.get(1).initLast_throw_display(player_last_Score_2);
                player_average_2 = (TextView) right_layout2.findViewById(R.id.player_avg);
                players.get(1).initAverage_display(player_average_2);
                players.get(1).initDashboard(right_layout2);
                player_dashboard_2 = right_layout2.findViewById(R.id.dashboard);
                players.get(1).initDashboard(player_dashboard_2);
                break;


            case 3:
                LayoutInflater layoutInflater3 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //left
                LinearLayout left_layout3 = new LinearLayout(getApplicationContext());
                left_layout3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                left_layout3.setOrientation(LinearLayout.VERTICAL);

                //left top
                LinearLayout left_top_layout3 = new LinearLayout(getApplicationContext());
                left_top_layout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                left_top_layout3.setOrientation(LinearLayout.VERTICAL);

                //left bottom
                LinearLayout left_bottom_layout3 = new LinearLayout(getApplicationContext());
                left_bottom_layout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                left_bottom_layout3.setOrientation(LinearLayout.VERTICAL);


                //right
                LinearLayout right_layout3 = new LinearLayout(getApplicationContext());
                right_layout3.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                right_layout3.setOrientation(LinearLayout.VERTICAL);

                //right top
                LinearLayout right_top_layout3 = new LinearLayout(getApplicationContext());
                right_top_layout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                right_top_layout3.setOrientation(LinearLayout.VERTICAL);

                //right bottom
                LinearLayout right_bottom_layout3 = new LinearLayout(getApplicationContext());
                right_bottom_layout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                right_bottom_layout3.setOrientation(LinearLayout.VERTICAL);


                left_layout3.addView(left_top_layout3);
                left_layout3.addView(left_bottom_layout3);
                right_layout3.addView(right_top_layout3);
                right_layout3.addView(right_bottom_layout3);

                dashboard.addView(left_layout3);
                dashboard.addView(right_layout3);

                layoutInflater3.inflate(R.layout.player_dashboard, left_top_layout3);
                layoutInflater3.inflate(R.layout.player_dashboard, right_top_layout3);
                layoutInflater3.inflate(R.layout.player_dashboard, left_bottom_layout3);


                // Get and initialize TextViews
                //Player 1
                player_name_1 = (TextView) left_top_layout3.findViewById(R.id.players_name);
                players.get(0).initNameDisplay(player_name_1);
                player_score_1 = (TextView) left_top_layout3.findViewById(R.id.player_score);
                players.get(0).initScore_display(player_score_1);
                player_last_Score_1 = (TextView) left_top_layout3.findViewById(R.id.last_throw);
                players.get(0).initLast_throw_display(player_last_Score_1);
                player_average_1 = (TextView) left_top_layout3.findViewById(R.id.player_avg);
                players.get(0).initAverage_display(player_average_1);
                player_dashboard_1 = left_top_layout3.findViewById(R.id.dashboard);
                players.get(0).initDashboard(player_dashboard_1);

                //Player 2
                player_name_2 = (TextView) right_top_layout3.findViewById(R.id.players_name);
                players.get(1).initNameDisplay(player_name_2);
                player_score_2 = (TextView) right_top_layout3.findViewById(R.id.player_score);
                players.get(1).initScore_display(player_score_2);
                player_last_Score_2 = (TextView) right_top_layout3.findViewById(R.id.last_throw);
                players.get(1).initLast_throw_display(player_last_Score_2);
                player_average_2 = (TextView) right_top_layout3.findViewById(R.id.player_avg);
                players.get(1).initAverage_display(player_average_2);
                player_dashboard_2 = right_top_layout3.findViewById(R.id.dashboard);
                players.get(1).initDashboard(player_dashboard_2);

                //Player 3
                player_name_3 = (TextView) left_bottom_layout3.findViewById(R.id.players_name);
                players.get(2).initNameDisplay(player_name_3);
                player_score_3 = (TextView) left_bottom_layout3.findViewById(R.id.player_score);
                players.get(2).initScore_display(player_score_3);
                player_last_Score_3 = (TextView) left_bottom_layout3.findViewById(R.id.last_throw);
                players.get(2).initLast_throw_display(player_last_Score_3);
                player_average_3 = (TextView) left_bottom_layout3.findViewById(R.id.player_avg);
                players.get(2).initAverage_display(player_average_3);
                player_dashboard_3 = left_bottom_layout3.findViewById(R.id.dashboard);
                players.get(2).initDashboard(player_dashboard_3);
                break;

            case 4:
                LayoutInflater layoutInflater4 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //left
                LinearLayout left_layout4 = new LinearLayout(getApplicationContext());
                left_layout4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                left_layout4.setOrientation(LinearLayout.VERTICAL);

                //left top
                LinearLayout left_top_layout4 = new LinearLayout(getApplicationContext());
                left_top_layout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                left_top_layout4.setOrientation(LinearLayout.VERTICAL);

                //left bottom
                LinearLayout left_bottom_layout4 = new LinearLayout(getApplicationContext());
                left_bottom_layout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                left_bottom_layout4.setOrientation(LinearLayout.VERTICAL);


                //right
                LinearLayout right_layout4 = new LinearLayout(getApplicationContext());
                right_layout4.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
                right_layout4.setOrientation(LinearLayout.VERTICAL);

                //right top
                LinearLayout right_top_layout4 = new LinearLayout(getApplicationContext());
                right_top_layout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                right_top_layout4.setOrientation(LinearLayout.VERTICAL);

                //right bottom
                LinearLayout right_bottom_layout4 = new LinearLayout(getApplicationContext());
                right_bottom_layout4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));
                right_bottom_layout4.setOrientation(LinearLayout.VERTICAL);


                left_layout4.addView(left_top_layout4);
                left_layout4.addView(left_bottom_layout4);
                right_layout4.addView(right_top_layout4);
                right_layout4.addView(right_bottom_layout4);

                dashboard.addView(left_layout4);
                dashboard.addView(right_layout4);

                layoutInflater4.inflate(R.layout.player_dashboard, left_top_layout4);
                layoutInflater4.inflate(R.layout.player_dashboard, right_top_layout4);
                layoutInflater4.inflate(R.layout.player_dashboard, left_bottom_layout4);
                layoutInflater4.inflate(R.layout.player_dashboard, right_bottom_layout4);

                // Get and initialize TextViews
                //Player 1
                player_name_1 = (TextView) left_top_layout4.findViewById(R.id.players_name);
                players.get(0).initNameDisplay(player_name_1);
                player_score_1 = (TextView) left_top_layout4.findViewById(R.id.player_score);
                players.get(0).initScore_display(player_score_1);
                player_last_Score_1 = (TextView) left_top_layout4.findViewById(R.id.last_throw);
                players.get(0).initLast_throw_display(player_last_Score_1);
                player_average_1 = (TextView) left_top_layout4.findViewById(R.id.player_avg);
                players.get(0).initAverage_display(player_average_1);
                player_dashboard_1 = left_top_layout4.findViewById(R.id.dashboard);
                players.get(0).initDashboard(player_dashboard_1);

                //Player 2
                player_name_2 = (TextView) right_top_layout4.findViewById(R.id.players_name);
                players.get(1).initNameDisplay(player_name_2);
                player_score_2 = (TextView) right_top_layout4.findViewById(R.id.player_score);
                players.get(1).initScore_display(player_score_2);
                player_last_Score_2 = (TextView) right_top_layout4.findViewById(R.id.last_throw);
                players.get(1).initLast_throw_display(player_last_Score_2);
                player_average_2 = (TextView) right_top_layout4.findViewById(R.id.player_avg);
                players.get(1).initAverage_display(player_average_2);
                player_dashboard_2 = right_top_layout4.findViewById(R.id.dashboard);
                players.get(1).initDashboard(player_dashboard_2);

                //Player 3
                player_name_3 = (TextView) left_bottom_layout4.findViewById(R.id.players_name);
                players.get(2).initNameDisplay(player_name_3);
                player_score_3 = (TextView) left_bottom_layout4.findViewById(R.id.player_score);
                players.get(2).initScore_display(player_score_3);
                player_last_Score_3 = (TextView) left_bottom_layout4.findViewById(R.id.last_throw);
                players.get(2).initLast_throw_display(player_last_Score_3);
                player_average_3 = (TextView) left_bottom_layout4.findViewById(R.id.player_avg);
                players.get(2).initAverage_display(player_average_3);
                player_dashboard_3 = left_bottom_layout4.findViewById(R.id.dashboard);
                players.get(2).initDashboard(player_dashboard_3);

                //Player 4
                player_name_4 = (TextView) right_bottom_layout4.findViewById(R.id.players_name);
                players.get(3).initNameDisplay(player_name_4);
                player_score_4 = (TextView) right_bottom_layout4.findViewById(R.id.player_score);
                players.get(3).initScore_display(player_score_4);
                player_last_Score_4 = (TextView) right_bottom_layout4.findViewById(R.id.last_throw);
                players.get(3).initLast_throw_display(player_last_Score_4);
                player_average_4 = (TextView) right_bottom_layout4.findViewById(R.id.player_avg);
                players.get(3).initAverage_display(player_average_4);
                player_dashboard_4 = right_bottom_layout4.findViewById(R.id.dashboard);
                players.get(3).initDashboard(player_dashboard_4);
                break;

            default:
                break;
        }


    }
}