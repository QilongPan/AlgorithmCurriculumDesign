package theSecondSubject;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * ���һ�������д������Һʹ����������һ�������֮Ϊ���ġ�
 * ���磬����ACGTGTCAAAATCG�кܶ���������У�
 * ����ACGCA��AAAA�������һ���㷨�������Ļ���������
 */
public class Test4 {
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int flag = 1;
		while(flag == 1){
			System.out.println("�������ַ���: ");
			String testStr = input.next();
			printBackToTheSequenceOfFa(getBackToTheSequenceOfFa(testStr));
			System.out.println("����������1��");
			flag = input.nextInt();
		}
		
	}
	
	public static ArrayList<String> getBackToTheSequenceOfFa(String str){
		ArrayList<String> sequences = new ArrayList<String>();
		ArrayList<String> subLists = new ArrayList<String>();
		//i������ַ�������
		for(int i = str.length() ; i > 0 ; i--){
			boolean flag = false;
			subLists = getAllSubstring(str, str.length()-i);
			for(int m = 0 ; m < subLists.size() ; m++){
				String subStr = subLists.get(m);
				if(isBackToTheSequenceOfFa(subStr)){
					sequences.add(subStr);
					flag = true;
				}
			}
			if(flag){
				break;
			}
		}
		return sequences;
	}
	//�õ���ȡָ�����ȵ��ַ���
	public static ArrayList<String> getAllSubstring(String str,int length){
		ArrayList<String> lists = new ArrayList<String>();
		ArrayList<String> returnLists = new ArrayList<String>();
		lists.add(str);
		int time = 0;	//�ѽ�ȡ�ַ��ĸ���
		while(time < length){
			for(int n = 0 ; n < lists.size() ; n++){
				String tempStr = lists.get(n);	//�õ��ַ���
				for(int i = 0 ; i < tempStr.length() ; i++){
					returnLists.add(tempStr.substring(0,i)+tempStr.substring(i+1));
				}
			}
			time++;
			if(time != length ){
				lists.clear();
				lists = changList(lists,returnLists);
				returnLists.clear();
			}
		}
		//����ȡ����Ϊ0 ��������whileѭ��
		if(length == 0){
			returnLists.add(str);
		}
		returnLists = clearReapt(returnLists);
		
		return returnLists;
	}
	
	//ȥ��lists���ظ���Ԫ��
	public static ArrayList<String> clearReapt(ArrayList<String> lists){
		ArrayList<String> tempList = new ArrayList<String>();
		for(int i = 0 ; i < lists.size() ; i++){
			if(!tempList.contains(lists.get(i)))
				tempList.add(lists.get(i));
		}
		return tempList;
	}
	//��lists�����ݱ��returnLists��
	public static ArrayList<String> changList(ArrayList<String> lists,ArrayList<String> returnLists){
		for(int i = 0 ; i < returnLists.size() ; i++){
			lists.add(returnLists.get(i));
		}
		return lists;
	}
	//�ж��Ƿ��ǻ���
	public static boolean isBackToTheSequenceOfFa(String str){
		String reserveStr = "";
		for(int i = str.length()-1 ; i >= 0 ; i--){
			reserveStr+=str.charAt(i);
		}
		return reserveStr.equals(str);
	}

	//��������������
	public static void printBackToTheSequenceOfFa(ArrayList<String> sequences){
		System.out.println("��Ļ���������Ϊ��");
		for(String str:sequences){
			System.out.println(str);
		}
	}
}
