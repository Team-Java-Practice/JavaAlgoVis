import java.util.Objects;

public class Vertex<T> {
    private T value;
    private int path;

    private int inTime = Integer.MAX_VALUE;
    private int outTime = Integer.MAX_VALUE;

    public Vertex(T value) {
        this.value = value;
        path = Integer.MAX_VALUE;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public T getValue() {
        return value;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    public String toString() {
        return "" + value;
    }
}
