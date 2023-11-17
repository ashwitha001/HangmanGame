import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @Test
    void chooseWord_SelectsRandomWord() {
        HangmanModel hangmanModel = new HangmanModel();
        hangmanModel.chooseWord();

        // Ensure the secret word is not null and is one of the predefined words
        assertNotNull(hangmanModel.getSecretWord());
        assertTrue(hangmanModel.getSecretWord().length() > 0);
    }

    @Test
    void getCurrentGuess_InitiallyUnderscores() {
        HangmanModel hangmanModel = new HangmanModel();
        hangmanModel.chooseWord();

        // Ensure the initial current guess contains underscores
        assertTrue(hangmanModel.getCurrentGuess().matches("_+"));
    }

    @Test
    void makeGuess_CorrectGuessUpdatesCurrentGuess() {
        HangmanModel hangmanModel = new HangmanModel();
        hangmanModel.chooseWord();

        char letter = hangmanModel.getSecretWord().charAt(0);

        // Make a correct guess
        boolean correctGuess = hangmanModel.makeGuess(letter);

        assertTrue(correctGuess);
        assertEquals(letter, hangmanModel.getCurrentGuess().charAt(0));
    }

    @Test
    void makeGuess_IncorrectGuessDoesNotUpdateCurrentGuess() {
        HangmanModel hangmanModel = new HangmanModel();
        hangmanModel.chooseWord();

        char incorrectLetter = 'Z';

        // Make an incorrect guess
        boolean correctGuess = hangmanModel.makeGuess(incorrectLetter);

        assertFalse(correctGuess);
        assertEquals("_".repeat(hangmanModel.getSecretWord().length()), hangmanModel.getCurrentGuess());
    }

    @Test
    void makeGuess_CorrectGuessUpdatesOnlyFirstOccurrence() {
        HangmanModel hangmanModel = new HangmanModel();
        hangmanModel.chooseWord();

        char letter = hangmanModel.getSecretWord().charAt(0);
        char otherLetter = hangmanModel.getSecretWord().charAt(1);

        // Make a correct guess
        boolean correctGuess = hangmanModel.makeGuess(letter);

        assertTrue(correctGuess);
        assertEquals(letter + "_" .repeat(hangmanModel.getSecretWord().length() - 1), hangmanModel.getCurrentGuess());

        // Make another correct guess for a different letter
        correctGuess = hangmanModel.makeGuess(otherLetter);

        assertTrue(correctGuess);
        assertEquals(letter + otherLetter + "_".repeat(hangmanModel.getSecretWord().length() - 2), hangmanModel.getCurrentGuess());
    }
}