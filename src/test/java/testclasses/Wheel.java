package testclasses;


public class Wheel {

    private Integer size;

    public Wheel(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wheel wheel = (Wheel) o;

        if (size != null ? !size.equals(wheel.size) : wheel.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size != null ? size.hashCode() : 0;
    }
}
