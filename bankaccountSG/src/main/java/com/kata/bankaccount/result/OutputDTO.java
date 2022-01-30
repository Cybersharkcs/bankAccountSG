package com.kata.bankaccount.result;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kata.bankaccount.dto.MessageDTO;


public class OutputDTO implements Serializable {

    /**
     * Generated serial Version UID.
     */
    private static final long serialVersionUID = -2926063130681674878L;

    private TypeReturn codeRetour;

    private List<MessageDTO> listMessage;

    /**
     * Constructeur.
     */
    public OutputDTO() {
        super();
    }

    public TypeReturn getCodeRetour() {
        return codeRetour;
    }
    public void setCodeRetour(TypeReturn codeRetour) {
        this.codeRetour = codeRetour;
    }

    /**
     * @return the listMessage.
     */
    public List<MessageDTO> getListMessage() {
        if (listMessage == null) {
            listMessage = new ArrayList<>();
        }
        return listMessage;
    }

    /**
     * @param listMessage the listMessage to set.
     */
    public void setListMessage(List<MessageDTO> listMessage) {
        this.listMessage = listMessage;
    }

    public void addMessage(MessageDTO message) {
        this.getListMessage().add(message);
    }
}

