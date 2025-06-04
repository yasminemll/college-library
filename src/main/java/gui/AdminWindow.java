package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminWindow {

    public void show() {
        Stage stage = new Stage();

        Button gestionLivresBtn = new Button("Gestion des livres");
        Button gestionEtudiantsBtn = new Button("Gestion des étudiants");
        Button gestionEmpruntsBtn = new Button("Gestion des emprunts et retours");
        Button retourBtn = new Button("Retour");

        // Style
        gestionLivresBtn.getStyleClass().add("button");
        gestionEtudiantsBtn.getStyleClass().add("button");
        gestionEmpruntsBtn.getStyleClass().add("button");
        retourBtn.getStyleClass().add("button");

        // Actions
        gestionLivresBtn.setOnAction(e -> new GestionLivreWindow().show());
        gestionEtudiantsBtn.setOnAction(e -> new GestionEtudiantWindow().show());
        gestionEmpruntsBtn.setOnAction(e -> new GestionEmpruntWindow().show());
        retourBtn.setOnAction(e -> stage.close()); // ← Ferme la fenêtre Admin

        VBox layout = new VBox(20, gestionLivresBtn, gestionEtudiantsBtn, gestionEmpruntsBtn, retourBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Tableau de bord Bibliothécaire");
        stage.setScene(scene);
        stage.show();
    }
}
