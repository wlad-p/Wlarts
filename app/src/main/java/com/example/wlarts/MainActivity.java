package com.example.wlarts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button start_game;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout player_selection = (LinearLayout) findViewById(R.id.player_selection);
        View new_player_layout = getLayoutInflater().inflate(R.layout.new_player_helper, null);
        player_selection.addView(new_player_layout);
        ConstraintLayout delete_player = (ConstraintLayout) new_player_layout.findViewById(R.id.delete_player);
        delete_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player_selection.getChildCount() > 1)
                    player_selection.removeView(new_player_layout);
            }
        });

        Button add_player = (Button) findViewById(R.id.add_player);
        add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player_selection.getChildCount() < 4) {
                    View new_player_layout = getLayoutInflater().inflate(R.layout.new_player_helper, null);
                    ConstraintLayout add_photo = (ConstraintLayout) new_player_layout.findViewById(R.id.add_photo);

                    ImageView photo_image_view = (ImageView) new_player_layout.findViewById(R.id.photo);

                    add_photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                                {
                                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                }
                                else
                                {
                                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                }
                            }

                        }
                    });

                    ConstraintLayout delete_player = (ConstraintLayout) new_player_layout.findViewById(R.id.delete_player);
                    delete_player.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (player_selection.getChildCount() > 1)
                                player_selection.removeView(new_player_layout);
                        }
                    });

                    player_selection.addView(new_player_layout);
                }

            }
        });


        start_game = (Button) findViewById(R.id.start_game);
        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> players_names = new ArrayList<>();

                for( int i=0; i < player_selection.getChildCount(); i++){
                    View v = player_selection.getChildAt(i);
                    EditText name_input = (EditText) v.findViewById(R.id.player_name);
                    name_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            boolean handled = false;
                            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                                handled = true;
                            }
                            return handled;
                        }
                    });
                    String name = name_input.getText().toString();
                    players_names.add(name);


                }


                Intent i = new Intent(MainActivity.this, GameActivity.class);
                i.putExtra("players_names", players_names);
                startActivity(i);
            }

        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }


    @Override
    public void onBackPressed()
    {
    }
}