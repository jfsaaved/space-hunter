package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.Planet2State;
import com.jfsaaved.libgdxgamejam15.states.PlanetState;
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
            exploreOptions();
        else if(currentOption.elementAt(0) == 1)
            huntOptions();
        else if(currentOption.elementAt(0) == 2)
            campOptions();
        else if(currentOption.elementAt(0) == 3)
            state.getGSM().set(new ShipState(state.getGSM()));

        if(currentOption.empty()){
            state.statusImages.resetPreviewAll();
            state.statusImages.resetColorAll();

            String[] dialogue = {"EXPLORE BLACK MAMBA 24"};
            state.dialogueImages.setDialogues(dialogue);

            String[] options = {"EXPLORE","HUNT","CAMP","SHIP","OPTIONS"};
            state.menuImages = new MenuImages(state.getCam(), options);
        }
    }

    @Override
    public void setHoverDesc(int i) {
        if(currentOption.empty()){
            if(i == 0) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"EXPLORE BLACK MAMBA 24 AND FIND ARTIFACTS"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getExplorer(), 1, Options.Stats.EXPLORER.getValue());
                setIncrementPreview(state.hero.getArtifacts(), 1 + (state.hero.getExplorer() / 10), Options.Stats.ARTIFACTS.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 30, Options.Stats.HUNGER.getValue());
                else
                    setDecrementPreview(state.hero.getHunger(), 10, Options.Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Options.Stats.ENERGY.getValue());
            }
            else if(i == 1) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"HUNT FOR FOOD"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getHunter(), 1, Options.Stats.HUNTER.getValue());
                setIncrementPreview(state.hero.getFood(), (state.hero.getHunter() / 10), Options.Stats.FOOD.getValue());
                setDecrementPreview(state.hero.getHealth(), 50, Options.Stats.HEALTH.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 20, Options.Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Options.Stats.ENERGY.getValue());
            }
            else if(i == 2) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"HUNT FOR FOOD"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getHunter(), 1, Options.Stats.HUNTER.getValue());
                setIncrementPreview(state.hero.getFood(), (state.hero.getHunter() / 10), Options.Stats.FOOD.getValue());
                setDecrementPreview(state.hero.getHealth(), 50, Options.Stats.HEALTH.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 20, Options.Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Options.Stats.ENERGY.getValue());
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

    private void exploreOptions(){
        changeStatsAt(Options.Stats.HUNGER.getValue(), -10);
        changeStatsAt(Options.Stats.ENERGY.getValue(), -40);
        changeStatsAt(Options.Stats.EXPLORER.getValue(), 1);
        changeStatsAt(Options.Stats.ARTIFACTS.getValue(), (1 + (state.hero.getExplorer()/10)));
        String[] notification = {
                "+1 EXPLORER",
                "+"+(state.hero.getArtifacts()-originalArtifacts)+" ARTIFACTS",
                (state.hero.getHunger()-originalHunger)+"% HUNGER",
                (state.hero.getEnergy()-originalEnergy)+"% ENERGY"};
        state.notificationImages = new NotificationImages(state.getCam(),notification);
        state.useTurn(2);
        currentOption.clear();
    }

    private void huntOptions(){
        changeStatsAt(Options.Stats.HEALTH.getValue(), -50);
        changeStatsAt(Options.Stats.ENERGY.getValue(), -40);
        changeStatsAt(Options.Stats.HUNTER.getValue(), 1);
        changeStatsAt(Options.Stats.FOOD.getValue(), (state.hero.getFood()/10));
        String[] notification = {
                "+1 HUNTER",
                "+"+(state.hero.getFood()-originalFood)+" FOOD",
                (state.hero.getHealth()-originalHealth)+"% HEALTH",
                (state.hero.getEnergy()-originalEnergy)+"% ENERGY"};
        state.notificationImages = new NotificationImages(state.getCam(),notification);
        state.useTurn(2);
        currentOption.clear();
    }

    private void campOptions(){

    }
}
