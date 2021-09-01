package data;

import android.util.Log;

public class AND extends PlayerMode{

    private String input;
    private final int level ;
    private final String wordType;

    public AND(WordsPuzzleGame game ,int level,String wordType){
        super.ObjectOfGame = game;
        this.level = level;
        this.wordType = wordType;
    }
    @Override
    public void startTheGame() {
        try {
            if(level < 1 && level > 3) throw new Exception();
            if (!wordType.equals("Arabic") && !wordType.equals("English")) throw new Exception();

            super.ObjectOfGame.CreateGame(level,wordType);
        }
        catch (Exception e)
        {
            Log.e("Error","The level is not exists ..");
        }

    }
    @Override
    public GameState getPlayerState() {
       return ((HMGame)ObjectOfGame).getGameState();
    }

    @Override
    public void play() {
       try {
           if (input == null) throw new Exception();
       }
       catch (Exception e)
       {
           Log.e("Error","the input is null");
       }
        if(input.length() == 1)
            ((HMGame)(ObjectOfGame)).nextChar(input.charAt(0));
        else
            ((HMGame)(ObjectOfGame)).nextWord(input);
    }
    public void setNextGuess(String yourGuess){ this.input = yourGuess; }
    public String getHiddenWord(){
        return ((HMGame)(ObjectOfGame)).getHiddenWord();
    }
    public String getHintWord(){ return ((HMGame)ObjectOfGame).getHintWord(); }
    public int getPartOfBody(){ return ((HMGame)ObjectOfGame).getWrongTry(); }

}
