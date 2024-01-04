package test2;

import javax.swing.*;
import java.awt.*;

    public class HeapSortVisualization extends JFrame {

        public HeapSortVisualization() {
            setTitle("Cây Cân Bằng");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Tạo một JPanel tùy chỉnh để vẽ cây cân bằng
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    veCayCanBang(g, getWidth() / 2, 30, 1);
                }
            };

            // Thêm JPanel vào JFrame
            add(panel);

            // Hiển thị cửa sổ
            setVisible(true);
            veCayCanBangTheoDoTre(panel.getGraphics(), panel.getWidth() / 2, 30, 1);
        }

        private void veCayCanBang(Graphics g, int x, int y, int doSau) {
            // Vẽ nút gốc
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString("1", x - 5, y + 5);

            // Vẽ nút con trái
            int xTrai = x - 50;
            int yTrai = y + 50;
            g.drawLine(x, y, xTrai, yTrai);
            g.drawOval(xTrai - 15, yTrai - 15, 30, 30);
            g.drawString("2", xTrai - 5, yTrai + 5);

            // Vẽ nút con phải
            int xPhai = x + 50;
            int yPhai = y + 50;
            g.drawLine(x, y, xPhai, yPhai);
            g.drawOval(xPhai - 15, yPhai - 15, 30, 30);
            g.drawString("3", xPhai - 5, yPhai + 5);

            // Vẽ cây con trái của nút con trái
            int xTraiConTrai = xTrai - 30;
            int yTraiConTrai = yTrai + 50;
            g.drawLine(xTrai, yTrai, xTraiConTrai, yTraiConTrai);
            g.drawOval(xTraiConTrai - 15, yTraiConTrai - 15, 30, 30);
            g.drawString("4", xTraiConTrai - 5, yTraiConTrai + 5);

            // Vẽ cây con phải của nút con trái
            int xPhaiConTrai = xTrai + 30;
            int yPhaiConTrai = yTrai + 50;
            g.drawLine(xTrai, yTrai, xPhaiConTrai, yPhaiConTrai);
            g.drawOval(xPhaiConTrai - 15, yPhaiConTrai - 15, 30, 30);
            g.drawString("5", xPhaiConTrai - 5, yPhaiConTrai + 5);
        }

        private void veCayCanBangTheoDoTre(Graphics g, int x, int y, int doSau) {
            veNhanh(g, x, y, doSau);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

        private void veNhanh(Graphics g, int x, int y, int doSau) {
            // Các bước vẽ nhanh cây cân bằng ở đây
            // ...

            // Ví dụ:
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString("1", x - 5, y + 5);
        }



        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new HeapSortVisualization());
        }
    }

