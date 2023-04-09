import java.util.Objects;

public class Entry <T>{
    private T dataObject;
    private String status;

    public Entry(T dataObject, String status) {
        this.dataObject = dataObject;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "dataObject=" + dataObject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry<?> entry)) return false;
        return Objects.equals(getDataObject(), entry.getDataObject()) && Objects.equals(getStatus(), entry.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataObject(), getStatus());
    }

    public T getDataObject() {
        return dataObject;
    }

    public void setDataObject(T dataObject) {
        this.dataObject = dataObject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
