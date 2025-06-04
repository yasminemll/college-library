package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.dao.LivreDAO;
import com.example.models.Livre;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ModifierLivreWindow {

    public void show(Stage previousStage) {
        Stage stage = new Stage();

        TextField codeField = new TextField();
        codeField.setPromptText("Entrez le code du livre");

        TextField titreField = new TextField();
        titreField.setPromptText("Titre");

        TextField auteurField = new TextField();
        auteurField.setPromptText("Auteur");

        TextField categorieField = new TextField();
        categorieField.setPromptText("Catégorie");

        TextField anneeField = new TextField();
        anneeField.setPromptText("Année Publication");

        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité Disponible");

        Button rechercherBtn = new Button("Rechercher");
        Button modifierBtn = new Button("Modifier");
        Button retourBtn = new Button("Retour");

        rechercherBtn.getStyleClass().add("button");
        modifierBtn.getStyleClass().add("button");
        retourBtn.getStyleClass().add("button");

        Label messageLabel = new Label();

        retourBtn.setOnAction(e -> {
            stage.close();
            previousStage.show();
        });

        rechercherBtn.setOnAction(e -> {
            try {
                int code = Integer.parseInt(codeField.getText());
                LivreDAO LivreDAO = new LivreDAO();
                Livre livre = LivreDAO.getLivre(code);
                if (livre != null) {
                    titreField.setText(livre.getTitre());
                    auteurField.setText(livre.getAuteur());
                    categorieField.setText(livre.getCategorie());
                    anneeField.setText(String.valueOf(livre.getAnneePublication()));
                    quantiteField.setText(String.valueOf(livre.getQuantiteDisponible()));
                    messageLabel.setText("Livre trouvé, vous pouvez le modifier.");
                    messageLabel.setStyle("-fx-text-fill: white;");
                    modifierBtn.setDisable(false);
                } else {
                    messageLabel.setText("Livre introuvable.");
                    messageLabel.setStyle("-fx-text-fill: white;");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Veuillez entrer un code valide.");
                messageLabel.setStyle("-fx-text-fill: white;");
            }
        });

        modifierBtn.setOnAction(e -> {
            try {
                int code = Integer.parseInt(codeField.getText());
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                String categorie = categorieField.getText();
                int annee = Integer.parseInt(anneeField.getText());
                int quantite = Integer.parseInt(quantiteField.getText());

                Livre livre = new Livre(code, titre, auteur, categorie, annee, quantite);
                LivreDAO dao = new LivreDAO();
                boolean success = dao.modifierLivre(livre);

                if (success) {
                    messageLabel.setText("Livre modifié avec succès !");
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

        modifierBtn.setDisable(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.addRow(0, new Label("Code Livre :"), codeField);
        form.addRow(1, new Label("Titre :"), titreField);
        form.addRow(2, new Label("Auteur :"), auteurField);
        form.addRow(3, new Label("Catégorie :"), categorieField);
        form.addRow(4, new Label("Année Publication :"), anneeField);
        form.addRow(5, new Label("Quantité Disponible :"), quantiteField);

        VBox layout = new VBox(15, form, titreField, rechercherBtn, messageLabel, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Modifier un Livre");
        stage.setScene(scene);
        stage.show();
        previousStage.hide();
    }
}
