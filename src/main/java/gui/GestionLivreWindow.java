package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.dao.LivreDAO;
import com.example.models.Livre;

public class GestionLivreWindow {

    public void show() {
        Stage stage = new Stage();

        Button ajouterBtn = new Button("Ajouter un livre");
        Button modifierBtn = new Button("Modifier un livre");
        Button supprimerBtn = new Button("Supprimer un livre");
        Button rechercherBtn = new Button("Rechercher un livre");
        Button retourBtn = new Button("Retour");
        retourBtn.getStyleClass().add("button");
        retourBtn.setOnAction(e -> stage.close());

        ajouterBtn.getStyleClass().add("button");
        modifierBtn.getStyleClass().add("button");
        supprimerBtn.getStyleClass().add("button");
        rechercherBtn.getStyleClass().add("button");

        // Actions des boutons
        ajouterBtn.setOnAction(e -> new AjouterLivreWindow().show(stage));

        // Action du bouton "Modifier un livre"
        modifierBtn.setOnAction(e -> {
            // Ouvre la fenêtre de modification de livre
            openModifierLivreWindow(stage);
        });

        supprimerBtn.setOnAction(e -> new SupprimerLivreWindow().show(stage)); // Supprimer un livre
        rechercherBtn.setOnAction(e -> new RechercherLivreWindow().show(stage)); // Rechercher un livre

        VBox layout = new VBox(15, ajouterBtn, modifierBtn, supprimerBtn, rechercherBtn, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Gestion des livres");
        stage.setScene(scene);
        stage.show();
    }

    private void openModifierLivreWindow(Stage parentStage) {
        // Créer une nouvelle fenêtre pour modifier un livre
        Stage modifierStage = new Stage();
        modifierStage.setTitle("Modifier un livre");

        // Champ pour entrer le code du livre à modifier
        TextField codeLivreField = new TextField();
        codeLivreField.setPromptText("Entrez le code du livre à modifier");

        // Champ pour modifier le titre
        TextField titreField = new TextField();
        titreField.setPromptText("Titre du livre");

        // Champ pour modifier l'auteur
        TextField auteurField = new TextField();
        auteurField.setPromptText("Auteur du livre");

        // Champ pour modifier la catégorie
        TextField categorieField = new TextField();
        categorieField.setPromptText("Catégorie du livre");

        // Champ pour modifier l'année de publication
        TextField anneeField = new TextField();
        anneeField.setPromptText("Année de publication");

        // Champ pour modifier la quantité disponible
        TextField quantiteField = new TextField();
        quantiteField.setPromptText("Quantité disponible");

        // Bouton pour charger les informations du livre
        Button chargerBtn = new Button("Charger le livre");
        chargerBtn.getStyleClass().add("button");
        chargerBtn.setOnAction(e -> {
            String code = codeLivreField.getText();
            if (!code.isEmpty()) {
                LivreDAO livreDAO = new LivreDAO();
                Livre livre = livreDAO.getLivre(Integer.parseInt(code));

                if (livre != null) {
                    // Si le livre existe, remplir les champs avec les informations existantes
                    titreField.setText(livre.getTitre());
                    auteurField.setText(livre.getAuteur());
                    categorieField.setText(livre.getCategorie());
                    anneeField.setText(String.valueOf(livre.getAnneePublication()));
                    quantiteField.setText(String.valueOf(livre.getQuantiteDisponible()));
                } else {
                    // Si le livre n'existe pas
                    showAlert("Erreur", "Aucun livre trouvé avec ce code", Alert.AlertType.ERROR);
                }
            }
        });

        // Bouton pour modifier les informations du livre
        Button modifierBtn = new Button("Modifier le livre");
        modifierBtn.getStyleClass().add("button");
        modifierBtn.setOnAction(e -> {
            String codeLivre = codeLivreField.getText();
            if (!codeLivre.isEmpty()) {
                // Récupérer les informations mises à jour
                Livre livre = new Livre(
                        Integer.parseInt(codeLivre),
                        titreField.getText(),
                        auteurField.getText(),
                        categorieField.getText(),
                        Integer.parseInt(anneeField.getText()),
                        Integer.parseInt(quantiteField.getText())
                );

                LivreDAO livreDAO = new LivreDAO();
                boolean success = livreDAO.modifierLivre(livre);

                if (success) {
                    showAlert("Succès", "Livre modifié avec succès", Alert.AlertType.INFORMATION);
                    modifierStage.close();
                } else {
                    showAlert("Erreur", "Échec de la modification du livre", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Erreur", "Le code du livre est requis", Alert.AlertType.ERROR);
            }
        });

        // Layout pour la fenêtre de modification
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));

        // Ajouter les éléments dans le GridPane avec les indices de lignes et colonnes
        form.add(new Label("Code du Livre :"), 0, 0);
        form.add(codeLivreField, 1, 0);

        form.add(new Label("Titre :"), 0, 1);
        form.add(titreField, 1, 1);

        form.add(new Label("Auteur :"), 0, 2);
        form.add(auteurField, 1, 2);

        form.add(new Label("Catégorie :"), 0, 3);
        form.add(categorieField, 1, 3);

        form.add(new Label("Année de Publication :"), 0, 4);
        form.add(anneeField, 1, 4);

        form.add(new Label("Quantité Disponible :"), 0, 5);
        form.add(quantiteField, 1, 5);

        // Ajouter les boutons en dehors du GridPane
        VBox layout = new VBox(15, form, chargerBtn, modifierBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        modifierStage.setScene(scene);
        modifierStage.show();
    }

    // Méthode pour afficher des alertes
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

