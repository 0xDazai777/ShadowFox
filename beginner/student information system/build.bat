@echo off


set PATH_TO_FX="C:\Program Files\Java\javafx-sdk-24.0.1\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -d classes app/Main.java app/StudentTable.java
