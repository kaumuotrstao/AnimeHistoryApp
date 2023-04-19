import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimeDatabase {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:anime.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS anime " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " title TEXT, " +
                    " genre TEXT, " +
                    " year INTEGER, " +
                    " rating INTEGER)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAnime(Anime anime) {
        String sql = "INSERT INTO anime (title, genre, year, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, anime.getTitle());
            preparedStatement.setString(2, anime.getGenre());
            preparedStatement.setInt(3, anime.getYear());
            preparedStatement.setInt(4, anime.getRating());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAnime(Anime anime) {
        String sql = "DELETE FROM anime WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, anime.getId());
            preparedStatement.executeUpdate();
            MainDisplay.getInstance().updateJList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAnime(Anime anime) {
        String sql = "UPDATE anime SET title=?, genre=?, year=?, rating=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, anime.getTitle());
            preparedStatement.setString(2, anime.getGenre());
            preparedStatement.setInt(3, anime.getYear());
            preparedStatement.setInt(4, anime.getRating());
            preparedStatement.setInt(5, anime.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Anime> getAllAnime() {
        List<Anime> animeList = new ArrayList<>();
        String sql = "SELECT * FROM anime";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Anime anime = new Anime(
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("year"),
                        resultSet.getInt("rating"),
                        resultSet.getInt("id")
                );
                animeList.add(anime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animeList;
    }

	
    public static ArrayList<Anime> getAnimeList() { 
    	List<Anime> animeList = getAllAnime(); 
    	ArrayList<Anime> arrayList = new ArrayList<>(animeList.size()); 
    	arrayList.addAll(animeList); return arrayList; 
    	}

    public static boolean removeAnime(Anime anime) {
        String sql = "DELETE FROM anime WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, anime.getId());
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



	public static AnimeDatabase getInstance() {
		return null;
	}
}
