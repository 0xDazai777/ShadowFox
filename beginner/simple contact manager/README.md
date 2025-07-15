-----

# Contact Management System

This is a simple command-line based Contact Management System developed in Java. It allows users to perform basic operations like adding, deleting, updating, and displaying contact information.

## Features

  * **Add Contact:** Create new contact entries with full name, phone number, and email address.
  * **Delete Contact:** Remove existing contacts by their unique ID.
  * **Update Contact:** Modify the details of an existing contact using its ID.
  * **Display Contacts:** View a list of all stored contacts with their details.
  * **Interactive Menu:** A user-friendly menu guides you through the operations.
  * **Clear Screen Functionality:** Clears the console for a cleaner interface (primarily for Windows systems).

## How to Run

1.  **Save the Code:** Save all the provided Java code into three separate `.java` files in the same directory:

      * `Contact.java`
      * `Manager.java`
      * `Main.java` (This file also contains the `MutableString` class, but it's fine to keep it here, or create a `MutableString.java` file for it).

2.  **Compile:** Open a terminal or command prompt, navigate to the directory where you saved the files, and compile the Java code:

    ```bash
    javac Contact.java Manager.java Main.java
    ```

    *If you prefer to include `MutableString` in its own file:*

    ```bash
    javac Contact.java Manager.java MutableString.java Main.java
    ```

3.  **Run:** After successful compilation, run the `Main` class:

    ```bash
    java Main
    ```

## Usage

Once the program starts, you will see a main menu:

```
    ____  _             _         ____            _        _
/ ___|(_)_ __ ___  _ __ | | ___   / ___|___  _ __ | |_ __ _  ___| |_
\___ \| | '_ ` _ \| '_ \| |/ _ \ | |  / _ \| '_ \| __/ _` |/ __| __|
 ___) | | | | | | | |_) | |  __/ | |__| (_) | | | | || (_| | (__| |_
|____/|_|_| |_| |_| .__/|_|\___|  \____\___/|_| |_|\__\__,_|\___|\__|
                  | | __ _ _ __|_|__ _  __ _  ___ _ __
                  | |/ _` | '_ \ / _` |/ _` |/ _ \ '__|
                  | | (_| | | | | (_| | (_| |  __/ |
                  |_|\__,_|_| |_|\__,_|\__, |\___|_|
                                         |___/




[1] Add Contact
[2] Delete Contact
[3] Update Contact
[4] Display Contacts
[Q] Quit




Enter your choice:
```

Enter the corresponding number or 'Q' (case-insensitive) for your desired action:

  * **1 (Add Contact):** You'll be prompted to enter the full name, phone number, and email address for the new contact.
  * **2 (Delete Contact):** All current contacts will be displayed with their IDs. Enter the ID of the contact you wish to delete.
  * **3 (Update Contact):** All current contacts will be displayed. Enter the ID of the contact you wish to update, then provide the new full name, phone number, and email address.
  * **4 (Display Contacts):** Shows all contacts currently stored in the system. Press 'B' to return to the main menu.
  * **Q (Quit):** Exits the application.

## Code Structure

  * **`Contact.java`**: Defines the `Contact` class, representing a single contact with properties like full name, phone number, email, and a unique ID.
  * **`Manager.java`**: Contains the `Manager` class, which handles the core logic for managing contacts (adding, deleting, updating, and fetching contacts from an `ArrayList`).
  * **`MutableString.java`** (or within `Main.java`): A simple helper class `MutableString` demonstrating how to achieve "pass-by-reference" like behavior for strings in Java by wrapping the `String` object within a mutable container.
  * **`Main.java`**: The main entry point of the application. It handles user interaction, displays the menu, processes choices, and orchestrates calls to the `Manager` class. It also includes utility methods for clearing the screen and introducing delays.

## Notes

  * The `clearScreen()` method is specifically written for **Windows** command prompt (`cmd.exe /c cls`). If you are running this on a Unix-like system (Linux, macOS), you might need to change `ProcessBuilder("cmd.exe", "/c", "cls")` to `ProcessBuilder("clear")` or implement an alternative for clearing the screen.
  * The `MutableString` class is used in `Main` to showcase how to allow a method to "modify" string variables from the caller's perspective, effectively demonstrating Java's pass-by-value for references combined with object mutability. While it works, for actual `String` manipulation, returning a new `String` or using `StringBuilder` is generally preferred as it aligns better with `String`'s immutable nature.

-----
