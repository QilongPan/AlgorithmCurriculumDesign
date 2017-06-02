package theFourthSubject;

import java.util.ArrayList;

public class TheLocationMap {
	private final static int INF = 32767;
	private int number = 4;
	private String[] nodeName;	//单位名称
	private int[][] connectivity;	//各单位之间的相通情况
	private int[][] distance; //各单位之间的距离
	private int[] frequency; //各单位去超市的频率
	private int nodeNumber;	//顶点数
	private int edgeNumber;	//边数
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String[] getNodeName() {
		return nodeName;
	}
	public void setNodeName(String[] nodeName) {
		this.nodeName = nodeName;
	}
	public int[][] getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(int[][] connectivity) {
		this.connectivity = connectivity;
	}
	public int[][] getDistance() {
		return distance;
	}
	public void setDistance(int[][] distance) {
		this.distance = distance;
	}
	public int[] getFrequency() {
		return frequency;
	}
	public void setFrequency(int[] frequency) {
		this.frequency = frequency;
	}
	public int getNodeNumber() {
		return nodeNumber;
	}
	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	public int getEdgeNumber() {
		return edgeNumber;
	}
	public void setEdgeNumber(int edgeNumber) {
		this.edgeNumber = edgeNumber;
	}
	
	
	public void getData(ArrayList<RoomPane> list) {
		
		nodeName = new String[list.size()];
		nodeNumber = list.size();
		distance = new int[list.size()][list.size()];
		frequency = new int[list.size()];
		connectivity = new int[list.size()][list.size()];
		
		for(int i = 0 ; i < connectivity.length ; i++){
			for(int j = 0 ; j < connectivity[i].length ; j++){
				connectivity[i][j] = 0;
				distance[i][j] = 0;
			}
		}
		
		for(int i = 0 ; i < frequency.length ; i++){
			frequency[i] = 0 ;
		}
		
		for (int i = 0; i < list.size(); i++) {
			nodeName[i] = list.get(i).apane.name;	//单位
			frequency[i] = (int) list.get(i).apane.freq;
		}		
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).apane.model.contains(nodeName[j])) {
					int index = list.get(i).apane.model.indexOf(nodeName[j]);
					distance[i][j] = (int) list.get(i).apane.lenmodel
							.getElementAt(index);
					distance[j][i]= distance[i][j];
					connectivity[i][j] = 1;
					connectivity[j][i] = 1;
				} 
			}
		}
		
		for(int i = 0 ; i < nodeNumber ; i++){	//以距离和频率的乘积作为权值
			for(int j = 0 ; j < nodeNumber; j++){
				distance[i][j] *= frequency[i];
				if(connectivity[i][j] == 0 && i!=j){
					distance[i][j] = INF;
				}
			}
		}
		
		for(int i = 0 ; i < nodeNumber ; i++){
			for(int j = 0 ; j < nodeNumber; j++){
				System.out.print(distance[i][j] +" ");
			}
			System.out.println();
		}
		
	}
	
	
	

}
