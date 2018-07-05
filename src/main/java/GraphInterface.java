public interface GraphInterface {
    void addVertex(Vertex vertex);
    void deleteVertex(Vertex vertex);
  
    void addEdge(VertexEdgeVertex edge);
    void deleteEdge(Vertex source, Vertex destination);

    Vertex findNearestVertex(Vertex source);

}
