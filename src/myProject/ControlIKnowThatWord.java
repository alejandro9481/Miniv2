package myProject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase IKnowThatWord is the controller
 */
public class ControlIKnowThatWord {
    private int time, level, success, cont, fail, learnWord, score;
    private String [] trueWord, allWord;
    private String firstWord;

    private boolean yesOrNot, start;
    private Word word = new Word();

    /**
     * Clase IKnowThatWord is the constructor
     */
    public ControlIKnowThatWord(int level){
        this.level = level;
        this.score = score;
        this.success = 0;
        this.time = 0;
        this.cont = 0;
        this.start = true;
        this.firstWord = "";

        levelSize();

        this.trueWord = new String[learnWord];
        this.allWord = new String[2*learnWord];

        fullSelection();
        trueSelection();
    }

    /**
     * Method fullSelection is ...
     */
    public String[] fullSelection(){
        Random random = new Random();
        random.nextInt(word.getRange());

        allWord[0] = word.getFrase(random.nextInt(word.getRange()));

        for(int i=1; i<2*learnWord; i++){
            allWord[i] = word.getFrase(random.nextInt(word.getRange()));
            for(int j=0; j<i; j++){
                if(allWord[i] == allWord[j]){
                    i--;
                }
            }
        }
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        for(int j=0; j<2*learnWord; j++){
            System.out.println(allWord[j]);
        }

        return allWord;
    }

    /**
     * Method trueSelection is ...
     */
    public String[] trueSelection(){
        Random random = new Random();
        random.nextInt(2*learnWord);

        trueWord[0] = allWord[random.nextInt(2*learnWord)];

        for(int i=1; i<learnWord; i++){
            Random random1 = new Random();
            random1.nextInt(2*learnWord);

            trueWord[i] = allWord[random1.nextInt(2*learnWord)];
            for(int j=0; j<i; j++){
                if(trueWord[i] == trueWord[j]){
                    i--;
                }
            }
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for(int j=0; j<learnWord; j++){
            System.out.println(trueWord[j]);
        }

        return trueWord;
    }

    /**
     * Method levelSize is ...
     */
    public int levelSize(){
        int five = 5;

        if(level == 1 || level == 2){
            return learnWord = level*10;
        }else if(level == 3 || level == 4 ||
                level == 5 || level == 6){
            return learnWord = (level*10)-(five*(level-2));
        }else if(level == 7 || level == 8 || level == 9){
            return learnWord = (level*10)-20;
        }else{
            return learnWord = 100;
        }
    }

    /**
     * Method message is ...
     */
    public int message(){
        if(level == 1 || level == 2){
            return 70;
        }else if(level == 3){
            return 75;
        }else if(level == 4 || level == 5){
            return 80;
        }else if(level == 6){
            return 85;
        }else if(level == 7 || level == 8){
            return 90;
        }else if(level == 9){
            return 95;
        }else{
            return 100;
        }
    }

    /**
     * Method verifyAnswer is the answers requiere to the next level
     */
    public int verifyAnswer(){
        if(level == 3){
            return 38;
        }else if(level == 1 || level == 2 || level == 4 ||
                level == 5 || level == 6 || level == 7  ||
                level == 8 || level == 9){
            return message()*learnWord*2/100;
        }else{
            return 200;
        }
    }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public String getFirstWord() { return firstWord; }

    public void setFirstWord(String firstWord) { this.firstWord = firstWord; }

    public int getLearnWord() { levelSize();return learnWord;}

    public void setLearnWord(int learnWord) { this.learnWord = learnWord; }

    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getSuccess() { return success; }

    public void setSuccess(int success) { this.success = success; }

    public int getCont() { return cont; }

    public void setCont(int cont) { this.cont = cont; }

    public int getFail() { return fail; }

    public void setFail(int fail) { this.fail = fail; }

    public String[] getTrueWord() { return trueWord; }

    public void setTrueWord(String[] trueWord) { this.trueWord = trueWord; }

    public String[] getAllWord() { return allWord; }

    public void setAllWord(String[] allWord) { this.allWord = allWord; }

    public boolean isYesOrNot() { return yesOrNot; }

    public void setYesOrNot(boolean yesOrNot) { this.yesOrNot = yesOrNot; }

    public boolean isStart() { return start; }

    public void setStart(boolean start) { this.start = start; }

    public Word getWord() { return word; }

    public void setWord(Word word) { this.word = word; }
}