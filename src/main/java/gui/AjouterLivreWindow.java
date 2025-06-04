package gui;

import com.example.dao.LivreDAO;
import com.example.models.Livre;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterLivreWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        // Création des champs de formulaire
        TextField codeField = new TextField();
        TextField titreField = new TextField();
        TextField auteurField = new TextField();
        TextField categorieField = new TextField();
        TextField anneeField = new TextField();
        TextField quantiteField = new TextField();

        // Création des labels avec style
        Label codeLabel = new Label("Code Livre :");
        codeLabel.getStyleClass().add("form-label");

        Label titreLabel = new Label("Titre :");
        titreLabel.getStyleClass().add("form-label");

        Label auteurLabel = new Label("Auteur :");
        auteurLabel.getStyleClass().add("form-label");

        Label categorieLabel = new Label("Catégorie :");
        categorieLabel.getStyleClass().add("form-label");

        Label anneeLabel = new Label("Année Publication :");
        anneeLabel.getStyleClass().add("form-label");

        Label quantiteLabel = new Label("Quantité Disponible :");
        quantiteLabel.getStyleClass().add("form-label");

        // Boutons
        Button ajouterBtn = new Button("Ajouter");
        ajouterBtn.getStyleClass().add("button");

        Button retourBtn = new Button("Retour");
        retourBtn.getStyleClass().add("button");
        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        Label messageLabel = new Label();

        // Action du bouton ajouter
        ajouterBtn.setOnAction(e -> {
            try {
                int code = Integer.parseInt(codeField.getText());
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                String categorie = categorieField.getText();
                int annee = Integer.parseInt(anneeField.getText());
                int quantite = Integer.parseInt(quantiteField.getText());

                Livre livre = new Livre(code, titre, auteur, categorie, annee, quantite);
                LivreDAO dao = new LivreDAO();
                boolean success = dao.ajouterLivre(livre);

                if (success) {
                    messageLabel.setText("Livre ajouté avec succès !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                    codeField.clear(); titreField.clear(); auteurField.clear();
                    categorieField.clear(); anneeField.clear(); quantiteField.clear();
                } else {
                    messageLabel.setText(" Échec de l'ajout !");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }
            } catch (Exception ex) {
                messageLabel.setText(" Données invalides !");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        // Layout du formulaire
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, codeLabel, codeField);
        form.addRow(1, titreLabel, titreField);
        form.addRow(2, auteurLabel, auteurField);
        form.addRow(3, categorieLabel, categorieField);
        form.addRow(4, anneeLabel, anneeField);
        form.addRow(5, quantiteLabel, quantiteField);

        VBox layout = new VBox(15, form, ajouterBtn, messageLabel, retourBtn);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Ajouter un Livre");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
