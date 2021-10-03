// program to find closest coordinate
import java.util.*;

class GFG{
	

static void closest(int [][]pts, int k)
{
	int n = pts.length;
	int[] distance = new int[n];
	for(int i = 0; i < n; i++)
	{
		int x = pts[i][0], y = pts[i][1];
		distance[i] = (x * x) + (y * y);
	}

	Arrays.sort(distance);
	

	int distk = distance[k - 1];

	
	
	for(int i = 0; i < n; i++)
	{
		int x = pts[i][0], y = pts[i][1];
		int dist = (x * x) + (y * y);
		
		if (dist <= distk)
			System.out.println("[" + x + ", " + y + "]");
	}
}

public static void main (String[] args)
{
	int points[][] = { { 2,3},
					{5,8 },
                      {3,4},
					{5,7 } };

	int K = 3;
	
	closest(points, K);
}
}

