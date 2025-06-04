package gui;

import com.example.dao.EtudiantDAO;
import com.example.models.Etudiant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RechercherEtudiantWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        TextField numField = new TextField();
        numField.setPromptText("Numéro étudiant");

        Button rechercherBtn = new Button("Rechercher");
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
                    messageLabel.setText("Étudiant trouvé : " + etudiant.getNom() + " " + etudiant.getPrenom());
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Étudiant introuvable.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }

            } catch (Exception ex) {
                messageLabel.setText("Numéro invalide !");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        VBox layout = new VBox(15, numField, rechercherBtn, messageLabel, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Rechercher un étudiant");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
