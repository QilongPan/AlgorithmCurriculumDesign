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
		System.out.println("请输入单位个数: ");
		locationMap.setNodeNumber(scanner.nextInt());
		System.out.println("请输入单位间的路径数：");
		locationMap.setEdgeNumber(scanner.nextInt());
		System.out.println("请输入单位名称:");
		String[] nodeName = new String[locationMap.getNodeNumber()];
		for(int i = 0 ; i < locationMap.getNodeNumber() ; i++){
			System.out.println("请输入第"+i+"个单位名称");
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
			System.out.println("请输入相通的两单位：");
			System.out.println("单位1：");
			int x = scanner.nextInt();
			System.out.println("单位2：");
			int y = scanner.nextInt();
			connectivity[x][y] = 1;
			connectivity[y][x] = 1;
			System.out.println("请输入两相通单位之间的距离:");
			distance[x][y] = scanner.nextInt();
			distance[y][x] = distance[x][y];
		}
		
		for(int k = 0 ; k < locationMap.getNodeNumber() ; k++){
			System.out.println("请输入第"+k+"个单位去超市的相对频率");
			frequency[k] = scanner.nextInt();
		}
		for(int i = 0 ; i < locationMap.getNodeNumber() ; i++){	//以距离和频率的乘积作为权值
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
		// 存储两地之间的距离
		int[][] a = 
				new int[locationMap.getNodeNumber()][locationMap.getNodeNumber()];
		//p[i][j]表示从i到j的最短路径上 i的后继
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
		 * 以v为起点，w为终点，再以k作为v和w之间的中间点，去判断d[v][ w]和d[v][k] + d[k][w]的大小关系
		 * ，如果d[v][w] > d[v][k] + d[k][w]，说明找到从v→w的更短路径了，
		 * 此时更改d[v][w]的值为d[v][k] + d[k][w]。p[v][w]的值也要相应改成p[v][k]的值，
		 * 因为 p[v][k]的值是v→k最短路径上v的后继顶点，而v→w这段最短路径是连接在v→k这段路径后面的，
		 * 所以令所当然p[v][w]也要指向p[v][k]
		 */
		for(k = 0 ; k < locationMap.getNodeNumber() ; k++){	//中间节点
			for(i = 0 ; i < locationMap.getNodeNumber(); i++){	//起始顶点
				for( j = 0 ; j < locationMap.getNodeNumber(); j++){//终点顶点
					if(a[i][j] >(a[i][k]+a[k][j])){
						a[i][j] = a[i][k]+a[k][j];
						path[i][j]=k;	//将K设为后继节点
					}
				}
			}
		}
		str += "Floyed算法求解如下：\n";
		System.out.println("Floyed算法求解如下：");
		for(i = 0 ; i < locationMap.getNodeNumber() ; i++){
			for(j = 0 ; j < locationMap.getNodeNumber() ; j++){
				if(i != j){
					str += ""+i+"->"+j+";\n";
					System.out.println(""+i+"->"+j+";");
					if(a[i][j] == INF){
						if(i != j){
							str+="不存在路径\n";
							System.out.println("不存在路径");
						}
					}
					else{
						str+="路径长度为："+a[i][j]+"\n";
						System.out.println("路径长度为："+a[i][j]);
						str+="路径为："+i+"->\n";
						System.out.println("路径为："+i+"->");
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
		//以下为选择总体最优过程，然后确址；
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
		str +="超市的最佳地址为："+locationMap.getNodeName()[k];
		System.out.println("超市的最佳地址为："+locationMap.getNodeName()[k]);
	//	return locationMap.getNodeName()[k];
		return str;
	}
}
