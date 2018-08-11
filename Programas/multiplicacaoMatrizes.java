public class multiplicacaoMatrizes{
    public static int[][] multiplica(int[][] a, int[][] b){
        //Inicia as variaveis para poder criar a matriz.
        int m = a.length;
        int n = b[0].length;
        int i = 0;
        int p = 0;
        int[][] c = new int[m][n];
        m = 0;
        n = 0;
        //Inicia a operacao de soma e multiplicacao
        while(m < (c.length-1)){
            while(n < (c[0].length-1)){
                for(int j=0;j<b.length-1;j++){
                    c[m][n] += a[i][j] * b[j][p];
                }
                p++;
                n++;
            }
            p = 0;
            n = 0;
            i++;
            m++;
        }
        return c;
    }
    public static void main(String []args){
        int[][] a = {{1,3,4,5},{6,7,8,9},{10,11,12,13},{5,0,12,6}};
        int[][] b = {{0,2,8,3},{4,6,9,1},{6,43,2,3},{32,4,0,1}};
        int[][] c = multiplica(a, b);
        for(int i=0;i<c.length-1;i++){
            for(int j=0;j<c[0].length-1;j++){
                System.out.print(c[i][j]+" ");
            }
            System.out.println();
        }
    }
}