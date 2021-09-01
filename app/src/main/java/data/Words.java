package data;


import android.util.Log;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.LineNumberReader;

import java.nio.charset.StandardCharsets;

import java.util.Random;


import ui.Sc_MainActivity;

public class Words {

    private final Random rndIdx;
    private final int id;
    private final BufferedReader brd;

    Words(int id){
        this.id = id;
        this.brd = INI_Reader(id);
        this.rndIdx = new Random();
    }
    public String getWord() {
        int xRnd;
        String word = "";
        try {
            xRnd = rndIdx.nextInt(this.getNumOfLines());
           for (int i = xRnd; i >= 0; i--) word = brd.readLine();
            word = (word == null) ? brd.readLine() : word;
            brd.close();
        } catch (Exception e) {
            Log.e("Error","Can't read from A Buffer Reader ..");
        }
        return word;
    }

    private BufferedReader INI_Reader(int id) {
        try {
            InputStream  in = Sc_MainActivity.getContext().getResources().openRawResource(id);
            return new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            Log.e("Error","Can't initialize the Reader object ..");
        }
        return  null;
    }
    private int getNumOfLines() {
        int num = 0;
        try {
            InputStream in = Sc_MainActivity.getContext().getResources().openRawResource(this.id);
            LineNumberReader Lrd = new LineNumberReader(new InputStreamReader(in));
            Lrd.skip(Integer.MAX_VALUE);
            num = Lrd.getLineNumber();
            in.close();
            Lrd.close();
        }
        catch (Exception e)
        {
            Log.e("Error","Cant get the number of lines ");
        }
        return num + 1;
    }


}
