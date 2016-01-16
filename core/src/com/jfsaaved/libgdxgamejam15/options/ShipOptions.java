package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.PlanetState;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;

/**
 * Created by 343076 on 13/01/2016.
 */
public class ShipOptions extends Options {

    public ShipOptions(ShipState state){
        super(state);
    }

    @Override
    public void handleInput(){
        super.handleInput();
        if(currentOption.elementAt(0) == 0)
            navigateOptions();
        else if(currentOption.elementAt(0) == 1)
            suppliesOptions();
        else if(currentOption.elementAt(0) == 2)
            maintenanceOptions();

        if(currentOption.empty()){
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();

            String[] dialogue = {"Navigate through out the galaxy to different systems."};
            state.dialogueImages.setDialogues(dialogue);

            String[] options = {"NAVIGATE","HERO","SHIP","LAND","OPTIONS"};
            state.menuImages = new MenuImages(state.getCam(), options);
        }
    }

    @Override
    // Explains options through the dialogue box
    public void setHoverDesc(int i){
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

        // Preview At index
        // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
        // ship health = 6, ship fuel = 7, ship level = 8;
        // food = 9, artifacts = 10, gold = 11
        // setPercentagePreview: hero's status, value, colourAt Index, previewAt Index
        else if(currentOption.get(0) == 1) { // Hero options
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();
            if(i == 0) { // EAT
                setIncrementPreview(state.hero.getHunger(), 25, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getFood(), 1, Stats.FOOD.getValue());
            }
            else if(i == 1) { // SLEEP
                setIncrementPreview(state.hero.getEnergy(), 80, Stats.ENERGY.getValue());
                setIncrementPreview(state.hero.getHealth(), 50, Stats.HEALTH.getValue());
                setDecrementPreview(state.hero.getHunger(), 10, Stats.HUNGER.getValue());
            }
            else if(i == 2) { // RELAX
                setIncrementPreview(state.hero.getHealth(), 5, Stats.HEALTH.getValue());
                setDecrementPreview(state.hero.getEnergy(), 10, Stats.ENERGY.getValue());
            }
        }
    }

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    private void navigateOptions(){
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

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    private void suppliesOptions(){
        String[] options = {"EAT","SLEEP","RELAX","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);

        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){

                if(state.hero.getFood() > 0) {

                    changeStatsAt(Stats.HUNGER.getValue(), 25);
                    changeStatsAt(Stats.FOOD.getValue(), -1);
                    String[] notification = {"SUCCESS",
                            "+"+(state.hero.getHunger()-originalHunger)+"% HUNGER",
                            "-1 FOOD"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }else{
                    String[] notification = {"FAILED","NO FOOD"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }

                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){

                changeStatsAt(Stats.ENERGY.getValue(), 80);
                changeStatsAt(Stats.HEALTH.getValue(), 50);
                changeStatsAt(Stats.HUNGER.getValue(), -10);
                String[] notification = {"SUCCESS",
                        "+"+(state.hero.getEnergy()-originalEnergy)+"% ENERGY",
                        "+"+(state.hero.getHealth()-originalHealth)+"% HEALTH",
                        (state.hero.getHunger()-originalHunger)+"% HUNGER"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);

                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                if(state.hero.getEnergy() > 0)
                    changeStatsAt(0, 5);
                changeStatsAt(2, -10);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 3){
                currentOption.clear();
            }
        }
    }

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    private void maintenanceOptions(){
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
