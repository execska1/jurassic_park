package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> result = new ArrayList<>();
        String SQL = "SELECT breed FROM dinosaur WHERE expected < actual ORDER BY breed ASC;";
        try ( Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
