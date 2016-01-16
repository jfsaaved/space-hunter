package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.OptionState;
import com.jfsaaved.libgdxgamejam15.states.Planet2State;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;

/**
 * Created by 343076 on 16/01/2016.
 */
public class Planet2Options extends Options {

    public Planet2Options(Planet2State state){
        super(state);

        state.statusImages.resetPreviewAll();
        state.statusImages.resetColorAll();
        String[] dialogue = {"BUY FOOD FOR GOLD"};
        state.dialogueImages.setDialogues(dialogue);
        setIncrementPreview(state.hero.getFood(), 1, Stats.FOOD.getValue());
        setDecrementPreview(state.hero.getGold(), 10, Stats.GOLD.getValue());

    }

    @Override
    public void handleInput() {
        super.handleInput();
        if(currentOption.elementAt(0) == 0)
            foodOptions();
        else if(currentOption.elementAt(0) == 1)
            serviceOptions();
        else if(currentOption.elementAt(0) == 2)
            sellOptions();
        else if(currentOption.elementAt(0) == 3)
            state.getGSM().set(new ShipState(state.getGSM()));
        else if(currentOption.elementAt(0) == 4){
            currentOption.clear();
            state.getGSM().push(new OptionState(state.getGSM()));
        }

        if(currentOption.empty()){
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();

            String[] dialogue = {"BUY FOOD FOR GOLD"};
            state.dialogueImages.setDialogues(dialogue);

            String[] options = {"BUY FOOD","SHIP SERVICE","SELL","SHIP","OPTIONS"};
            state.menuImages = new MenuImages(state.getCam(), options);
        }
    }

    @Override
    public void setHoverDesc(int i) {
        if(currentOption.empty()){
            if(i == 0) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"EXCHANGE YOUR GOLD FOR FOOD", "EACH FOOD COST $20"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getFood(), 1, Stats.FOOD.getValue());
                setDecrementPreview(state.hero.getGold(), 20, Stats.GOLD.getValue());
            }
            else if(i == 1) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"REFUEL AND REPAIR YOUR SHIP","THE SERVICE WILL COST $80"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.ship.getHealth(), 100, Stats.SHIP_HEALTH.getValue());
                setIncrementPreview(state.ship.getFuel(), 100, Stats.SHIP_FUEL.getValue());
                setDecrementPreview(state.hero.getGold(), 80, Stats.GOLD.getValue());
            }
            else if(i == 2) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"SELL YOUR ARTIFACTS FOR GOLD","YOU CAN SELL THEM FOR $20 EACH"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getGold(), 20 * state.hero.getArtifacts(), Stats.GOLD.getValue());
                setDecrementPreview(state.hero.getArtifacts(), 1 * state.hero.getArtifacts(), Stats.ARTIFACTS.getValue());
            }
            else if(i == 3) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"GO BACK TO SPACE"};
                state.dialogueImages.setDialogues(dialogue);
            }
            else if(i == 4) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"CHANGE THE SETTINGS"};
                state.dialogueImages.setDialogues(dialogue);
            }
        }
    }

    private void foodOptions(){
        if(state.hero.getGold() > 20) {
            changeStatsAt(Stats.GOLD.getValue(), -20);
            changeStatsAt(Stats.FOOD.getValue(), 1);
            String[] notification = {"SUCCESS",
                    (state.hero.getGold() - originalGold)+" GOLD",
                    "+1 FOOD"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }else{
            String[] notification = {"FAILED","NOT ENOUGH $"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }
        currentOption.clear();
    }

    private void serviceOptions(){
        if(state.hero.getGold() > 80) {
            changeStatsAt(Stats.GOLD.getValue(), -80);
            changeStatsAt(Stats.SHIP_HEALTH.getValue(), 100);
            changeStatsAt(Stats.SHIP_FUEL.getValue(), 100);
            String[] notification = {"SUCCESS",
                    (state.hero.getGold() - originalGold)+" GOLD",
                    "+"+(state.ship.getFuel() - originalShipFuel)+" FUEL",
                    "+"+(state.ship.getHealth() - originalShipHealth)+" SHIP HP"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }else {
            String[] notification = {"FAILED","NOT ENOUGH $"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }
        currentOption.clear();
    }

    private void sellOptions(){
        if(state.hero.getArtifacts() > 0) {
            changeStatsAt(Stats.GOLD.getValue(), (20 * state.hero.getArtifacts()));
            changeStatsAt(Stats.ARTIFACTS.getValue(), (-1 * state.hero.getArtifacts()));
            String[] notification = {"SUCCESS",
                    "+"+(state.hero.getGold() - originalGold)+" GOLD",
                    "-"+originalArtifacts+" ARTIFACTS"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }else {
            String[] notification = {"FAILED","NO ARTIFACTS"};
            state.notificationImages = new NotificationImages(state.getCam(),notification);
        }
        currentOption.clear();
    }

}
