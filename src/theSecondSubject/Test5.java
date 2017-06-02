package theSecondSubject;

import java.util.ArrayList;
import java.util.Scanner;

public class Test5 {
	private int[][] scoreMatrix;
	private String gene1;
	private String gene2;
	private String newGene1;
	private String newGene2;
	
	public Test5(String gene1,String gene2){
		this.gene1 = gene1;
		this.gene2 = gene2;
		scoreMatrix = new int[gene1.length()+1][gene2.length()+1];
		fillScoreMatrix();
	}
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in) ;
		int flag = 1;
		while(flag == 1){
			System.out.println("请输入基因1: ");
			String gene1 = input.next();
			System.out.println("请输入基因2: ");
			String gene2 = input.next();
			Test5 test5 = new Test5(gene1,gene2);
			if(test5.isValidate(gene2) && test5.isValidate(gene1)){
				int similarDegree = test5.getSimilarDegreeOnTwoGene(gene1, gene2);
				test5.getNewGenes();
				System.out.println("两个基因的相似程度为: "+similarDegree);
				System.out.println("对齐方案:");
				System.out.println(test5.newGene1);
				System.out.println(test5.newGene2);
				System.out.println("分数矩阵为：");
				test5.printScoreMatrix();
				System.out.println("如果继续则输入1：,退出输入0");
				flag = input.nextInt();
			}
			else{
				System.out.println("基因序列不合法，请重新输入");
			}
		}
	}
	
	public int getSimilarDegreeOnTwoGene(String gene1 , String gene2){
		if(gene1.length() == 0 || gene2.length() == 0 ){
			return 0;
		}
		else{
			return scoreMatrix[scoreMatrix.length-1][scoreMatrix[0].length-1];
		}		
	}
	//判断基因序列是否合法
	public boolean isValidate(String gene){
		ArrayList<String> genes = getGenes();
		for(int i = 0;i < gene.length();i++) {
			if(!genes.contains(gene.substring(i,i+1))){
				return false;
			}
		}
		return true;
	}
	//得到核苷酸
	public ArrayList<String> getGenes(){
		ArrayList<String> genes = new ArrayList<String>();
		genes.add("A");
		genes.add("C");
		genes.add("G");
		genes.add("T");
		return genes;
	}
	//得到分值
	public int getScore(String nucleotide1 ,String nucleotide2){
		 if (nucleotide1.equals(nucleotide2)){
			 return 5;
		 }
         else if ((nucleotide1.equals("A") && nucleotide2.equals("C")) 
        		 || (nucleotide1.equals("C") && nucleotide2.equals("A"))){
        	 return -1;
         }
            
         else if ((nucleotide1.equals("A") && nucleotide2.equals("G")) 
        		 || (nucleotide1.equals("G") && nucleotide2.equals("A"))){
        	 return -2;
         }
            
         else if ((nucleotide1.equals("A") && nucleotide2.equals("T"))
        		 || (nucleotide1.equals("T") && nucleotide2.equals("A"))){
        	 return -1;
         }
            
         else if ((nucleotide1.equals("A") && nucleotide2.equals("_")) 
        		 || (nucleotide1.equals("_") && nucleotide2.equals("A"))){
        	 return -3;
         }
            
         else if ((nucleotide1.equals("C") && nucleotide2.equals("G")) 
        		 || (nucleotide1.equals("G") && nucleotide2.equals("C"))){
        	 return -3;
         }
            
         else if ((nucleotide1.equals("C") && nucleotide2.equals("T")) 
        		 || (nucleotide1.equals("T") && nucleotide2.equals("C"))){
        	 return -2;
         }
             
         else if ((nucleotide1.equals("C") && nucleotide2.equals("_")) 
        		 || (nucleotide1.equals("_") && nucleotide2.equals("C"))){
        	 return -4;
         }
             
         else if ((nucleotide1.equals("G") && nucleotide2.equals("T"))
        		 || (nucleotide1.equals("T") && nucleotide2.equals("G"))){
        	  return -2;
         }
           
         else if ((nucleotide1.equals("G") && nucleotide2.equals("_"))
        		 || (nucleotide1.equals("_") && nucleotide2.equals("G"))){
        	 return -2;
         }
            
         else if ((nucleotide1.equals("T") && nucleotide2.equals("_")) 
        		 || (nucleotide1.equals("_") && nucleotide2.equals("T"))){
        	   return -1;
         }
          
         return 0;
	}
	//填充分数矩阵
	public void fillScoreMatrix(){
		for (int i = 1; i < scoreMatrix.length; i++)
			scoreMatrix[i][0] = scoreMatrix[i - 1][0] + getScore(gene1.substring(i - 1, i), "_");
        for (int i = 1; i < scoreMatrix[0].length; i++)
        	scoreMatrix[0][i] = scoreMatrix[0][i - 1] + getScore("_", gene2.substring(i - 1, i));
        for (int i = 1; i < scoreMatrix.length; i++)
        {
            for (int j = 1; j < scoreMatrix[0].length; j++)
            {
                int score1 = 0, score2 = 0, score3 = 0;
                //情况1：DNA1和"_"搭配
                score1 = scoreMatrix[i - 1][j] + getScore(gene1.substring(i - 1, i), "_");
                //情况2：DNA1和DNA2搭配
                score2 = scoreMatrix[i - 1][j - 1] + getScore(gene1.substring(i - 1,i), gene2.substring(j - 1, j));
                //情况3："_"和DNA2搭配
                score3 = scoreMatrix[i][j - 1] + getScore("_" , gene2.substring(j - 1, j));
                scoreMatrix[i][j] = getMaxScore(score1, score2, score3);
            }
        }
	}
	
	//获取3个分值中最大的
    private int getMaxScore(int score1, int score2, int score3)
    {
        int max = score1;
        max = score2 > max ? score2 : max;
        max = score3 > max ? score3 : max;
        return max;
    }
    
    //得到变更后的基因
    public void getNewGenes()
    {
    	newGene1 = "";
    	newGene2 = "";
        int rowIndex = scoreMatrix.length - 1;//行
        int fieldIndex = scoreMatrix[0].length - 1;//列
        while (rowIndex != 0 || fieldIndex != 0)
        {
            if (rowIndex == 0)//当行等于0时，SDNA1已经匹配完成，SDNA2前面的剩余基因与空格匹配
            {
                for (int i = 0; i < fieldIndex; i++)
                	newGene1 = "_" + newGene1;//匹配后的SDNA1
                newGene2 = gene2.substring(0, fieldIndex) + newGene2;//匹配后的SDNA2
                return;
            }
            if (fieldIndex == 0)//列为SDNA2 同行一样
            {
                for (int i = 0; i < rowIndex; i++)
                	newGene2 = "_" + newGene2;
                newGene1 = gene1.substring(0, rowIndex) + newGene1;
                return;
            }

            int top = scoreMatrix[rowIndex - 1][fieldIndex] 
            		+getScore(gene1.substring(rowIndex - 1, rowIndex), "_");//DNA1的最后一个基因和空格匹配
            int left = scoreMatrix[rowIndex][fieldIndex - 1] 
            		+ getScore(gene2.substring(fieldIndex - 1, fieldIndex), "_");//DNA2的最后一个基因和空格匹配
            //DNA1的最后一个基因和DNA最后一个基因匹配
            int value = scoreMatrix[rowIndex][fieldIndex];
            if (value == top)
            {
            	newGene1 = gene1.substring(rowIndex - 1, rowIndex) + newGene1;
            	newGene2 = "_" + newGene2;
                rowIndex = rowIndex - 1;
            }
            else if (value == left)
            {
            	newGene1 = "_" + newGene1;
            	newGene2 = gene2.substring(fieldIndex - 1, fieldIndex) + newGene2;
                fieldIndex = fieldIndex - 1;
            }
            else
            {
            	newGene1 = gene1.substring(rowIndex - 1, rowIndex) + newGene1;
            	newGene2 = gene2.substring(fieldIndex - 1, fieldIndex) + newGene2;
                rowIndex = rowIndex - 1;
                fieldIndex = fieldIndex - 1;
            }
        }
    }
    //输出分数矩阵
    public void printScoreMatrix(){
    	int nullNumber = 4;
    	for(int i = 0 ; i < scoreMatrix.length ; i++){
    		for(int j = 0 ; j < scoreMatrix[i].length ; j++){
    			String str = scoreMatrix[i][j]+"";
    			System.out.print(str);
    			for(int m = 0 ; m < nullNumber - str.length() ; m++){
    				System.out.print(" ");
    			}
    		}
    		System.out.println();
    	}
    }
        
        
}
