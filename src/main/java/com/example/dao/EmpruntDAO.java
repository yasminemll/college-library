package com.example.dao;

import com.example.utils.DBConnection;
import com.example.models.Emprunt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    // ✅ Add a new loan
    public boolean ajouterEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO Emprunt (Num_Emprunt, Num_Etudiant, Code_Livre, Date_Emprunt, Date_Retour_Prévue, Statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprunt.getNumEmprunt());
            stmt.setInt(2, emprunt.getNumEtudiant());
            stmt.setInt(3, emprunt.getCodeLivre());
            stmt.setDate(4, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            stmt.setDate(5, new java.sql.Date(emprunt.getDateRetourPrevue().getTime()));
            stmt.setString(6, emprunt.getStatut());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Mark as returned
    public boolean marquerCommeRendu(int numEmprunt, Date dateRetourEffective) {
        String sql = "UPDATE Emprunt SET Date_Retour_Effective = ?, Statut = 'Rendu' WHERE Num_Emprunt = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(dateRetourEffective.getTime()));
            stmt.setInt(2, numEmprunt);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Update status (e.g., En retard)
    public boolean mettreAJourStatut(int numEmprunt, String statut) {
        String sql = "UPDATE Emprunt SET Statut = ? WHERE Num_Emprunt = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statut);
            stmt.setInt(2, numEmprunt);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Get all loans
    public List<Emprunt> getAllEmprunts() {
        List<Emprunt> liste = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                        rs.getInt("Num_Emprunt"),
                        rs.getInt("Num_Etudiant"),
                        rs.getInt("Code_Livre"),
                        rs.getDate("Date_Emprunt"),
                        rs.getDate("Date_Retour_Prévue"),
                        rs.getDate("Date_Retour_Effective"),
                        rs.getString("Statut")
                );
                liste.add(emprunt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}

