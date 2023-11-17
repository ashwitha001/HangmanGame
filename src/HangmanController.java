import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanController implements ActionListener {
    private HangmanModel model;
    private HangmanView view;

    public HangmanController(HangmanModel model, HangmanView view) {
        this.model = model;
        this.view = view;
        initializeGame();
    }

    private void initializeGame() {
        model.chooseWord();
        view.setSecretWord(model.getSecretWord());
        view.displayCurrentGuess(model.getCurrentGuess());
        view.clearIncorrectGuesses();
        view.clearGuessField();
    }

    public void processGuess() {
        String guess = view.getGuess();
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
            char letter = guess.charAt(0);
            boolean correctGuess = model.makeGuess(letter);

            if (correctGuess) {
                view.displayCurrentGuess(model.getCurrentGuess());
            } else {
                view.displayIncorrectGuess(letter);
            }

            view.clearGuessField();

            // Check for win
            if (model.getCurrentGuess().equals(model.getSecretWord())) {
                JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the word!");
                initializeGame();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid single letter.");
            view.clearGuessField();
        }
    }

    public HangmanModel getModel() {
        return model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processGuess();
    }
}