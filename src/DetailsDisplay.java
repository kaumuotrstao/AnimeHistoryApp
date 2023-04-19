import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DetailsDisplay extends JFrame {
    private JLabel titleLabel;
    private JLabel genreLabel;
    private JLabel yearLabel;
    private JLabel ratingLabel;
    private JButton editButton;
    private JButton deleteButton;
    public DetailsDisplay(Anime anime, DefaultListModel<Anime> animeListModel) {
        setTitle("アニメ詳細");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        titleLabel = new JLabel("タイトル: " + anime.getTitle());
        genreLabel = new JLabel("ジャンル: " + anime.getGenre());
        yearLabel = new JLabel("放送年: " + Integer.toString(anime.getYear()));
        ratingLabel = new JLabel("評価: " + anime.getRating());
        infoPanel.add(titleLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(yearLabel);
        infoPanel.add(ratingLabel);

        JPanel buttonPanel = new JPanel();
        editButton = new JButton("編集");
        deleteButton = new JButton("削除");
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.CENTER);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "本当に削除しますか？", "確認", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    boolean isDeleted = AnimeDatabase.removeAnime(anime);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(null, "削除が完了しました。");
                        animeListModel.removeElement(anime); // アニメリストからも削除する
                        MainDisplay.getInstance().updateJList(); // アニメリストを更新する
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "削除に失敗しました。");
                    }
                }
            }
        });



        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditDisplay editDisplay = new EditDisplay(anime);
                editDisplay.setVisible(true);
            }
        });

    }
}

