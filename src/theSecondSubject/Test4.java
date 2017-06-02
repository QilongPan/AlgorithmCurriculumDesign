package theSecondSubject;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 如果一个子序列从左向右和从右向左读都一样，则称之为回文。
 * 例如，序列ACGTGTCAAAATCG有很多回文子序列，
 * 比如ACGCA和AAAA。请给出一个算法，求出最长的回文子序列
 */
public class Test4 {
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int flag = 1;
		while(flag == 1){
			System.out.println("请输入字符串: ");
			String testStr = input.next();
			printBackToTheSequenceOfFa(getBackToTheSequenceOfFa(testStr));
			System.out.println("继续请输入1：");
			flag = input.nextInt();
		}
		
	}
	
	public static ArrayList<String> getBackToTheSequenceOfFa(String str){
		ArrayList<String> sequences = new ArrayList<String>();
		ArrayList<String> subLists = new ArrayList<String>();
		//i代表最长字符串长度
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
	//得到截取指定长度的字符串
	public static ArrayList<String> getAllSubstring(String str,int length){
		ArrayList<String> lists = new ArrayList<String>();
		ArrayList<String> returnLists = new ArrayList<String>();
		lists.add(str);
		int time = 0;	//已截取字符的个数
		while(time < length){
			for(int n = 0 ; n < lists.size() ; n++){
				String tempStr = lists.get(n);	//得到字符串
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
		//当截取长度为0 ，不进入while循环
		if(length == 0){
			returnLists.add(str);
		}
		returnLists = clearReapt(returnLists);
		
		return returnLists;
	}
	
	//去掉lists中重复的元素
	public static ArrayList<String> clearReapt(ArrayList<String> lists){
		ArrayList<String> tempList = new ArrayList<String>();
		for(int i = 0 ; i < lists.size() ; i++){
			if(!tempList.contains(lists.get(i)))
				tempList.add(lists.get(i));
		}
		return tempList;
	}
	//将lists的内容变成returnLists的
	public static ArrayList<String> changList(ArrayList<String> lists,ArrayList<String> returnLists){
		for(int i = 0 ; i < returnLists.size() ; i++){
			lists.add(returnLists.get(i));
		}
		return lists;
	}
	//判断是否是回文
	public static boolean isBackToTheSequenceOfFa(String str){
		String reserveStr = "";
		for(int i = str.length()-1 ; i >= 0 ; i--){
			reserveStr+=str.charAt(i);
		}
		return reserveStr.equals(str);
	}

	//输出最长回文子序列
	public static void printBackToTheSequenceOfFa(ArrayList<String> sequences){
		System.out.println("最长的回文子序列为：");
		for(String str:sequences){
			System.out.println(str);
		}
	}
}
