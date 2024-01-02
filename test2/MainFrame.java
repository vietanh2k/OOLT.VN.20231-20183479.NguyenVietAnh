package test2;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hàng ngang 5 ô vuông");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 150));

        // Đặt vị trí cho JPanel
        panel.setLocation(0, 0);

        // Thêm các thành phần vào JPanel
        JLabel label = new JLabel("Hello, JPanel!");
        JButton button = new JButton("Click Me");

        panel.add(label);
        panel.add(button);

        // Thêm JPanel vào JFrame
        add(panel);

//        // Tạo panel chứa các ô vuông
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(1, 5));
//        panel.setBackground(Color.BLACK);
//
//        // Tạo và thêm các ô vuông vào panel
//        for (int i = 1; i <= 5; i++) {
//            JPanel square = createSquare(i);
//            panel.add(square);
//        }
//
//        // Thêm panel vào frame
//        add(panel);

        // Hiển thị frame
        setVisible(true);
    }

    private JPanel createSquare(int value) {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(30, 30));
        square.setBackground(Color.WHITE);

        // Thêm giá trị vào tâm ô vuông
        JLabel label = new JLabel(String.valueOf(value));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        square.add(label);

        return square;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
