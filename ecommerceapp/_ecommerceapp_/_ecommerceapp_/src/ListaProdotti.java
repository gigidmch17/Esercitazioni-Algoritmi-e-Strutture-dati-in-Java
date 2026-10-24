public class ListaProdotti<P> {

    private class Prodotto<P> {
        private P prodotto;
        private Prodotto<P> prev;
        private Prodotto<P> next;

        public Prodotto(P prod, Prodotto<P> p, Prodotto<P> n) {
            prodotto = prod;
            prev = p;
            next = n;
        }

        public P getProdotto() { return prodotto; }
        public Prodotto<P> getPrev() { return prev; }
        public Prodotto<P> getNext() { return next; }

        public void setPrev(Prodotto<P> p) { prev = p; }
        public void setNext(Prodotto<P> p) { next = p; }
    }

    private Prodotto<P> header;
    private Prodotto<P> trailer;
    private int size = 0;

    public ListaProdotti() {
        header = new Prodotto<>(null, null, null);
        trailer = new Prodotto<>(null, header, null);
        header.setNext(trailer);
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public P first() {
        if (isEmpty()) return null;
        return header.getNext().getProdotto();
    }

    public P last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getProdotto();
    }

    private void addBetween(P prodotto, Prodotto<P> predecessor, Prodotto<P> successor) {
        Prodotto<P> newProdotto = new Prodotto<>(prodotto, predecessor, successor);
        predecessor.setNext(newProdotto);
        successor.setPrev(newProdotto);
        size++;
    }

    private P remove(Prodotto<P> prod) {
        Prodotto<P> predecessor = prod.getPrev();
        Prodotto<P> successor = prod.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return prod.getProdotto();
    }

    public void addFirst(P prodotto) {
        addBetween(prodotto, header, header.getNext());
    }

    public void addLast(P prodotto) {
        addBetween(prodotto, trailer.getPrev(), trailer);
    }

    public P removeFirst() {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    public P removeLast() {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    public P removeAt(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Prodotto<P> walk = header.getNext();
        for (int i = 0; i < index; i++) {
            walk = walk.getNext();
        }
        return remove(walk);
    }

    public P getAt(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Prodotto<P> walk = header.getNext();
        for (int i = 0; i < index; i++) {
            walk = walk.getNext();
        }
        return walk.getProdotto();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListaProdotti{size=").append(size).append(", elementi=[");
        for (int i = 0; i < size; i++) {
            sb.append((i + 1) + ". " + getAt(i));
            if (i < size - 1) {
                sb.append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        ListaProdotti other = (ListaProdotti) o;
        if (size != other.size) return false;
        Prodotto<P> walkA = header.getNext();
        Prodotto<P> walkB = other.header.getNext();
        while (walkA != trailer) {
            if (!walkA.getProdotto().equals(walkB.getProdotto())) return false;
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;
    }

}
