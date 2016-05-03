package mada_huffman;

import java.math.BigInteger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class HuffmanApp extends Application {
    
	static final String FILE_RAWTEXT = "text.txt";
	static final String FILE_ENCODED = "output.dat";
	static final String FILE_CODETABLE = "dec_tab.txt";
	
    TextArea tex_raw;
    TextArea tex_encoded;
    TextArea tex_codetable;
    TextArea tex_frequency;
	
    public static void main(String[] args) {
    	launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {      
        //Menu
        Menu men_open = new Menu("Open");
        Menu men_export = new Menu("Export");
        MenuItem men_openraw = new MenuItem("Open ASCII file");
        MenuItem men_openencoded = new MenuItem("Open encoded file");
        MenuItem men_exportencoded = new MenuItem("Export encoded String");
        men_open.getItems().addAll(men_openraw, men_openencoded);
        men_export.getItems().addAll(men_exportencoded);
        
        //File Choosers
        FileChooser fil_raw = new FileChooser();
        fil_raw.setInitialFileName(FILE_RAWTEXT);
        fil_raw.setTitle("Choose Raw ASCII text file");
        fil_raw.getExtensionFilters().add(new ExtensionFilter("TEXT files (*.txt)", "*.txt"));
        FileChooser fil_encoded = new FileChooser();
        fil_encoded.setInitialFileName(FILE_ENCODED);
        fil_encoded.setTitle("Choose encoded file");
        fil_encoded.getExtensionFilters().add(new ExtensionFilter("BINARY files (*.dat)", "*.dat"));
        FileChooser fil_codetable = new FileChooser();
        fil_codetable.setInitialFileName(FILE_CODETABLE);
        fil_codetable.setTitle("Choose codetable file for previously selected encoded file");
        fil_codetable.getExtensionFilters().add(new ExtensionFilter("TEXT files (*.txt)", "*.txt"));
        FileChooser fil_export = new FileChooser();
        fil_export.setInitialFileName(FILE_ENCODED);
        fil_export.getExtensionFilters().add(new ExtensionFilter("BINARY files (*.dat)", "*.dat"));
        
        //Menu Events
        men_openraw.setOnAction(e -> {
        	try {
				String content = IOUtil.readAsciiFile(fil_raw.showOpenDialog(primaryStage).getPath());
				tex_raw.setText(content);
			} catch (Exception e1) {
				new Alert(AlertType.ERROR, e1.getMessage()).showAndWait();
			}
        });
        men_openencoded.setOnAction(e -> { 
            try {
            	byte[] bytes = IOUtil.readBytesFromFile(fil_encoded.showOpenDialog(primaryStage).getPath());
				String content = Huffman.byteArrayToBinaryString(bytes);
				tex_encoded.setText(content);
				content = IOUtil.readAsciiFile(fil_codetable.showOpenDialog(primaryStage).getPath());
				tex_codetable.setText(content);
			} catch (Exception e1) {
				new Alert(AlertType.ERROR, e1.getMessage()).showAndWait();
			}
        });
        men_exportencoded.setOnAction(e -> {
            try {
				String binaryString = tex_encoded.getText();
				byte[] bytes = Huffman.binaryStringToByteArray(binaryString);
				IOUtil.writeBytesToFile(bytes, fil_export.showSaveDialog(primaryStage).getPath());
			} catch (Exception e1) {
				new Alert(AlertType.ERROR, e1.getMessage()).showAndWait();
			}
        });

        //Textareas
        tex_raw = new TextArea();
        tex_encoded = new TextArea();
        tex_codetable = new TextArea();
        tex_frequency = new TextArea();
        
        //Buttons
        Button but_encode = new Button(" Encode > ");
        Button but_decode = new Button(" < Decode ");
        but_decode.setMinWidth(100);
        but_encode.setMinWidth(100);
        
        //Button Events
        but_encode.setOnAction(e -> {
        	HuffmanTree tree = new HuffmanTree(Huffman.getCharacterFrequency(tex_raw.getText()));
        	tex_encoded.setText(Huffman.encode(new CodeTable(tree), tex_raw.getText()));
        });
        
        //Main pane, Layout
        BorderPane pane = new BorderPane();
        pane.setPrefWidth(800);
        pane.setTop(new MenuBar(men_open,men_export));
        pane.setCenter(new HBox(tex_raw, new VBox(but_encode, but_decode), tex_encoded));
        pane.setBottom(new HBox(tex_frequency, tex_codetable));
        tex_raw.prefWidthProperty().bind(pane.widthProperty().divide(2));
        tex_encoded.prefWidthProperty().bind(pane.widthProperty().divide(2));
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
