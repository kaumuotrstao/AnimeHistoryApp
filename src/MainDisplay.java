import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MainDisplay extends JFrame {
    private JList<Anime> animeList;
    private DefaultListModel<Anime> animeListModel;
    private static MainDisplay instance;

    public MainDisplay() {
        setTitle("アニメ一覧");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        animeListModel = new DefaultListModel<>();
        animeList = new JList<>(animeListModel);

        // AnimeDatabaseからアニメリストを取得し、animeListModelに追加する
        List<Anime> allAnime = AnimeDatabase.getAllAnime();
        for (Anime anime : allAnime) {
            animeListModel.addElement(anime);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(animeList);

        // アニメタイトルをダブルクリックすると詳細画面を表示する
        animeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList<Anime> list = (JList<Anime>) e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    Anime anime = animeListModel.getElementAt(index);
                    DetailsDisplay detailsDisplay = new DetailsDisplay(anime, animeListModel);
                    detailsDisplay.setVisible(true);
                }
            }
        });

        JButton addButton = new JButton("新規追加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDisplay addDisplay = new AddDisplay(animeListModel);
                addDisplay.setVisible(true);
            }
        });

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(addButton, BorderLayout.SOUTH);
    }

    public static MainDisplay getInstance() {
        if (instance == null) {
            instance = new MainDisplay();
        }
        return instance;
    }

	public void updateJList() {
        animeListModel.clear(); // 現在のアニメリストをクリアする

        // 更新後のアニメリストを取得し、animeListModelに追加する
        List<Anime> allAnime = AnimeDatabase.getAllAnime();
        for (Anime anime : allAnime) {
            animeListModel.addElement(anime);
        }
	}
    
    
}
