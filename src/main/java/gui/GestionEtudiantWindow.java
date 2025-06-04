package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GestionEtudiantWindow {

    public void show() {
        Stage stage = new Stage();

        // Création des boutons
        Button ajouterBtn = new Button("Ajouter un étudiant");
        Button modifierBtn = new Button("Modifier un étudiant");
        Button supprimerBtn = new Button("Supprimer un étudiant");
        Button rechercherBtn = new Button("Rechercher un étudiant");
        Button retourBtn = new Button("Retour");

        // Application du style aux boutons
        retourBtn.getStyleClass().add("button");
        ajouterBtn.getStyleClass().add("button");
        modifierBtn.getStyleClass().add("button");
        supprimerBtn.getStyleClass().add("button");
        rechercherBtn.getStyleClass().add("button");

        // Action du bouton Retour
        retourBtn.setOnAction(e -> stage.close());

        // Actions des autres boutons
        ajouterBtn.setOnAction(e -> new AjouterEtudiantWindow().show(stage)); // Ajouter un étudiant
        modifierBtn.setOnAction(e -> {
            // Lorsque le bouton "Modifier un étudiant" est cliqué
            // On ouvre la fenêtre de modification
            ModifierEtudiantWindow modifierEtudiantWindow = new ModifierEtudiantWindow();
            modifierEtudiantWindow.show(stage); // Passer la fenêtre actuelle (stage) pour pouvoir revenir après
        });

        supprimerBtn.setOnAction(e -> new SupprimerEtudiantWindow().show(stage)); // Supprimer un étudiant
        rechercherBtn.setOnAction(e -> new RechercherEtudiantWindow().show(stage)); // Rechercher un étudiant

        // Mise en page des boutons dans une VBox
        VBox layout = new VBox(15, ajouterBtn, modifierBtn, supprimerBtn, rechercherBtn, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.getStyleClass().add("root");

        // Création de la scène avec le layout et application du style CSS
        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        // Définition du titre de la fenêtre et affichage
        stage.setTitle("Gestion des étudiants");
        stage.setScene(scene);
        stage.show();
    }
}
