import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanFrame extends JFrame implements HangmanView {
    private JLabel secretWordLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea incorrectGuessesArea;

    private HangmanController controller;

    public HangmanFrame() {
        setTitle("Hangman Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        secretWordLabel = new JLabel();
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        incorrectGuessesArea = new JTextArea();

        add(secretWordLabel, BorderLayout.NORTH);
        add(guessField, BorderLayout.CENTER);
        add(guessButton, BorderLayout.SOUTH);
        add(incorrectGuessesArea, BorderLayout.EAST);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.processGuess();
            }
        });
    }

    @Override
    public String getGuess() {
        return guessField.getText().toUpperCase();
    }

    @Override
    public void setSecretWord(String word) {
        secretWordLabel.setText("Secret Word: " + word);
    }

    @Override
    public void displayCurrentGuess(String currentGuess) {
        secretWordLabel.setText("Current Guess: " + currentGuess);
    }

    @Override
    public void displayIncorrectGuess(char incorrectGuess) {
        incorrectGuessesArea.append(incorrectGuess + " ");
    }

    @Override
    public void clearGuessField() {
        guessField.setText("");
    }

    @Override
    public void clearIncorrectGuesses() {
        incorrectGuessesArea.setText("");
    }

    @Override
    public void update() {
        // Update UI elements based on the model
        setSecretWord(controller.getModel().getSecretWord());
        displayCurrentGuess(controller.getModel().getCurrentGuess());
    }

    public void setController(HangmanController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        HangmanModel model = new HangmanModel();
        HangmanFrame frame = new HangmanFrame();
        HangmanController controller = new HangmanController(model, frame);

        frame.setController(controller);
        model.attach(frame);

        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }
}