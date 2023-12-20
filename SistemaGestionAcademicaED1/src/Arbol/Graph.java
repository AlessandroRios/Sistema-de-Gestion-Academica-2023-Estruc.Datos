package Arbol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private int vertices;
    private LinkedList<Integer> adj[];
    private String pathResult;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new LinkedList[vertices];

        for (int i = 0; i < vertices; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);  // Agregar la conexión en ambas direcciones para hacerla no dirigida
    }

    public void findPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[vertices];

        pathResult = dfs(start, end, visited, path);

        if (pathResult.isEmpty()) {
            pathResult = "Debes tomar las escaleras. No hay camino disponible.";
        }
    }
     private void connectGroup(int start, int end) {
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                addEdge(i - 1, j - 1);
            }
        }
    }

    private void connectWithinGroup(int start, int end) {
        // Lógica para conectar aulas consecutivas dentro de cada grupo
        for (int i = start; i < end; i++) {
            addEdge(i - 1, i);
        }
    }
    public void configureConnections() {
        connectGroup(1, 10);
        connectGroup(11, 21);
        connectGroup(22, 33);
        
        connectWithinGroup(1, 10);
        connectWithinGroup(11, 21);
        connectWithinGroup(22, 33);
    }

    private String dfs(int current, int end, boolean[] visited, List<Integer> path) {
        visited[current] = true;
        path.add(current);

        if (current == end) {
            return buildPathString(path);
        }

        for (int neighbor : adj[current]) {
            if (!visited[neighbor]) {
                String remainingPath = dfs(neighbor, end, visited, path);
                if (!remainingPath.isEmpty()) {
                    return remainingPath;
                }
            }
        }

        // Si no encontramos el camino desde el nodo actual, retrocedemos
        path.remove(path.size() - 1);
        return "";
    }

    private String buildPathString(List<Integer> path) {
        StringBuilder pathString = new StringBuilder("Aulas en el camino: ");
        for (int aula : path) {
            pathString.append(aula + 1).append(" ");
        }
        return pathString.toString();
    }

    public String getPathResult() {
        return pathResult;
    }
   
}