package com.example.trackandfield;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class DataActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        //this is a comment
        viewText = (TextView)findViewById(R.id.textView3);
        fixString(getIntent().getStringArrayListExtra("scores"));
        viewText.setText(getIntent().getStringArrayListExtra("scores").get(2));
    }
    // start of string is =, end of string is ;
    TextView viewText;
    private void fixString(ArrayList<String> scores) {
        if(scores != null) {
            for (int i = 0; i < scores.size(); i++) {
                String temp = scores.get(i);
                if(temp != null) {
                    String name = temp.substring(0, temp.indexOf(";"));
                    Double time = Double.parseDouble(temp.substring(1 + temp.indexOf(";")));
                    holdPlayerData.add(new Player(name, time));
                }
            }
            sortPlayers();

        }
    }
    ArrayList<Player> tempArray = new ArrayList<>();
    ArrayList<Player> holdPlayerData = new ArrayList<>();
    public void sortPlayers(){
        tempArray.clear();
        //Purpose of this function is to sort the players score
        for(int x=0;x<holdPlayerData.size();x++){
            int indexOf = x;
            for(int y=x+1;y<holdPlayerData.size();y++){
                if(holdPlayerData.get(y).getScore() < holdPlayerData.get(indexOf).getScore() ){
                    indexOf = y;//if their is a lower age found  take the index of it
                }
            }
            tempArray.add(holdPlayerData.get(indexOf));//Add that index to a temporary array for sorting
            holdPlayerData.set(indexOf, holdPlayerData.get(x));//Set the original array of objects to that temp spot in the index
            holdPlayerData.set(x, tempArray.get(x));//Finally, set the original positions wth the objects at the temporary spots
        }

    }
}
