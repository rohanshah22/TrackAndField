package com.example.trackandfield;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class DataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        fixString(getIntent().getStringArrayListExtra("scores"));
//        init();
        listView=(ListView)findViewById(R.id.listView);
        textView=(TextView)findViewById(R.id.textView);
        convertArrayList();
        setListView();
    }
    String[] listItem;
    ListView listView;
    TextView textView;

    //creates the listview
    private void setListView() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
    }

    // takes information from formatted string, uses substring to isolate data
    // isolates data and adds to class
    private void fixString(ArrayList<String> scores) {
        for (int i = 0; i < scores.size(); i++) {
            String temp = scores.get(i);
            if (temp != null) {
                String name = temp.substring(0, temp.indexOf(";"));
                Double time = Double.parseDouble(temp.substring(1 + temp.indexOf(";")));
                holdPlayerData.add(new Player(name, time));
            }
        }
        sortPlayers();
        String x = "";
        for (int i = 0; i < holdPlayerData.size(); i++) {
            x += holdPlayerData.get(i).getName() + "" + holdPlayerData.get(i).getScore();
        }
    }
    // creates a traditional array the size of the arraylist
    // iterates through and adds data in the form of a string
    private void convertArrayList() {
        listItem = new String[holdPlayerData.size()];
        for(int i = 0; i < holdPlayerData.size();i++) {
            Player x = holdPlayerData.get(i);
            listItem[i] = i+1 + ". "+ x.getName() + "   " + x.getScore();
        }
    }
    ArrayList<Player> tempArray = new ArrayList<>();
    ArrayList<Player> holdPlayerData = new ArrayList<>();

    public void sortPlayers() {
        tempArray.clear();
        //Purpose of this function is to sort the players score
        for (int x = 0; x < holdPlayerData.size(); x++) {
            int indexOf = x;
            for (int y = x + 1; y < holdPlayerData.size(); y++) {
                if (holdPlayerData.get(y).getScore() < holdPlayerData.get(indexOf).getScore()) {
                    indexOf = y;//if their is a lower age found  take the index of it
                }
            }
            tempArray.add(holdPlayerData.get(indexOf));//Add that index to a temporary array for sorting
            holdPlayerData.set(indexOf, holdPlayerData.get(x));//Set the original array of objects to that temp spot in the index
            holdPlayerData.set(x, tempArray.get(x));//Finally, set the original positions wth the objects at the temporary spots
        }

    }
    //saves a blank string as the whole file
    private static final String FILE_NAME = "example.txt";
    public void resetFile(View v) {
        String text = "";
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        holdPlayerData.clear();
        convertArrayList();
        setListView();
    }
    private Intent intent;
    public void switchActivity(View v) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }
}
