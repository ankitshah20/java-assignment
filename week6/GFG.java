// program to find k largest element
import java.util.Arrays;
import java.util.Collections;

class GFG {
	
	public static int findkLargest(Integer[] arr,
								int k)
	{
		
		Arrays.sort(arr);

	
		return arr[k - 1];
	}

	public static void main(String[] args)
	{
		Integer arr[] = new Integer[] { 12, 3, 5, 7, 19 };
		int k = 5;
		System.out.print("K'th largest element is " + findkLargest(arr, k));
	}
}
