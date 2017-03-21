package mainPackage;

import java.util.*;
import java.io.*;

public class Main {

	HashMap<Integer,String> mapIdName, mapIdQty, mapIdPrice;
	Scanner scan;
	double totalBill = 0;
	
	Main( String fileName) // constructor
	{
		mapIdName = new HashMap<Integer, String>();
		mapIdQty = new HashMap<Integer, String>();
		mapIdPrice = new HashMap<Integer, String>();
		String line;
		try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(",");
				mapIdName.put(Integer.parseInt(element[0]), element[1]);
				mapIdQty.put(Integer.parseInt(element[0]), element[2]);
				mapIdPrice.put(Integer.parseInt(element[0]), element[3]);
			}
			br.close();
		}catch(IOException e)
		{
			System.out.println(e.toString());
		}

	}
	
	public void insertP() // inserting a whole new product
	{
		scan = new Scanner(System.in);
		String temp;
		int id;
		
		System.out.println("Enter Product Id...");
			id = scan.nextInt();
		System.out.println("Enter Product Name...");
			temp = scan.next();
			
			mapIdName.put(id, temp);
			
		System.out.println("Enter Product Quantity...");
			temp = scan.next();
			
			mapIdQty.put(id, temp);
			
		System.out.println("Enter Product Price...");
			temp = scan.next();
			
			mapIdPrice.put(id, temp);
	}
	
	public void sale()
	{
		int id,qty;
		scan = new Scanner(System.in);
		
		System.out.println("Enter Product ID...");
		id = scan.nextInt();
		System.out.println("Enter Product Quantity...");
		qty = scan.nextInt();
		
		Iterator itr = mapIdName.entrySet().iterator();
		Iterator itr1 = mapIdQty.entrySet().iterator();
		Iterator itr2 = mapIdPrice.entrySet().iterator();

		while(itr.hasNext())
		{
			Map.Entry mIdName = (Map.Entry)itr.next();
			Map.Entry mIdQty = (Map.Entry)itr1.next();
			Map.Entry mIdPrice = (Map.Entry)itr2.next();
			
			if(mIdName.getKey().equals(id))
			{
				int setQty = Integer.valueOf((String)mIdQty.getValue());
				int amt = Integer.valueOf((String)mIdPrice.getValue());
				if(setQty < qty)
					System.out.println("Quantity is greater that available stock");
				else
				{
					mIdQty.setValue(setQty - qty);
					totalBill = totalBill + (amt*qty);
					System.out.println( "Amount = " + (amt*qty));
				}
				break;
			}
		}
		
		System.out.println("Press 1 to Enter More Products to this bill...");
			qty = scan.nextInt(); // as qty was of no use now so i used it again over here
			if(qty == 1)
				sale();
			else
				System.out.println("Total Bill of this Sale= " + totalBill);
	}
	
	public void insertQty()
	{
		Integer id,qty,tempQty;
		scan = new Scanner(System.in);
		
		System.out.println("Enter Product ID...");
			id = scan.nextInt();
		System.out.println("Enter Product Qty...");
			qty = scan.nextInt();
			
		Iterator itr = mapIdQty.entrySet().iterator();
		
		while(itr.hasNext())
		{
			Map.Entry mIdQty = (Map.Entry)itr.next();
			if(mIdQty.getKey().equals(id))
			{
				tempQty = Integer.valueOf((String)mIdQty.getValue());
				tempQty += qty;
				mIdQty.setValue(tempQty.toString());
				System.out.println("*** Product Quantity Successfully Updated *** ");
				break;
			}
		}
	}
	
	
	private String generateContent()
	{
		
		StringBuilder builder = new StringBuilder();
		
		Iterator itr = mapIdQty.entrySet().iterator();
		Iterator itr1 = mapIdPrice.entrySet().iterator();
		
		for(HashMap.Entry<Integer,String> write : mapIdName.entrySet())
		{
			Map.Entry mIdQty = (Map.Entry)itr.next();
			Map.Entry mIdPrice = (Map.Entry)itr1.next();
			
			builder.append(write.getKey());
			builder.append(",");
			builder.append(write.getValue());
			builder.append(",");
			builder.append(mIdQty.getValue());
			builder.append(",");
			builder.append(mIdPrice.getValue());
			builder.append("\r\n");
		}
		String content = builder.toString().trim();
		return content;
	}
	
	public void displayAll()
	{
		System.out.println(generateContent());
	}
	
	public void display()
	{
		int id;
		scan = new Scanner(System.in);
		
		System.out.println("Enter Product Id...");
			id = scan.nextInt();
		Iterator itrIdName = mapIdName.entrySet().iterator();
		Iterator itrIdQty = mapIdQty.entrySet().iterator();
		Iterator itrIdPrice = mapIdPrice.entrySet().iterator();

		while(itrIdName.hasNext())
		{
			Map.Entry mIdName = (Map.Entry)itrIdName.next();
			Map.Entry mIdQty = (Map.Entry)itrIdQty.next();
			Map.Entry mIdPrice = (Map.Entry)itrIdPrice.next();
			
			if(mIdName.getKey().equals(id))
			{
				System.out.println("Name : " + mIdName.getValue());
				System.out.println("Available Quantity : " + mIdQty.getValue());
				System.out.println("Price per Piece : " + mIdPrice.getValue());
				break;
			}
		}
	}
	
	public void updateFile()
	{
		String content = generateContent();
		
		try(PrintWriter out = new PrintWriter("test.csv"))
		{
			out.println(content);
			out.close();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
}
