import java.util.Objects;

public class State<T> {
    private String information;
    private Vertex<T> vertex;

    public State(String information, Vertex<T> vertex) {
        this.information = information;
        this.vertex = vertex;
    }

    public String getInformation() {
        return information;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state = (State<?>) o;
        return Objects.equals(information, state.information) &&
                Objects.equals(vertex, state.vertex);
    }

    @Override
    public int hashCode() {

        return Objects.hash(information, vertex);
    }

    @Override // переопределение  toString для выводы информации об описании шагов алгоритма
    public String toString() {
        return "Step{" +
                "description='" + information + '\'' +
                ", value=" + vertex +
                '}';
    }
}
