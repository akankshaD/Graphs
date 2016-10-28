import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DFSTraversal{

	int size = 0;
	int capacity = 16;   // default capacity
	int top = -1;
	int[] stack = new int[capacity];

	int isStackEmpty()
	{
		if(size == 0)
		{
			System.out.println("Empty Stack!");
			return 1;
		}	
		else 
			return 0;
	}

	void push(int x)
	{
		if(size != capacity)
		{
			stack[++top] = x;
			size++;		
		}
		else 
			System.out.println("Stack full!");
		
	}

	int pop()
	{
		if( isStackEmpty() == 0 )
		{
			int data = stack[top];
			stack[top] = Integer.MIN_VALUE;
			top--;
			size--;
			return data;
		}
		else
		{
			System.out.println("Empty Stack!");
			return Integer.MIN_VALUE;
		}
		
	}

	void DFS(int n, ArrayList<ArrayList<Integer>> edges)
	{
		int[] status = new int[n];
		int[] piFunc = new int[n];
		Arrays.fill(status, 0);

		int s = 1;
		piFunc[s-1] = -1;
		//status[s-1] = 1;
		push(s);

		while( isStackEmpty() == 0)
		{
			int u = pop();
			System.out.print(u + " ");
			if(status[u-1] != 1)
			{
				status[u-1] = 1;
				ArrayList<Integer> v = new ArrayList<Integer>();
				v = edges.get(u-1);
				for(int x: v)
				{
					if(status[x-1] != 1)
					{
						push(x);
						piFunc[x-1] = u;
						//status[x-1] = 1;
					}
				}
			}
		}

		System.out.println("");
		for(int i=0; i< n; i++)
		{
			System.out.println(piFunc[i] + ", " + (i+1));
		}
	}

	public static void main(String[] args)
	{

		// Dataset 1:

/*		int n = 5;                 // Number of nodes in the graph

		ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();  // Adjacency List for storing the edges of the graph
		ArrayList<Integer> n1 = new ArrayList<Integer>();
		n1.add(2);
		n1.add(4);
		AdjList.add(n1);

		ArrayList<Integer> n2 = new ArrayList<Integer>();
		n2.add(3);
		n2.add(4);
		AdjList.add(n2);

		ArrayList<Integer> n3 = new ArrayList<Integer>();
		AdjList.add(n3);

		ArrayList<Integer> n4 = new ArrayList<Integer>();
		n4.add(5);
		AdjList.add(n4);

		ArrayList<Integer> n5 = new ArrayList<Integer>();
		n5.add(2);
		n5.add(3);
		AdjList.add(n5);*/

		// Dataset 2:
		/*int n = 10;                 // Number of nodes in the graph

		ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();  // Adjacency List for storing the edges of the graph
		ArrayList<Integer> n1 = new ArrayList<Integer>();
		n1.add(2);
		n1.add(3);
		n1.add(4);

		AdjList.add(n1);

		ArrayList<Integer> n2 = new ArrayList<Integer>();
		n2.add(5);
		AdjList.add(n2);

		ArrayList<Integer> n3 = new ArrayList<Integer>();
		n3.add(6);
		n3.add(7);
		AdjList.add(n3);

		ArrayList<Integer> n4 = new ArrayList<Integer>();
		n4.add(8);
		AdjList.add(n4);

		ArrayList<Integer> n5 = new ArrayList<Integer>();
		n5.add(9);
		AdjList.add(n5);

		ArrayList<Integer> n6 = new ArrayList<Integer>();
		n6.add(10);
		AdjList.add(n6);

		ArrayList<Integer> n7 = new ArrayList<Integer>();
		AdjList.add(n7);

		ArrayList<Integer> n8 = new ArrayList<Integer>();
		AdjList.add(n8);

		ArrayList<Integer> n9 = new ArrayList<Integer>();
		AdjList.add(n9);

		ArrayList<Integer> n10 = new ArrayList<Integer>();
		AdjList.add(n10);*/


		// Dataset 3:

		int n = 4;

		ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();  // Adjacency List for storing the edges of the graph

		ArrayList<Integer> n1 = new ArrayList<Integer>();
		n1.add(2);
		n1.add(4);
		AdjList.add(n1);

		ArrayList<Integer> n2 = new ArrayList<Integer>();
		n2.add(1);
		n2.add(3);
		AdjList.add(n2);

		ArrayList<Integer> n3 = new ArrayList<Integer>();
		n3.add(1);
		AdjList.add(n3);

		ArrayList<Integer> n4 = new ArrayList<Integer>();
		n4.add(4);
		AdjList.add(n4);


		/*for(int i =0; i < 5; i++)
		{
			System.out.print("u: " + (i+1) + " " );
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp = AdjList.get(i);
			for(int a: temp)
			{
				System.out.print("v: "+ a + " ");
			}
			System.out.println("");
		}*/

		DFSTraversal graph = new DFSTraversal();

		graph.DFS(n, AdjList);

	}
}