import java.util.*;

public class Main {
    public static void main(String[] args) {
        SRFLP a = new SRFLP("QAP_sko100_04_n.txt");
        int tinicial = 1000000000;
        double alfa = 0.9; //
        Double P ;

        //se crea una lista con los puestos
        ArrayList<Integer> listaPuestos = new ArrayList<Integer>();
        for (int i = 0 ; i < a.getN() ; i++){
            listaPuestos.add(i);
        }
        //se crea una solucion aleatoria con el metodo swap
        ArrayList<Integer> solucionInicial = hacerSwap(listaPuestos);
            a.printInstance();

        //mientras el criterio de termino no se cumpla se seguira iterando.
        while (tinicial >0.1) {
            ArrayList<Integer> solucionVecina =hacerSwap(solucionInicial);
            //criterio metropolis
            //System.out.println(a.getTotalDistance(obtenerOrden(solucionInicial, listaPuestos)));
            if (a.getTotalDistance(obtenerOrden(solucionInicial, listaPuestos)) >
                                    a.getTotalDistance(obtenerOrden(solucionVecina, listaPuestos))) {

                Collections.copy(solucionInicial, solucionVecina);
            }
            if (a.getTotalDistance(obtenerOrden(solucionInicial, listaPuestos)) >=
                    a.getTotalDistance(obtenerOrden(solucionVecina, listaPuestos)))
            {
                P = Math.exp(a.getTotalDistance(obtenerOrden(solucionInicial, listaPuestos)) -
                        a.getTotalDistance(obtenerOrden(solucionVecina, listaPuestos)) / tinicial);
                double numRandom = Math.random() * (1);
                if (numRandom <= P){
                    Collections.copy(solucionInicial, solucionVecina);
                }
            }
            tinicial *= alfa;
        }
        System.out.println("Mejor solucion encontrada: " + solucionInicial);
        System.out.println("Evaluacion funcion objetivo: " + a.getTotalDistance(obtenerOrden(solucionInicial, listaPuestos)));
    }
    private static int[] obtenerOrden(ArrayList<Integer> solucion, ArrayList<Integer> listaPuestos){
        //funcion que obtiene el orde
        int[] orden = new int[listaPuestos.size()];
        // System.out.println(solucion);
        for (int i = 0 ; i < listaPuestos.size() ; i++){
            orden[i] = listaPuestos.indexOf(solucion.get(i));
            //System.out.println(solucion.get(i));
        }
        //System.out.println(Arrays.toString(orden));
        return orden;
    }


    private static ArrayList<Integer> hacerSwap(ArrayList<Integer> puestos){
        // se generan 2 % aleatorios para selecionar a que puestos se les hara swap
        // se crea la funcion normalizacion para retornar el puesto.
        int indice1 = Math.round((int)(Math.random() * (puestos.size() - 1)));
        int indice2 = Math.round((int)(Math.random() * (puestos.size() - 1)));
        //se usa la funcion swap que viene integrada en java
        ArrayList<Integer> nuevaLista = new ArrayList<Integer>(puestos);;

        Collections.swap(nuevaLista,indice1,indice2);
        return nuevaLista; // se retorna el nuevo orden de la lista.
    }
}




