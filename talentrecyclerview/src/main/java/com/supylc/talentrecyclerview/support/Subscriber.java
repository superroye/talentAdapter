package com.supylc.talentrecyclerview.support;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Roye
 * @date 2019/4/18
 */
public abstract class Subscriber implements Observer {

    public abstract void update(HolderMessage message);

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof HolderMessage) {
            update((HolderMessage) arg);
        }
    }
}
