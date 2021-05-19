package edu.obj.persis;

import java.io.Serializable;

public class Node<T> implements Serializable {
    private Node<T> up;
    private Node<T> down;
    private Node<T> left;
    private Node<T> rigth;
    private T fact;

    public Node() {}

    public Node(T fact) {
        this.fact = fact;
    }

    public T get() {
        return fact;
    }

    public void set(T fact) {
        this.fact = fact;
    }

    public Node<T> getUp() {
        return up;
    }

    public void setUp(Node<T> up_) {
        this.up = up_;
        up_.down = this;
    }

    public Node<T> getDown() {
        return down;
    }

    public void setDown(Node<T> down_) {
        this.down = down_;
        down_.up = this;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left_) {
        this.left = left_;
        left_.rigth = this;
    }

    public Node<T> getRigth() {
        return rigth;
    }

    public void setRigth(Node<T> rigth_) {
        this.rigth = rigth_;
        rigth_.left = this;
    }

    @Override
    public boolean equals(Object obj) {
        return fact.equals(obj);
    }

    @Override
    public int hashCode() {
        return fact.hashCode();
    }

    @Override
    public String toString() {
        return fact.toString();
    }
}
