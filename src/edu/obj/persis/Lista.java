package edu.obj.persis;

import java.io.Serializable;

public class Lista implements Serializable {
    private Node<String> root;

    public Lista(String t) {
        root = new Node<String>(t);
    }

    public Lista() {}

    public void add(String t) {
        Node<String> ant = root;
        Node<String> aux = root;

        while (true) {

            int c = 0;
            if (aux != null && aux.get() != null) {
                c = aux.get().compareTo(t);
            } else {
                if (root == null)
                    root = new Node<>(t);
                else if (ant.getRigth() == null)
                    ant.setRigth(new Node<String>(t));
                else if (ant.getLeft() == null)
                    ant.setLeft(new Node<String>(t));
                break;
            }

            if (c == 0) {
                aux.getRigth().setLeft(new Node<>(t));
                aux.setRigth(aux.getRigth().getLeft());
                break;
            } else if (c > 0) {
                if (aux.getRigth() != null && aux.getRigth().get() != null && aux.getRigth().get().compareTo(t) <= 0) {
                    aux.getRigth().setLeft(new Node<>(t));
                    aux.setRigth(aux.getRigth().getLeft());
                    break;
                } else if (aux.getRigth() == null) {
                    aux.setRigth(new Node<>(t));
                    break;
                } else if (aux.getRigth().get() == null) {
                    aux.getRigth().set(t);
                } else {
                    ant = aux;
                    aux = aux.getRigth();
                }
            } else {
                if (aux.getLeft() != null && aux.getLeft().get() != null && aux.getLeft().get().compareTo(t) >= 0) {
                    aux.getLeft().setRigth(new Node<>(t));
                    aux.setLeft(aux.getLeft().getRigth());
                    break;
                } else if (aux.getLeft() == null) {
                    aux.setLeft(new Node<>(t));
                    break;
                } else if (aux.getLeft().get() == null) {
                    aux.getLeft().set(t);
                } else {
                    ant = aux;
                    aux = aux.getLeft();
                }
            }
        }
    }

    public int getLength() {
        Node<String> aux = root;
        int cont = 0;

        while (true) {
            if (aux != null) {
                cont++;
                aux.getRigth();
            } else {
                break;
            }
        } return cont;
    }

    public String get(int n) throws IndexOutOfBoundsException {
        Node<String> aux = root;
        String ret = root.get();

        for (int x = 0; x < n; x++) {
            if (aux != null) {
                aux = aux.getRigth();
                if (aux != null)
                    ret = aux.get();
                else
                    throw new IndexOutOfBoundsException();
            } else {
                throw new IndexOutOfBoundsException();
            }
        } return ret;
    }

    public boolean isClear() {
        try {
            get(1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
