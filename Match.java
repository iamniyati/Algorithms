import java.util.Scanner;

/**
 * Created by niyatishah on 2/10/16.
 */
public class Match {


    public static void main(String [] args){
        Match m = new Match();
        int elements;
        int [][] men;
        int [][] menFree;
        int [][] women;
        int [][] womenFree;

        m.takeInput( elements, men,  menFree, women, womenFree);
        m.print(elements, menFree,womenFree);
    }

    public static void takeInput(int elements, int[][] men, int[][] menFree, int[][] women, int[][] womenFree){
        Scanner scan        = new Scanner(System.in);
        elements = scan.nextInt();
        men = new int [elements][elements];
        menFree = new int[elements][1];
        women = new int[elements][elements];
        womenFree = new int[elements][1];

        for (int i =0;i<elements;i++){
            for ( int j =0;j<elements;j++ ){
                men[i][j] = scan.nextInt();
            }
            menFree[i][0]=men[i][0];
        }

        for (int i =0;i<elements;i++){
            for ( int j =0;j<elements;j++ ){
                women[i][j] = scan.nextInt();
            }
            womenFree[i][0]=men[i][0];
        }


    }


    public static void print(){
        for (int i =0;i<elements;i++){
           System.out.println("men"+ menFree[i][0]);
        }
        for (int i =0;i<elements;i++){
            System.out.println("women"+ womenFree[i][0]);
        }
    }
    public static void stablematching(){

    }
}
