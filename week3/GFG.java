// program to find remaining jar
import java.util.ArrayList;
import java.util.Arrays;

class GFG{




static ArrayList<Integer> mergeDel(ArrayList<Integer> l)
{
	
	
	if (l.size() == 1)
		return l;

	int m = l.size() / 2;

	ArrayList<Integer> temp1 = new ArrayList<>(
		l.subList( m, l.size()));
	ArrayList<Integer> temp2 = new ArrayList<>(
		l.subList(0,m));


	return merge(mergeDel(temp1), mergeDel(temp2));
}
	

static ArrayList<Integer> merge(ArrayList<Integer> x,ArrayList<Integer> y)
{
	for(Integer i : y)
	{
		if (x.get(x.size() - 1) > i)
			x.add(i);
	}
	return x;
}

public static void main(String[] args)
{
	

	Integer[] ar = { 1,7,3,9,2 };
	
	ArrayList<Integer> arr = new ArrayList<>(
		Arrays.asList(ar));
	ArrayList<Integer> ans = mergeDel(arr);
	
	System.out.print("[ ");
	for(Integer x : ans)
		System.out.print(x + ", ");
		
	System.out.println("]");
}
}

