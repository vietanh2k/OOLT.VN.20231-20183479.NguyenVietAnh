package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortingDemoApp {

    private JFrame mainFrame;
    private JPanel mainMenuPanel;
    private JPanel demoPanel;
    private JTextField arrayInputField;
    private JLabel resultLabel;

    public SortingDemoApp() {
        initializeMainFrame();
        initializeMainMenu();
        initializeDemoPanel();
        showMainMenu();
    }

    private void initializeMainFrame() {
        mainFrame = new JFrame("Sorting Demo App");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new CardLayout());
        mainFrame.setLocationRelativeTo(null);
    }

    private void initializeMainMenu() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Sorting Demo App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Tăng cỡ chữ lên
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(10, 10, 20, 10);
        mainMenuPanel.add(titleLabel, titleConstraints);

        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort"});
        sortComboBox.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ lên
        GridBagConstraints sortComboBoxConstraints = new GridBagConstraints();
        sortComboBoxConstraints.gridx = 0;
        sortComboBoxConstraints.gridy = 1;
        sortComboBoxConstraints.insets = new Insets(0, 10, 10, 10);
        mainMenuPanel.add(sortComboBox, sortComboBoxConstraints);

        JButton startButton = new JButton("Start Demo");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Tăng cỡ chữ lên
        startButton.addActionListener(e -> showDemo());
        GridBagConstraints startButtonConstraints = new GridBagConstraints();
        startButtonConstraints.gridx = 0;
        startButtonConstraints.gridy = 2;
        startButtonConstraints.insets = new Insets(10, 10, 10, 10);
        mainMenuPanel.add(startButton, startButtonConstraints);

        JButton helpButton = new JButton("Help");
        helpButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ lên
        helpButton.addActionListener(e -> showHelp());
        GridBagConstraints helpButtonConstraints = new GridBagConstraints();
        helpButtonConstraints.gridx = 0;
        helpButtonConstraints.gridy = 3;
        helpButtonConstraints.insets = new Insets(10, 10, 10, 10);
        mainMenuPanel.add(helpButton, helpButtonConstraints);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng cỡ chữ lên
        quitButton.addActionListener(e -> confirmQuit());
        GridBagConstraints quitButtonConstraints = new GridBagConstraints();
        quitButtonConstraints.gridx = 0;
        quitButtonConstraints.gridy = 4;
        quitButtonConstraints.insets = new Insets(10, 10, 10, 10);
        mainMenuPanel.add(quitButton, quitButtonConstraints);

        mainFrame.add(mainMenuPanel, "Main Menu");
    }

    private void initializeDemoPanel() {
        demoPanel = new JPanel();
        demoPanel.setLayout(new GridBagLayout());

        JLabel arrayInputLabel = new JLabel("Enter Array:");
        arrayInputLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(5, 5, 5, 5);
        demoPanel.add(arrayInputLabel, labelConstraints);

        arrayInputField = new JTextField(20);
        arrayInputField.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        GridBagConstraints inputFieldConstraints = new GridBagConstraints();
        inputFieldConstraints.gridx = 0;
        inputFieldConstraints.gridy = 1;
        inputFieldConstraints.gridwidth = 2;
        inputFieldConstraints.insets = new Insets(5, 5, 10, 5);
        demoPanel.add(arrayInputField, inputFieldConstraints);

        JButton createArrayButton = new JButton("Create Array");
        createArrayButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        createArrayButton.addActionListener(e -> createArray());
        GridBagConstraints createArrayButtonConstraints = new GridBagConstraints();
        createArrayButtonConstraints.gridx = 0;
        createArrayButtonConstraints.gridy = 2;
        createArrayButtonConstraints.insets = new Insets(10, 5, 5, 5);
        demoPanel.add(createArrayButton, createArrayButtonConstraints);

        JButton startAlgorithmButton = new JButton("Start Algorithm");
        startAlgorithmButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        startAlgorithmButton.addActionListener(e -> startAlgorithm());
        GridBagConstraints startAlgorithmButtonConstraints = new GridBagConstraints();
        startAlgorithmButtonConstraints.gridx = 0;
        startAlgorithmButtonConstraints.gridy = 3;
        startAlgorithmButtonConstraints.insets = new Insets(10, 5, 5, 5);
        demoPanel.add(startAlgorithmButton, startAlgorithmButtonConstraints);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        backButton.addActionListener(e -> showMainMenu());
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0;
        backButtonConstraints.gridy = 4;
        backButtonConstraints.gridwidth = 2;
        backButtonConstraints.insets = new Insets(10, 5, 5, 5);
        demoPanel.add(backButton, backButtonConstraints);

        JLabel resu = new JLabel("Result:");
        resu.setFont(new Font("Arial", Font.PLAIN, 24)); // Tăng cỡ chữ lên
        GridBagConstraints resuConstraints = new GridBagConstraints();
        resuConstraints.gridx = 0;
        resuConstraints.gridy = 5;
        resuConstraints.insets = new Insets(5, 5, 5, 5);
        demoPanel.add(resu, resuConstraints);

        resultLabel = new JLabel("Result: ");
        GridBagConstraints resultLabelConstraints = new GridBagConstraints();
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        resultLabelConstraints.gridx = 0;
        resultLabelConstraints.gridy = 6;
        resultLabelConstraints.gridwidth = 2;
        resultLabelConstraints.insets = new Insets(10, 5, 5, 5);
        demoPanel.add(resultLabel, resultLabelConstraints);

        mainFrame.add(demoPanel, "Demo");
    }

    private void showMainMenu() {
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "Main Menu");
    }

    private void showDemo() {
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "Demo");
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(mainFrame,
                "This program demonstrates various sorting algorithms.\n" +
                        "Select a sort type and start the demo to see the sorting in action.\n" +
                        "Use the 'Create Array' button to generate or input an array.",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmQuit() {
        int result = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to quit?",
                "Quit Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void createArray() {
        String arrayInput = arrayInputField.getText();

        String[] numberArray = arrayInput.split("\\s+");
        String output = "Length array: "+numberArray.length +"\n\n";
        String output2 = "Array: ";
        try {
            resultLabel.setForeground(Color.BLACK);
            for (int i=0; i<numberArray.length; i++){
                System.out.println(numberArray[i]);
                int number1 = Integer.parseInt(numberArray[i]);
                output2 += numberArray[i] +",";
            }

            output+=output2;
        } catch (NumberFormatException e) {
            output = "ko hợp lệ";
            resultLabel.setForeground(Color.RED);
        }
        // Logic for creating an array goes here
        resultLabel.setText(output);
//        JOptionPane.showMessageDialog(mainFrame, "Array created or input successfully!");
    }

    private void startAlgorithm() {
        // Logic for starting the sorting algorithm goes here
        JOptionPane.showMessageDialog(mainFrame, "Sorting algorithm started. Showing each step...");
    }

    public void display() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingDemoApp sortingDemoApp = new SortingDemoApp();
            sortingDemoApp.display();
        });
    }
}
