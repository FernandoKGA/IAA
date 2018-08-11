public class ex1_lista1{
	public static void soma(int[] a, int x){
        int y;
		for(int i=0;i<=(a.length-1);i++){
            for(int j=0;j<=(a.length-1);j++){
                y = a[i]+a[j];
                if(y == x){
                    System.out.println("Pode-se somar o indice "+i+" e o "+j+" para se obter o resultado.");
                }
            }
        }
	}
	public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int x = 24;
		soma(a,x);
	}
}