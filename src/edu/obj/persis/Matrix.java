package edu.obj.persis;

import java.io.Serializable;

import javax.swing.*;

public class Matrix<T> implements Serializable {
    private Node<T> root;

    public Matrix(int rows, int cols) {
        root = new Node<T>();
        Node<T> auxR = root;
        Node<T> auxRUp = null;
        Node<T> auxC = root;
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols - 1; n++) {
                auxC.setRigth(new Node<T>());
                auxC = auxC.getRigth();
                if (auxRUp != null) {
                    auxRUp = auxRUp.getRigth();
                    auxRUp.setDown(auxC);
                }
            }
            if (m != rows - 1) {
                auxR.setDown(new Node<T>());
                auxRUp = auxR;
                auxR = auxR.getDown();
                auxC = auxR;
            }
        }
    }

    public void add(T fact) {
        Node<T> auxR = this.root.getDown();
        Node<T> auxC = this.root;

        while (true) {
            if (auxC != null) {
                if (auxC.get() == null) {
                    auxC.set(fact);
                    break;
                } else {
                    auxC = auxC.getRigth();
                }
            } else if (auxR != null) {
                auxC = auxR;
                auxR = auxR.getDown();
            } else {
                JOptionPane.showMessageDialog(null, "No hay ningun espacio disponible",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }

    public T get(int row, int col) {
        Node<T> aux = this.root;
        for (int x = 0; x < row; x++) {
            if (aux.getDown() != null)
                aux = aux.getDown();
        } for (int x = 0; x < col; x++) {
            if (aux.getRigth() != null)
                aux = aux.getRigth();
        } return aux.get();
    }

    public void set(T fact, int row, int col) {
        Node<T> aux = this.root;
        for (int x = 0; x < row; x++) {
            if (aux.getDown() != null)
                aux = aux.getDown();
        } for (int x = 0; x < col; x++) {
            if (aux.getRigth() != null)
                aux = aux.getRigth();
        } aux.set(fact);
    }

    public void addRow() {
        Node<T> aux = root;
        while (true) {
            if (aux != null) {
                if (aux.getDown() == null) {
                    aux.setDown(new Node<T>());

                    if (aux.getLeft() != null && aux.getLeft().getDown() != null) {
                        aux.getLeft().getDown().setRigth(aux.getDown());
                    }

                    aux = aux.getRigth();
                } else {
                    aux = aux.getDown();
                }
            } else {
                break;
            }
        }
    }

    public void addCol() {
        Node<T> aux = root;
        while (true) {
            if (aux != null) {
                if (aux.getRigth() == null) {
                    aux.setRigth(new Node<T>());
                    
                    if (aux.getUp() != null && aux.getUp().getRigth() != null) {
                        aux.getUp().getRigth().setDown(aux.getRigth());
                    }

                    aux = aux.getDown();
                } else {
                    aux = aux.getRigth();
                }
            } else {
                break;
            }
        }
    }

    public int[] length() {
        int rows = 0, cols = 0;
        Node<T> aux = root;
        while (true) {
            if (aux != null) {
                if (aux.getRigth() == null) {
                    aux = aux.getDown();
                    rows++;
                } else {
                    aux = aux.getRigth();
                    cols++;
                    if (aux != null && aux.getRigth() == null) {
                        cols++;
                    }
                }
            } else {
                break;
            }
        } return new int[] {rows, cols};
    }
}
