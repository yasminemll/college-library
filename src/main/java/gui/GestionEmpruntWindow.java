package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.dao.EmpruntDAO;
import com.example.models.Emprunt;

import java.sql.Date;

public class GestionEmpruntWindow {

    public void show() {
        Stage stage = new Stage();

        // Boutons pour enregistrer un emprunt ou un retour de livre
        Button empruntBtn = new Button("Enregistrer un nouvel emprunt");
        Button retourBtn = new Button("Enregistrer un retour de livre");
        Button retourMenuBtn = new Button("Retour");

        // Styles des boutons
        empruntBtn.getStyleClass().add("button");
        retourBtn.getStyleClass().add("button");
        retourMenuBtn.getStyleClass().add("button");

        // Actions des boutons
        empruntBtn.setOnAction(e -> openEmpruntForm(stage));
        retourBtn.setOnAction(e -> openRetourForm(stage));
        retourMenuBtn.setOnAction(e -> stage.close());

        // Mise en page des boutons
        VBox layout = new VBox(20, empruntBtn, retourBtn, retourMenuBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("root");

        // Scène et affichage
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Gestion des emprunts et retours");
        stage.setScene(scene);
        stage.show();
    }

    // Formulaire pour enregistrer un emprunt
    private void openEmpruntForm(Stage stage) {
        Stage empruntStage = new Stage();

        // Création des champs pour l'emprunt
        TextField numempruntField = new TextField();
        numempruntField.setPromptText("Numéro emprunt");

        TextField numEtudiantField = new TextField();
        numEtudiantField.setPromptText("Numéro Etudiant");

        TextField codeLivreField = new TextField();
        codeLivreField.setPromptText("Code Livre");

        DatePicker dateEmpruntPicker = new DatePicker();
        dateEmpruntPicker.setPromptText("Date Emprunt");

        DatePicker dateRetourPicker = new DatePicker();
        dateRetourPicker.setPromptText("Date Retour Prévues");

        TextField statutField = new TextField();
        statutField.setPromptText("Statut");

        Button enregistrerBtn = new Button("Enregistrer");
        Button retourBtn = new Button("Retour");

        // Label pour afficher les messages
        Label messageLabel = new Label();

        // Action pour enregistrer l'emprunt
        enregistrerBtn.setOnAction(e -> {
            try {
                int numemprunt = Integer.parseInt(numempruntField.getText());
                int numEtudiant = Integer.parseInt(numEtudiantField.getText());
                int codeLivre = Integer.parseInt(codeLivreField.getText());
                Date dateEmprunt = Date.valueOf(dateEmpruntPicker.getValue());
                Date dateRetourPrevue = Date.valueOf(dateRetourPicker.getValue());
                String statut = statutField.getText();

                Emprunt emprunt = new Emprunt(numemprunt , numEtudiant, codeLivre, dateEmprunt, dateRetourPrevue, null, statut);
                EmpruntDAO dao = new EmpruntDAO();
                boolean success = dao.ajouterEmprunt(emprunt);

                if (success) {
                    messageLabel.setText("Emprunt enregistré avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Échec de l'enregistrement !");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (Exception ex) {
                messageLabel.setText("Données invalides !");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Action pour revenir en arrière
        retourBtn.setOnAction(e -> {
            empruntStage.close();
            stage.show();
        });

        // Mise en page du formulaire
        VBox layout = new VBox(15, numempruntField,numEtudiantField, codeLivreField, dateEmpruntPicker, dateRetourPicker, statutField, enregistrerBtn, messageLabel, retourBtn);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        empruntStage.setTitle("Enregistrer un emprunt");
        empruntStage.setScene(scene);
        empruntStage.show();
        stage.hide();
    }

    // Formulaire pour enregistrer un retour de livre
    private void openRetourForm(Stage stage) {
        Stage retourStage = new Stage();

        // Création des champs pour le retour
        TextField numEmpruntField = new TextField();
        numEmpruntField.setPromptText("Numéro Emprunt");

        DatePicker dateRetourEffectivePicker = new DatePicker();
        dateRetourEffectivePicker.setPromptText("Date Retour Effective");

        Button enregistrerRetourBtn = new Button("Enregistrer Retour");
        Button retourBtn = new Button("Retour");

        // Label pour afficher les messages
        Label messageLabel = new Label();

        // Action pour enregistrer le retour
        enregistrerRetourBtn.setOnAction(e -> {
            try {
                int numEmprunt = Integer.parseInt(numEmpruntField.getText());
                Date dateRetourEffective = Date.valueOf(dateRetourEffectivePicker.getValue());

                EmpruntDAO dao = new EmpruntDAO();
                boolean success = dao.marquerCommeRendu(numEmprunt, dateRetourEffective);

                if (success) {
                    messageLabel.setText("Retour enregistré avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Échec de l'enregistrement du retour !");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (Exception ex) {
                messageLabel.setText("Données invalides !");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Action pour revenir en arrière
        retourBtn.setOnAction(e -> {
            retourStage.close();
            stage.show();
        });

        // Mise en page du formulaire
        VBox layout = new VBox(15, numEmpruntField, dateRetourEffectivePicker, enregistrerRetourBtn, messageLabel, retourBtn);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        retourStage.setTitle("Enregistrer un retour");
        retourStage.setScene(scene);
        retourStage.show();
        stage.hide();
    }
}
