package activeRecord;

import java.sql.*;
import java.util.ArrayList;

public class Personne {

    private int id;
    private String nom;
    private String prenom;

    public  Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void save(){

    }

    private void saveNew(){

    }

    public int getId(){
        return id;
    }

    public void delete(){

    }

    private void update(){

    }

    public static Personne findById(int id) throws SQLException {
        Connection connect = DBConnection.getConnection();
        String createString = "SELECT * FROM Personne WHERE ID = ?;";
        PreparedStatement prep;
        prep = connect.prepareStatement(createString);
        prep.setInt(1, id);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        if (rs==null){
            return null;
        }
        Personne p=new Personne(rs.getString("nom"), rs.getString("prenom"));
        p.id=id;
        return p;
    }

    public static ArrayList<Personne> findByName(String name) throws SQLException {
        ArrayList<Personne> list;
        Connection connect = DBConnection.getConnection();
        String createString = "SELECT * FROM Personne WHERE NOM = ?;";
        PreparedStatement prep;
        prep = connect.prepareStatement(createString);
        prep.setString(1, name);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        if (rs==null){
            list= null;
        }else{
            list = new ArrayList<>();
            while (rs.next()){
                list.add(new Personne(rs.getString("nom"), rs.getString("prenom")));
            }
        }
        return list;
    }

    public static ArrayList<Personne> findAll(){
        return null;
    }

    public static void createTable(){

    }

    public static void deleteTable(){

    }

    public String toString(){
        return nom + " " + prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
