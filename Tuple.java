public class Tuple<A, B> {

    public A a;
    public B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A left() {
        return a;
    }

    public B right() {
        return b;
    }

    public void set(A left_entry, B right_entry) {
        this.a = left_entry;
        this.b = right_entry;
    }

    public void print() {
        System.out.println("<" + this.a + "," + this.b + ">");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        if (!a.equals(tuple.a)) return false;
        return b.equals(tuple.b);
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }
}
