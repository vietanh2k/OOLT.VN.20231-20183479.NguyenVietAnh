package test2;

import javax.swing.*;
import java.awt.*;

public class DrawSquares extends JFrame {

    public DrawSquares() {
        setTitle("Drawing Squares");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SquarePanel squarePanel = new SquarePanel();
        add(squarePanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawSquares());
    }
}

class SquarePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Đặt màu xanh
        g.setColor(Color.GREEN);

        // Vẽ 5 hình vuông ngang cách nhau 100
        int x = 50; // Vị trí x bắt đầu
        int y = 25; // Vị trí y bắt đầu
        int size = 50; // Kích thước hình vuông

        for (int i = 0; i < 5; i++) {
            g.fillRect(x, y, size, size);
            x += 100; // Cách nhau 100
        }
    }
}

