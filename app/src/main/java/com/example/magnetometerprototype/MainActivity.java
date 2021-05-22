package com.example.magnetometerprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String filepath = this.getApplicationInfo().dataDir + File.separatorChar + "outputCut.csv";
        ArrayList<Float[]> result = importMapping();

    }

    ArrayList<Float[]> importMapping(){
        //loads a mapping from real coordinates x [0] y [1] to magnetic field strength values x [2] y [3]
        ArrayList<Float[]> result = new ArrayList<>();
        try {
            Log.i("Info", "test");
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.outputcut)));
            String[] nextLine;
            boolean firstLine=true;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                if(firstLine){
                    firstLine=false;
                    continue;
                }
                Log.i("Info",""+nextLine[0]);
                result.add(new Float[] {Float.parseFloat(nextLine[0]),Float.parseFloat(nextLine[1]), Float.parseFloat(nextLine[6]), Float.parseFloat(nextLine[5])});
            }
        } catch (IOException e) {

        }
        return result;
    }
}