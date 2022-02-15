package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Word is the one that manages the information of the user files and words
 * @author Alejandro Villamil
 * @author Javier Castrillon
 * @version v.2.0.0 date 14/02/2022
 */

public class Word {
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> usuarios = new ArrayList<String>();
    private int range, rangeUsuario;
    private boolean verificador;
    private FileManager fileManager = new FileManager();
    private int linea;

    /**
     * Constructor of Word class
     */
    public Word(){
        datos();
        this.rangeUsuario = usuarios.size();
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile();
        this.range = diccionario.size();

    }
    /**
     * Method datos read the data of the users file
     */
    public void datos(){
        FileManager fileManager = new FileManager();
        usuarios = fileManager.LecturaUsuario();
    }
    /**
     * This method is used for get player level
     * @param n
     * @return the level of the player
     */
    public int getLevel(int n) {
        char c = usuarios.get(n).charAt(usuarios.get(n).length()-1);
        int dig = Character.getNumericValue(c);
        System.out.println(dig);
        return dig;
    }
    /**
     * Method setLevel set the users level
     */
    public void setLevel(int n, int level) {
        char c = usuarios.get(n).charAt(usuarios.get(n).length()-1);
        String nuevoTexto = getNombre(n) + ";" + level;
        fileManager.escribirTexto(nuevoTexto);
    }
    /**
     * Method setLevelName set the users name
     */
    public String setLevelName(String nombre, int level) {
        String nuevoTexto = nombre + ";" + level;
        fileManager.escribirTexto(nuevoTexto);
        return nuevoTexto;
    }

    /**
     *
     * @param nombre is the name I want to search
     * @return the last name in the file of users
     */
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

    /**
     *
     * @param n is the line in the users file
     * @return the name of the user
     */
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

    /**
     *
     * @param nuevo is the user name
     * @return if a user is new in the users file
     */
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

    /**
     *
     * @param number is the position on the file
     * @return the phrase of a specific position
     */
    public String getFrase(int number) { return diccionario.get(number); }

    /**
    * Method getRangeUsuario return the users range
     */
    public int getRangeUsuario() { return rangeUsuario; }

    /**
     * Method getRange return the words range
     */
    public int getRange() { return range; }
}