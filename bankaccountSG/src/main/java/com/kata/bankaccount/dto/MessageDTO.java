package com.kata.bankaccount.dto;

import java.io.Serializable;

import com.kata.bankaccount.result.TypeMessage;

import lombok.Data;
@Data
public class MessageDTO implements Serializable{

    /**
     * Indique le type de message (INFO | WARN | ERREUR) Seule un message d'erreur indique que le traitement ne s'est
     * pas effectué.
     */
    private TypeMessage typeMessage;

    /**
     * si code est vrai alors représente un code erreur à interpréter, sinon, libellé directement interprétable.
     */
    private String libelleMessage;
}
