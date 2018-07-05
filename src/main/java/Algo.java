import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class Algo {
    private DijkstraTable algoRepr;
    private int stepsNumber;

    Algo(GraphRepresentation repr, Vertex vertex){
        algoRepr = new DijkstraTable(repr, vertex);
        stepsNumber = repr.getVertexNumber();
    }

    public int getNumberOfSteps(){
        return stepsNumber;
    }

    public void getStatmentByStep(int step){
        /* todo */
    }

}


class DijkstraTable {
    private Cell[][] table = null;
    private int vertecesNumber;

    public DijkstraTable(GraphRepresentation repr, Vertex source) {
        vertecesNumber = repr.getVertexNumber();
        AdjacencyList AdjList = new AdjacencyList(repr); // список смежности переданного графа.
        table = new Cell[vertecesNumber][vertecesNumber];
        for (Cell[] str : table)
            for (int i = 0; i < vertecesNumber; i++)
                str[i] = new Cell();

        // Initialization
        table[0][repr.getIndexByName(source)].currentLabel = 0;

        for (int step = 0; step < vertecesNumber; step++) {
            copyPrevStroke(step);
            Vertex currVertex = getNextVertex(AdjList, table[step]);
            recountLabels(AdjList, currVertex, step);
        }
    }

    private void copyPrevStroke(int step) {
        if (step != 0)
            for (int i = 0; i < vertecesNumber; i++)
                table[step][i] = table[step-1][i];
    }

    private void recountLabels(AdjacencyList adjList, Vertex currVertex, int step) {
        int indexOfCurrVertex = adjList.repr.getIndexByName(currVertex);
        for (EdgeVertex semiEdge : adjList.getAdjVertecesList(currVertex)){
            Vertex adjVertex = semiEdge.getDestination();
            int indexOfAdjVertex = adjList.repr.getIndexByName(adjVertex);

            if (!table[step][indexOfAdjVertex].isVisited){
                int currentLength = table[step][indexOfAdjVertex].currentLabel;
                if (currentLength == -1) currentLength = Integer.MAX_VALUE;
                int fromCurrToAdj = semiEdge.getLength();
                int recountedLength = table[step][indexOfCurrVertex].currentLabel + fromCurrToAdj;
                if (recountedLength < currentLength){
                    table[step][indexOfAdjVertex].currentLabel = recountedLength;
                    System.out.println("Recount! by " + currVertex);
                }
            }

        }
    }

    private Vertex getNextVertex(AdjacencyList AdjList, Cell[] stroke) {
            int minPath = Integer.MAX_VALUE;
            int index = -1;
            System.out.println(vertecesNumber);
            for (int i = 0; i < vertecesNumber; i++) {
                System.out.println(stroke[i]);
                if (stroke[i].currentLabel < minPath && stroke[i].currentLabel != -1 && !stroke[i].isVisited) {
                    index = i;
                    minPath = stroke[i].currentLabel;
                }
            }

            stroke[index].isVisited = true;
            return AdjList.repr.getVertexByName(index);
        }

    @Override
    public String toString() {
        return "DijkstraTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
}



    class Cell {
    int currentLabel = -1;
    boolean isVisited = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return currentLabel == cell.currentLabel &&
                isVisited == cell.isVisited;
    }


    @Override
    public String toString() {
        return currentLabel + "/" + isVisited;
    }
}
