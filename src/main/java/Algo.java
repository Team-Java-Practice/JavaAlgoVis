import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Algo {
    private DijkstraTable algoRepr;
    private GraphRepresentation repr;
    private int stepsNumber;

    Algo(GraphRepresentation repr, Vertex source){
        this.repr = repr;
        algoRepr = new DijkstraTable(repr, source);
        stepsNumber = repr.getVertexNumber();
    }

    public int getNumberOfSteps(){
        return stepsNumber;
    }

    public void getStatmentByStep(int step){
        /* todo */
    }

    public int getStepOfDestination(Vertex destination){
        int indexOfDestination = repr.getIndexByName(destination);
        for (int i = 0; i < stepsNumber ; i++)
            if (algoRepr.getCellAccess(i, indexOfDestination).isVisited)
                return i;
        throw new NoSuchElementException();
    }

}


class DijkstraTable {
    private Cell[][] table = null;
    private int vertecesNumber;

    public DijkstraTable(GraphRepresentation repr, Vertex source) {
        vertecesNumber = repr.getVertexNumber();
        AdjacencyList AdjList = new AdjacencyList(repr); // список смежности переданного графа.
        table = new Cell[vertecesNumber+1][vertecesNumber];
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

        System.out.println(this);

        copyPrevStroke(vertecesNumber);
        for (Cell cell : table[vertecesNumber])
            cell.isVisited = true;

        System.out.println(this);
    }

    public Cell getCellAccess(int stroke, int indexVertex){
        return table[stroke][indexVertex];
    }

    private void copyPrevStroke(int step) {
        if (step != 0)
            for (int i = 0; i < vertecesNumber; i++) {
                table[step][i].isVisited = table[step - 1][i].isVisited;
                table[step][i].currentLabel = table[step-1][i].currentLabel;
            }
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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < vertecesNumber+1; i++){
            for (int j = 0; j < vertecesNumber; j++)
                builder.append(table[i][j]).append(" ");
            builder.append("\n");
        }
//        for (Cell[] str : table) {
//            for (Cell cell : str)
//                builder.append(cell).append(" ");
//            builder.append("\n");
//        }
        return new String(builder);
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
