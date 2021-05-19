package edu.obj.items;

import java.util.ArrayList;

import edu.obj.*;

/**
 * This class represent a card, indifferent if this is a credit card, or a debit card.
 * This is only for the control of the money of the user. Takes values of numbre of the passport
 * of the user, an number to identify the card, and a cvc that is a number that I don't what is his
 * purposse.
 */
public class Tarjeta implements Creable {
    private static final ArrayList<Long> nums = new ArrayList<>();
    private final int pasaporte;
    private final long numTarjeta;
    private final int cvc;
    private int gastos = 0;
    private int dinero;

    /**
     * Creates an card with his number, this get all that an card in the system needs.
     * @param numTarjeta is the number that identify this one.
     * @param pasaporte is the passport of the user, only for get his number.
     * @param dinero is the actual amount of money that the user gets.
     * @param cvc is an number for... I don't know...
     */
    public Tarjeta(long numTarjeta, int pasaporte, int dinero, int cvc) {
        this.numTarjeta = numTarjeta;
        this.pasaporte = pasaporte;
        this.dinero = dinero;
        this.cvc = cvc;
        nums.add(Long.valueOf(numTarjeta));
    }
    
    public static boolean exists(Long num) {
        return nums.contains(num);
    }

    public static boolean exists(long num) {
        return exists(Long.valueOf(num));
    }
    
    /**
     * This is for the knowledge of if a number gets only three digits.
     * @param num it's the number to evaluate.
     * @return <code>true</code> if the number get only three digits, <code>false</code> otherwise
     */
    public static boolean isValidNum(int num) {
        return num > 99 && num < 1_000;
    }

    /**
     * 
     * @return the number that identify the card.
     */
    public long getNumeroTarjeta() {
        return this.numTarjeta;
    }

    /**
     * 
     * @return the number of the passport of the owner of the card, an user.
     */
    public int getNumPasaporte() {
        return this.pasaporte;
    }

    public int getGastos() {
        return this.gastos;
    }

    public void setGastos(int dinero) {
        this.gastos += dinero;
    }

    /**
     * 
     * @return the actual amount of money.
     */
    public int getDinero() {
        return this.dinero;
    }

    /**
     * This set the new amount of money, pay attention, don't take away the money entered, only change the value
     * of the amount of money for the money entered.
     * @param dinero is the new amount of money.
     */
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    /**
     * If you need to know if you can buy an object, then this is the method to know it.
     * @param price is the price of the object.
     * @return <code>true</code> if you can buy it, <code>false</code> otherwise.
     */
    public boolean isEnoughMoney(int price) {
        return this.dinero >= price && this.dinero >= 0 && price >= 0;
    }

    /**
     * 
     * @return the number of the CVC.
     */
    public int getCvc() {
        return this.cvc;
    }

    @Override
    public String getFilename() {
        return "Tarjeta_" + this.numTarjeta;
    }
}
