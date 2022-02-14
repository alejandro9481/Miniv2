package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> usuarios = new ArrayList<String>();
    private int range;

    public Word(){
        this.range = 200;
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
    }

    public void datos(){
        FileManager fileManager = new FileManager();
        usuarios = fileManager.LecturaUsuario();
    }

    public String getLevel() { return usuarios.get(usuarios.size()-1); }

    public Boolean getUsuario(String nuevo) {


        for(int i=0; i<usuarios.size(); i++){
            if(nuevo == usuarios.get(i)){
                i = usuarios.size();
                return true;

            }else{
                return false;
            }
        }
        return true;
    }


    public String getFrase(int number) { return diccionario.get(number); }

    public int getRange() { return range; }
}