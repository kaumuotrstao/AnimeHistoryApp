import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditDisplay extends JFrame {


    private JTextField titleTextField, genreTextField;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> ratingComboBox;
	private Anime anime;

    public EditDisplay(Anime anime) {
        this.anime = anime;
        setTitle("アニメ情報編集画面");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("タイトル:");
        inputPanel.add(titleLabel);
        titleTextField = new JTextField(20);
        titleTextField.setText(anime.getTitle());
        inputPanel.add(titleTextField);

        JLabel genreLabel = new JLabel("ジャンル:");
        inputPanel.add(genreLabel);
        genreTextField = new JTextField(20);
        genreTextField.setText(anime.getGenre());
        inputPanel.add(genreTextField);

        JLabel yearLabel = new JLabel("放送年:");
        inputPanel.add(yearLabel);
        yearComboBox = new JComboBox<>();
        for (int i = 1980; i <= 2023; i++) {
            yearComboBox.addItem(i);
        }
        yearComboBox.setSelectedItem(anime.getYear());
        inputPanel.add(yearComboBox);

        JLabel ratingLabel = new JLabel("評価:");
        inputPanel.add(ratingLabel);
        ratingComboBox = new JComboBox<>(new String[] {"☆", "☆☆", "☆☆☆", "☆☆☆☆", "☆☆☆☆☆"});
        ratingComboBox.setSelectedItem(anime.getRating());
        inputPanel.add(ratingComboBox);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JButton updateButton = new JButton("更新");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newTitle = titleTextField.getText();
                String newGenre = genreTextField.getText();
                int newYear = (int) yearComboBox.getSelectedItem();

                String selectedRating = (String) ratingComboBox.getSelectedItem();
                int newRating;
                switch(selectedRating) {
                    case "☆":
                        newRating = 1;
                        break;
                    case "☆☆":
                        newRating = 2;
                        break;
                    case "☆☆☆":
                        newRating = 3;
                        break;
                    case "☆☆☆☆":
                        newRating = 4;
                        break;
                    case "☆☆☆☆☆":
                        newRating = 5;
                        break;
                    default:
                        newRating = 0;
                        break;
                }

                anime.setTitle(newTitle);
                anime.setGenre(newGenre);
                anime.setYear(newYear);
                anime.setRating(newRating);

                AnimeDatabase.updateAnime(anime);

                MainDisplay.getInstance().updateJList();
                dispose();
            }
        });

        mainPanel.add(updateButton, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
