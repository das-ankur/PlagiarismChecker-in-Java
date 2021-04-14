import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class PlagiarismChecker {
    static void printLCSubStr(String X, String Y, int m, int n)
    {
        int[][] LCSuff = new int[m + 1][n + 1];
        int len = 0;
        int row = 0, col = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    LCSuff[i][j] = 0;
 
                else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    LCSuff[i][j] = LCSuff[i - 1][j - 1] + 1;
                    if (len < LCSuff[i][j]) {
                        len = LCSuff[i][j];
                        row = i;
                        col = j;
                    }
                }
                else
                    LCSuff[i][j] = 0;
            }
        }
        if (len == 0) {
            System.out.println("No Common Substring");
            return;
        }
        String resultStr = "";
        while (LCSuff[row][col] != 0) {
            resultStr = X.charAt(row - 1) + resultStr;
            --len;
            row--;
            col--;
        }
        if(resultStr.charAt(resultStr.length()-1)!=' '||resultStr.charAt(resultStr.length()-1)!=';' || resultStr.charAt(resultStr.length()-1)!=',' ||
        resultStr.charAt(resultStr.length()-1)!=')' || resultStr.charAt(resultStr.length()-1)!='}' || resultStr.charAt(resultStr.length()-1)!=']') {
            int idx=resultStr.lastIndexOf(" ");
            resultStr=resultStr.substring(0, idx+1);
        }
        int index=X.indexOf(resultStr);
        int line=1;
        int tcount=1;
        for(int i=0;i<index;i++) {
            if(X.charAt(i)=='\n') {
                line++;
            }
        }
        int l=-1;
        for(int i=X.indexOf(resultStr);X.charAt(i)!='\n';i--) {
            if(X.charAt(i)==' ')
                tcount++;
        }
        //System.out.println(resultStr);
        int  token=0;
        for(int i=0;i<resultStr.length();i++) {
            if(resultStr.charAt(i)==' ' ||  resultStr.charAt(i)=='\n')
                token++;
        }
        token++;
        System.out.println("**** Plagiarism Report ****");
        System.out.println("Duplication starts on line "+line+", token "+tcount+", for "+token+" consecutive tokens.");
    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of lines in file1 and file2: ");
        String line=br.readLine();
        String input[]=line.split(" ");
        int a=Integer.parseInt(input[0]);
        int b=Integer.parseInt(input[1]);
        String temp;
        String X=new String();
        String Y=new String();
        System.out.println("Enter the content of file 1.");
        for(int  i=0;i<a;i++) {
            temp=br.readLine();
            X+=temp+"\n";
        }
        System.out.println("Enter the content of file 2.");
        for(int  i=0;i<b;i++) {
            temp=br.readLine();
            Y+=temp+"\n";
        }
        int m = X.length();
        int n = Y.length();
        printLCSubStr(X, Y, m, n);
    }
}