import java.util.*;
public class array {
    public static void main(String[] args) {
        int[][] a = {{10,20,30},{40,50,60},{70,80,90}};
        for (int[] value : a) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(value[j] + " ");
            }
            System.out.println();
        }
        for (int i=0;i<a.length;i++)
        {
            for (int j=i;j<a.length;j++)
            {
                int temp=a[i][j];
                a[i][j]=a[j][i];
                a[j][i]=temp;
            }
        }
        for (int[] ints : a) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
