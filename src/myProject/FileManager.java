package myProject;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static  final String PATH = "src/files/word.txt";
    public static  final String PATH2 = "src/files/datos.txt";
    private FileReader fileReader , fileReaderUsuario;
    private BufferedReader input , inputUsuario;
    private FileWriter fileWriter, fileWriterGuardados;
    private BufferedWriter output, outputGuardados;

    /**
     * Method LecturaUsuario read word data file
     */
    public ArrayList<String> lecturaFile() {
        ArrayList<String> frases = new ArrayList<String>();
        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                frases.add(line);
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return frases;
    }
    /**
     * Method LecturaUsuario read users data file
     */
    public ArrayList<String> LecturaUsuario() {
        ArrayList<String> datos = new ArrayList<String>();
        try {
            fileReaderUsuario = new FileReader(PATH2);
            inputUsuario = new BufferedReader(fileReaderUsuario);
            String line = inputUsuario.readLine();
            while(line!=null){
                datos.add(line);
                line=inputUsuario.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                inputUsuario.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datos;
    }
    /**
     * Method escribirTexto Write text in a archive
     */
    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter("src/files/datos.txt",true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void limpiarTextoGuardados(){
        try {
            fileWriterGuardados = new FileWriter("src/files/datos.txt",false);
            outputGuardados = new BufferedWriter(fileWriterGuardados);
            outputGuardados.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outputGuardados.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
