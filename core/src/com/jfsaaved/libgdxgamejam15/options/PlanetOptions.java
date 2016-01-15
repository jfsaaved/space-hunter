package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.PlanetState;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;

/**
 * Created by 343076 on 14/01/2016.
 */
public class PlanetOptions extends Options {

    private PlanetState state;

    public PlanetOptions(PlanetState state){
        super();
        this.state = state;
    }

    @Override
    public void setHoverDesc(int i) {
        if(currentOption.empty()){
            if(i == 0) {
                String[] dialogue = {"Navigate through out the galaxy to different systems."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 1) {
                String[] dialogue = {"Options to maintain the hero."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 2) {
                String[] dialogue = {"Options to maintain the ship."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 3) {
                String[] dialogue = {"Land at a habitable planet in this system."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 4) {
                String[] dialogue = {"Change the settings."};
                state.dialogueImages.setDialogues(dialogue);
            }
        }
        else if(currentOption.get(0) == 0) { // System explanations
            if(i == 0) {
                String[] dialogue = {"A system that has many exotic places, ready to be explored."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 1) {
                String[] dialogue = {"This system is where the planet Earth resides."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 2) {
                String[] dialogue = {"A system that has been abandoned by the government."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 3) {
                String[] dialogue = {"A hostile system, not well known to many."};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 4) {
                String[] dialogue = {"Go back."};
                state.dialogueImages.setDialogues(dialogue);
            }
        }
    }

    @Override
    public void handleInput() {
        if(currentOption.elementAt(0) == 0)
            exploreOptions();
        else if(currentOption.elementAt(0) == 1)
            huntOptions();
        else if(currentOption.elementAt(0) == 2)
            campOptions();

        if(currentOption.empty()){
            String[] dialogue = {"This is planet A."};
            state.dialogueImages.setDialogues(dialogue);

            String[] options = {"EXPLORE","HUNT","CAMP","SHIP","OPTIONS"};
            state.menuImages = new MenuImages(state.getCam(), options);
        }
    }

    private void exploreOptions(){
        String[] options = {"SYSTEM 1","SYSTEM 2","SYSTEM 3","SYSTEM 4","BACK"};
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

    private void huntOptions(){
        String[] options = {"EAT","SLEEP","RELAX","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                state.hero.setHunger(state.hero.getHunger() + 1);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                state.hero.setHealth(state.hero.getHealth() + 1);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                state.hero.setEnergy(state.hero.getEnergy() + 1);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 3){
                currentOption.clear();
            }
        }
    }

    private void campOptions(){
        String[] options = {"CLEAN","REPAIR","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                state.ship.setFuel(state.ship.getFuel() + 1);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                state.ship.setHealth(state.ship.getHealth() + 1);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                currentOption.clear();
            }
        }
    }
}
