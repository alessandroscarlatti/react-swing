package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.component.ntv.RxJPanel;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 *
 * just a wrapper around the same simple jButton example...
 */
public class LottoComponent2 implements RxUsrComponent {

    private int count = 0;  // this is the state...
    private String myNtvRndId;
    private ChildIdIncrementer idIncrementer = new ChildIdIncrementer();

    public LottoComponent2() {
        this.myNtvRndId = obtainNtvRndId();
    }

    @Override
    public String getNextChildNtvRndId() {
        return idIncrementer.getNextId();
    }

    @Override
    public String obtainNtvRndId() {
        return null;
//        return RdrMger.getInstance().getNextNtvRndId();
    }

    @Override
    public int getState() {
        return count;
    }

    @Override
    public void setState(int count) {
        this.count = count;

        // now initiate render.
//        RdrMger.getInstance().pleaseRdr(this);
    }

    @Override
    public RxJPanel render() {
        RxJPanel panel = new RxJPanel();
        panel.add(new RxJButton(String.valueOf(count)));
        panel.add(new RxJButton(String.valueOf(count + 1)));
//        panel.add(Rx.createElement(MyCoolComponent.class));
//        panel.add(Rx.createElement(MyCoolComponent.class));
        panel.add(this::innerPanel);
        return panel;
    }

    private RxJPanel innerPanel() {
        RxJPanel innerPanel = new RxJPanel();
        innerPanel.add(new RxJButton(String.valueOf(count)));
        innerPanel.add(new RxJButton(String.valueOf(count + 1)));
//        innerPanel.add(new MyCoolComponent());
        return innerPanel;
    }

    @Override
    public String getNtvRndId() {
        return myNtvRndId;
    }

    @Override
    public void setNtvRndId(String id) {
        myNtvRndId = id;
    }
}
