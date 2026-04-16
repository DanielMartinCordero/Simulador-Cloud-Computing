import java.util.Random;

public class EdgeNode {
    private final String name;
    private final FogNode fogNode;
    private final Random RANDOM = new Random();

    public EdgeNode(String name, FogNode fogNode) {
        this.name = name;
        this.fogNode = fogNode;
        fogNode.getEdgesAsignados().add(this);
    }

    public void sendData(int totalReadings) {
        for (int i = 1; i <= totalReadings; i++) {
            if (fogNode.getAlertCount() < 20) {
                double temperature = 20 + RANDOM.nextDouble() * 15;
                SensorData data = new SensorData("sensor-1", temperature);
                System.out.println("[EDGE] Enviando lectura: " + i + ": " + data);
                fogNode.processData(data);
                System.out.println();
            } else {
                System.out.println("Registro parado, el número de alertas es igual o mayor a 20");
                break;
            }
        }
    }
}
