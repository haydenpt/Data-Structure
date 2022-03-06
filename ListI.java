public interface ListI<E> extends Iterable<E> {

    public int getCurrentSize();
    public void addFirst(E obj);
    public void addLast(E obj);
    public E removeFirst();
    public E removeLast();
    public boolean contains(E obj);
    public E remove(E obj);


}
