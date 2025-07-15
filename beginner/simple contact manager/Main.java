import java.util.*;
import java.io.IOException;


class Contact
{
	static int ncontacts = 0;
	
	String full_name;
	String phone_number;
	String email_address;
	protected int id;
	Contact(String full_name, String phone_number, String email_address)
	{
		this.full_name = full_name;
		this.phone_number = phone_number;
		this.email_address = email_address;
		this.id = ++ncontacts;
	}
}



class Manager
{
	ArrayList<Contact> contacts;
	Manager()
	{
		contacts = new ArrayList<Contact>();
	}
	
	private Contact fetchContact(int id)
	{
		for(Contact contact : contacts)
		{
			if(contact.id == id)
			{
				return contact;
			}
			
		}
		return null;
		
	}
	
	private void copyContact(Contact src, Contact dest)
	{
		if(src == null || dest == null)
			return;
		dest.full_name = src.full_name;
		dest.phone_number = src.phone_number;
		dest.email_address = src.email_address;
	}
	
	void addContact(Contact new_contact)
	{
		contacts.add(new_contact);
	}

	int deleteContact(int id)
	{
		Contact contact = fetchContact(id);
		if(contact == null)
		{
			return -1;
		}
		contacts.remove(contact);
		return 0;
	}

	int updateContact(int id, Contact new_contact)
	{
		Contact contact = fetchContact(id);
		if(contact == null || new_contact == null)
		{
			return -1;
		}
		copyContact(new_contact,contact);
		return 0;
	}

	void displayContacts()
	{
		for(Contact contact : contacts)
		{
			System.out.println("--------------------------------------------------");
			System.out.println("ID = " +contact.id);
			System.out.println("Name = "+contact.full_name);
			System.out.println("Phone Number = "+contact.phone_number);
			System.out.println("Email Address = "+contact.email_address);
		}

	}

}


class MutableString
{
	public String value;
		public MutableString(String value) {
			this.value = value;
		}
}


class Main
{
	static String ascii_art = """
		____  _                 _         ____            _             _   
/ ___|(_)_ __ ___  _ __ | | ___   / ___|___  _ __ | |_ __ _  ___| |_ 
\\___ \\| | '_ ` _ \\| '_ \\| |/ _ \\ | |   / _ \\| '_ \\| __/ _` |/ __| __|
 ___) | | | | | | | |_) | |  __/ | |__| (_) | | | | || (_| | (__| |_ 
|____/|_|_| |_| |_| .__/|_|\\___|  \\____\\___/|_| |_|\\__\\__,_|\\___|\\__|
|  \\/  | __ _ _ __|_|__ _  __ _  ___ _ __                            
| |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|                           
| |  | | (_| | | | | (_| | (_| |  __/ |                              
|_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|                              
                          |___/          
""";


	static Scanner sc = new Scanner(System.in);
	static Manager man = new Manager();



	static void wait(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}



	static void clearScreen()
	{
		try 
		{
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cls");

            // Inherit the standard I/O of the current Java process
            pb.inheritIO();

            // Start the process
            Process process = pb.start();

            // Wait for the process to complete and get its exit code
            int exit_code = process.waitFor();
            //System.out.println("Process exited with code: " + exitCode);

		} 

		catch (IOException | InterruptedException e) 
		{
            e.printStackTrace();
        }
    }


	static void displayMenu()
	{
		System.out.println("\n\n\n\n");
		
		System.out.println("[1] Add Contact");
		System.out.println("[2] Delete Contact");
		System.out.println("[3] Update Contact");
		System.out.println("[4] Display Contacts");
		System.out.println("[Q] Quit");

		System.out.println("\n\n\n\n");


	}

	
	private static void inputDetails(MutableString full_name, MutableString phone_number, MutableString email_address)
	{
		sc.nextLine();
		System.out.print("Enter full name: ");
		full_name.value = sc.nextLine();
		
		System.out.print("\nEnter phone number: ");
		phone_number.value = sc.nextLine();
		
		System.out.print("\nEnter email address: ");
		email_address.value = sc.nextLine();
		
	}
	

	static void processChoice(char choice)
	{
		int id;
		MutableString full_name = new MutableString(null), phone_number = new MutableString(null), email_address=new MutableString(null);
		
		
		clearScreen();
		System.out.println(ascii_art);
		System.out.println("\n\n\n\n");
		switch(choice)
		{
			case '1':
				inputDetails(full_name, phone_number, email_address);
				
				man.addContact(new Contact(full_name.value, phone_number.value, email_address.value));
				break;

			case '2':
				man.displayContacts();
				System.out.print("\nEnter deletion id: ");
				id = sc.nextInt();
				int ret = man.deleteContact(id);
				if(ret == -1)
				{
					System.out.println("Invalid id");
					wait(2000);
					processChoice('2');
				}
					
				break;

			case '3':
				man.displayContacts();
				System.out.print("\nEnter updation id: ");
				id = sc.nextInt();
				inputDetails(full_name,phone_number,email_address);
				man.updateContact(id,new Contact(full_name.value, phone_number.value, email_address.value));
				break;
			
			case '4':
				man.displayContacts();
				System.out.print("\nEnter 'B' to go back: ");
				sc.next();	
				break;

			case 'Q':
				System.out.println("Exiting...");
				sc.close();
				System.exit(0);
				break;

			default: 
				System.out.printf("'%c' is Invalid Choice", choice);
				wait(2000);

		}
	}

	public static void main(String args[])
	{
		char choice = ' ';
		while(true)
		{
			clearScreen();
		
			System.out.println(ascii_art);
			displayMenu();

			System.out.print("Enter your choice: ");
			choice = sc.next().charAt(0);
			processChoice(choice);

		}

	}

}

