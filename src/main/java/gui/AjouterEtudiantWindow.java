package gui;

import com.example.dao.EtudiantDAO;
import com.example.models.Etudiant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterEtudiantWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        // Création des champs de formulaire pour les informations de l'étudiant
        TextField numField = new TextField();
        numField.setPromptText("Numéro étudiant");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField telField = new TextField();
        telField.setPromptText("Téléphone");

        Button ajouterBtn = new Button("Ajouter");
        Button retourBtn = new Button("Retour");

        Label messageLabel = new Label();

        // Action du bouton retour
        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        // Action du bouton ajouter
        ajouterBtn.setOnAction(e -> {
            try {
                int numEtudiant = Integer.parseInt(numField.getText());
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String tel = telField.getText();

                Etudiant etudiant = new Etudiant(numEtudiant, nom, prenom, email, tel);
                EtudiantDAO dao = new EtudiantDAO();
                boolean success = dao.ajouterEtudiant(etudiant);

                if (success) {
                    messageLabel.setText("Étudiant ajouté avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Échec de l'ajout !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }

            } catch (Exception ex) {
                messageLabel.setText("Données invalides !");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        // Layout du formulaire
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Numéro Étudiant :"), numField);
        form.addRow(1, new Label("Nom :"), nomField);
        form.addRow(2, new Label("Prénom :"), prenomField);
        form.addRow(3, new Label("Email :"), emailField);
        form.addRow(4, new Label("Téléphone :"), telField);

        VBox layout = new VBox(15, form, ajouterBtn, messageLabel, retourBtn);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Ajouter un étudiant");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
