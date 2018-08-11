public class subProblemasIterativo{
    public static int soma(int[] a){
        int b = 0;
        for(int k=0;k<a.length;k++){
            b += a[k];
        }
        return b;
    }
    public static int menor(int[] a){
        int c = a[0];
        for(int k=0;k<a.length;k++){
            if(a[k]<c){
                c = a[k];
            }
        }
        return c;
    }
    public static int valor(int[] a, int x){
        int d = 0;
        for(int k=0;k<a.length;k++){
            if(a[k] == x){
                d = a[k];
            }
        }
        return d;
    }
    public static int ocorrencias(int[] a, int z){
        int oc = 0;
        for(int k=0;k<a.length;k++){
            if(a[k] <= z){
                oc++;
            }
        }
        return oc;
    }
    public static void iguais(int[] a){
        int v = 1;
        if(v != a.length){
            for(int k=0;k<a.length-1;k++){
                if(a[k] == a[k+1]) v++;
                else{
                    System.out.println("Nao sao iguais.");
                    break;
                }
            }
        }
        if(v == a.length) System.out.println("Sao iguais.");
    }
    public static void imprimeFrontEnd(int[] a){
        for(int k=0;k<a.length;k++){
            System.out.print(a[k]+" ");
        }
        System.out.println();
    }
    public static void imprimeEndFront(int[] a){
        for(int k=a.length-1;k>=0;k--){
            System.out.print(a[k]+" ");
        }
        System.out.println();
    }
    public static void main(String []args){
        int[] a = {13, 14, 5, 6, 80, 12, 5};
        int b = soma(a);
        System.out.println("Soma: "+b);
        int c = menor(a);
        System.out.println("Menor: "+c);
        int x = 80;
        int d = valor(a, x);
        System.out.println("Valor pedido: "+x+" Valor achado: "+d);
        int z = 8;
        int e = ocorrencias(a, z);
        System.out.println("Ocorrencias: "+e);
        iguais(a);
        imprimeFrontEnd(a);
        imprimeEndFront(a);
    }
}