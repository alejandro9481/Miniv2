package myProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Class GUIIKTW is the V
 * @author Alejandro Villamil
 * @author Javier Castrillon
 * @version v.1.0.0 date 07/02/2022
 */

public class GUIIKTW extends JFrame {

    private JPanel panelsur, panelCentro, panelNorte;
    private JLabel puntuacion, nivel, palabra, espacio0, espacio1,espacio2;
    private JButton si,no,salir;

    private JFrame vista= this;
    private Timer tiempo;
    private Escucha escucha;

    private DecimalFormat df = new DecimalFormat("#.00");
    private ControlIKnowThatWord control = new ControlIKnowThatWord(1);

    /**
     * Constructor of GUI class
     */
    public GUIIKTW(){

            initGUI();

            //Default JFrame configuration
            this.setTitle("I KNOW THAT WORD!!");
            //this.setSize(200,100);
            this.pack();
            this.setResizable(true);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //definir Window container y layout

        //crear el escucha junto al timer
        escucha = new Escucha();
        tiempo = new Timer(1000, escucha);
        vista =this;

        //Botones y agregar al escucha
        si = new JButton("YES");                si.addActionListener(escucha);
        no = new JButton("NOT");                no.addActionListener(escucha);
        salir = new JButton("EXIT");            salir.addActionListener(escucha);

        //No visible e inhabilitado
        si.setVisible(false);               si.setEnabled(false);
        no.setVisible(false);               no.setEnabled(false);
        salir.setVisible(false);            salir.setEnabled(false);

        //JPanels
        panelsur = new JPanel();    panelCentro = new JPanel();
        panelNorte = new JPanel();


        //JLabels
        puntuacion = new JLabel("Score:");
        nivel = new JLabel("Level: ");
        palabra = new JLabel(control.getFirstWord());
        espacio0 = new JLabel("                                              "+
                "                                                                            ");
        espacio1 = new JLabel("                                      ");
        espacio2 = new JLabel("                                              "+
                                    "                                             "+
                                    "                  ");


        //Norte
        panelNorte.setPreferredSize(new Dimension(100,100));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Word's Zone "));
        panelNorte.add(espacio0);
        panelNorte.add(palabra);

        //panelNorte.add(palabra,BorderLayout.SOUTH);
        this.add(panelNorte,BorderLayout.NORTH);

        //centro
        panelCentro.setPreferredSize(new Dimension(400,150));
        panelCentro.add(si);
        panelCentro.add(espacio1);
        panelCentro.add(no);
        this.add(panelCentro,BorderLayout.CENTER);

        //sur
        panelsur.setPreferredSize(new Dimension(400,40));
        panelsur.add(espacio2);
        panelsur.add(salir);
        this.add(panelsur,BorderLayout.SOUTH);

        control.setTime(control.getTime()+3);
        tiempo.start();
    }

    public void finalRonda(){
        double PorcentajeAciertos = (100/2*control.getLearnWord())*control.getSuccess();

        if(control.getFail() >= 10){
            int option = JOptionPane.showConfirmDialog(panelCentro, "Perdiste!!\n"+
                        " End of level \n"+
                        " No has superado el nivel. \n"+
                        " Necesitas un "+control.message()+
                        "% de aciertos para pasar al siguiente nivel. \n"+
                        " Aciertos:  "+control.getSuccess()+" \n"+
                        " Porcentaje de aciertos: "+df.format(PorcentajeAciertos)+"% \n"+
                        " puntuacion: "+control.getSuccess()*10+" \n"+
                        "¿Quieres jugar la misma ronda?",

                    "Perdiste", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //nueva ronda

            }else if(option == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }else{
            int option = JOptionPane.showConfirmDialog(panelCentro, "Ganaste!!\n"+
                            " End of level \n"+
                            " You have passed to the next level. \n"+
                            " Right guess:  "+control.getSuccess()+" \n"+
                            " Right guess percentage: "+df.format(PorcentajeAciertos)+"% \n"+
                            " Score: "+control.getSuccess()*10+" \n"+
                            "¿Quieres jugar otra ronda?",

                    "Ganaste", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //nueva ronda

            }else if(option == JOptionPane.NO_OPTION){
                //Guardar los datos

                System.exit(0);
            }
        }
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIIKTW miProjectGUI = new GUIIKTW();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventAction) {
            if(eventAction.getSource() == salir){
                System.exit(0);
            }else if(eventAction.getSource() == tiempo){

                //Para saber si es el inicio de la vista o no
                if(control.isStart()){
                //System.out.println("aquí estoy");
                    control.setTime(control.getTime()+1);
                    if(control.getTime() < 1){//5

                    }else{
                        //Palabra
                        control.setTime(0);

                        //Condicional para que muestre las palabras verdaderas cada 5 segundos
                        if(control.getTrueWord().length > control.getCont()){
                            control.setFirstWord(control.getTrueWord()[control.getCont()]);
                            control.setCont(control.getCont()+1);
                            palabra.setText(control.getFirstWord());
                            panelNorte.repaint();
                        }else{
                            control.setStart(false);
                            control.setCont(0);
                            tiempo.stop();

                            int option = JOptionPane.showConfirmDialog(panelCentro,
                                    "Desea continuar?",

                                    "¿?", JOptionPane.YES_NO_OPTION);

                            if(option == JOptionPane.YES_OPTION){
                                // visible y Habilitado
                                si.setVisible(true);               si.setEnabled(true);
                                no.setVisible(true);               no.setEnabled(true);
                                salir.setVisible(true);            salir.setEnabled(true);

                                tiempo.start();
                            }else{
                                System.exit(0);
                            }
                        }
                    }
                }else{
                    control.setTime(control.getTime()+1);
                    if(control.getTime() < 1){//7

                    }else{
                        control.setTime(0);

                        //Condicional para que muestre todas las palabras 
                        if(control.getAllWord().length > control.getCont()){
                            control.setFirstWord(control.getTrueWord()[control.getCont()]);
                            control.setCont(control.getCont()+1);
                            palabra.setText(control.getFirstWord());

                            panelNorte.repaint();

                        }else{
                            control.setCont(0);
                        }
                    }
                }


            }else if(eventAction.getSource() == si){

            }else if(eventAction.getSource() == no){

            }else if(eventAction.getSource() == salir){

            }
            revalidate();
            repaint();
        }
    }
}
