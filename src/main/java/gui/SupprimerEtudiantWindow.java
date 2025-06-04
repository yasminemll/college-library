package gui;

import com.example.dao.EtudiantDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SupprimerEtudiantWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        TextField numField = new TextField();
        numField.setPromptText("Numéro étudiant");

        Button supprimerBtn = new Button("Supprimer");
        Button retourBtn = new Button("Retour");

        Label messageLabel = new Label();

        // Action du bouton retour
        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        // Action du bouton supprimer
        supprimerBtn.setOnAction(e -> {
            try {
                int numEtudiant = Integer.parseInt(numField.getText());
                EtudiantDAO dao = new EtudiantDAO();
                boolean success = dao.supprimerEtudiant(numEtudiant);

                if (success) {
                    messageLabel.setText("Étudiant supprimé avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Échec de la suppression !");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }

            } catch (Exception ex) {
                messageLabel.setText("Numéro invalide !");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        VBox layout = new VBox(15, numField, supprimerBtn, messageLabel, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Supprimer un étudiant");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
