import java.util.Random;

public class HangmanModel implements Subject {
    private String[] words = {"JAVA", "SWING", "COMPUTER", "PROGRAMMING", "DEVELOPMENT"};
    private String secretWord;
    private StringBuilder currentGuess;
    private HangmanView view;

    public HangmanModel() {
        currentGuess = new StringBuilder();
        chooseWord();
    }

    @Override
    public void attach(HangmanView v) {
        this.view = (HangmanView) v;
    }

    @Override
    public void detach(HangmanView v) {
        this.view = null;
    }

    @Override
    public void notifyViews() {
        if (view != null) {
            view.update();
        }
    }

    public void chooseWord() {
        Random random = new Random();
        secretWord = words[random.nextInt(words.length)];
        currentGuess.setLength(0);
        currentGuess.append("_".repeat(secretWord.length()));
        notifyViews();
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String getCurrentGuess() {
        return currentGuess.toString();
    }

    public boolean makeGuess(char letter) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter && currentGuess.charAt(i) == '_') {
                currentGuess.setCharAt(i, letter);
                correctGuess = true;
            }
        }
        notifyViews();
        return correctGuess;
    }
}