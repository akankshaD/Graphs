import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Map;

public class Dijkstra{

	//************************************************** Heap Data Structure ******************************************************

	void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	int bottom_up_heapify(int[] a, int[] p, int i)
	{
		while((i >= 0) &&  (((i-1)/2) >= 0 ) && (p[(i-1)/2] > p[i]))
		{	
			swap(a, (i-1)/2, i);
			swap(p, (i-1)/2, i);
			i = (i-1)/2;
		}
		return i;

	}

	int top_down_heapify(int[] a,int[] p, int i, int last_index)
	{
		if(last_index > 0)
		{
			int l, r, min;
			while((2*i + 1)  < last_index)
			{
				l = 2*i+1;
				r = 2*i+2;

				if((r <= last_index) && (p[l] > p[r]))
					min = r;
				else
					min = l;

				if(p[i] > p[min])
				{
					swap(a, i, min);
					swap(p, i, min);
					i = min;
				}
				else
					break;

			}
		}

		return i;
		
	}	

	int addElement(int[] a, int[] p, int wt, int key, int last_index)
	{
		p[last_index] = wt;
		a[last_index] = key;
		int i = bottom_up_heapify(a, p, (last_index));
		return i;
	}

	int deleteMin(int[] a, int[] p, int last_index)
	{
		int data = a[0];
		a[0] = a[last_index-1];
		p[0] = p[last_index-1];
		last_index--;
		top_down_heapify(a, p, 0, last_index);
		return data;
	}

	int decreaseKey(int[] a, int[] p, int index, int value)
	{
		p[index] = value;
		int i = bottom_up_heapify(a, p, index);
		return i;
	}
	
	//********************************************************************************************************************************

	void Dijkstras(int n, ArrayList<HashMap<Integer, Integer>> edges)
	{
		int s = 1;
		int[] node = new int[n];
		int[] piFunc = new int[n];
		int[] status = new int[n];
		int[] heap = new int[n];
		int[] priority = new int[n];
		int[] vertex_pos = new int[n];

		Arrays.fill(vertex_pos, -1);
		Arrays.fill(status, 0);

		/*for(int i=0; i < n; i++)
			node[i] = i+1;*/

		node[0] = 1;

		int sizeHeap = 0;
		int last_index = 0;

		//heap[s-1] = 0;
		priority[s-1] = 0;
		status[s-1] = 1;

		for(int i =1; i< n; i++)
			priority[i] = Integer.MAX_VALUE;

		vertex_pos[s-1] = addElement(node, heap, 0, s, last_index);

		//System.out.println(vertex_pos[s-1]);

		/*System.out.println("Heap: ");
		for(int i =0; i< n; i++)
			System.out.print(heap[i] + " " );
		System.out.println("");*/

		last_index++;
		sizeHeap++;

		piFunc[s-1] = -1;

		while( sizeHeap != 0)
		{
			int u = deleteMin(node, heap, last_index);

			//System.out.println("u: " + u);

			/*System.out.println("Heap: ");
			for(int i =0; i< n; i++)
				System.out.print(heap[i] + " " );
			System.out.println("");*/

			
			vertex_pos[u-1] = -10;
			sizeHeap--;
			last_index--;
			status[u-1] = 2;;
			HashMap<Integer, Integer> neighbor = new HashMap<Integer, Integer>();
			neighbor = edges.get(u-1);

			Set set = neighbor.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext())
			{
				Map.Entry mentry = (Map.Entry)iterator.next();
				int v = (Integer)mentry.getKey();
				int w = (Integer)mentry.getValue();
				//System.out.print("v: " + v + " w(u, v): " + w + " ");
				if(status[v-1] == 0)
				{
					priority[v-1] = priority[u-1] + w;
					//priority[v-1] = priority[u-1] + w;
					//System.out.print("1st condition ");

					piFunc[v-1] = u;
					vertex_pos[v-1] = addElement(node, heap, priority[v-1], v, last_index);

					last_index++;
					sizeHeap++;

					

					/*System.out.println(vertex_pos[v-1]);

					System.out.println("Heap: ");
					for(int i =0; i< n; i++)
						System.out.print(heap[i] + " " );
					System.out.println("");*/

					status[v-1] = 1;
				}
				else if(status[v-1] == 1)
				{
					if(priority[v-1] > (priority[u-1] + w))
					{
						priority[v-1] = priority[u-1] + w;
						//System.out.print("2nd condition ");
						piFunc[v-1] = u;
						decreaseKey(node, heap, vertex_pos[v-1], priority[v-1]);
					}
				}
				//System.out.print(" p(" + (v) + "): " + + priority[v-1] + " ");
			}
			//System.out.println("");
		}

		System.out.println("");
		for(int i=0; i< n; i++)
		{
			System.out.println(piFunc[i] + ", " + (i+1) + " wt: " + priority[i]);
		}


	}

	public static void main(String[] args)
	{

		// DataSet 1

		/*int n = 5;
		ArrayList<HashMap<Integer, Integer>> AdjList = new ArrayList<HashMap<Integer, Integer>>();

		HashMap<Integer, Integer> e1 = new HashMap<Integer, Integer>();
		e1.put(2, 3);
		e1.put(4, 4);

		AdjList.add(e1);

		HashMap<Integer, Integer> e2 = new HashMap<Integer, Integer>();
		e2.put(3, 9);
		e2.put(4, 2);

		AdjList.add(e2);

		HashMap<Integer, Integer> e3 = new HashMap<Integer, Integer>();

		AdjList.add(e3);

		HashMap<Integer, Integer> e4 = new HashMap<Integer, Integer>();
		e4.put(5, 6);

		AdjList.add(e4);

		HashMap<Integer, Integer> e5 = new HashMap<Integer, Integer>();
		e5.put(2, 3);
		e5.put(3, 1);

		AdjList.add(e5);*/

		// DataSet 2

		int n = 7;
		ArrayList<HashMap<Integer, Integer>> AdjList = new ArrayList<HashMap<Integer, Integer>>();

		HashMap<Integer, Integer> e1 = new HashMap<Integer, Integer>();
		e1.put(2, 1);
		e1.put(4, 5);
		e1.put(5, 3);

		AdjList.add(e1);

		HashMap<Integer, Integer> e2 = new HashMap<Integer, Integer>();
		e2.put(3, 2);
		e2.put(4, 2);

		AdjList.add(e2);

		HashMap<Integer, Integer> e3 = new HashMap<Integer, Integer>();
		e3.put(7, 3);

		AdjList.add(e3);

		HashMap<Integer, Integer> e4 = new HashMap<Integer, Integer>();
		e4.put(3, 6);

		AdjList.add(e4);

		HashMap<Integer, Integer> e5 = new HashMap<Integer, Integer>();
		e5.put(6, 8);

		AdjList.add(e5);

		HashMap<Integer, Integer> e6 = new HashMap<Integer, Integer>();
		e6.put(4, 1);
		e6.put(7, 4);

		AdjList.add(e6);

		HashMap<Integer, Integer> e7 = new HashMap<Integer, Integer>();

		AdjList.add(e7);



		Dijkstra obj = new Dijkstra();
		obj.Dijkstras(n, AdjList);


	}
}