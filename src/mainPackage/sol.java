package mainPackage;
import java.io.*;
import java.util.Scanner;

public class sol {

	public static void main(String[] args) {
		
		int choice;
		Main obj = new Main("test.csv");
		Scanner scan = new Scanner(System.in);
		
		do
		{
			
			System.out.println("1. Generate Sale...");
			System.out.println("2. Insert Quantity...");
			System.out.println("3. Insert Product...");
			System.out.println("4. Display Product Details...");
			System.out.println("5. Display All Products...");
			System.out.println("6. Exit");
				choice = scan.nextInt();
				
			switch(choice)
			{
			case 1:
					obj.sale();
					break;
			case 2:
					obj.insertQty();
					break;
			case 3:
					obj.insertP();
					break;
			case 4:
					obj.display();
					break;
			case 5:
					obj.displayAll();
					break;
			case 6:
					obj.updateFile();
					break;
			default:
					System.out.println("*** Incorrect Input ***");
			}
		}while(choice != 6);
		
		scan.close();
		
	}

}
