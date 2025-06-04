package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoleWindow {

    public void show() {
        Stage stage = new Stage();

        Label label = new Label("Tu es :");
        label.getStyleClass().add("label");

        Button connecterBtn = new Button("Se connecter");

        connecterBtn.getStyleClass().add("button");

        connecterBtn.setOnAction(e -> {
            new LoginWindow().show();
            stage.close();
        });

        VBox layout = new VBox(15, label, connecterBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("root");

        Scene scene = new Scene(layout, 450, 750);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Choisir un rôle");
        stage.setScene(scene);
        stage.show();
    }
}
