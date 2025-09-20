package Java;

public interface Attackable {
    void attack();

    default void beAttacked(int i) {

    }
}
