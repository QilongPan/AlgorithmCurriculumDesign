package theThirdSubject;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import theSecondSubject.Test4;
/*
 * 分支界限法解决背包问题
 * 输入的数组为价值比从高到低
 */
class HeapNode {
	public double upbound; // 结点的价值上界
	public double value; // 结点所对应的价值
	public double weight; // 结点所相应的重量
	public int level; // 活节点在子集树中所处的层序号

	public HeapNode() {
		
	}
}

// 分支限界法实现01背包问题
public class Test2 {
	private double[] weights;	//重量
	private double[] values;	//价值
	private double maxWeight; // 背包的最大承重量
	private int n;		//记录返回上界的边界
	private double currentWeight; // 当前背包重量
	private double currentValue; // 当前背包价值
	private double bestValue; // 最优的背包价值
	private Stack<HeapNode> heap;		//节点
	private ArrayList<Integer> searchItems = new ArrayList<Integer>();

	public ArrayList<Integer> getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(ArrayList<Integer> searchItems) {
		this.searchItems = searchItems;
	}

	public Test2(double[] weights, double[] values , double maxWeight) {
		this.weights = weights;
		this.values = values;
		this.maxWeight = maxWeight;
		n = weights.length - 1;
		this.currentWeight = 0;
		this.currentValue = 0;
		this.bestValue = 0;
		this.heap = new Stack<HeapNode>();
	}

	// 求子树的最大上界
	private double maxBound(int t) {
		double left = maxWeight - currentWeight;
		double bound = currentValue;
		// 剩余容量和价值上界
		if(t < n)
			bound += (1.0*values[t] / weights[t]) * left;
		return bound;
	}

	// 将一个新的活结点插入到子集树和最大堆heap中
	private void addLiveNode(double upper, double cvalue, double cweight,
			int level) {
		HeapNode node = new HeapNode();
		node.upbound = upper;
		node.value = cvalue;
		node.weight = cweight;
		node.level = level;
		if (level <= n)
			heap.push(node);
	}

	// 利用分支限界法，返回最大价值bestValue
	private double knapsackByBranchAndBound() {
		int i = 0;
		double upbound = maxBound(i);	//上界
		while (true) // 非叶子结点
		{
			double wt = currentWeight + weights[i];
			if (wt <= maxWeight)// 左儿子结点为可行结点
			{
				if (currentValue + values[i] > bestValue){
					bestValue = currentValue + values[i];
					searchItems.add(i);
				}
					
				addLiveNode(upbound, currentValue + values[i], currentWeight + weights[i],
						i + 1);
			}
			upbound = maxBound(i + 1);
			addLiveNode(upbound, currentValue, currentWeight, i + 1);
			if (heap.empty())
				return bestValue;
			HeapNode node = heap.peek();
			heap.pop();
			currentWeight = node.weight;
			currentValue = node.value;
			upbound = node.upbound;
			i = node.level;
		}
	}

	public String getItemsByValues(double bestValue2){
		ArrayList<String> items = new ArrayList<String>();
		for(int i  = 0 ; i < weights.length ; i++){
			items = getItemsByItems(i);
			for(int j = 0 ; j < items.size() ;j++){
				String item = items.get(j);
				double valueCopy = 0;
				double weight = 0 ;
				for(int m = 0 ; m < item.length() ; m++){
					int index = Integer.parseInt(item.substring(m, m+1));
					weight += weights[index];
					valueCopy = valueCopy + values[index];
				}
				if(valueCopy == bestValue2 && weight <= maxWeight){
					return item;
				}
			}
		}
		return null;
	}
	
	public ArrayList<String> getItemsByItems(int n){	//截取几个出去
		String str ="";
		for(int i = 0 ; i < weights.length ; i++){
			str+=i;
		}
		return Test4.getAllSubstring(str, n);
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int flag = 1;
		while(flag == 1){
			System.out.println("请输入物品个数：");
			int itemsNumber = input.nextInt();
			double[] weights = new double[itemsNumber];
			double[] values = new double[itemsNumber];
			for(int i = 0 ; i < itemsNumber; i++){
				System.out.println("请输入第"+(i+1)+"个物品的重量");
				weights[i] = input.nextInt();
				System.out.println("请输入第"+(i+1)+"个物品的价值");
				values[i] = input.nextDouble();
			}
			System.out.println("请输入背包的最大承重量:");
			double maxWeight = input.nextDouble();
			Test2 test2 = new Test2(weights,values,maxWeight);
			double bestValue = test2.knapsackByBranchAndBound();
			System.out.println("背包能装下的最大价值为:"+bestValue);
			String itemStr = test2.getItemsByValues(bestValue);
			System.out.println("所装物品为：");
			for(int i = 0 ; i < itemStr.length() ; i++){
				System.out.print(itemStr.charAt(i)+"  ");
			}
			System.out.println();
			System.out.println("继续请输入1：");
			flag = input.nextInt();
		}
		
	}
}