package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {

    public void show() {
        Stage stage = new Stage();

        Label title = new Label("Connexion Bibliothécaire");
        title.getStyleClass().add("label");

        TextField codeField = new TextField();
        codeField.setPromptText("Code");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");

        Button loginBtn = new Button("Se connecter");
        loginBtn.getStyleClass().add("button");

        // Action du bouton
        loginBtn.setOnAction(e -> {
            String code = codeField.getText();
            String mdp = passwordField.getText();

            if (code.equals("bib") && mdp.equals("bib")) {
                System.out.println("Connexion réussie en tant que bibliothécaire !");
                // Ouvre la fenêtre avec les trois boutons
                new AdminWindow().show();
                stage.close(); // Ferme la fenêtre de connexion
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText("Identifiants incorrects");
                alert.setContentText("Veuillez vérifier le code et le mot de passe.");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(15, title, codeField, passwordField, loginBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Connexion Bibliothécaire");
        stage.setScene(scene);
        stage.show();
    }
}
