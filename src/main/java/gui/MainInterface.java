package gui;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainInterface {

    public void start(Stage stage) {


        Label label = new Label("Bienvenue !");
        label.getStyleClass().add("label");

        Label titreLabel = new Label("Bibliothèque Universitaire");
        titreLabel.getStyleClass().add("title-button");

        Button connecterbutton = new Button("Bibliothécaire");
        connecterbutton.getStyleClass().add("button");

        connecterbutton.setOnAction(event -> {
            playMagicEffect(connecterbutton);
            // Ouvre la 2e fenêtre après une courte pause
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> {
                RoleWindow roleWindow = new RoleWindow();
                roleWindow.show();
            });
            pause.play();
        });

        VBox layout = new VBox(20,titreLabel, label, connecterbutton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("root");


        Scene scene = new Scene(layout, 450,750 );
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Interface Magique");
        stage.setScene(scene);
        stage.show();
    }

    private void playMagicEffect(Button button) {
        Glow glow = new Glow(0.4);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GOLD);
        shadow.setRadius(25);
        shadow.setSpread(0.7);

        button.setEffect(new Blend(BlendMode.ADD, glow, shadow));

        ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.2);
        st.setToY(1.2);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(0.6));
        pause.setOnFinished(e -> button.setEffect(null));
        pause.play();
    }

}
