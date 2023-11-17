public interface HangmanView {
    String getGuess();

    void setSecretWord(String word);

    void displayCurrentGuess(String currentGuess);

    void displayIncorrectGuess(char incorrectGuess);

    void clearGuessField();

    void clearIncorrectGuesses();

    void update();
}