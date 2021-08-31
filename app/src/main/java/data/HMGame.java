package data;


import android.util.Log;

import ui.R;

public class HMGame extends WordsPuzzleGame{

   private String wordToGuess_c,wordToGuess_w,Hintword,wordToplay;
   private Words wordGenerator;
   public int count;

    public HMGame(){
        this.count = 0;
    }

    @Override
    public void CreateGame(int level,String wordType) {
        try {
            wordGenerator = INI_Words(level,wordType);
            if(wordGenerator == null) {   throw  new Exception(); }

            String []sp = wordGenerator.getWord().split("-");

            wordToGuess_w = wordToGuess_c = sp[0];
            Hintword = sp[1];

            if(Hintword == null || wordToGuess_w == null) throw new Exception();

             wordToplay = repeatString("_",wordToGuess_w.length());
        }
        catch (Exception e)
        {
            Log.e("Error","Can't creat a new Game ");
        }

    }
    private String repeatString(String s,int n) {
        String repeated ="";
        for (int i=0;i<n;i++)
           repeated += s;
        return repeated;
    }
    private String getAlphWord(String word) {
       word = word.toLowerCase();
       char ch = word.charAt(0);
       word = replaceAtIndex(word,Character.toUpperCase(ch),0);
       return word;
    }
    private Words INI_Words(int level,String wordType){
        try {
            if (level == -1 || wordType.isEmpty()) throw new Exception();
            switch (wordType)
            {
                case "Arabic":{
                    switch (level) {
                        case 1 :return new Words(R.raw.easyarabicwords);
                        case 2 :return new Words(R.raw.normalarabicwords);
                        case 3 :return new Words(R.raw.hardarabicwords);
                    }
                }
                case "English":{
                    switch (level) {
                        case 1 :return new Words(R.raw.easyenglishwords);
                        case 2 :return new Words(R.raw.normalenglishwords);
                        case 3 :return new Words(R.raw.hardenglishwords);
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.e("Error","Can't initialize the Word generator ");
        }
         return null;
    }
    private boolean isCorrectChar(char ch){ return wordToGuess_c.indexOf(Character.toLowerCase(ch)) != -1 || wordToGuess_c.indexOf(Character.toUpperCase(ch)) != -1; }
    private boolean isCorrectWord(String word){ return wordToGuess_w.equals(word); }
    private String replaceAtIndex(String s,char newChar,int idx) {
        return s.substring(0,idx)+newChar+s.substring(idx+1);
    }
    public String getHiddenWord() {
        return wordToplay;
    }
    public String getHintWord(){ return Hintword; }
    public void nextChar(char ch){

        if(isCorrectChar(ch)) {
            int idx = (Character.toUpperCase(ch) == wordToGuess_c.charAt(0)) ? 0:wordToGuess_c.indexOf(Character.toLowerCase(ch));
            ch = idx == 0 ? Character.toUpperCase(ch) : ch;
            wordToplay = replaceAtIndex(wordToplay,ch,idx);
            wordToGuess_c = replaceAtIndex(wordToGuess_c,'_',idx);
        }
        else count++;
    }
    public void nextWord(String word){
        word = getAlphWord(word);
        if(isCorrectWord(word)) wordToplay = wordToGuess_w;
        else count++;
    }
    public int getWrongTry(){  return count;  }
    public GameState getGameState(){

        if(count == 0 && wordToplay.equals(repeatString("_",wordToGuess_w.length()))) { return GameState.newGame; }
        else
        if(count == 10) return GameState.lose;
        else
        if(count < wordToGuess_w.length() && !wordToplay.contains("_"))  return GameState.win;
        return GameState.running;
    }
}
