package gui;

import com.example.dao.LivreDAO;
import com.example.models.Livre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RechercherLivreWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        // Champs
        TextField titreField = new TextField();
        titreField.setPromptText("Entrez le titre du livre");

        Button rechercherBtn = new Button("Rechercher");
        Button retourBtn = new Button("Retour");
        Label messageLabel = new Label();

        rechercherBtn.getStyleClass().add("button");
        retourBtn.getStyleClass().add("button");

        // Action bouton retour
        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        // Action bouton rechercher
        rechercherBtn.setOnAction(e -> {
            String titre = titreField.getText().trim();
            if (!titre.isEmpty()) {
                LivreDAO dao = new LivreDAO();
                Livre livreTrouve = dao.getLivreParTitre(titre);
                if (livreTrouve != null) {
                    messageLabel.setText(" Livre trouvé dans la bibliothèque !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText(" Livre introuvable.");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }
            } else {
                messageLabel.setText("Veuillez entrer un titre.");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        VBox layout = new VBox(15, titreField, rechercherBtn, messageLabel, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Rechercher un Livre");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
