import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {

    private JTextField guessInput;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private int randomNumber;
    private int attempts;

    public GuessingGame() {
        setTitle("Guess the Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        initGame();

        guessInput = new JTextField(10);
        feedbackLabel = new JLabel("Enter a number between 1 and 100:");
        attemptsLabel = new JLabel("Attempts: 0");
        JButton guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        
        setLayout(new GridLayout(4, 1));
        add(feedbackLabel);
        add(guessInput);
        add(guessButton);
        add(attemptsLabel);

        setVisible(true);
    }

    private void initGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessInput.getText());
            attempts++;
            if (guess < 1 || guess > 100) {
                feedbackLabel.setText("Please enter a number between 1 and 100.");
            } else if (guess < randomNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > randomNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Correct! The number was " + randomNumber);
                JOptionPane.showMessageDialog(this, "You guessed it in " + attempts + " attempts!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                initGame(); // Reset the game
            }
            attemptsLabel.setText("Attempts: " + attempts);
            guessInput.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGame();
            }
        });
    }
}
