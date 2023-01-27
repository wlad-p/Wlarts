package com.example.wlarts;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Player {

    private int id;
    private String name;
    private float average;
    private ArrayList<Integer> darts_thrown;
    private int points_left;
    private TextView name_display;
    private TextView score_display;
    private TextView average_display;
    private TextView last_throw_display;
    private LinearLayout dashboard;

    public Player(int id, String name, int starting_points) {
        this.id = id;
        this.name = name;
        this.average = 0;
        this.darts_thrown = new ArrayList<Integer>();
        this.points_left = starting_points;
    }

    public void enterScore(int score) {
        this.darts_thrown.add(score);
        // Score
        this.points_left -= score;
        this.score_display.setText(String.valueOf(this.points_left));

        String last_throw = "";
        if (this.darts_thrown.size() < 1)
            last_throw += "";
        else
            last_throw += String.valueOf(this.darts_thrown.get(this.darts_thrown.size() - 1));

        last_throw_display.setText(last_throw);
        computeAverage();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        average_display.setText(df.format(this.average));
    }

    public void computeAverage() {

        float total = 0;
        if (darts_thrown.size() > 0) {
            for (int i = 0; i < darts_thrown.size(); i++) {
                total += darts_thrown.get(i);
            }
            this.average = (total / darts_thrown.size());
        } else
            this.average = 0.00f;
    }

    public int abc() {
        return this.darts_thrown.size();
    }

    public int getId() {
        return this.id;
    }

    public void registerPoints(int points) {
        this.points_left -= points;
    }

    public int getPointsLeft() {
        return this.points_left;
    }

    public void setPlayersTurn() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 0);
        this.dashboard.setLayoutParams(params);
        this.dashboard.setBackgroundColor(Color.parseColor("#7E2626"));
        this.dashboard.setPadding(20, 20, 20, 20);
    }


    public void setNotPlayersTurn() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(10, 10, 10, 10);
        this.dashboard.setLayoutParams(params);
        this.dashboard.setBackgroundColor(Color.parseColor("#272626"));
        this.dashboard.setPadding(0, 0, 0, 0);
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
        this.average_display.setText(String.valueOf(average));
    }

    public ArrayList<Integer> getDarts_thrown() {
        return darts_thrown;
    }

    public void setDarts_thrown(ArrayList<Integer> darts_thrown) {
        this.darts_thrown = darts_thrown;
    }

    public int getPoints_left() {
        return points_left;
    }

    public void setPoints_left(int points_left) {
        this.points_left = points_left;
    }

    public TextView getScore_display() {
        return score_display;
    }

    public void initScore_display(TextView score_display) {
        this.score_display = score_display;
    }

    public void initDashboard(LinearLayout dashboard) {
        this.dashboard = dashboard;
    }


    public void initNameDisplay(TextView name_display) {
        this.name_display = name_display;
        this.name_display.setText(this.name);
    }

    public TextView getAverage_display() {
        return average_display;
    }

    public void initAverage_display(TextView average_display) {
        this.average_display = average_display;
    }

    public void deleteLastScore() {
        int last_score = darts_thrown.get(darts_thrown.size() - 1);
        this.darts_thrown.remove(darts_thrown.size() - 1);
        this.points_left += last_score;
        this.score_display.setText(String.valueOf(this.points_left));

        String last_throw = "";
        if (this.darts_thrown.size() < 1)
            last_throw += "";
        else
            last_throw += String.valueOf(this.darts_thrown.get(this.darts_thrown.size() - 1));

        last_throw_display.setText(last_throw);
        computeAverage();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        average_display.setText(df.format(this.average));
    }

    public int getNumberOfThrows() {
        return this.darts_thrown.size();
    }

    public TextView getLast_throw_display() {
        return last_throw_display;
    }

    public void initLast_throw_display(TextView last_throw_display) {
        this.last_throw_display = last_throw_display;
    }
}
