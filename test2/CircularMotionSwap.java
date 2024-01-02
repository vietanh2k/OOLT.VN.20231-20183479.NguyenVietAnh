package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircularMotionSwap extends JFrame {

    private JPanel panel;
    private JButton startButton;
    private final int squareSize = 100;
    private int x1, y1, x2, y2;

    public CircularMotionSwap() {
        setTitle("Circular Motion Swap");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCircularMotionSwap();
            }
        });

        startButton.setBounds(100, 100, 150, 30);
        panel.setLayout(null);
        panel.add(startButton);

        // Set initial positions of the squares
        x1 = 50;
        y1 = 50;
        x2 = 150;
        y2 = 50;

        getContentPane().add(panel);
        setVisible(true);
    }

    private void startCircularMotionSwap() {
        Timer timer = new Timer(100, new ActionListener() {
            double angle = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 0.1;

                x1 = (int) (50 + 50 * Math.cos(angle));
                y1 = (int) (50 + 50 * Math.sin(angle));

                x2 = (int) (150 + 50 * Math.cos(angle));
                y2 = (int) (150 + 50 * Math.sin(angle));

                panel.repaint();

                if (angle >= 2 * Math.PI) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);
        g.fillRect(x1, y1, squareSize, squareSize);

        g.setColor(Color.RED);
        g.fillRect(x2, y2, squareSize, squareSize);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CircularMotionSwap();
            }
        });
    }
}
