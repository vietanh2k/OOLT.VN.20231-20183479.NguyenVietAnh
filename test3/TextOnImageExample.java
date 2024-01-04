package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TextOnImageExample extends JFrame {
    private JLabel[] labelList;
    private Timer timer;

    public TextOnImageExample() {
        setTitle("Image with Text");
        setSize(1280, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một JLabel để chứa hình ảnh và văn bản
        labelList = new JLabel[100];
        for (int i=0; i< 10; i++){
            JLabel label = new JLabel();
            label.setLayout(new BorderLayout());
            String pathName = "a.png";
            // Tạo một Icon từ một hình ảnh (đặt đường dẫn đến hình ảnh của bạn)
//        ImageIcon imageIcon = new ImageIcon(pathName);
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
            Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            // Đặt hình ảnh vào JLabel
            label.setIcon(scaledIcon);

//        // Đặt văn bản vào JLabel và căn giữa
            label.setText("<html><center>"+i+"</center></html>");
            label.setFont(new Font("Arial", Font.BOLD, 26));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.CENTER);

            // Thêm JLabel vào JFrame
            add(label);



            // Đặt vị trí và kích thước cho JLabel
            label.setBounds(0+ 70 *i, 0, 50, 50);
            setLayout(null); // Chọn layout là null để có thể tự do đặt vị trí và kích thước
            add(label);
            labelList[i] = label;
        }



        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation(0, 1);
            }
        });
        startButton.setBounds(150, 150, 100, 30); // X, Y, Width, Height
        add(startButton);
        setVisible(true);


    }

    private void startAnimation(int ind1, int ind2) {
        if(ind1 >= labelList.length || ind2 >= labelList.length){
            return;
        }

        int startY = labelList[ind1].getY();
        int endY = labelList[ind1].getHeight() * 2 +startY;

        // Tạo Timer để thực hiện di chuyển xuống trong 2 giây
        int delayDown = 10;
        int stepsDown = 10;
        int stepSizeDown = (endY - startY) / stepsDown;

        timer = new Timer(delayDown, new ActionListener() {
            int dem1 = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentY = labelList[ind1].getY();
                currentY += stepSizeDown;
                labelList[ind1].setLocation(labelList[ind1].getX(), currentY);
                labelList[ind2].setLocation(labelList[ind2].getX(), currentY);
                labelList[ind1].setForeground(Color.RED);
                dem1++;
                if (dem1 >= stepsDown) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    // Khi đạt tới vị trí cuối cùng xuống, dừng Timer và bắt đầu di chuyển sang phải
                    timer.stop();
                    startMoveRightAnimation(ind1, ind2);
                }
            }
        });

        // Bắt đầu Timer
        timer.start();
    }

    private void startMoveRightAnimation(int ind1, int ind2) {
        int posX1 = labelList[ind1].getX();
        int posX2 = labelList[ind2].getX();

        int sub = Math.abs(ind1 - ind2);
        if(sub <= 2) sub = 2;

        int delayMove = 10;
        // Tạo Timer để thực hiện di chuyển sang phải trong 2 giây
        int stepsRight = 2*sub;
        int stepSizeRight = (int)Math.ceil((posX2- posX1) / stepsRight);

        timer = new Timer(delayMove, new ActionListener() {
            int dem1 = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                labelList[ind1].setLocation(labelList[ind1].getX() + stepSizeRight, labelList[ind1].getY());
                labelList[ind2].setLocation(labelList[ind2].getX() - stepSizeRight, labelList[ind1].getY());

                dem1++;
                if (dem1 >= stepsRight) {
                    labelList[ind1].setLocation(posX2, labelList[ind1].getY());
                    labelList[ind2].setLocation(posX1, labelList[ind1].getY());
                    // Khi đạt tới vị trí cuối cùng sang phải, dừng Timer và bắt đầu di chuyển lên trên
                    timer.stop();
                    startMoveUpAnimation(ind1, ind2);
                }
            }
        });

        // Bắt đầu Timer
        timer.start();
    }

    private void startMoveUpAnimation(int ind1, int ind2) {
        int startY = labelList[ind1].getY();
        int endY = 0;

        // Tạo Timer để thực hiện di chuyển lên trên trong 2 giây
        int delayUp = 10;
        int stepsUp = 10;
        int stepSizeUp = (endY - startY)/stepsUp;

        timer = new Timer(delayUp, new ActionListener() {
            int dem1 = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                labelList[ind1].setLocation(labelList[ind1].getX(), labelList[ind1].getY()+stepSizeUp);
                labelList[ind2].setLocation(labelList[ind2].getX(), labelList[ind2].getY()+stepSizeUp);
                dem1++;
                if (dem1 >= stepsUp) {
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
