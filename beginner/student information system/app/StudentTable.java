package app;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

class CellClicked implements EventHandler<MouseEvent>
{
	StackPane stack;
	CellClicked(StackPane stack)
	{
		this.stack = stack; //Mapping the stack of cell to it's handler
	}
	@Override
	public void handle(MouseEvent event) {
		// When the Label is clicked during edit mode
        // We want it to change into TextField
        // 1 --> Label (currently visible)
        // 0 --> TextField (currently hidden)

        // Make Label invisible
        stack.getChildren().get(1).setVisible(false);
        // Make TextField visible
        stack.getChildren().get(0).setVisible(true);
        // Request focus for the TextField
        ((TextField) stack.getChildren().get(0)).requestFocus();
		
	}
	
}

class Cell
{
	private StackPane stack;
	TextField textBox;
	Label text;
	static Label statusLabel;
	//Creating new empty cell
	public Cell()
	{
		this(new TextField(), new Label());
	}
	
	
	//Creating new custom cell
	public Cell(TextField textBox, Label text)
	{
		this.textBox = textBox;
		this.text = text;
		
		this.textBox.setVisible(false);

		stack = new StackPane(this.textBox, this.text);
		stack.setStyle("-fx-border-color: black");
		stack.setOnMouseClicked(new CellClicked(stack));
		
		this.textBox.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                saveCell(this);
                if(statusLabel.getText() != "Mode: EDIT")
                	this.textBox.setEditable(false); // Make TextField not editable when losing focus
                else
                	this.textBox.setEditable(true);

                this.text.setVisible(true);
                this.textBox.setVisible(false);
            }
        });
	}
	
	
	static void saveCell(Cell cell)
	{
		//cell.textBox.setVisible(false);
        //cell.textBox.setEditable(false); // Make TextField not editable when losing focus
		cell.text.setText(cell.textBox.getText());
		//cell.text.setVisible(true);
	}
	
	StackPane getContainer()
	{
		return stack;
	}
}

class CrossClicked implements EventHandler<MouseEvent>
{
	static StudentTable sTable;
	Row row;
	CrossClicked(Row row)
	{
		this.row = row;
	}
	@Override
	public void handle(MouseEvent event) {
		int index = sTable.getContainer().getChildren().indexOf(row.getContainer()); 
		sTable.getContainer().getChildren().remove(index);
	}
	
}

class Row
{
	private HBox row;
	static Image crossBtn;
	ImageView cross = new ImageView(crossBtn);
	boolean isDeletable;
	

	private Node createDummyNode() {
	    Region dummy = new Region();
	    dummy.setPrefSize(30, 30); // Match the cross button's size
	    dummy.setVisible(false); // Always invisible
	    return dummy;
	}				
	
	// Mapping Existing Row Data
	Row(HBox row)
	{
		this.row = row;
		int lastIndex = this.row.getChildren().size() - 1;
		Node n = this.row.getChildren().get(lastIndex);
		if(n instanceof ImageView)
			cross = (ImageView) n;

		this.row.setAlignment(Pos.CENTER);
	}

	
	// Creating New Row
	public Row(int nCells, boolean isDeletable)
	{
		this(isDeletable); //setting up the base row/template
		for(int i = 0; i < nCells; i++)
		{
			addCell(new Cell());
		}
		if(isDeletable)
			add(cross);
		else
			add(createDummyNode()); //for alignment
	}
	
	// Creating Custom New Row
	public Row(boolean isDeletable, Cell ...cells)
	{
		this(isDeletable);
		addAll(cells);
		if(isDeletable)
			add(cross);
		else
			add(createDummyNode()); //for alignment
		
	}
	

	//Default/Base Row
	public Row(boolean isDeletable)
	{
		this.isDeletable = isDeletable;
		row = new HBox();
		row.setAlignment(Pos.CENTER);
		cross.setVisible(false);
		cross.setFitHeight(30);
		cross.setFitWidth(30);
		cross.setOnMouseClicked(new CrossClicked(this));
	}

	void addCell(Cell cell)
	{
		if(cell != null)
		{
			row.getChildren().add(cell.getContainer());
		}
	}

	void add(Node node)
	{
		row.getChildren().add(node);
	}
	
	public void addAll(Cell ...cells)
	{
		for(Cell cell : cells)
		{
			addCell(cell);
		}
		if(isDeletable)
			add(cross);
	}
	
	
	static void saveRow(Row row)
	{
		for(Node node : row.getContainer().getChildren())
		{
			if(!(node instanceof StackPane))
			{
				break;
			}
			StackPane stack = (StackPane) node;
			TextField textBox = (TextField) stack.getChildren().get(0);
			boolean flag = textBox.isVisible();
			
			Label text = (Label) stack.getChildren().get(1);
			if(flag)
			{
				Cell.saveCell(new Cell(textBox,text));
			}

		}
	}
	
	static void setEditable(Row row, boolean flag)
    {
        for(Node node : row.getContainer().getChildren())

        {
            if(!(node instanceof StackPane))

            {
                break;

            }
            StackPane stack = (StackPane) node;
            TextField textBox = (TextField) stack.getChildren().get(0);
            textBox.setEditable(flag);

        }
    } 
	
	HBox getContainer()
	{
		return row;
	}
}


public class StudentTable
{
	Row headerRow;
	private VBox outerTable;
	private VBox table;
	static Image plusBtn;
	ImageView plus;
	
	private class PlusClicked implements EventHandler<MouseEvent>
	{

		@Override
		public void handle(MouseEvent event) {
			Row row = new Row(3,true);
			addRow(row);
		}
		
	}
	
	public StudentTable(Row headerRow)
	{
		table = new VBox();
		outerTable = new VBox();

		CrossClicked.sTable = this;
		
		plus = new ImageView(plusBtn);
		plus.setFitHeight(30);
		plus.setFitWidth(30);

		plus.setVisible(false);
		plus.setOnMouseClicked(new PlusClicked());
		outerTable.getChildren().addAll(table,plus);
		
		
		this.headerRow = headerRow;
		table.getChildren().add(headerRow.getContainer());
		table.setAlignment(Pos.CENTER);
		outerTable.setAlignment(Pos.CENTER);
	}
	
	void addRow(Row newRow)
	{
		if(newRow != null)
		{
			table.getChildren().add(newRow.getContainer());
		}
	}
	
	
	void add(Node node)
	{
		outerTable.getChildren().add(node);
	}
	
	void addAll(Row ...rows)
	{
		for(Row row : rows)
		{
			addRow(row);
		}
		
		add(plus);
	}
	
	public void saveTable()
	{
		for(Node node : this.getContainer().getChildren())
		{
			if(node instanceof HBox)
			{
				HBox hBox = (HBox) node;
				Row.saveRow(new Row(hBox));
			}
		}
	}
	
	 public void setEditable(boolean flag)
	    {
	        for(Node node : this.getContainer().getChildren())

	        {
	            if(node instanceof HBox)

	            {
	                HBox hBox = (HBox) node;
	                Row.setEditable(new Row(hBox),flag);
	            }
	        }

	    } 
	
	
	public void setAllCrossesVisible(boolean flag)
	{
		for(Node node : this.getContainer().getChildren())
		{
			HBox hBox = (HBox) node;

			int lastIndex = hBox.getChildren().size() - 1;
			Node n = hBox.getChildren().get(lastIndex);
			
			if(n instanceof ImageView)
			{
				Row row = new Row(hBox);
				row.cross.setVisible(flag);
			}
		}
	}
	
	VBox getContainer()
	{
		return table;
	}

	VBox getOuterContainer()
	{
		return outerTable;
	}
}
