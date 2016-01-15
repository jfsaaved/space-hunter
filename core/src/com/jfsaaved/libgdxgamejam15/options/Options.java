package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.GSM;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.states.State;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by 343076 on 13/01/2016.
 */
public abstract class Options {

    protected Stack<Integer> currentOption;

    public Options() {
        currentOption = new Stack<Integer>();
    }

    public void pushOption(int i){
        currentOption.push(i);
    }

    public abstract void setHoverDesc(int i);

    public abstract void handleInput();

}
