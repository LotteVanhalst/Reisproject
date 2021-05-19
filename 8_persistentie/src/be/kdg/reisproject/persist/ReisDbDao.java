package be.kdg.reisproject.persist;

import be.kdg.reisproject.model.Reis;
import be.kdg.reisproject.model.ReisDao;
import be.kdg.reisproject.model.Werelddeel;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Lotte Vanhalst
 * @version 1.0 1/05/2019 19:50
 */
public class ReisDbDao implements ReisDao {
    private static ReisDbDao enigeReisDbDao;
    private String databasePath;
    private Connection connection;

    private ReisDbDao(String databasePath) {
        try {
            setDatabasePath(databasePath);
            maakConnectie();
            maakTabel();
        } catch (Exception e){
            System.out.println("Probleem in constructor ReisDbDao");
        }
    }

    public static ReisDbDao getInstance(String databasePath){
        if (enigeReisDbDao == null){
            enigeReisDbDao = new ReisDbDao(databasePath);
        }
        return enigeReisDbDao;
    }

    private void maakConnectie() {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "lotte", "");
            System.out.println("Connection gemaakt");
        } catch (SQLException e) {
            System.err.println("Kan geen connectie maken met database " + databasePath);
            System.exit(1);
        }
    }


    private void maakTabel() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS reistable ");
            String createQuery = "CREATE TABLE reistable (id INTEGER NOT NULL IDENTITY, " +
                    "naam VARCHAR(20)," +
                    "werelddeel VARCHAR(7)," +
                    "begindatum DATE," +
                    "aantal_dagen INTEGER," +
                    "kostprijs DOUBLE,"+
                    "taal VARCHAR(30),"+
                    "munteenheid VARCHAR(20),"+
                    "gemiddelde_temp DOUBLE,"+
                    "zakgeld DOUBLE)";
            statement.execute(createQuery);
            System.out.println("Database aangemaakt");

        } catch (SQLException e) {
            String message = e.getMessage();
            if (message.contains("Tabel bestaat al")) return;
            System.err.println("Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            System.exit(1);
        }
    }

    public void close() {
        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            System.out.println("\nDatabase gesloten");
        } catch (SQLException e) {
            System.out.println("Probleem bij sluiten van database: " + e.getMessage());
        }
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String getDatabasePath() {
        return databasePath;
    }


    @Override
    public void voegToe(Reis reis) {
        if (reis.getId() == -1) {
            try {
                PreparedStatement prepStatement = connection.prepareStatement(
                        "INSERT INTO reistable (naam, werelddeel, begindatum, aantal_dagen, kostprijs, taal, munteenheid, gemiddelde_temp, zakgeld) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                prepStatement.setString(1, reis.getNaam());
                prepStatement.setString(2, reis.getWerelddeel().name());
                prepStatement.setDate(3, Date.valueOf(reis.getBegindatum()));
                prepStatement.setInt(4, reis.getAantalDagen());
                prepStatement.setDouble(5, reis.getKostprijs());
                prepStatement.setString(6, reis.getTaal());
                prepStatement.setString(7, reis.getMunteenheid());
                prepStatement.setDouble(8, reis.getGemiddeldeTemp());
                prepStatement.setDouble(9, reis.getZakgeld());

                prepStatement.executeUpdate();

                prepStatement.close();

            } catch (SQLException e) {
                System.err.println("Fout bij toevoegen: " + e);
            }
        }
    }



     public boolean update (Reis reis, Reis nieuweReis){
        boolean update = FALSE;
             try {
                 PreparedStatement prepStatement = connection.prepareStatement(
                         "UPDATE reistable SET naam = ?, werelddeel = ?, begindatum = ?, aantal_dagen = ?, kostprijs = ?, taal = ?, munteenheid = ?, gemiddelde_temp = ?, zakgeld = ? " +
                                 "WHERE id = ?");
                 prepStatement.setString(1, nieuweReis.getNaam());
                 prepStatement.setString(2, nieuweReis.getWerelddeel().name());
                 prepStatement.setDate(3, Date.valueOf(nieuweReis.getBegindatum()));
                 prepStatement.setInt(4, nieuweReis.getAantalDagen());
                 prepStatement.setDouble(5, nieuweReis.getKostprijs());
                 prepStatement.setString(6, nieuweReis.getTaal());
                 prepStatement.setString(7, nieuweReis.getMunteenheid());
                 prepStatement.setDouble(8, nieuweReis.getGemiddeldeTemp());
                 prepStatement.setDouble(9, nieuweReis.getZakgeld());
                 prepStatement.setDouble(10, reis.getId());

                 int i = prepStatement.executeUpdate();

                 if (!(i == 0)) {
                     System.out.println(reis.getNaam() + " is aangepast.");
                 } else {
                     System.out.println(reis.getNaam() + " staat niet in de lijst.");
                 }

                 prepStatement.close();

                 update = TRUE;

             } catch (SQLException e) {
                 System.err.println("Fout bij update: " + e);
             }

             return update;
         }


    @Override
    public void verwijder(String naam, LocalDate begindatum, int aantalDagen) {
        try {
            PreparedStatement prepStatement = connection.prepareStatement("DELETE FROM reistable WHERE naam = ? AND begindatum = ? AND aantal_dagen = ?");
            prepStatement.setString(1, naam);
            prepStatement.setDate(2,Date.valueOf(begindatum));
            prepStatement.setInt(3,aantalDagen);

            int i = prepStatement.executeUpdate();

            prepStatement.close();

            if (!(i == 0)) {
                System.out.println(naam + " succesvol verwijderd.");
            } else {
                System.out.println(naam + " staat niet in de lijst.");
            }


        } catch (SQLException e) {
            System.err.println("Fout bij verwijderen: " + e);
        }
    }

    @Override
    public Reis zoek(String naam, LocalDate begindatum, int aantalDagen) {
        ResultSet rs = null;
        Reis reis = new Reis();
        try {
            PreparedStatement prepStatement = connection.prepareStatement(
            "SELECT * FROM reistable WHERE naam = ? AND begindatum = ? AND aantal_dagen = ?");
            prepStatement.setString(1, naam);
            prepStatement.setDate(2,Date.valueOf(begindatum));
            prepStatement.setInt(3,aantalDagen);

            rs = prepStatement.executeQuery();

            prepStatement.close();

            while (rs.next()) {
                reis.setId(rs.getInt("id"));
                reis.setNaam(rs.getString("naam"));
                reis.setWerelddeel(Werelddeel.valueOf(rs.getString("werelddeel")));
                reis.setBegindatum(rs.getDate("begindatum").toLocalDate());
                reis.setAantalDagen(rs.getInt("aantal_dagen"));
                reis.setKostprijs(rs.getDouble("kostprijs"));
                reis.setTaal(rs.getString("taal"));
                reis.setMunteenheid(rs.getString("munteenheid"));
                reis.setGemiddeldeTemp(rs.getDouble("gemiddelde_temp"));
                reis.setZakgeld(rs.getDouble("zakgeld"));
            }

        } catch (SQLException e) {
            System.err.println("Fout bij zoeken: " + e);
        }
        return reis;
    }

    @Override
    public List<Reis> gesorteerdOpKostprijs() {
        return geefResultaten("SELECT * FROM reistable ORDER BY kostprijs");
    }

    @Override
    public List<Reis> gesorteerdOpDatum() {
        return geefResultaten("SELECT * FROM reistable ORDER BY begindatum");
    }

    @Override
    public List<Reis> gesorteerdOpDuur() {
        return geefResultaten("SELECT * FROM reistable ORDER BY aantal_dagen");
    }

    public  List<Reis> filter(Double limiet) {
        return geefResultaten("SELECT * FROM reistable WHERE kostprijs <" + limiet);
    }

    public List<Reis> geefResultaten (String query){
        List<Reis> gesorteerdeLijst = new ArrayList<>();
        ResultSet rs = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()){
                gesorteerdeLijst.add(new Reis (
                        rs.getString("naam"),
                        Werelddeel.valueOf(rs.getString("werelddeel")),
                        rs.getDate("begindatum").toLocalDate(),
                        rs.getInt("aantal_dagen"),
                        rs.getDouble("kostprijs"),
                        rs.getString("taal"),
                        rs.getString("munteenheid"),
                        rs.getDouble("gemiddelde_temp"),
                        rs.getDouble("zakgeld"),
                        rs.getInt("id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gesorteerdeLijst;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
