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

public class ModifierEtudiantWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        // Création des champs de formulaire pour rechercher un étudiant
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

        Button rechercherBtn = new Button("Rechercher");
        Button modifierBtn = new Button("Modifier");
        Button retourBtn = new Button("Retour");

        Label messageLabel = new Label();

        // Action du bouton retour
        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        // Recherche de l'étudiant
        rechercherBtn.setOnAction(e -> {
            try {
                int numEtudiant = Integer.parseInt(numField.getText());
                EtudiantDAO dao = new EtudiantDAO();
                Etudiant etudiant = dao.getEtudiant(numEtudiant);

                if (etudiant != null) {
                    nomField.setText(etudiant.getNom());
                    prenomField.setText(etudiant.getPrenom());
                    emailField.setText(etudiant.getEmail());
                    telField.setText(etudiant.getTelephone());
                    messageLabel.setText("Étudiant trouvé. Vous pouvez modifier ses informations.");
                    modifierBtn.setDisable(false);
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Étudiant introuvable.");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }

            } catch (Exception ex) {
                messageLabel.setText("Numéro invalide !");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        // Action du bouton modifier
        modifierBtn.setOnAction(e -> {
            try {
                int numEtudiant = Integer.parseInt(numField.getText());
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String tel = telField.getText();

                Etudiant etudiant = new Etudiant(numEtudiant, nom, prenom, email, tel);
                EtudiantDAO dao = new EtudiantDAO();
                boolean success = dao.modifierEtudiant(etudiant);

                if (success) {
                    messageLabel.setText("Étudiant modifié avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Échec de la modification !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }

            } catch (Exception ex) {
                messageLabel.setText("Données invalides !");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        // Désactiver le bouton Modifier jusqu'à ce que l'étudiant soit trouvé
        modifierBtn.setDisable(true);

        // Layout du formulaire
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Numéro Étudiant :"), numField);
        form.addRow(1, new Label("Nom :"), nomField);
        form.addRow(2, new Label("Prénom :"), prenomField);
        form.addRow(3, new Label("Email :"), emailField);
        form.addRow(4, new Label("Téléphone :"), telField);

        VBox layout = new VBox(15, form, rechercherBtn, modifierBtn, messageLabel, retourBtn);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Modifier un étudiant");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
