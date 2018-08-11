public class fatorialRecursivo{
    public static int fat(int x){
        if(x == 1) return 1;
        else{
            return x * fat(x-1);
        }
    }
    public static void main(String []args){
        int x = 5;
        int k = fat(x);
        System.out.println(k);
    }
}