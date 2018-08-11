public class subProblemasRecursivoModoUm{
    public static int somaRecur(int[] a, int x){
        if(x == a.length-1) return a[x];
        else    return a[x] + somaRecur(a, x+1);
    }
    public static int menorRecur(int[] a){
        int loc = 0;
        if()
    }
    public static void main(String []args){
        int[] a = {13, 14, 5, 6, 80, 12, 5};
        int b = somaRecur(a, 0);
        System.out.println("Soma: "+b);
        int c = menorRecur(a)
    }
}