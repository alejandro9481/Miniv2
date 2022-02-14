package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ControlIKnowThatWord control = new ControlIKnowThatWord(1,0);

    /**
     * Constructor of GUI class
     */
    public GUIIKTW(){

            initGUI();

            //Default JFrame configuration
            this.setTitle("I KNOW THAT WORD!! ");
            this.setSize(400,300);
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

        //JPanels
        panelsur = new JPanel();    panelCentro = new JPanel();
        panelNorte = new JPanel();


        //JLabels
        puntuacion = new JLabel("       Score:       "+control.getSuccess()*10);
        nivel = new JLabel("Level:    "+control.getLevel());
        palabra = new JLabel(control.getFirstWord());
        espacio0 = new JLabel("                                              "+
                                "                                                 "+
                                "                                                 "+
                                "                                                 ");
        espacio1 = new JLabel("                                               "+
                                "            ");
        espacio2 = new JLabel("                                              "+
                                "                                 ");


        //Norte
        panelNorte.setPreferredSize(new Dimension(500,100));
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

        panelsur.add(nivel);
        panelsur.add(puntuacion);
        panelsur.add(espacio2);
        panelsur.add(salir);
        this.add(panelsur,BorderLayout.SOUTH);

        //No visible e inhabilitado
        si.setVisible(false);               si.setEnabled(false);
        no.setVisible(false);               no.setEnabled(false);
        salir.setVisible(false);            salir.setEnabled(false);

        control.setTime(control.getTime()+4);
        tiempo.start();
    }

    public void finalRonda(){
        double porcentajeAciertos = (100/(2*control.getLearnWord()))*control.getSuccess();

        if(control.getSuccess() < control.verifyAnswer()){
            int option = JOptionPane.showConfirmDialog(panelCentro, "You Lose!!\n"+
                        " End of level \n"+
                        " You have not passed the level. \n"+
                        " You need a "+control.message()+
                        "% of right guess to go to the next level. \n"+
                        " Right guess:  "+control.getSuccess()+" \n"+
                        " Right guess percentage: "+df.format(porcentajeAciertos)+"% \n"+
                        " Score: "+control.getSuccess()*10+" \n"+
                        " Do you want to play the same round?",

                    "Defeat ", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //Reinicio ronda
                control = new ControlIKnowThatWord(control.getLevel(), control.getScore());
                initGUI();
            }else if(option == JOptionPane.NO_OPTION){
                //Guardar ronda
                System.exit(0);
            }
        }else{
            int option = JOptionPane.showConfirmDialog(panelCentro, "you win!!\n"+
                            " End of level \n"+
                            " You have passed to the next level. \n"+
                            " Right guess:  "+control.getSuccess()+" \n"+
                            " Right guess percentage: "+df.format(porcentajeAciertos)+"% \n"+
                            " Score: "+control.getSuccess()*10+" \n"+
                            " Do you want to play another round ?",

                    "Victory", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //nueva ronda

            }else if(option == JOptionPane.NO_OPTION){
                //Guardar los datos

                System.exit(0);
            }
        }
    }

    public boolean encontrar(String palabra){
        for(int i=0;i<control.getTrueWord().length;i++){
            if(palabra == control.getTrueWord()[i]){
                control.setYesOrNot(true);
                i += control.getTrueWord().length;
            }else{
                control.setYesOrNot(false);
            }
        }
        return control.isYesOrNot();
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
                panelCentro.setBackground(Color.WHITE);
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
                            control.setCont(-1);
                            tiempo.stop();

                            int option = JOptionPane.showConfirmDialog(panelCentro,
                                    "Do you wish continue?",

                                    "¿?", JOptionPane.YES_NO_OPTION);

                            if(option == JOptionPane.YES_OPTION){
                                // visible y Habilitado
                                si.setVisible(true);               si.setEnabled(true);
                                no.setVisible(true);               no.setEnabled(true);
                                salir.setVisible(true);            salir.setEnabled(true);

                                tiempo.start();
                            }else{ System.exit(0); }
                        }
                    }
                }else{
                    control.setTime(control.getTime()+1);
                    if(control.getTime() < 5){//7

                    }else{
                        //ERROR
                        panelCentro.setBackground(Color.RED);
                        control.setFail(control.getFail()+1);

                        //Condicional para que muestre todas las palabras 
                        if(control.getAllWord().length > control.getCont()+1){
                            control.setCont(control.getCont()+1);
                            control.setFirstWord(control.getAllWord()[control.getCont()]);
                            palabra.setText(control.getFirstWord());


                            panelNorte.repaint();

                        }else{
                            //Estadisticas de la partida
                            tiempo.stop();
                            control.setCont(0);
                            finalRonda();
                        }
                        control.setTime(0);
                    }
                }


            }else if(eventAction.getSource() == si){
                if(encontrar(control.getFirstWord())){
                    //Correcto
                    panelCentro.setBackground(Color.GREEN);
                    control.setSuccess(control.getSuccess()+1);
                    puntuacion.setText("       Score:       "+control.getSuccess()*10);
                    panelsur.repaint();

                    //Cambio de palabra
                    if(control.getAllWord().length > control.getCont()+1){
                        control.setCont(control.getCont()+1);
                        control.setFirstWord(control.getAllWord()[control.getCont()]);
                        palabra.setText(control.getFirstWord());

                        panelNorte.repaint();
                    }

                    control.setTime(0);
                }else{
                    //ERROR
                    panelCentro.setBackground(Color.RED);
                    control.setFail(control.getFail()+1);

                    //Cambio de palabra
                    if(control.getAllWord().length > control.getCont()+1){
                        control.setCont(control.getCont()+1);
                        control.setFirstWord(control.getAllWord()[control.getCont()]);
                        palabra.setText(control.getFirstWord());

                        panelNorte.repaint();
                    }
                    control.setTime(0);
                }
            }else if(eventAction.getSource() == no){
                if(encontrar(control.getFirstWord())){
                    //ERROR
                    panelCentro.setBackground(Color.RED);
                    control.setFail(control.getFail()+1);

                    //Cambio de palabra
                    if(control.getAllWord().length > control.getCont()+1){
                        control.setCont(control.getCont()+1);
                        control.setFirstWord(control.getAllWord()[control.getCont()]);
                        palabra.setText(control.getFirstWord());

                        panelNorte.repaint();
                    }
                    control.setTime(0);
                }else{
                    //Correcto
                    panelCentro.setBackground(Color.GREEN);
                    control.setSuccess(control.getSuccess()+1);
                    puntuacion.setText("       Score:       "+control.getSuccess()*10);
                    panelsur.repaint();

                    //Cambio de palabra
                    if(control.getAllWord().length > control.getCont()+1){
                        control.setCont(control.getCont()+1);
                        control.setFirstWord(control.getAllWord()[control.getCont()]);
                        palabra.setText(control.getFirstWord());

                        panelNorte.repaint();
                    }
                    control.setTime(0);
                }
            }
            revalidate();
            repaint();
        }
    }
}
