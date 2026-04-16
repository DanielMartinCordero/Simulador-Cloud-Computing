import java.util.ArrayList;

public class FogNode {
    private String name;
    private final CloudServer cloudServer;
    ArrayList<EdgeNode> edgesAsignados = new ArrayList<EdgeNode>();
    private int alertCount;

    public FogNode(String name, CloudServer cloudServer) {
        this.name = name;
        this.cloudServer = cloudServer;
        cloudServer.getFogsAsignados().add(this);
        for (int j = 1; j <= 5; j++) {
            new EdgeNode("EDGE-" + CloudServer.edgeCounter, this);
            CloudServer.edgeCounter++;
        }
    }

    public void processData(SensorData data) {
        System.out.println("[FOG] Dato recibido: " + data);
        if (data.getTemperature() > 30) {
            alertCount++;

            System.out.println("[FOG] Alerta: temperatura alta");
        } else {
            System.out.println("[FOG] Temperatura normal");
        }
        cloudServer.saveData(data);
    }

    public ArrayList<EdgeNode> getEdgesAsignados() {
        return edgesAsignados;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }
}
