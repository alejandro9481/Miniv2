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
    private String firstWord, name;
    private boolean yesOrNot, start;
    private Word word = new Word();

    /**
     * Clase IKnowThatWord is the constructor
     */
    public ControlIKnowThatWord(int level, String name){
        this.word = new Word();
        this.name = name;
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
     * Method fullSelection is for Save all words per level
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
        //System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        for(int j=0; j<2*learnWord; j++){
            System.out.println(allWord[j]);
        }

        return allWord;
    }

    /**
     * Method trueSelection is for save all true words
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
        for(int j=0; j<learnWord; j++){
            System.out.println(trueWord[j]);
        }

        return trueWord;
    }

    /**
     * Method levelSize is for Calculate the level size
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
     * Method message is for calculate the percent of hits to beat the level
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
    /**
     * Method getName return the name
     */
    public String getName() { return name; }
    /**
     * Method setName set the name
     */
    public void setName(String name) { this.name = name; }
    /**
     * Method getScore return the score
     */
    public int getScore() { return score; }
    /**
     * Method setScore set the score
     */
    public void setScore(int score) { this.score = score; }
    /**
     * Method getFirstWord return the first word
     */
    public String getFirstWord() { return firstWord; }
    /**
     * Method setFirstWord set the first word
     */
    public void setFirstWord(String firstWord) { this.firstWord = firstWord; }
    /**
     * Method getLearnWord return the learn word
     */
    public int getLearnWord() { levelSize();return learnWord;}
    /**
     * Method setLearnWord set the learn word
     */
    public void setLearnWord(int learnWord) { this.learnWord = learnWord; }
    /**
     * Method getTime return the time
     */
    public int getTime() { return time; }
    /**
     * Method getTime set the time
     */
    public void setTime(int time) { this.time = time; }
    /**
     * Method getLevel return the level
     */
    public int getLevel() { return level; }
    /**
     * Method setLevel set the level
     */
    public void setLevel(int level) { this.level = level; }
    /**
     * Method getSuccess return if is Success
     */
    public int getSuccess() { return success; }
    /**
     * Method setSuccess set if is Success
     */
    public void setSuccess(int success) { this.success = success; }
    /**
     * Method getCont return the cont
     */
    public int getCont() { return cont; }
    /**
     * Method setCont set the cont
     */
    public void setCont(int cont) { this.cont = cont; }
    /**
     * Method getFail return the fail
     */
    public int getFail() { return fail; }
    /**
     * Method setFail set the fail
     */
    public void setFail(int fail) { this.fail = fail; }
    /**
     * Method getTrueWord return the true word
     */
    public String[] getTrueWord() { return trueWord; }
    /**
     * Method setTrueWord set the true word
     */
    public void setTrueWord(String[] trueWord) { this.trueWord = trueWord; }
    /**
     * Method getTrueWord return all Words
     */
    public String[] getAllWord() { return allWord; }
    /**
     * Method setAllWord set all Words
     */
    public void setAllWord(String[] allWord) { this.allWord = allWord; }
    /**
     * Method getTrueWord return if is Yes or Not
     */
    public boolean isYesOrNot() { return yesOrNot; }
    /**
     * Method setYesOrNot set if is Yes or Not
     */
    public void setYesOrNot(boolean yesOrNot) { this.yesOrNot = yesOrNot; }
    /**
     * Method isStart return if the Start
     */
    public boolean isStart() { return start; }
    /**
     * Method setStart set the Start
     */
    public void setStart(boolean start) { this.start = start; }
    /**
     * Method getWord return the word
     */
    public Word getWord() { return word; }
    /**
     * Method setWord set the word
     */
    public void setWord(Word word) { this.word = word; }
}
