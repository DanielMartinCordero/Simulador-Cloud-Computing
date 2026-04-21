import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Al instanciar CloudServer se crean automáticamente 5 FogNodes,
        // y cada FogNode crea automáticamente 5 EdgeNodes (25 en total)
        CloudServer cloudServer = new CloudServer();

        System.out.println("======== Simulación Cloud - Fog - Edge ========\n");

        System.out.println("Escriba el número de rondas de lecturas que quiere registrar:");
        int numRondas = sc.nextInt();

        // Cada ronda recorre todos los fogs y, dentro de cada uno, todos sus edges.
        // Cada edge envía 1 lectura por ronda, en orden.
        for (int ronda = 1; ronda <= numRondas; ronda++) {
            if (cloudServer.getTotalAlertCount() >= 20) break;  // Para el sistema si ya hay 20 alertas globales
            System.out.println("\n===== Ronda " + ronda + " =====");
            for (FogNode fog : cloudServer.getFogsAsignados()) {
                for (EdgeNode edge : fog.getEdgesAsignados()) {
                    if (cloudServer.getTotalAlertCount() < 20) {
                        edge.sendData(1);
                    }
                }
            }
        }

        cloudServer.showData();

        System.out.println("Alertas detectadas en total: " + cloudServer.getTotalAlertCount());
    }
}
