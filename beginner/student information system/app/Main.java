package app;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


class ModeHandler
{
	StudentTable sTable;
	ModeHandler(StudentTable sTable)
	{
		this.sTable = sTable;
	}
	void defaultState()
	{
		sTable.plus.setVisible(false);
		sTable.setAllCrossesVisible(false);
		
	}
}


class AddButtonClicked extends ModeHandler implements EventHandler<MouseEvent> 
{
	private Label statusLabel;
	AddButtonClicked(StudentTable sTable, Label statusLabel)
	{
		super(sTable);
		this.statusLabel = statusLabel;
		this.sTable = sTable;
	}

	@Override
	public void handle(MouseEvent event) {
		defaultState();
		//sTable.saveTable();
		statusLabel.setText("Mode: ADD");
		sTable.plus.setVisible(true);
	}

}

class EditButtonClicked extends ModeHandler implements EventHandler<MouseEvent>
{
	private Label statusLabel;
	EditButtonClicked(StudentTable sTable, Label statusLabel)
	{
		super(sTable);
		this.statusLabel = statusLabel;
		this.sTable = sTable;
	}

	@Override
	public void handle(MouseEvent event) {
		defaultState();
		this.statusLabel.setText("Mode: EDIT");
		sTable.plus.setVisible(false);
		sTable.setEditable(true);
	}

}
class ViewButtonClicked extends ModeHandler implements EventHandler<MouseEvent>
{
	private Label statusLabel;

	ViewButtonClicked(StudentTable sTable, Label statusLabel)
	{
		super(sTable);
		this.statusLabel = statusLabel;
		this.sTable = sTable;
	}

	@Override
	public void handle(MouseEvent event) {
		sTable.setEditable(false);
		defaultState();
		//sTable.saveTable();
		this.statusLabel.setText("Mode: VIEW");
		sTable.plus.setVisible(false);
		//sTable.saveTable();
	}

}
class DeleteButtonClicked extends ModeHandler implements EventHandler<MouseEvent>
{
	private Label statusLabel;
	DeleteButtonClicked(StudentTable sTable, Label statusLabel)
	{
		super(sTable);
		this.statusLabel = statusLabel;
	}

	@Override
	public void handle(MouseEvent event) {
		defaultState();
		//sTable.saveTable();
		this.statusLabel.setText("Mode: DELETE");
		sTable.setAllCrossesVisible(true);
		sTable.setEditable(false);
	}

}


public class Main extends Application
{
    @Override
    public void start(Stage stage)
    {
    	// ---------------- Asset Paths -----------------------
    	String assetFolderPath = "file:///C:/Users/karth/Desktop/java/shadowfox/beginner/student information system/assets/";
    	String logoUrl = assetFolderPath+"logo.png";
    	String plusUrl = assetFolderPath+"plus-button.png"; //every sTable at last has a plus button
    	String crossUrl = assetFolderPath+"cross-button.png"; //every row has a cross button
    														// but in normal conditions/mode they are not visible
    	

    	// ---------------- Plus and Cross Button --------------
    	Row.crossBtn = new Image(crossUrl); 
    	StudentTable.plusBtn = new Image(plusUrl);
    
    	

    	// ---------------- Header Row ----------------
    	Row headerRow;
    	Cell column1, column2, column3;
    	column1 = new Cell(new TextField(), new Label("RollNo"));
    	column2 = new Cell(new TextField(), new Label("Name"));
    	column3 = new Cell(new TextField(), new Label("Age"));
    	
    	headerRow = new Row(false, column1, column2, column3);
    	
    	// ---------------- sTable ---------------------
    	StudentTable sTable = new StudentTable(headerRow);

    	// 	--------------- Label ---------------------
    	Label statusLabel = new Label("Mode: ");
    	statusLabel.setFont(new Font("Arial",24));
    	
    	Cell.statusLabel = statusLabel;
    	
    	// -------------- Logo ------------------------
    	Image logo = new Image(logoUrl);
    	ImageView logoFrame = new ImageView(logo);
    	logoFrame.setFitWidth(50);
    	logoFrame.setFitHeight(50);

    	HBox topHBox = new HBox(30); 
    	topHBox.getChildren().addAll(logoFrame, statusLabel);
    	topHBox.setAlignment(Pos.CENTER);
    	
    	
    	// -------------- bottomHBox ---------------------
    	Button addBtn = new Button("ADD");
        Button editBtn = new Button("EDIT");
        Button viewBtn = new Button("VIEW");
        Button deleteBtn = new Button("DELETE");
       
        
        addBtn.setOnMouseClicked(new AddButtonClicked(sTable,statusLabel));
        editBtn.setOnMouseClicked(new EditButtonClicked(sTable,statusLabel));
        viewBtn.setOnMouseClicked(new ViewButtonClicked(sTable,statusLabel));
        deleteBtn.setOnMouseClicked(new DeleteButtonClicked(sTable,statusLabel));
        
        HBox bottomHBox = new HBox(30);
        
        bottomHBox.getChildren().addAll(addBtn,editBtn,deleteBtn,viewBtn);
        bottomHBox.setAlignment(Pos.CENTER);
    
        
        // -------------- Main Layout --------------
        BorderPane root = new BorderPane();
    	root.setBottom(bottomHBox);
    	root.setTop(topHBox);
    	root.setCenter(sTable.getOuterContainer());
    	
    	
    	
    	// -------------- Window Stuff --------------
        Scene scene = new Scene(root);
        stage.setTitle("Student Information System");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(400);

        stage.show();
    }

    public static void main(String args[])
    {
        launch(args);
    }

}