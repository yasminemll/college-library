package gui;

import com.example.dao.LivreDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SupprimerLivreWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        Label titreLabel = new Label("Titre du livre à supprimer :");
        TextField titreField = new TextField();
        titreField.setPromptText("Titre du livre");

        Button supprimerBtn = new Button("Supprimer");
        Button retourBtn = new Button("Retour");
        Label messageLabel = new Label();

        supprimerBtn.setOnAction(e -> {
            String titre = titreField.getText();
            if (!titre.isEmpty()) {
                LivreDAO dao = new LivreDAO();
                boolean success = dao.supprimerLivreParTitre(titre);
                if (success) {
                    messageLabel.setText("Livre supprimé avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                } else {
                    messageLabel.setText("Aucun livre trouvé ou erreur !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }
            } else {
                messageLabel.setText("Veuillez entrer un titre !");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        VBox layout = new VBox(15, titreLabel, titreField, supprimerBtn, messageLabel, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Supprimer un Livre");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
