package JavaFx.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizableDesktopPet extends JFrame {
    private final JLabel petLabel;
    private Point mouseOffset;

    public ResizableDesktopPet(int width, int height) {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(width, height);

        petLabel = new JLabel();
        ImageIcon normalIcon = new ImageIcon("C:/Users/user/Desktop/java-desktop-pet/2.png");
        ImageIcon hoverIcon = new ImageIcon("C:/Users/user/Desktop/java-desktop-pet/1.png");
        // 調整圖片大小
        Image scaledNormalImage = normalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image scaledHoverImage = hoverIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledNormalIcon = new ImageIcon(scaledNormalImage);
        ImageIcon scaledHoverIcon = new ImageIcon(scaledHoverImage);
        petLabel.setIcon(scaledNormalIcon);

        // 滑鼠按下事件
        petLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseOffset = e.getPoint(); // 紀錄當前滑鼠位置和視窗左上角距離
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseOffset = null; // 重置滑鼠位置和視窗左上角距離
                }
            }
        });

        // 滑鼠移動事件
        petLabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseOffset != null) {
                    Point mousePosition = e.getLocationOnScreen();
                    setLocation(mousePosition.x - mouseOffset.x, mousePosition.y - mouseOffset.y); // 移动窗口
                    petLabel.setIcon(scaledHoverIcon); // 切換為浮動狀態照片
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                petLabel.setIcon(scaledNormalIcon); // 切換為正常狀態照片
            }
        });

        setLayout(new BorderLayout());
        add(petLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResizableDesktopPet(300, 400));
    }
}
