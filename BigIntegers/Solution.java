import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;

public class Solution {

    public static BigInteger factorial(BigInteger n){
        if (n.compareTo(BigInteger.ONE) == 0)
            return BigInteger.ONE;
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input;
        try{
            input = Integer.parseInt(br.readLine());
        }
        catch(NumberFormatException e){
            System.out.println("Program expects a single integer.");
			return;
        }
        BigInteger biInput = BigInteger.valueOf(input);
        BigInteger ans = factorial(biInput);
        System.out.println(ans);
    }
}