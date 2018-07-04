public class AdjacencyMatrix {
    private int[][] AdjArray = null;

    public AdjacencyMatrix(GraphRepresentation repr) {
        AdjArray = new int[repr.getVertexNumber()][repr.getVertexNumber()];

        for (int[] str : AdjArray)
            for (int i = 0; i < str.length; i++) {
                str[i] = -1;
            }

        for (VertexEdgeVertex currEdge : repr.Edges)
            AdjArray[repr.getIndexByName(currEdge.getSource())]
                    [repr.getIndexByName(currEdge.getDestination())]
                    = currEdge.getLength();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] stroke : AdjArray) {
            for (int cell : stroke)
                builder.append(cell + " ");
            builder.append("\n");
        }
        return new String(builder);
    }
}
