package Maven.Maven_epam_hometask1;
import java.util.*;
interface Gift
{
	public int getWeight();
	public int getPrice();
}
class Chocolate implements Gift
{
	int weight=0;
	int price=0;
	Chocolate(int weight,int price)
	{
		this.weight=weight;
		this.price=price;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public int getPrice()
	{
		return this.price;
	}
}
class Sweet implements Gift
{
	int weight=0;
	int price=0;
	Sweet(int weight,int price)
	{
		this.weight=weight;
		this.price=price;
	}
	public int getWeight()
	{
		return this.weight;
	}
	public int getPrice()
	{
		return this.price;
	}
}
class Gulabjamun extends Sweet
{
	GulabJamun(int weight,int price)
	{
		super(weight,price);
	}
	public int getWeight()
	{
		return this.weight;
	}
	public int getPrice()
	{
		return this.price;
	}
}
 class NewYearGift 
{
	static int sortType;
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
	static ArrayList<Chocolate> candies = new ArrayList<Chocolate>();
	static ArrayList<Sweet> sweets = new ArrayList<Sweet>();
	static HashMap<String,Integer> nameToWeight = new HashMap<String,Integer>();
	static HashMap<String,Integer> nameToPrice = new HashMap<String,Integer>();
	public static void main(String args[])
	{
		Gulabjamun g = new Gulabjamun(10,10);
		enterChocolates();
		enterSweets();
		System.out.println("Total weight of gift : "+calculateWeight());
		System.out.println("Choose the way to sort : 1->sort by weight\t2->sort by price");
		sortType=sc.nextInt();
		if(sortType==1)
		{
			Comparator<Chocolate> weightComparision = (Chocolate c1,Chocolate c2) -> ((Integer)c1.getWeight()).compareTo(c2.getWeight());
			Collections.sort(chocolates,weightComparision);
		}
		else
		{
			Comparator<Chocolate> priceComparision = (Chocolate c1,Chocolate c2) -> ((Integer)c1.getPrice()).compareTo(c2.getPrice());
			Collections.sort(chocolates,priceComparision);	
		}
		System.out.println("The sorted results : ");
		for(Chocolate chocolate : chocolates)
		{
			System.out.println(chocolate.getPrice());
		}
		calculateRange(sortType);
	}
	public static void enterChocolates()
	{
		int nchoc;
		System.out.println("Enter number of chocolates in the gift : ");
		nchoc=sc.nextInt();
		for(int i=0;i<nchoc;i++)
		{
			System.out.println("Enter chocolate type : 1->candy\t2->wafer");
			int chocType=sc.nextInt();
			System.out.println("Enter chocolate weight : ");
			int chocWeight=sc.nextInt();
			System.out.println("Enter chocolate Price : ");
			int chocPrice=sc.nextInt();
			if(chocType==1)
			{
				System.out.println("Enter the name of the candy : ");
				String candyName=sc.next();
				if(nameToWeight.containsKey(candyName))
				{
					nameToWeight.put(candyName,(int)nameToWeight.get(candyName)+chocWeight);
				}
				else
				{
					nameToWeight.put(candyName,chocWeight);
				}
				if(nameToPrice.containsKey(candyName))
				{
					nameToPrice.put(candyName,(int)nameToPrice.get(candyName)+chocPrice);
				}
				else
				{
					nameToPrice.put(candyName,chocPrice);
				}
			}
			Chocolate choco = new Chocolate(chocWeight,chocPrice);
			chocolates.add(choco);
			if(chocType==1)
			{
				candies.add(choco);
			}
		}
	}
	public static void enterSweets()
	{
		int nsw;
		System.out.println("Enter the number of sweets in the gift : ");
		nsw=sc.nextInt();
		for(int i=0;i<nsw;i++)
		{
			System.out.println("Enter Sweet weight : ");
			int swWeight=sc.nextInt();
			System.out.println("Enter Sweet Price : ");
			int swPrice=sc.nextInt();
			Sweet sweet=new Sweet(swWeight,swPrice);
			sweets.add(sweet);
		}
	}
	public static int calculateWeight()
	{
		int totalWeight=0;
		for(Chocolate chocolate : chocolates)
		{
			totalWeight+=chocolate.getWeight();
		}
		for(Sweet sweet : sweets)
		{
			totalWeight+=sweet.getWeight();
		}
		return totalWeight;
	}
	@SuppressWarnings("rawtypes")
	public static void calculateRange(int sortType)
	{
		int l,h;
		if(sortType==1)
		{
			System.out.println("Enter lower limit : ");
			l=sc.nextInt();
			System.out.println("Enter higher limit : ");
			h=sc.nextInt();
			Set<Map.Entry<String,Integer>> candyset = nameToWeight.entrySet();
			Iterator<Map.Entry<String,Integer>> candyIterator =candyset.iterator();
			while(candyIterator.hasNext())
			{
				Map.Entry candyelement = (Map.Entry)candyIterator.next();
				int currentweight = (int)candyelement.getValue();
				if(currentweight>=l && currentweight<=h)
				{
					System.out.println(candyelement.getKey());
				}
			}
		}
		else
		{
			System.out.println("Enter lower limit : ");
			l=sc.nextInt();
			System.out.println("Enter higher limit : ");
			h=sc.nextInt();
			Set<Map.Entry<String,Integer>> candyset = nameToPrice.entrySet();
			Iterator<Map.Entry<String,Integer>> candyIterator =candyset.iterator();
			while(candyIterator.hasNext())
			{
				Map.Entry candyelement = (Map.Entry)candyIterator.next();
				int currentprice = (int)candyelement.getValue();
				if(currentprice>=l && currentprice<=h)
				{
					System.out.println(candyelement.getKey());
				}
			}
		}
	}
}
