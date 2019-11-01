
/*
 * Present a menu to the user and let them choose an item (by number or letter).
  - Allow the user to choose a quantity for the item ordered
  - Give the user a line total.
     Validator
- Either through the menu or a separate question, allow them to re-display the menu and to complete the purchase.
    Validator

- GIve the subtotal, sales tax, and grand total.  
     Methods 

- Ask for payment type (cash, credit, check)
   For cash, 
   For check 
   For credit, get the credit card number, expiration, and CVV.

- At the end, display a receipt with all items ordered, the subtotal, the grand total, and appropriate payment information.

- Return to the original menu for a new order.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POSApp {

	public static void main(String[] args) {
		
		int counter = 0;
		int userNum = 0;
		
		List<CatsProduct> adoptableCats = readCatsFromCSV("Cats.csv");
		
		Path pathToFile = Paths.get("Cats.csv");
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to our Cat'fe! Please adopt a cat.");

//		Set<String> catSet = new TreeSet<>();
//		String userCon = "y"; 
//		int userCategorySelect; 

		for (CatsProduct c : adoptableCats) {
			counter += 1;
			System.out.println(counter + ". " + c.getName());

		}
		
		userNum = Validator.getInt(scnr, 
				"Choose a number 1-12 to view from our fine selection of cats: ", 
				1, 
				adoptableCats.size());
		
		System.out.println(adoptableCats.get(userNum - 1).pretty());
		
		System.out.println("Would you like to adopt another cat? y/n");
	}
		 
	private static List<CatsProduct> readCatsFromCSV(String fileName) {
		ArrayList<CatsProduct> x = new ArrayList<CatsProduct>();

		// Open the file for reading
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			// One-at-a-time, read each line in the file.
			while (line != null) {
				// For each line,
				// Create a CatsProduct from the line and
				CatsProduct theCatOnThisLine = CatsProduct.fromString(line);
				// Add it to the list.
				x.add(theCatOnThisLine);
				// Now read a new line.
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read the file.");

		}

		return (x);

	}
}
