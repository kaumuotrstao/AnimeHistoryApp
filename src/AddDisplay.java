import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDisplay extends JFrame {
    private JTextField titleTextField;
    private JTextField genreTextField;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> ratingComboBox;
    
    public AddDisplay(DefaultListModel<Anime> animeListModel) {
        super("アニメ新規追加");
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());

        JLabel titleLabel = new JLabel("タイトル:");
        inputPanel.add(titleLabel);
        titleTextField = new JTextField(20);
        inputPanel.add(titleTextField);

        JLabel genreLabel = new JLabel("ジャンル:");
        inputPanel.add(genreLabel);
        genreTextField = new JTextField(20);
        inputPanel.add(genreTextField);

        JLabel yearLabel = new JLabel("放送年:");
        inputPanel.add(yearLabel);
        yearComboBox = new JComboBox<>();
        for (int i = 1980; i <= 2023; i++) {
            yearComboBox.addItem(i);
        }
        inputPanel.add(yearComboBox);

        JLabel ratingLabel = new JLabel("評価:");
        inputPanel.add(ratingLabel);
        ratingComboBox = new JComboBox<>(new String[] {"☆", "☆☆", "☆☆☆", "☆☆☆☆", "☆☆☆☆☆"});
        inputPanel.add(ratingComboBox);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("追加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String genre = genreTextField.getText();
                int year = (int) yearComboBox.getSelectedItem();
                int rating = ratingComboBox.getSelectedIndex() + 1;

                Anime anime = new Anime(title, genre, year, rating, 0);
                AnimeDatabase.addAnime(anime);
                animeListModel.clear(); // animeListModelの内容をクリアする
                animeListModel.addAll(AnimeDatabase.getAnimeList()); // 最新のanimeListを取得して、animeListModelに追加する
                dispose();
            }
        });

        buttonPanel.add(addButton);

        JButton cancelButton = new JButton("キャンセル");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
}
