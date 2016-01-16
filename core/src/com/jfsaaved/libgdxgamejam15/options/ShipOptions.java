package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.Planet2State;
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
        if(currentOption.elementAt(0) == 0) {
            ShipState shipState = (ShipState) state;
            if(shipState.getTravelTime() > 0){
                String[] notification = {"FAILED","YOU ARE","TRAVELLING"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                currentOption.clear();
            }else
                navigateOptions();
        }
        else if(currentOption.elementAt(0) == 1)
            suppliesOptions();
        else if(currentOption.elementAt(0) == 2)
            maintenanceOptions();
        else if(currentOption.elementAt(0) == 3) {
            ShipState shipState = (ShipState) state;
            if(shipState.getTravelTime() > 0){
                String[] notification = {"FAILED","YOU ARE","TRAVELLING"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                currentOption.clear();
            }else {
                if (shipState.getCurrentSystem() == 0)
                    state.getGSM().set(new PlanetState(state.getGSM()));
                else
                    state.getGSM().set(new Planet2State(state.getGSM()));
            }
        }

        if(currentOption.empty()){
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();

            String[] dialogue = {"NAVIGATE THROUGH THE GALAXY TO DIFFERENT SYSTEMS"};
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
                String[] dialogue = {"NAVIGATE THROUGH THE GALAXY TO DIFFERENT SYSTEMS"};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 1) {
                String[] dialogue = {"OPTIONS TO MAINTAIN THE HERO"};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 2) {
                String[] dialogue = {"OPTIONS TO MAINTAIN THE SHIP"};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 3) {
                String[] dialogue = {"LAND AT A HABITABLE PLANET IN THIS SYSTEM"};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 4) {
                String[] dialogue = {"CHANGE THE SETTINGS"};
                state.dialogueImages.setDialogues(dialogue);
            }
        }
        else if(currentOption.get(0) == 0) { // System explanations
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();
            if(i == 0) {
                String[] dialogue = {"SYSTEM 1","A HOSTILE SYSTEM, ABANDONED MANY YEARS AGO","THIS IS WHERE MOST HUNTERS CAN EARN THEIR LIVING","HABITABLE PLANET: BLACK MAMBA 24"};
                state.dialogueImages.setDialogues(dialogue);
                setDecrementPreview(state.ship.getFuel(), 20, Stats.SHIP_FUEL.getValue());
                setDecrementPreview(state.ship.getHealth(), 20, Stats.SHIP_HEALTH.getValue());
            }
            else if(i == 1) {
                String[] dialogue = {"SYSTEM 2","MAINTAINED BY THE UNITED STATES OF THE GALAXY","YOU CAN REFUEL YOUR SHIP AND SELL YOUR ARTIFACTS HERE","HABITABLE PLANET: FROBE 8"};
                state.dialogueImages.setDialogues(dialogue);
                setDecrementPreview(state.ship.getFuel(), 20, Stats.SHIP_FUEL.getValue());
                setDecrementPreview(state.ship.getHealth(), 20, Stats.SHIP_HEALTH.getValue());
            }
            else if(i == 2) {
                String[] dialogue = {"GO BACK"};
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
                setIncrementPreview(state.hero.getHunger(), 50, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getFood(), 1, Stats.FOOD.getValue());
            }
            else if(i == 1) { // SLEEP
                setIncrementPreview(state.hero.getEnergy(), 100, Stats.ENERGY.getValue());
                setIncrementPreview(state.hero.getHealth(), 50, Stats.HEALTH.getValue());
                setDecrementPreview(state.hero.getHunger(), 20, Stats.HUNGER.getValue());
            }
            else if(i == 2) { // RELAX
                setIncrementPreview(state.hero.getHealth(), 5, Stats.HEALTH.getValue());
                setDecrementPreview(state.hero.getEnergy(), 20, Stats.ENERGY.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 25, Stats.HUNGER.getValue());
                else
                    setDecrementPreview(state.hero.getHunger(), 5, Stats.HUNGER.getValue());
            }
        }

        else if(currentOption.get(0) == 2) { // Ship options
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();
            if(i == 0) { // MAINTENANCE
                setIncrementPreview(state.hero.getMechanic(), 1, Stats.MECHANIC.getValue());
                setIncrementPreview(state.ship.getFuel(), 1 + (state.hero.getMechanic() / 5), Stats.SHIP_FUEL.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 30, Stats.HUNGER.getValue());
                else
                    setDecrementPreview(state.hero.getHunger(), 10, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Stats.ENERGY.getValue());
            }
            else if(i == 1) { // REPAIR
                setIncrementPreview(state.hero.getMechanic(), 1, Stats.MECHANIC.getValue());
                setIncrementPreview(state.ship.getHealth(), 1 + (state.hero.getMechanic()/5), Stats.SHIP_HEALTH.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 30, Stats.HUNGER.getValue());
                else
                    setDecrementPreview(state.hero.getHunger(), 10, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Stats.ENERGY.getValue());
            }
        }
    }

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    private void navigateOptions(){
        String[] options = {"SYSTEM 1","SYSTEM 2","BACK"};
        state.menuImages = new MenuImages(state.getCam(), options);
        ShipState shipState = (ShipState)state;
        if(currentOption.size() > 1){
            if(currentOption.elementAt(1) == 0){
                if(shipState.travelTo(0)){
                    String[] notification = {"TRAVELING TO","SYSTEM 1"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }
                else {
                    String[] notification = {"FAILED","ALREADY IN","SYSTEM 1"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                if(shipState.travelTo(1)){
                    String[] notification = {"TRAVELING TO","SYSTEM 2"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }
                else {
                    String[] notification = {"FAILED","ALREADY IN","SYSTEM 2"};
                    state.notificationImages = new NotificationImages(state.getCam(),notification);
                }
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
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
            if(currentOption.elementAt(1) == 0){ // EAT
                if(state.hero.getFood() > 0) {
                    changeStatsAt(Stats.HUNGER.getValue(), 50);
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
            else if(currentOption.elementAt(1) == 1){ // SLEEP
                changeStatsAt(Stats.ENERGY.getValue(), 100);
                changeStatsAt(Stats.HEALTH.getValue(), 50);
                changeStatsAt(Stats.HUNGER.getValue(), -20);
                String[] notification = {"SUCCESS",
                        "+"+(state.hero.getEnergy()-originalEnergy)+"% ENERGY",
                        "+"+(state.hero.getHealth()-originalHealth)+"% HEALTH",
                        (state.hero.getHunger()-originalHunger)+"% HUNGER"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                calculateTravelingTurns(4);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){ // RELAX
                changeStatsAt(Stats.HEALTH.getValue(), 5);
                changeStatsAt(Stats.ENERGY.getValue(), -20);
                changeStatsAt(Stats.HUNGER.getValue(), -5);
                String[] notification = {"SUCCESS",
                        "+"+(state.hero.getHealth()-originalHealth)+"% HEALTH",
                        (state.hero.getEnergy()-originalEnergy)+"% ENERGY",
                        (state.hero.getHunger()-originalHunger+"% HUNGER")};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                calculateTravelingTurns(1);
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
                changeStatsAt(Stats.HUNGER.getValue(), -10);
                changeStatsAt(Stats.ENERGY.getValue(), -40);
                changeStatsAt(Stats.MECHANIC.getValue(), 1);
                changeStatsAt(Stats.SHIP_FUEL.getValue(), (1 + (state.hero.getMechanic()/5)));
                String[] notification = {
                        "+1 MECHANIC",
                        "+"+(state.ship.getFuel()-originalShipFuel)+"% FUEL",
                        (state.hero.getHunger()-originalHunger)+"% HUNGER",
                        (state.hero.getEnergy()-originalEnergy)+"% ENERGY"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                calculateTravelingTurns(2);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 1){
                changeStatsAt(Stats.HUNGER.getValue(), -10);
                changeStatsAt(Stats.ENERGY.getValue(), -40);
                changeStatsAt(Stats.MECHANIC.getValue(), 1);
                changeStatsAt(Stats.SHIP_HEALTH.getValue(), (1 + (state.hero.getMechanic()/5)));
                String[] notification = {
                        "+1 MECHANIC",
                        "+"+(state.ship.getHealth()-originalShipHealth)+"% SHIP HP",
                        (state.hero.getHunger()-originalHunger)+"% HUNGER",
                        (state.hero.getEnergy()-originalEnergy)+"% ENERGY"};
                state.notificationImages = new NotificationImages(state.getCam(),notification);
                calculateTravelingTurns(2);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 2){
                currentOption.clear();
            }
        }
    }

    public void calculateTravelingTurns(int i) {
        ShipState shipState = (ShipState) state;
        if(shipState.getTravelTime() > 0){
            if(i > 2)
                shipState.setTravelTime(0);
            else
                shipState.changeTravelTime(-500);
        }
        state.useTurn(i);
    }


}
