package com.example.dao;

import com.example.models.Livre;
import com.example.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // ✅ Add a book
    public boolean ajouterLivre(Livre livre) {
        String sql = "INSERT INTO LIVRES (Code, Titre, Auteur, Categorie, Annee_Publication, Quantite_Disponible) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livre.getCodeLivre());
            stmt.setString(2, livre.getTitre());
            stmt.setString(3, livre.getAuteur());
            stmt.setString(4, livre.getCategorie());
            stmt.setInt(5, livre.getAnneePublication());
            stmt.setInt(6, livre.getQuantiteDisponible());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erreur ajout livre: " + e.getMessage());  // <-- ADD THIS
            return false;
        }
    }

    // ✅ Update a book
    public boolean modifierLivre(Livre livre) {
        String sql = "UPDATE LIVRES SET Titre = ?, Auteur = ?, Categorie = ?, Annee_Publication = ?, Quantite_Disponible = ? WHERE Code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getAnneePublication());
            stmt.setInt(5, livre.getQuantiteDisponible());
            stmt.setInt(6, livre.getCodeLivre());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un livre par titre
    public boolean supprimerLivreParTitre(String titre) {
        String sql = "DELETE FROM Livres WHERE Titre = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titre);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erreur suppression livre : " + e.getMessage());
            return false;
        }
    }

    public Livre getLivreParTitre(String titre) {
        String sql = "SELECT * FROM Livres WHERE Titre = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livre(
                        rs.getInt("Code"),
                        rs.getString("Titre"),
                        rs.getString("Auteur"),
                        rs.getString("Categorie"),
                        rs.getInt("Annee_Publication"),
                        rs.getInt("Quantite_Disponible")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Livre getLivre(int code) {
        String sql = "SELECT * FROM Livres WHERE Code = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Livre(
                        rs.getInt("Code"),
                        rs.getString("Titre"),
                        rs.getString("Auteur"),
                        rs.getString("Categorie"),
                        rs.getInt("Annee_Publication"),
                        rs.getInt("Quantite_Disponible")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
