package test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

public class DrawSquares extends JFrame {
    private JLabel[] labelList;
    private HashMap<String, DrawableObject> labelLine = new HashMap<>();
    private HashMap<Integer, JLabel> labelCircle = new HashMap<>();
    private HashSet<String> lineSet = new HashSet<>();
    private Timer timer;
    private JPanel panel2;

    public DrawSquares() {
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


            // Đặt vị trí và kích thước cho JLabel
            label.setBounds(100+ 70 *i, 100, 50, 50);
            setLayout(null); // Chọn layout là null để có thể tự do đặt vị trí và kích thước
            add(label);
            labelList[i] = label;

            JLabel label2 = new JLabel();
            label2.setLayout(new BorderLayout());
            label2.setText("<html><center>"+i+"</center></html>");
            label2.setFont(new Font("Arial", Font.BOLD, 26));
            label2.setHorizontalTextPosition(JLabel.CENTER);
            label2.setVerticalTextPosition(JLabel.CENTER);

            // Thêm JLabel vào JFrame
            add(label2);



            // Đặt vị trí và kích thước cho JLabel
            label2.setBounds(100+ 70 *i+10, 20, 50, 50);
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
        drawHeapTree();

        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                float strokeWidth = 3.0f; // Độ dày của đường vẽ
                ((Graphics2D) g).setStroke(new BasicStroke(strokeWidth));
                veLine(g);
            }
        };

        // Thêm JPanel vào JFrame
        panel2.setBounds(0, 0, 1280, 900);
        add(panel2);
//        repaint();


    }

    private JLabel getCircle(int value, int ind, int parentX, int parentY, int soTang) {
        JLabel label = new JLabel();
        label.setLayout(new BorderLayout());
        String pathName = "c.png";

        ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt hình ảnh vào JLabel
        label.setIcon(scaledIcon);

//        // Đặt văn bản vào JLabel và căn giữa
        label.setText("<html><center>"+value+"</center></html>");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);

        if(ind == 0){
            label.setBounds(parentX, parentY, 50, 50);
        }else{
            int tang = (int)Math.floor(Math.log(ind + 1) / Math.log(2));
            int mul = (int)Math.pow(soTang- tang, 2) +1;
            System.out.println(Math.pow(3,2));
//            if(ind <= 2) mul+= soTang/2 + 1;
            if(ind % 2 == 0){
                label.setBounds(parentX + 25 * mul, parentY+100, 50, 50);
            }else{
                label.setBounds(parentX - 25 * mul, parentY+100, 50, 50);
            }
        }

        return label;
    }

    private JLabel getLine(int x1, int y1, int x2, int y2, boolean isRight) {
        JLabel label = new JLabel();
        label.setLayout(new BorderLayout());
        String pathName = "lt.png";
        if(isRight){
            pathName = "lp.png";
        }
        int w = Math.abs(x2-x1);
        int h = Math.abs(y2-y1);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathName));
        Image image = imageIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        // Đặt hình ảnh vào JLabel
        label.setIcon(scaledIcon);


        label.setBounds((x1 +x2)/2, (y1 +y2)/2, w, h);


        return label;
    }

    private void drawHeapTree() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10, 11, 12,13,14,15};

        int soTang = (int)Math.floor(Math.log(arr.length + 1) / Math.log(2));

        for (int i=0; i<arr.length; i++){
            int parentX = 500, parentY = 300;
            int parentInd = (i-1)/2;
            if(i > 0){
                JLabel par = labelCircle.get(parentInd);
                if(par == null) return;
                parentX = par.getX();
                parentY = par.getY();

            }
            JLabel circle = getCircle(arr[i], i, parentX, parentY, soTang);
            add(circle);
            labelCircle.put(i, circle);
            circle.setVisible(false);

            boolean isRight = true;
            if(i > 0){
                if(i % 2 == 1) isRight = false;
                String name = parentInd+"_"+i;
                DrawableObject line = new DrawableObject(circle.getX(), circle.getY(), parentX, parentY);
                labelLine.put(name, line);
//                JLabel line = getLine(circle.getX(), circle.getY(), parentX, parentY, isRight);
//                add(line);
//                labelLine.put(name, line);
//                line.setVisible(true);

//                DrawableObject a = new DrawableObject(1,2,3);
//                a.draw( circle.getX(), circle.getY(), parentX, parentY);
            }

        }

        timer = new Timer(100, new ActionListener() {
            int ind = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                labelCircle.get(ind).setVisible(true);
                System.out.println("i== "+ind +" "+arr.length);


                int parentInd = (ind-1)/2;
                if(parentInd >= 0){
                    lineSet.add(parentInd+"_"+ind);
                    DrawableObject b = labelLine.get(parentInd+"_"+ind);
                    panel2.repaint();
                }

                ind++;
                if (ind >= arr.length) {
                    timer.stop();
                }
            }
        });

        // Bắt đầu Timer
        timer.start();

        // Tạo Timer để thực hiện di chuyển xuống trong 2 giây
//        int delayDown = 10;
//        int stepsDown = 10;
//        int stepSizeDown = (endY - startY) / stepsDown;
//
//        timer = new Timer(delayDown, new ActionListener() {
//            int dem1 = 0;
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int currentY = labelList[ind1].getY();
//                currentY += stepSizeDown;
//                labelList[ind1].setLocation(labelList[ind1].getX(), currentY);
//                labelList[ind2].setLocation(labelList[ind2].getX(), currentY);
//                labelList[ind1].setForeground(Color.RED);
//                dem1++;
//                if (dem1 >= stepsDown) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }
//                    // Khi đạt tới vị trí cuối cùng xuống, dừng Timer và bắt đầu di chuyển sang phải
//                    timer.stop();
//                    startMoveRightAnimation(ind1, ind2);
//                }
//            }
//        });
//
//        // Bắt đầu Timer
//        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Sử dụng Graphics2D để vẽ đường thẳng
        Graphics2D g2d = (Graphics2D) g;
        float thickness = 3.0f; // Độ dày của đường
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));
//        veLine(g2d, getWidth(), getHeight());
    }

    private void veLine(Graphics g) {
        for (String element : lineSet) {
            if (labelLine.get(element) != null) {
                labelLine.get(element).draw(g);
            }
        }
        // Vẽ đường thẳng từ điểm (50, 50) đến điểm (width - 50, height - 50)
//        DrawableObject a = new DrawableObject(50, 50, width - 50, height - 50);
//        a.draw(g);
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
        int endY = 100;

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
        SwingUtilities.invokeLater(() -> new DrawSquares());
    }
}

class DrawableObject {
    int x1;
    int y1;
    int x2;
    int y2;


    public DrawableObject(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics g) {
        int a= 25;
        int b= 25;
        // Ví dụ: vẽ hình tròn và giá trị
//        g.drawOval(x - 15, y - 15, 30, 30);
//        g.drawString(Integer.toString(value), x - 5, y + 5);
        g.drawLine(x1+a, y1+b, x2+a, y2+b);
    }
}
