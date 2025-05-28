package edu.austral.dissis.chess.engine;

import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.AdapterKt;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessGameApplication extends Application {

    private final GameEngine gameEngine = new SimpleGameEngine();
    private final ImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());

    private static final String GAME_TITLE = "Chess";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(GAME_TITLE);

        Parent root = AdapterKt.createGameViewFrom(gameEngine, imageResolver);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
