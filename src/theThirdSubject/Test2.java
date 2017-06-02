package theThirdSubject;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import theSecondSubject.Test4;
/*
 * ��֧���޷������������
 * ���������Ϊ��ֵ�ȴӸߵ���
 */
class HeapNode {
	public double upbound; // ���ļ�ֵ�Ͻ�
	public double value; // �������Ӧ�ļ�ֵ
	public double weight; // �������Ӧ������
	public int level; // ��ڵ����Ӽ����������Ĳ����

	public HeapNode() {
		
	}
}

// ��֧�޽編ʵ��01��������
public class Test2 {
	private double[] weights;	//����
	private double[] values;	//��ֵ
	private double maxWeight; // ��������������
	private int n;		//��¼�����Ͻ�ı߽�
	private double currentWeight; // ��ǰ��������
	private double currentValue; // ��ǰ������ֵ
	private double bestValue; // ���ŵı�����ֵ
	private Stack<HeapNode> heap;		//�ڵ�
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

	// ������������Ͻ�
	private double maxBound(int t) {
		double left = maxWeight - currentWeight;
		double bound = currentValue;
		// ʣ�������ͼ�ֵ�Ͻ�
		if(t < n)
			bound += (1.0*values[t] / weights[t]) * left;
		return bound;
	}

	// ��һ���µĻ�����뵽�Ӽ���������heap��
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

	// ���÷�֧�޽編����������ֵbestValue
	private double knapsackByBranchAndBound() {
		int i = 0;
		double upbound = maxBound(i);	//�Ͻ�
		while (true) // ��Ҷ�ӽ��
		{
			double wt = currentWeight + weights[i];
			if (wt <= maxWeight)// ����ӽ��Ϊ���н��
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
	
	public ArrayList<String> getItemsByItems(int n){	//��ȡ������ȥ
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
			System.out.println("��������Ʒ������");
			int itemsNumber = input.nextInt();
			double[] weights = new double[itemsNumber];
			double[] values = new double[itemsNumber];
			for(int i = 0 ; i < itemsNumber; i++){
				System.out.println("�������"+(i+1)+"����Ʒ������");
				weights[i] = input.nextInt();
				System.out.println("�������"+(i+1)+"����Ʒ�ļ�ֵ");
				values[i] = input.nextDouble();
			}
			System.out.println("�����뱳������������:");
			double maxWeight = input.nextDouble();
			Test2 test2 = new Test2(weights,values,maxWeight);
			double bestValue = test2.knapsackByBranchAndBound();
			System.out.println("������װ�µ�����ֵΪ:"+bestValue);
			String itemStr = test2.getItemsByValues(bestValue);
			System.out.println("��װ��ƷΪ��");
			for(int i = 0 ; i < itemStr.length() ; i++){
				System.out.print(itemStr.charAt(i)+"  ");
			}
			System.out.println();
			System.out.println("����������1��");
			flag = input.nextInt();
		}
		
	}
}