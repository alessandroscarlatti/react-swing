package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.inspect.RxNode;

import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/4/2018
 */
public class DomChangeManager implements RxChgMger {

    private RxNode currentDom;
    private RxNode newDom;

    public DomChangeManager(RxNode currentDom, RxNode newDom) {
        this.currentDom = currentDom;
        this.newDom = newDom;
    }

    @Override
    public List<Runnable> pleaseCreateChgPkt() {
        return new ArrayList<>();
    }
}
