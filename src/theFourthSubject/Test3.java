package theFourthSubject;

import java.util.Scanner;

public class Test3 {
	private final static int INF = 32767;
	public static void main(String[] args){
		TheLocationMap locationMap = new TheLocationMap();
		getLocationMap(locationMap);
		System.out.println(locationMap.getNodeNumber());
		floyed(locationMap);
	}

	public static void getLocationMap(TheLocationMap locationMap){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("�����뵥λ����: ");
		locationMap.setNodeNumber(scanner.nextInt());
		System.out.println("�����뵥λ���·������");
		locationMap.setEdgeNumber(scanner.nextInt());
		System.out.println("�����뵥λ����:");
		String[] nodeName = new String[locationMap.getNodeNumber()];
		for(int i = 0 ; i < locationMap.getNodeNumber() ; i++){
			System.out.println("�������"+i+"����λ����");
			nodeName[i] = scanner.next();
		}
		locationMap.setNodeName(nodeName);
		int[][] connectivity = new int[locationMap.getNodeNumber()][locationMap.getNodeNumber()];
		int[][] distance = new int[locationMap.getNodeNumber()][locationMap.getNodeNumber()];
		for(int i = 0 ; i < connectivity.length ; i++){
			for(int j = 0 ; j < connectivity[i].length ; j++){
				connectivity[i][j] = 0;
				distance[i][j] = 0;
			}
		}
		int[] frequency = new int[locationMap.getNodeNumber()];
		for(int i = 0 ; i < frequency.length ; i++){
			frequency[i] = 0 ;
		}
		
		for(int i = 0 ; i < locationMap.getEdgeNumber() ; i++){
			System.out.println("��������ͨ������λ��");
			System.out.println("��λ1��");
			int x = scanner.nextInt();
			System.out.println("��λ2��");
			int y = scanner.nextInt();
			connectivity[x][y] = 1;
			connectivity[y][x] = 1;
			System.out.println("����������ͨ��λ֮��ľ���:");
			distance[x][y] = scanner.nextInt();
			distance[y][x] = distance[x][y];
		}
		
		for(int k = 0 ; k < locationMap.getNodeNumber() ; k++){
			System.out.println("�������"+k+"����λȥ���е����Ƶ��");
			frequency[k] = scanner.nextInt();
		}
		for(int i = 0 ; i < locationMap.getNodeNumber() ; i++){	//�Ծ����Ƶ�ʵĳ˻���ΪȨֵ
			for(int j = 0 ; j < locationMap.getNodeNumber(); j++){
				distance[i][j]*=frequency[i];
				if(connectivity[i][j] == 0 && i!=j){
					distance[i][j] = INF;
				}
			}
		}
		locationMap.setConnectivity(connectivity);
		locationMap.setDistance(distance);
		locationMap.setFrequency(frequency);
	}
	
	public static String floyed(TheLocationMap locationMap){
		String str = "";
		// �洢����֮��ľ���
		int[][] a = 
				new int[locationMap.getNodeNumber()][locationMap.getNodeNumber()];
		//p[i][j]��ʾ��i��j�����·���� i�ĺ��
		int [][] path = 
				new int[locationMap.getNodeNumber()][locationMap.getNodeNumber()];
		
		int i ,j ,k ,pre;
		boolean[] count = new boolean[locationMap.getNodeNumber()];
		for(i = 0 ; i < locationMap.getNodeNumber() ; i++){
			for( j = 0 ; j < locationMap.getNodeNumber(); j++){
				a[i][j] = locationMap.getDistance()[i][j];
				path[i][j] = -1;
				count[i] = false;
			}
		}
		/*
		 * ��vΪ��㣬wΪ�յ㣬����k��Ϊv��w֮����м�㣬ȥ�ж�d[v][ w]��d[v][k] + d[k][w]�Ĵ�С��ϵ
		 * �����d[v][w] > d[v][k] + d[k][w]��˵���ҵ���v��w�ĸ���·���ˣ�
		 * ��ʱ����d[v][w]��ֵΪd[v][k] + d[k][w]��p[v][w]��ֵҲҪ��Ӧ�ĳ�p[v][k]��ֵ��
		 * ��Ϊ p[v][k]��ֵ��v��k���·����v�ĺ�̶��㣬��v��w������·����������v��k���·������ģ�
		 * ����������Ȼp[v][w]ҲҪָ��p[v][k]
		 */
		for(k = 0 ; k < locationMap.getNodeNumber() ; k++){	//�м�ڵ�
			for(i = 0 ; i < locationMap.getNodeNumber(); i++){	//��ʼ����
				for( j = 0 ; j < locationMap.getNodeNumber(); j++){//�յ㶥��
					if(a[i][j] >(a[i][k]+a[k][j])){
						a[i][j] = a[i][k]+a[k][j];
						path[i][j]=k;	//��K��Ϊ��̽ڵ�
					}
				}
			}
		}
		str += "Floyed�㷨������£�\n";
		System.out.println("Floyed�㷨������£�");
		for(i = 0 ; i < locationMap.getNodeNumber() ; i++){
			for(j = 0 ; j < locationMap.getNodeNumber() ; j++){
				if(i != j){
					str += ""+i+"->"+j+";\n";
					System.out.println(""+i+"->"+j+";");
					if(a[i][j] == INF){
						if(i != j){
							str+="������·��\n";
							System.out.println("������·��");
						}
					}
					else{
						str+="·������Ϊ��"+a[i][j]+"\n";
						System.out.println("·������Ϊ��"+a[i][j]);
						str+="·��Ϊ��"+i+"->\n";
						System.out.println("·��Ϊ��"+i+"->");
						pre = path[i][j];
						while(pre != -1){
							System.out.println(pre);
							pre = path[pre][j];
						}
						System.out.println(j);
					}
				}
			}
		}
		//����Ϊѡ���������Ź��̣�Ȼ��ȷַ��
		for( i = 0 ; i < locationMap.getNodeNumber() ; i++){
			for(j = 0 ; j < locationMap.getNodeNumber() ; j++){
				if(a[i][j] == INF){
					count[i] = false;
				}
				else{
					count[i] = true;
				}
			}
		}
		
		for(i = 0 ; i < locationMap.getNodeNumber() ; i++){
			if(count[i]){
				for( j = 0 ; j < locationMap.getNodeNumber() ; j++){
					if( i != j){
						a[i][i]+=a[j][i];
					}
				}
			}
			
		}
		
		k = 0 ;
		for(i = 0 ; i < locationMap.getNodeNumber() ; i++){
			if(count[i]){
				if(a[k][k] > a[i][i]){
					k = i;
				}
			}
		}
		str +="���е���ѵ�ַΪ��"+locationMap.getNodeName()[k];
		System.out.println("���е���ѵ�ַΪ��"+locationMap.getNodeName()[k]);
	//	return locationMap.getNodeName()[k];
		return str;
	}
}
