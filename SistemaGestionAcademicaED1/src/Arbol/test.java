package Arbol;

import java.util.*;
import javax.swing.JOptionPane;

class test {
    private int vertices;
    private LinkedList<Integer> adj[];

    test(int vertices) {
        this.vertices = vertices;
        adj = new LinkedList[vertices];

        for (int i = 0; i < vertices; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);  // Agregar la conexión en ambas direcciones para hacerla no dirigida
    }

    void findPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[vertices];

        String result = dfs(start, end, visited, path);

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debes tomar las escaleras. No hay camino disponible.");
        } else {
            JOptionPane.showMessageDialog(null, result);
        }
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
        int totalDistance = (path.size() - 1) * 8;  // Distancia entre aulas es 8 metros
        StringBuilder pathString = new StringBuilder("Aulas en el camino: ");
        for (int aula : path) {
            pathString.append(aula + 1).append(" ");
        }
        pathString.append("\nDistancia total a recorrer: ").append(totalDistance).append(" metros");
        return pathString.toString();
    }
}