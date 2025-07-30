Student Information System

This is a simple Student Information System built using JavaFX. It allows users to add, edit, view, and delete student records in a table format.

Features

    Add Students: Easily add new student records to the table.

    Edit Student Information: Modify existing student details directly within the table cells.

    View Students: Display all student records in a clear, non-editable view.

    Delete Students: Remove unwanted student records from the system.

    Interactive UI: A user-friendly interface powered by JavaFX.

Demo Video link: https://vimeo.com/1105946136

Getting Started

Prerequisites

    Java Development Kit (JDK) 11 or higher

    Maven or Gradle (for dependency management, though this project appears to be set up as a direct JavaFX application)

Project Structure

.
├── src/
│   └── main/
│       └── java/
│           └── app/
│               ├── Main.java
│               ├── Cell.java
│               ├── Row.java
│               └── StudentTable.java
└── assets/
    ├── logo.png
    ├── plus-button.png
    └── cross-button.png

Note: The asset paths in Main.java currently use an absolute path: file:///C:/Users/karth/Desktop/java/shadowfox/beginner/student information system/assets/. You will need to change this to a relative path or an absolute path that matches your system for the images to load correctly. A common practice is to place assets within the src/main/resources directory and load them using getClass().getResource().

Running the Application

    Clone the repository (if applicable):
    Bash

git clone <your-repository-url>
cd student-information-system

Update Asset Paths:
Open src/main/java/app/Main.java and modify the assetFolderPath variable to point to your assets directory. For example, if you move the assets folder into src/main/resources, you might change the loading to:
Java

// Example of relative loading from resources (requires assets in src/main/resources)
// String logoUrl = getClass().getResource("/logo.png").toExternalForm();
// String plusUrl = getClass().getResource("/plus-button.png").toExternalForm();
// String crossUrl = getClass().getResource("/cross-button.png").toExternalForm();
// For now, if keeping assets alongside src folder, adjust this:
String assetFolderPath = "file:./assets/"; // Assuming assets folder is in the project root
String logoUrl = assetFolderPath+"logo.png";
String plusUrl = assetFolderPath+"plus-button.png";
String crossUrl = assetFolderPath+"cross-button.png";

Make sure the assets folder is accessible relative to where you run the application or provide the correct absolute path.

Compile and Run:
You can compile and run the application using your IDE (e.g., IntelliJ IDEA, Eclipse) or via the command line.

Using Command Line (JavaFX 11+ with OpenJFX modules):
If you are using JavaFX as a module, you might need to add the necessary VM options.
Bash

    # Compile
    javac --module-path <path/to/javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml -d bin src/main/java/app/*.java

    # Run
    java --module-path <path/to/javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml -cp bin app.Main

    Replace <path/to/javafx-sdk> with the actual path to your JavaFX SDK.

    Using an IDE:
    Import the project into your preferred IDE and run Main.java. Ensure your IDE is configured to use the JavaFX SDK.

Usage

Upon launching the application, you will see a table with a header row.

    VIEW Mode (Default): In this mode, you can see the student records. Cells are not editable, and delete buttons are hidden.

    ADD Mode: Click the "ADD" button. A plus icon will appear at the bottom of the table. Click this icon to add new empty rows.

    EDIT Mode: Click the "EDIT" button. You can now click on any cell (except the header row) to edit its content. Press Enter or click outside the text field to save changes.

    DELETE Mode: Click the "DELETE" button. A red cross icon will appear next to each deletable row. Click the cross icon to remove the corresponding row.

Code Overview

    Main.java: The entry point of the application, responsible for setting up the primary stage, scene, and UI components like buttons and the StudentTable. It also handles button click events to switch between different modes (Add, Edit, View, Delete).

    StudentTable.java: Manages the main table structure, composed of Row objects. It handles adding new rows, saving table data (though not persistently in this version), and setting the editable state of cells.

    Row.java: Represents a single row in the student table, containing Cell objects. It handles the display and interaction of the "cross" (delete) button for each row.

    Cell.java: Represents an individual cell in the table. It toggles between a Label (for viewing) and a TextField (for editing) when clicked, based on the current application mode.

Future Enhancements

    Data Persistence: Implement saving and loading student data to/from a file (e.g., CSV, JSON) or a database.

    Input Validation: Add validation for student data (e.g., ensuring RollNo is unique, Age is a number).

    Search/Filter Functionality: Allow users to search for specific students or filter records.

    Sorting: Enable sorting of the table by different columns.

    Improved UI/UX: Enhance the visual design and user experience.
