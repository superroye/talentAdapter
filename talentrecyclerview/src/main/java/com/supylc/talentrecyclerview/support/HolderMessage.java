package com.supylc.talentrecyclerview.support;

/**
 * @author Roye
 * @date 2019/4/18
 */
public class HolderMessage {

    public Class getHolderClass() {
        return holderClass;
    }

    public void setHolderClass(Class holderClass) {
        this.holderClass = holderClass;
    }

    public Object getItemValue() {
        return itemValue;
    }

    public void setItemValue(Object itemValue) {
        this.itemValue = itemValue;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    private Class holderClass;
    private Object itemValue;
    private Object message;

    public HolderMessage() {

    }
}
