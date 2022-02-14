package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> usuarios = new ArrayList<String>();
    private int range, rangeUsuario;
    private boolean verificador;
    private FileManager fileManager = new FileManager();
    private int linea;


    public Word(){
        datos();
        this.rangeUsuario = usuarios.size();

        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
        this.range = diccionario.size();

    }

    public void datos(){
        FileManager fileManager = new FileManager();
        usuarios = fileManager.LecturaUsuario();
    }

    public int getLevel(int n) {
        char c = usuarios.get(n).charAt(usuarios.get(n).length()-1);
        int dig = Character.getNumericValue(c);
        System.out.println(dig);
        return dig;
    }

    public void setLevel(int n, int level) {
        char c = usuarios.get(n).charAt(usuarios.get(n).length()-1);
        String nuevoTexto = getNombre(n) + ";" + level;
        fileManager.escribirTexto(nuevoTexto);
    }

    public String setLevelName(String nombre, int level) {
        String nuevoTexto = nombre + ";" + level;
        fileManager.escribirTexto(nuevoTexto);
        return nuevoTexto;
    }

    public int ultimoNombre(String nombre){
        if(getUsuario(nombre)){
            for(int i=0;i<usuarios.size();i++){
                if(getNombre(i).equals(nombre)){
                    linea = i;
                }
            }
        }else{
            linea = 0;
        }

        return linea;
    }

    public String getNombre(int n){
        String clave ="";
        for(int i=0;i<usuarios.get(n).length();i++){
            if(usuarios.get(n).charAt(i) == ';'){
                i = usuarios.get(n).length();
            }else{
                clave += usuarios.get(n).charAt(i);
            }
        }
        return clave;
    }

    public Boolean getUsuario(String nuevo) {
        for(int i=0; i<usuarios.size(); i++){
            if(nuevo.equals(getNombre(i))){
                i = usuarios.size();
                verificador = true;
            }else{
                verificador = false;
            }
        }
        return verificador;
    }

    public String getFrase(int number) { return diccionario.get(number); }

    public int getRangeUsuario() { return rangeUsuario; }

    public int getRange() { return range; }
}