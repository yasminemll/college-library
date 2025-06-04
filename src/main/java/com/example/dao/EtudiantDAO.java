package com.example.dao;

import com.example.models.Etudiant;
import com.example.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    // ✅ Add a student
    public boolean ajouterEtudiant(Etudiant etudiant) {
        String sql = "INSERT INTO Etudiant (NumEtudiant, Nom, Prenom, Email, Tel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, etudiant.getNumEtudiant());
            stmt.setString(2, etudiant.getNom());
            stmt.setString(3, etudiant.getPrenom());
            stmt.setString(4, etudiant.getEmail());
            stmt.setString(5, etudiant.getTelephone());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Update a student
    public boolean modifierEtudiant(Etudiant etudiant) {
        String sql = "UPDATE Etudiant SET Nom = ?, Prenom = ?, Email = ?, Tel = ? WHERE numEtudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.setString(4, etudiant.getTelephone());
            stmt.setInt(5, etudiant.getNumEtudiant());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Delete a student
    public boolean supprimerEtudiant(int numEtudiant) {
        String sql = "DELETE FROM Etudiant WHERE numEtudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numEtudiant);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Get a student by ID
    public Etudiant getEtudiant(int numEtudiant) {
        String sql = "SELECT * FROM Etudiant WHERE numEtudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numEtudiant);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Etudiant(
                        rs.getInt("numEtudiant"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("Tel")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Get all students
    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> liste = new ArrayList<>();
        String sql = "SELECT * FROM Etudiant";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getInt("numEtudiant"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("Telephone")
                );
                liste.add(etudiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}

