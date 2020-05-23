import java.util.*;

public class GraphDfs<T> implements Graph<T> {
    private final Map<T, Set<T>> vertexCollection;
    private final boolean isUndirected;

    public GraphDfs(boolean isUndirected) {
        this.vertexCollection = new HashMap<>();
        this.isUndirected = isUndirected;
    }

    public void addVertex(T vertex) {
        this.vertexCollection.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(T vertex, Set<T> edges) {
        this.vertexCollection.computeIfPresent(vertex, (key, set) -> {
            set.addAll(edges);
            return set;
        });
        if (isUndirected) {
            for (T edge : edges) {
                vertexCollection.get(edge).add(vertex);
            }
        }
    }

    public List<List<T>> getPath(T source, T destination) {
        if (source == null || destination == null) throw new IllegalArgumentException("source or destination is null");
        if (source.equals(destination)) throw new IllegalArgumentException("source and destination is the same vertex in graph");
        if (!vertexCollection.containsKey(source)) throw new IllegalArgumentException("source vertex is not contained in graph");
        if (!vertexCollection.containsKey(destination)) throw new IllegalArgumentException("destination vertex is not contained in graph");

        List<List<T>> result = new ArrayList<>();
        findPath(source, destination, new ArrayList<>(), new HashSet<>(), result);
        return result;
    }

    private void findPath(T source, T destination, List<T> path, Set<T> visited, List<List<T>> result) {
        List<T> newPath = new ArrayList<>(path);
        newPath.add(source);
        visited.add(source);
        for (T vertex : this.vertexCollection.get(source)) {
            if (!vertex.equals(destination) && !visited.contains(vertex)) {
                findPath(vertex, destination, newPath, visited, result);
            } else if (vertex.equals(destination)) {
                newPath.add(vertex);
                result.add(newPath);
            }
        }
        visited.remove(source);
    }

    public Map<T, Set<T>> getVertexCollection() {
        return vertexCollection;
    }

    public boolean isUndirected() {
        return isUndirected;
    }
}

