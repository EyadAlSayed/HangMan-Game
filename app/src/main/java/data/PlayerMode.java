package data;

public abstract class PlayerMode {

    WordsPuzzleGame ObjectOfGame;
    public abstract void startTheGame();
    public abstract GameState getPlayerState();
    public abstract void play();

}
