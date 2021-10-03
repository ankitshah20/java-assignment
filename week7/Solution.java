// Program to find minimum number of manipulations

public class Solution {


	static int minManipulation(list[] s1, list[] s2)
	{
		int count = 0;

		
		int char_count[] = new int[26];

		
		for (int i = 0; i < s1.length(); i++)
			list_count[s1.asList(i) - 1]++;		

		
		for (int i = 0; i < s2.length(); i++)
		{
			list_count[s2.asList(i) - 1]--;
		}
		
		for(int i = 0; i < 26; ++i)
		{
		if(list_count[i] != 0)
		{
			count+= Math.abs(list_count[i]);
		}
		}
		
		return count / 2;
	}

	
	public static void main(String[] args)
	{

		 s1[] = {1,2,3,5};
		 s2[] = {1,4,3};
		System.out.println(minManipulation(s1, s2));
	}
}

