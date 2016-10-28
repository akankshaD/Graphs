import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class BFSTraversal{

	int capacity = 16;    // default capacity
	int size = 0;
	int[] q = new int[capacity];    // Circular Queue
	int front =0, rear = 0; 

	int isQueueEmpty()
	{
		if(size == 0)
			return 1;
		else return 0;
	}

	void enqueue(int x)
	{	
		if(size == capacity)
			System.out.println("Queue Overflow");
		else
		{
			size++;
			q[rear] = x;
			rear = (rear+1)%capacity;
		}		
	}

	int dequeue()
	{
		if(size == 0)
		{
			System.out.println("Queue Underflow");
			return Integer.MIN_VALUE;
		}
		else
		{
			size--;
			int data = q[front%capacity];
			q[front] = Integer.MIN_VALUE;
			front = (front + 1)%capacity;
			return data;
		}
	}

	void BFS(int n, ArrayList<ArrayList<Integer>> edges)
	{
		int[] status = new int[n];    // status of nodes:  1 - explored

		int[] piFunc = new int[n];

		Arrays.fill(status, 0);     // initialising with false
		int s = 1;   // source vertex;
		enqueue(s);
		status[s-1] = 1;
		
		/*for(int i=0; i < q.length; i++)
		{
			System.out.print(q[i] + " ");
		}
		System.out.println("");*/

		piFunc[s-1] = -1;  // Source has no root

		while( isQueueEmpty() == 0 )
		{
			int u = dequeue();
			System.out.print(u + " ");
			
			//status[u-1] = 1;  // deleting from data structure
			ArrayList<Integer> v = new ArrayList<Integer>();
			v = edges.get(u-1); 
			for(int x : v)
			{
				//System.out.print("v: " + x + " ");
				if(status[x-1] == 0)
				{
					enqueue(x);
					status[x-1] = 1;
					
					piFunc[x-1] = u;
				}
			}
			//System.out.println("");

			/*for(int i=0; i < q.length; i++)
			{
				System.out.print(q[i] + " ");
			}
			System.out.println("");*/

		}

		System.out.println("");

		for(int i=0; i < n; i++)
		{
			System.out.println( " pi(v): "+ piFunc[i] + " v: " + (i+1) );
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
/*		int n = 10;                 // Number of nodes in the graph

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
		AdjList.add(n10);

*/
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

		BFSTraversal graph = new BFSTraversal();
		graph.BFS(n, AdjList);



	}
}