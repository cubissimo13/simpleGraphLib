import java.util.List;
import java.util.Set;

public interface Graph<T> {
    void addVertex(T vertex);

    void addEdge(T vertex, Set<T> edges);

    List<List<T>> getPath(T source, T destination);
}
