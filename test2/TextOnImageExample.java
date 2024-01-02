package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TextOnImageExample extends JFrame {
    private JLabel label;
    private Timer timer;
    private int currentX;
    private int currentY;

    public TextOnImageExample() {
        setTitle("Image with Text");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một JLabel để chứa hình ảnh và văn bản
        label = new JLabel();
        label.setLayout(new BorderLayout());
        String pathName = "a.png";
        // Tạo một Icon từ một hình ảnh (đặt đường dẫn đến hình ảnh của bạn)
//        ImageIcon imageIcon = new ImageIcon(pathName);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt hình ảnh vào JLabel
        label.setIcon(scaledIcon);

//        // Đặt văn bản vào JLabel và căn giữa
        label.setText("<html><center>hello</center></html>");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);

        // Thêm JLabel vào JFrame
        add(label);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });
        startButton.setBounds(150, 150, 100, 30); // X, Y, Width, Height

        // Đặt vị trí và kích thước cho JLabel
        label.setBounds(0, 0, 100, 100);
        setLayout(null); // Chọn layout là null để có thể tự do đặt vị trí và kích thước
        add(label);
        add(startButton);
        setVisible(true);
    }

    private void startAnimation() {
        int startY = 0;
        int endY = 200;

        // Tạo Timer để thực hiện di chuyển xuống trong 2 giây
        int durationDown = 1000;
        int delayDown = 10;
        int stepsDown = durationDown / delayDown;
        int stepSizeDown = 10;

        timer = new Timer(delayDown, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentY = label.getY();
                currentY += stepSizeDown;
                label.setLocation(currentX, currentY);

                if (currentY >= endY) {
                    // Khi đạt tới vị trí cuối cùng xuống, dừng Timer và bắt đầu di chuyển sang phải
                    timer.stop();
                    startMoveRightAnimation();
                }
            }
        });

        // Bắt đầu Timer
        timer.start();
    }

    private void startMoveRightAnimation() {
        int endX = 400;

        // Tạo Timer để thực hiện di chuyển sang phải trong 2 giây
        int durationRight = 1000;
        int delayRight = 10;
        int stepsRight = durationRight / delayRight;
        int stepSizeRight = 10;

        timer = new Timer(delayRight, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX += stepSizeRight;
                label.setLocation(currentX, label.getY());

                if (currentX >= endX) {
                    // Khi đạt tới vị trí cuối cùng sang phải, dừng Timer và bắt đầu di chuyển lên trên
                    timer.stop();
                    startMoveUpAnimation();
                }
            }
        });

        // Bắt đầu Timer
        timer.start();
    }

    private void startMoveUpAnimation() {
        int startY = 0;

        // Tạo Timer để thực hiện di chuyển lên trên trong 2 giây
        currentY = label.getY();
        int durationUp = 1000;
        int delayUp = 10;
        int stepsUp = durationUp / delayUp;
        int stepSizeUp = 10;

        timer = new Timer(delayUp, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentY2 = label.getY();
                currentY2 -= stepSizeUp;
                label.setLocation(currentX, currentY2);

                if (currentY2 <= startY) {
                    // Khi đạt tới vị trí cuối cùng lên trên, dừng Timer
                    timer.stop();
                }
            }
        });

        // Bắt đầu Timer
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TextOnImageExample());
    }
}
