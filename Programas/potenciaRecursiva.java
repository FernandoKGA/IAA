public class potenciaRecursiva{
    public static int potRecur(int c, int n){
        if(n == 0) return 1;
        else{
            return c * potRecur(c, n-1);
        }
    }
    public static void main(String []args){
        int c = 2;
        int n = 4;
        int numero = potRecur(c, n);
        System.out.println(numero);
    }
}