import java.util.List;
import java.util.Set;

// Demonstration of SimpleGraphLib
//      2 - 8
//    /
//  1 - 3 - 6 - 5
//   \         /
//     4  -  7
//         /  \
//        10 - 9

public class Main {
    public static void main(String[] args) {
        GraphDfs<Integer> graph = new GraphDfs<>(true);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addVertex(10);
        graph.addEdge(1, Set.of(2, 3, 4));
        graph.addEdge(2, Set.of(1, 8));
        graph.addEdge(3, Set.of(1, 6));
        graph.addEdge(4, Set.of(1, 7));
        graph.addEdge(5, Set.of(6, 7));
        graph.addEdge(6, Set.of(5, 3));
        graph.addEdge(7, Set.of(4, 5));
        graph.addEdge(8, Set.of(2));
        graph.addEdge(9, Set.of(7));
        graph.addEdge(10, Set.of(7, 9, 11));
        System.out.println(graph.toString());
        List<List<Integer>> path = graph.getPath(8, 9);
        if (path.isEmpty()) {
            System.out.println("there is no path between given vertexes");
        } else {
            System.out.println("there is " + path.size() + " paths");
            System.out.println(path);
        }
    }
}
