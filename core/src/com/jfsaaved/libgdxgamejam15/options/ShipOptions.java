package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.PlanetState;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;

/**
 * Created by 343076 on 13/01/2016.
 */
public class ShipOptions extends Options {

    private ShipState state;

    public ShipOptions(ShipState state){
        super();
        this.state = state;
    }

    public void handleInput(){
        if(currentOption.elementAt(0) == 0)
            navigateOptions();
        else if(currentOption.elementAt(0) == 1)
            suppliesOptions();
        else if(currentOption.elementAt(0) == 2)
            maintenanceOptions();

        if(currentOption.empty()){
            String[] options = {"NAVIGATE","SUPPLIES","MAINTENANCE","LAND","OPTIONS"};
            state.menuImages = new MenuImages(state.getCam(), options);
        }
    }

    private void navigateOptions(){
        String[] options = {"PLANET 1","PLANET 2","PLANET 3","PLANET 4","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                state.changeTo(new PlanetState(state.getGSM()));
            }
            else if(currentOption.elementAt(1) == 1){
                state.changeTo(new PlanetState(state.getGSM()));
            }
            else if(currentOption.elementAt(1) == 2){
                state.changeTo(new PlanetState(state.getGSM()));
            }
            else if(currentOption.elementAt(1) == 3){
                state.changeTo(new PlanetState(state.getGSM()));
            }
            else if(currentOption.elementAt(1) == 4){
                currentOption.clear();
            }
        }
    }

    private void suppliesOptions(){
        String[] options = {"EAT","SLEEP","RELAX","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                String[] dialogue = {"You ate stuff."};
                state.dialogueImages.setDialogues(dialogue);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                String[] dialogue = {"A day passes by."};
                state.dialogueImages.setDialogues(dialogue);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                String[] dialogue = {"You watched the stars or whatever."};
                state.dialogueImages.setDialogues(dialogue);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 3){
                currentOption.clear();
            }
        }
    }

    private void maintenanceOptions(){
        String[] options = {"CLEAN","REPAIR","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                String[] dialogue = {"You cleaned the ship."};
                state.dialogueImages.setDialogues(dialogue);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                String[] dialogue = {"You repaired the ship."};
                state.dialogueImages.setDialogues(dialogue);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                currentOption.clear();
            }
        }
    }

}
