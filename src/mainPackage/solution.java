package mainPackage;
import java.util.*;
import java.io.*;
public class solution {

	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();
		int id,qty;
		String csvFile = "test.csv";
		String line = "";
		String csvSplitBy = ",";
		HashMap<Integer,String> mapIdName = new HashMap<Integer,String>();
		HashMap<Integer,String> mapIdQty = new HashMap<Integer,String>();
		HashMap<Integer,String> mapIdPrice = new HashMap<Integer,String>();
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(csvSplitBy);
				mapIdName.put(Integer.parseInt(element[0]), element[1]);
				mapIdQty.put(Integer.parseInt(element[0]), element[2]);
				mapIdPrice.put(Integer.parseInt(element[0]), element[3]);
				
			}
		}catch(IOException e)
		{
			System.out.println(e.toString());
		}
		//System.out.println(map);
		//System.out.println(map1);
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
				int bill = Integer.valueOf((String)mIdPrice.getValue());
				if(setQty < qty)
					System.out.println("Quantity is greater that available stock");
				else
				{
					mIdQty.setValue(setQty - qty);
					System.out.println("Total bill = " + (bill*qty));
				}
				//System.out.println("If called");
				break;
			}
		}
		System.out.println(mapIdName);
		System.out.println(mapIdQty);
		System.out.println(mapIdPrice);
		
		Iterator itr3 = mapIdQty.entrySet().iterator();
		Iterator itr4 = mapIdPrice.entrySet().iterator();
		
		for(HashMap.Entry<Integer,String> write : mapIdName.entrySet())
		{
			Map.Entry mIdQty = (Map.Entry)itr3.next();
			Map.Entry mIdPrice = (Map.Entry)itr4.next();
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
		System.out.println(content);
		
		try(PrintWriter out = new PrintWriter("test.csv"))
		{
			out.println(content);
			out.close();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		scan.close();
		}
		
	}
