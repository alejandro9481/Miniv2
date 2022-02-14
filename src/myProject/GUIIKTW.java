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

    private JPanel panelsur, panelCentro, panelNorte, panelUsuarioN, panelUsuarioS;
    private JLabel puntuacion, nivel, palabra, espacio0, espacio1,espacio2;
    private JButton si, no, salir, ingresar;
    private JTextField ingresarNombre;
    private JFrame vista = this;
    private Timer tiempo;
    private Escucha escucha;

    private DecimalFormat df = new DecimalFormat("#.00");
    private ControlIKnowThatWord control = new ControlIKnowThatWord(1,"");
    private ControlIKnowThatWord c0ntrol = null;

    /**
     * Constructor of GUI class
     */
    public GUIIKTW(){

            escucha = new Escucha();
            initGUI();

            //Default JFrame configuration

            this.setTitle("I KNOW THAT WORD!! ");
            //this.setSize(400,300);
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
    private void initGUI(){
        ingresar = new JButton("ENTER");
        ingresarNombre = new JTextField(20);
        panelUsuarioN = new JPanel();
        panelUsuarioS = new JPanel();
        panelUsuarioN.add(ingresarNombre);
        panelUsuarioS.add(ingresar);
        ingresar.addActionListener(escucha);

        panelUsuarioN.setPreferredSize(new Dimension(350,60));
        panelUsuarioN.setBorder(BorderFactory.createTitledBorder("Enter your username (Example: alejandro)"));
        panelUsuarioS.setPreferredSize(new Dimension(350,40));

        this.add(panelUsuarioN,BorderLayout.NORTH);
        this.add(panelUsuarioS,BorderLayout.SOUTH);

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initDIU(ControlIKnowThatWord otro) {
        //definir Window container y layout
        //removeAll();
        //crear el escucha junto al timer

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
        puntuacion = new JLabel("       Score:       "+otro.getSuccess()*10);
        nivel = new JLabel("Level:    "+otro.getLevel());
        palabra = new JLabel(otro.getFirstWord());
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

        otro.setTime(otro.getTime()+4);
        tiempo.start();
    }

    public void finalRonda(){
        double porcentajeAciertos = (100/(2* c0ntrol.getLearnWord()))* c0ntrol.getSuccess();

        if(c0ntrol.getSuccess() < c0ntrol.verifyAnswer()){
            int option = JOptionPane.showConfirmDialog(panelCentro, "You Lose!!\n"+
                        " End of level \n"+
                        " You have not passed the level. \n"+
                        " You need a "+ c0ntrol.message()+
                        "% of right guess to go to the next level. \n"+
                        " Right guess:  "+ c0ntrol.getSuccess()+" \n"+
                        " Right guess percentage: "+df.format(porcentajeAciertos)+"% \n"+
                        " Score: "+ c0ntrol.getSuccess()*10+" \n"+
                        " Do you want to play the same round?",

                    "Defeat ", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //Reinicio ronda
                vaciarDIU();
                c0ntrol = new ControlIKnowThatWord(c0ntrol.getLevel(),ingresarNombre.getText());
                c0ntrol.getWord().setLevelName(c0ntrol.getName(),1);
                initDIU(c0ntrol);
                revalidate();
                repaint();
            }else if(option == JOptionPane.NO_OPTION){


                System.exit(0);
            }
        }else{
            int option = JOptionPane.showConfirmDialog(panelCentro, "you win!!\n"+
                            " End of level \n"+
                            " You have passed to the next level. \n"+
                            " Right guess:  "+ c0ntrol.getSuccess()+" \n"+
                            " Right guess percentage: "+df.format(porcentajeAciertos)+"% \n"+
                            " Score: "+ c0ntrol.getSuccess()*10+" \n"+
                            " Do you want to play another round ?",

                    "Victory", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                //nueva ronda
                vaciarDIU();
                c0ntrol = new ControlIKnowThatWord(c0ntrol.getLevel()+1,ingresarNombre.getText());
                c0ntrol.getWord().setLevelName(c0ntrol.getName(),c0ntrol.getLevel());
                initDIU(c0ntrol);

                revalidate();
                repaint();
            }else if(option == JOptionPane.NO_OPTION){
                //Guardar los datos
                int a = control.getWord().ultimoNombre(control.getName());
                int b = control.getWord().getLevel(a);

                ControlIKnowThatWord controlNew = new ControlIKnowThatWord(b, control.getName());
                c0ntrol = controlNew;
                initDIU(c0ntrol);

                System.exit(0);
            }
        }
    }

    public boolean encontrar(String palabra){
        for(int i = 0; i< c0ntrol.getTrueWord().length; i++){
            if(palabra == c0ntrol.getTrueWord()[i]){
                c0ntrol.setYesOrNot(true);
                i += c0ntrol.getTrueWord().length;
            }else{
                c0ntrol.setYesOrNot(false);
            }
        }
        return c0ntrol.isYesOrNot();
    }

    public void vaciarDIU(){
        panelNorte.setEnabled(false);
        panelCentro.setEnabled(false);
        panelsur.setEnabled(false);

        panelNorte.setVisible(false);
        panelCentro.setVisible(false);
        panelsur.setVisible(false);

        panelNorte.removeAll();
        panelCentro.removeAll();
        panelsur.removeAll();

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
                si.setEnabled(true); no.setEnabled(true);
                panelCentro.setBackground(Color.WHITE);
                //Para saber si es el inicio de la vista o no
                if(c0ntrol.isStart()){
                //System.out.println("aquí estoy");
                    c0ntrol.setTime(c0ntrol.getTime()+1);
                    if(c0ntrol.getTime() < 1){//5

                    }else{
                        //Palabra
                        c0ntrol.setTime(0);

                        //Condicional para que muestre las palabras verdaderas cada 5 segundos
                        if(c0ntrol.getTrueWord().length > c0ntrol.getCont()){
                            c0ntrol.setFirstWord(c0ntrol.getTrueWord()[c0ntrol.getCont()]);
                            c0ntrol.setCont(c0ntrol.getCont()+1);
                            palabra.setText(c0ntrol.getFirstWord());
                            panelNorte.repaint();
                        }else{
                            c0ntrol.setStart(false);
                            c0ntrol.setCont(-1);

                            tiempo.stop();

                            int option = JOptionPane.showConfirmDialog(panelCentro,
                                    "Do you wish continue?",

                                    "¿?", JOptionPane.YES_NO_OPTION);

                            c0ntrol.setCont(c0ntrol.getCont()+1);
                            c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                            palabra.setText(c0ntrol.getFirstWord());

                            panelNorte.repaint();

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
                    c0ntrol.setTime(c0ntrol.getTime()+1);
                    if(c0ntrol.getTime() < 7){//7

                    }else{
                        //ERROR
                        panelCentro.setBackground(Color.RED);
                        c0ntrol.setFail(c0ntrol.getFail()+1);

                        //Condicional para que muestre todas las palabras 
                        if(c0ntrol.getAllWord().length > c0ntrol.getCont()+1){
                            c0ntrol.setCont(c0ntrol.getCont()+1);
                            c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                            palabra.setText(c0ntrol.getFirstWord());


                            panelNorte.repaint();

                        }else{
                            //Estadisticas de la partida
                            tiempo.stop();
                            c0ntrol.setCont(0);
                            finalRonda();
                        }
                        c0ntrol.setTime(0);
                    }
                }


            }else if(eventAction.getSource() == si){
                si.setEnabled(false);no.setEnabled(false);

                if(encontrar(c0ntrol.getFirstWord())){
                    //Correcto
                    panelCentro.setBackground(Color.GREEN);
                    c0ntrol.setSuccess(c0ntrol.getSuccess()+1);
                    puntuacion.setText("       Score:       "+c0ntrol.getSuccess()*10);
                    panelsur.repaint();

                    //Cambio de palabra
                    if(c0ntrol.getAllWord().length > c0ntrol.getCont()+1){
                        c0ntrol.setCont(c0ntrol.getCont()+1);
                        c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                        palabra.setText(c0ntrol.getFirstWord());

                        panelNorte.repaint();
                    }else{
                        //Estadisticas de la partida
                        tiempo.stop();
                        c0ntrol.setCont(0);
                        finalRonda();
                    }

                    c0ntrol.setTime(0);
                }else{
                    //ERROR
                    panelCentro.setBackground(Color.RED);
                    c0ntrol.setFail(c0ntrol.getFail()+1);

                    //Cambio de palabra
                    if(c0ntrol.getAllWord().length > c0ntrol.getCont()+1){
                        c0ntrol.setCont(c0ntrol.getCont()+1);
                        c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                        palabra.setText(c0ntrol.getFirstWord());

                        panelNorte.repaint();
                    }else{
                        //Estadisticas de la partida
                        tiempo.stop();
                        c0ntrol.setCont(0);
                        finalRonda();
                    }
                    c0ntrol.setTime(0);
                }
            }else if(eventAction.getSource() == no){
                no.setEnabled(false);si.setEnabled(false);

                if(encontrar(c0ntrol.getFirstWord())){
                    //ERROR
                    panelCentro.setBackground(Color.RED);
                    c0ntrol.setFail(c0ntrol.getFail()+1);

                    //Cambio de palabra
                    if(c0ntrol.getAllWord().length > c0ntrol.getCont()+1){
                        c0ntrol.setCont(c0ntrol.getCont()+1);
                        c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                        palabra.setText(c0ntrol.getFirstWord());

                        panelNorte.repaint();
                    }else{
                        //Estadisticas de la partida
                        tiempo.stop();
                        c0ntrol.setCont(0);
                        finalRonda();
                    }
                    c0ntrol.setTime(0);
                }else{
                    //Correcto
                    panelCentro.setBackground(Color.GREEN);
                    c0ntrol.setSuccess(c0ntrol.getSuccess()+1);
                    puntuacion.setText("       Score:       "+c0ntrol.getSuccess()*10);
                    panelsur.repaint();

                    //Cambio de palabra
                    if(c0ntrol.getAllWord().length > c0ntrol.getCont()+1){
                        c0ntrol.setCont(c0ntrol.getCont()+1);
                        c0ntrol.setFirstWord(c0ntrol.getAllWord()[c0ntrol.getCont()]);
                        palabra.setText(c0ntrol.getFirstWord());

                        panelNorte.repaint();
                    }else{
                        //Estadisticas de la partida
                        tiempo.stop();
                        c0ntrol.setCont(0);
                        finalRonda();
                    }
                    c0ntrol.setTime(0);
                }
            }else if(eventAction.getSource() == ingresar){
                //Guardamos el nombre del JTextField
                control.setName(ingresarNombre.getText());

                //Se quita el boton de inicio porque solamente se puede activar una vez por ejecución del programa
                ingresar.setVisible(false);
                ingresar.setEnabled(false);
                panelUsuarioN.setVisible(false);
                panelUsuarioN.setEnabled(false);
                panelUsuarioS.setVisible(false);
                panelUsuarioS.setEnabled(false);
                vista.remove(panelUsuarioN);
                vista.remove(panelUsuarioS);

                if(control.getWord().getUsuario(control.getName())){
                    int a = control.getWord().ultimoNombre(control.getName());
                    int b = control.getWord().getLevel(a);

                    ControlIKnowThatWord controlNew = new ControlIKnowThatWord(b, control.getName());
                    c0ntrol = controlNew;
                    initDIU(c0ntrol);
                }else{
                    c0ntrol = control;
                    initDIU(c0ntrol);
                    c0ntrol.getWord().setLevelName(c0ntrol.getName(),1);
                }

                vista.pack();
                vista.setLocationRelativeTo(null);
                revalidate();
                repaint();
            }
            revalidate();
            repaint();
        }
    }
}
