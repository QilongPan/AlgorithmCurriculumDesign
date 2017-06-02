package basicSubject;

import java.util.Scanner;

/*
 * 备忘录方法完成二项式公式计算，即Ckn=Ck−1n−1+Ckn−1.
 *  公式解释：为了从n个不同元素中抓取k个元素(Ckn)，
 *  可以这样考虑，如果第一个元素一定在结果中，
 *  那么就需要从剩下的n-1个元素中抓取k-1个元素(Ck−1n−1)；
 *  如果第一个元素不在结果中，
 *  就需要从剩下的n-1个元素中抓取k个元素(Ckn−1)。
 */
public class Test1 {

	private static int[][] values;

	public Test1(int number) {
		values = new int[number + 1][number + 1];
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = 0; // 元素个数
		int k = 0; // 抓取的元素个数
		int flag = 1;
		while (flag == 1) {
			System.out.println("Please enter n : ");
			n = input.nextInt();
			System.out.println("Please enter k : ");
			k = input.nextInt();
			// int value = getValue(n,k); //杨辉三角
			Test1 test1 = new Test1(n);
			if (n >= k) {
				int value = test1.computingBinomialByMemo(n, k); // 备忘录
				test1.printRecord();
				System.out.println("进行二项式计算后的结果为：" + value);
				System.out.println("如果要继续则按1：");
				flag = input.nextInt();
			} else {
				System.out.println("由于n大于k，请重新输入：");
			}

		}

	}

	// 进行二项式计算
	public static int getValue(int n, int k) {
		int[][] arr = new int[n + 1][n + 1];
		boolean flag = true;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (i == j || j == 0) {
					arr[i][j] = 1;
				} else {
					arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
				}
				if (i == n && j == k) {
					flag = false;
					break;
				}
			}
			if (flag == false) {
				break;
			}
		}
		return arr[n][k];
	}

	// 备忘录方法进行二项式计算
	public int computingBinomialByMemo(int n, int k) {
		if (n == k || k == 0) {
			recordValue(n, k, 1);
			return 1;
		}
		int record = getRecord(n, k);
		if (record > 0) {
			return record;
		} else {
			int value = computingBinomialByMemo(n - 1, k)
					+ computingBinomialByMemo(n - 1, k - 1);
			recordValue(n, k, value);
			return value;
		}
	}

	// 读取备忘录数据
	public void recordValue(int n, int k, int value) {
		values[n][k] = value;
	}

	// 存数据至备忘录
	public int getRecord(int n, int k) {
		return values[n][k];
	}

	public void printRecord() {
		int nullNumber = 4;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				System.out.print(values[i][j]);
				String str = values[i][j] + "";
				for (int m = 0; m < nullNumber - str.length(); m++) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
