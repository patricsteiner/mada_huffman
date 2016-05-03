package mada_huffman;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HuffmanGUI extends Application {

    File rawFile;
    File encodedFile;
    File exportFile;
    File codeTable;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Main pane
        BorderPane pane = new BorderPane();
        pane.setPrefWidth(800);
        
        //Menu
        Menu men_open = new Menu("Open");
        Menu men_export = new Menu("Export");
        MenuItem men_openraw = new MenuItem("Open ASCII file");
        MenuItem men_openencoded = new MenuItem("Open encoded file");
        MenuItem men_exportencoded = new MenuItem("Export encoded String");
        men_open.getItems().addAll(men_openraw, men_openencoded);
        men_export.getItems().addAll(men_exportencoded);
        pane.setTop(new MenuBar(men_open,men_export));
        
        FileChooser fil_raw = new FileChooser();
        fil_raw.setInitialFileName("bla.txt");
        fil_raw.setTitle("asdasd");
        FileChooser fil_encoded = new FileChooser();
        FileChooser fil_codetable = new FileChooser();
        FileChooser fil_export = new FileChooser();
        
        //Menu Events
        men_openraw.setOnAction(e -> fil_raw.showOpenDialog(primaryStage));
        men_openencoded.setOnAction(e -> { 
            fil_encoded.showOpenDialog(primaryStage);
            fil_codetable.showOpenDialog(primaryStage);   
        });
        men_exportencoded.setOnAction(e -> fil_export.showSaveDialog(primaryStage));

        
        //Textareas
        TextArea tex_raw = new TextArea();
        TextArea tex_encoded = new TextArea();
        tex_raw.prefWidthProperty().bind(pane.widthProperty().divide(2));
        tex_encoded.prefWidthProperty().bind(pane.widthProperty().divide(2));
        
        //Buttons
        Button but_encode = new Button(" Encode > ");
        Button but_decode = new Button(" < Decode ");
        
        but_decode.setMinWidth(100);
        but_encode.setMinWidth(100);
        pane.setCenter(new HBox(tex_raw, new VBox(but_encode,but_decode), tex_encoded));
        
        
        TextArea tex_freq = new TextArea("asdsadsdsd");
        tex_freq.setDisable(true);
        pane.setBottom(tex_freq);
        
        //Button Events
        //TODO
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
