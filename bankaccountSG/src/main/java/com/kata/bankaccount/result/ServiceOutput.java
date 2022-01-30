package com.kata.bankaccount.result;


import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class ServiceOutput<T> extends OutputDTO {

    /**
     * Generated serial Version UID.
     */
    private static final long serialVersionUID = -6053356598333917706L;

    /**
     * resultat de type T.
     */
    private Serializable resultat;

    /**
     * Constructeur.
     */
    public ServiceOutput() {
        super();
    }

    /**
     * @return the resultat.
     */
    public T getResultat() {
        return (T) resultat;
    }

    /**
     * @param resultat the resultat to set.
     */
    public void setResultat(T resultat) {
        this.resultat = (Serializable) resultat;
    }

}

