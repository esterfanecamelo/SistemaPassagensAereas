package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class Main extends Application {

	public static void main(String[] args) {
		launch(args); // Inicia o aplicativo JavaFX
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Criação da janela principal (Stage)
		// Stage stage = new Stage();
		//injeção
		

		//
		
		Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		Scene scene = new Scene(root,Color.LIGHTSKYBLUE); // Cria uma cena com o nó raiz
		//primaryStage.setFullScreen(true);
		primaryStage.setTitle("321Viajar.com");
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		//root.getChildren().add(texto);
		
		primaryStage.setScene(scene); // Define a cena para a janela principal

		primaryStage.show(); // Exibe a janela principal
	}
}
