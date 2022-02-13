package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private ArrayList<String> diccionario = new ArrayList<String>();
    private int range;
    public Word(){
        this.range = 200;
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
    }

    public String getFrase(int number) { return diccionario.get(number); }

    public int getRange() { return range; }
}