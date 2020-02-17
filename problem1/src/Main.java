import java.util.Arrays;
import java.util.Scanner;
//https://codeforces.com/problemset/problem/1279/B
public class Main {
	static Scanner scanner;
	static int datasetsAmount = 0;
	static int[][] partsAmountAndSeconds;
	static int currIndex=0;
	static int[][] secondsPerPart;
	public static void main(String[] args) {
	
		scanner = new Scanner(System.in);
		datasetsAmount = getAmountDatasets(scanner);
		if(datasetsAmount == 0)
		{
			System.out.println("Отменено");
			return;
		}
		partsAmountAndSeconds = new int[2][datasetsAmount];
		secondsPerPart= new int[datasetsAmount][];
		getInputData();
		PrintOutputData();
	}
	public static void getInputData()
	{
		for(currIndex=0; currIndex<datasetsAmount;currIndex++)
		{
			if(getPartsAmountAndSeconds(scanner) != false)
			{
				secondsPerPart[currIndex] = getSecondsPerPart(scanner);
				if(secondsPerPart==null)
				{
					partsAmountAndSeconds[0][currIndex]=0;
					partsAmountAndSeconds[1][currIndex]=0;
					datasetsAmount--;
				}
			}
			else
				datasetsAmount--;
		}
	}
	public static void PrintOutputData()
	{
		for(currIndex=0; currIndex<datasetsAmount;currIndex++)
		{
			findPartToSkipAndPrint(secondsPerPart[currIndex],partsAmountAndSeconds[1][currIndex]);
		}
	}
	public static void findPartToSkipAndPrint(int[] a,int seconds)
	{
		if(isSumLessValue(a,seconds) == true)
		{
			System.out.println("Можно прочитать весь стих");
			return;
		}
		int len = secondsPerPart[currIndex].length;
		int t = seconds;
		int sum=0;
		int max=0;
		int i=0;
		for(i = 0; i < len; i++)
		{
			if(a[i]>a[max]) max=i; 
			sum+=a[i];
			if(sum> t)
				break;
		}
		System.out.println("Часть которую нужно пропустить " + (max+1));
	}
	public static boolean isSumLessValue(int[] array, int maxVal)
	{
		if(array == null || array.length==0)
			return false;
		int sum=0;
		for(int i : array)
		{
			sum+=i;
		}
		if(sum>maxVal)
			return false;
		else
		return true;
		
	}
	public static int getAmountDatasets(Scanner scanner)
	{
		if(scanner == null) return 0;
		System.out.println("Введите количество наборов данных ( 1- 100) или Enter для выхода");
		int amount = 0;
		String res = "";
		while(amount<1)
		{
			res=scanner.nextLine();
			if(res.length() == 0) 
			{
				amount = 0;
				break;
			}
			amount =  Integer.parseInt(res);
			if(amount>100)
			{
				amount = 0;
			}
		}
		return amount;
	}
	public static boolean getPartsAmountAndSeconds(Scanner scanner)
	{
		if(scanner == null) return false;
		String res = "0";
		int arr[]= {};
		int partsAmount = 0;
		int seconds = 0;
		int MAX_partsAmount = 100000;
		int MAX_seconds = 1000000000;
		System.out.println("Введите через пробел количество частей стиха (1-10^5) и количество секунд, которое Санта будет слушать (1-10^9) или Enter для выхода");
		//
		while(partsAmount < 1 & seconds < 1)
		{
			res=scanner.nextLine();
			if(res.length() == 0)
			{
				partsAmount = 0;
				seconds = 0;
				break;
			}
			arr =  Arrays.stream(res.split(" ")).mapToInt(Integer::parseInt).toArray();
			partsAmount = arr[0];
			seconds = arr[1];
			if((partsAmount<1) | (partsAmount>MAX_partsAmount))
				partsAmount = 0;
			if((seconds<1) | (seconds>MAX_seconds))
				seconds = 0;
			if((partsAmount == 0) | (seconds == 0 ))
				{
				partsAmount = 0;
				seconds = 0;
				}
				
		}
		if((partsAmount == 0) & (seconds == 0 ))
			return false;
		else
		{
			partsAmountAndSeconds[0][currIndex] = partsAmount;
			partsAmountAndSeconds[1][currIndex] = seconds;
			return true;
		}
	}
	public static int[] getSecondsPerPart(Scanner scanner)
	{
		if(scanner==null) return null;
		if(partsAmountAndSeconds[0][currIndex]==0) return null;
		int partsAmount = partsAmountAndSeconds[0][currIndex];
		int[] secondsPP = {};
		String res = "";
		boolean isValuesCorrect = false;
		System.out.println("Введите через пробел " + partsAmount + " значений секунд, которые будут затрачены на прочтениие соответсвующей части стиха или Enter для выхода");
		while(isValuesCorrect == false)
		{
			res=scanner.nextLine();
			if(res.length() == 0)
			{
				return null;
			}
			if(Arrays.stream(res.split(" ")).mapToInt(Integer::parseInt).toArray().length !=partsAmount)
				{
				System.out.println("Вы ввели не " + partsAmount + " значений");
				continue;
				}
			else 
				secondsPP = Arrays.stream(res.split(" ")).mapToInt(Integer::parseInt).toArray();
			isValuesCorrect = isArrayValuesCorrect(secondsPP,1,1000000000);		
		}
		return secondsPP;
	}
	public static boolean isArrayValuesCorrect(int[] array,int minVal, int maxVal)
	{
		if(array==null) return false;
		for(int i : array)
		{
			if(i<minVal | i>maxVal)
				return false;
		}
		return true;
	}
}
