package com.supylc.talentrecyclerview.support;

import com.supylc.talentrecyclerview.TalentHolder;

import java.util.Observable;

/**
 * @author Roye
 * @date 2019/4/18
 */
public class Publisher extends Observable {

    public void postValue(Class<? extends TalentHolder> holderClass, Object itemValue, Object value) {
        setChanged();

        HolderMessage message = new HolderMessage();
        message.setItemValue(itemValue);
        message.setHolderClass(holderClass);
        message.setMessage(value);
        notifyObservers(message);
    }

    public void subscribe(Subscriber subscriber) {
        addObserver(subscriber);
    }
}
