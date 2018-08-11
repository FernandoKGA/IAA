public class progAritRecursiva{
    public static int progArit(int azero, int razao, int i){
        if(i == 1) return azero;
        else{
            return razao + progArit(azero, razao, i-1);
        }
    }
    public static void main(String []args){
        int azero = 5;
        int razao = 6;
        int i = 7;
        int ai = progArit(azero, razao, i);
        System.out.println(ai);
    }
}