import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // MainDisplayオブジェクトの作成と可視化
        MainDisplay mainDisplay = new MainDisplay();
        mainDisplay.updateJList();
        mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainDisplay.setVisible(true);
    }
}
