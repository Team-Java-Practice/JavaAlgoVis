public interface GraphInterface {
    public abstract void addVertex(Vertex vertex);
    public abstract void deleteVertex(Vertex vertex);

    public abstract void addEdge(VertexEdgeVertex edge);
    public abstract void deleteEdge(VertexEdgeVertex edge);



    public abstract Vertex findNearestVertex(Vertex source);

}
