import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {   
    public static long calculateMaxScore(int numberOfBlocks, long[] valueOfBlocks){
        long[] myBestResult = new long[numberOfBlocks];
        long[] friendBestResult = new long[numberOfBlocks];
        
        // The last four best choices can be determined at the beginning to reduce for loop complexity
        myBestResult[numberOfBlocks - 1] = valueOfBlocks[numberOfBlocks - 1];
        friendBestResult[numberOfBlocks - 1] = 0;
        if (numberOfBlocks - 1 > 0){
            myBestResult[numberOfBlocks - 2] = valueOfBlocks[numberOfBlocks - 2] + valueOfBlocks[numberOfBlocks - 1];
            friendBestResult[numberOfBlocks - 2] = 0;
        }
        if (numberOfBlocks - 2 > 0){
            myBestResult[numberOfBlocks - 3] = valueOfBlocks[numberOfBlocks - 3] + valueOfBlocks[numberOfBlocks - 2] + valueOfBlocks[numberOfBlocks - 1];
            friendBestResult[numberOfBlocks - 3] = 0;
        }
        if (numberOfBlocks - 3 > 0){
            myBestResult[numberOfBlocks - 4] = valueOfBlocks[numberOfBlocks - 4] + valueOfBlocks[numberOfBlocks - 3] + valueOfBlocks[numberOfBlocks - 2];
            friendBestResult[numberOfBlocks - 4] = Math.min(myBestResult[numberOfBlocks - 3], Math.min(myBestResult[numberOfBlocks - 2], myBestResult[numberOfBlocks - 1]));
        }
        
        // Choose the best result for me and friend chooses the worst result for me
        for (int i = numberOfBlocks - 5; i >= 0; i--){
            long maxChoiceOne = valueOfBlocks[i] + Math.max(friendBestResult[i + 1], Math.max(friendBestResult[i + 2], friendBestResult[i + 3]));
            long maxChoiceTwo = valueOfBlocks[i] + valueOfBlocks[i+1] + Math.max(friendBestResult[i+2], friendBestResult[i+3]);
            long maxChoiceThree = valueOfBlocks[i] + valueOfBlocks[i+1] + valueOfBlocks[i+2] + friendBestResult[i+3];
            myBestResult[i] = Math.max(maxChoiceOne, Math.max(maxChoiceTwo, maxChoiceThree));
            friendBestResult[i] = Math.min(myBestResult[i + 1], Math.min(myBestResult[i + 2], myBestResult[i + 3]));
        }
        
        return myBestResult[0];
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTests;
        int[] numberOfBlocks;
        long[][] valuesArr;
        try{
            numberOfTests = Integer.parseInt(br.readLine());
            numberOfBlocks = new int[numberOfTests];
            valuesArr = new long[numberOfTests][];
            for (int i = 0; i < numberOfTests * 2; i++){
                int iDiv2 = i / 2;
                if (i % 2 == 0){
                    numberOfBlocks[iDiv2] = Integer.parseInt(br.readLine());
                }
                else{
                    String[] values = br.readLine().split(" ");
                    valuesArr[iDiv2] = new long[values.length];
                    for (int j = 0; j < values.length; j++){
                        valuesArr[iDiv2][j] = Integer.parseInt(values[j]);
                    }
                }
            }
        }
        catch(NumberFormatException e){
            System.out.println("Expect Integers as Input");
            return;
        }
        
        for (int i = 0; i < numberOfTests; i++){
            long ans = calculateMaxScore(numberOfBlocks[i], valuesArr[i]);
            System.out.println(ans);
        }
    }
}