package com.example.trackandfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";

    EditText mEditText;
    EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a comment

        mEditText = findViewById(R.id.edit_text);
        mEditText2 = findViewById(R.id.edit_text2);
    }


    public void save(View v){
        String allEntries= "";
        String newEntry = mEditText.getText().toString();
        String newEntryTime = mEditText2.getText().toString();
        FileOutputStream fos = null;
        try {
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String oldEntries;

                while((oldEntries = br.readLine()) != null){
                    sb.append(oldEntries).append("\n");
                }
                // sb becomes all old entries, formatted
                allEntries = sb.toString()+ newEntry + ";" + newEntryTime;
                mEditText.setText(sb.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(allEntries.getBytes());

            mEditText.getText().clear();
            mEditText2.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
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
    }
    public ArrayList<String> load(){
        FileInputStream fis = null;
        String allEntries= "";
        ArrayList<String> allEntriesList = new ArrayList();
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                allEntriesList.add(br.readLine());
                sb.append(text).append("\n");
            }
            allEntries= sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allEntriesList;

    }
    private Intent intent;
    public void switchActivity(View v) {
        intent = new Intent(this, DataActivity.class);
        intent.putExtra("scores",load());
        startActivity(intent);
    }
}
