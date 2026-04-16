import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Al instanciar CloudServer se crean automáticamente 5 FogNodes,
        // y cada FogNode crea automáticamente 5 EdgeNodes (25 en total)
        CloudServer cloudServer = new CloudServer();

        System.out.println("======== Simulación Cloud - Fog - Edge ========\n");

        // Usamos el primer Edge del primer Fog para la simulación
        FogNode primerFog = cloudServer.getFogsAsignados().get(0);
        EdgeNode primerEdge = primerFog.getEdgesAsignados().get(0);
        /*
         * for (int i = 0; i < 5; i++) {
         * System.out.println(cloudServer.getFogsAsignados().get(i));
         * System.out.println(primerFog.getEdgesAsignados());
         * }
         */

        System.out.println("Escriba el número de datos que quiere registrar");
        int numData = sc.nextInt();
        primerEdge.sendData(numData);

        cloudServer.showData();

        // Mostramos alertas totales de todos los Fogs
        int totalAlertas = 0;
        for (FogNode fog : cloudServer.getFogsAsignados()) {
            totalAlertas += fog.getAlertCount();
        }
        System.out.println("Alertas detectadas en total: " + totalAlertas);
    }
}
