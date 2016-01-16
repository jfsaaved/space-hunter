package com.jfsaaved.libgdxgamejam15.options;

import com.jfsaaved.libgdxgamejam15.states.OptionState;
import com.jfsaaved.libgdxgamejam15.states.PlanetState;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;

/**
 * Created by 343076 on 14/01/2016.
 */
public class PlanetOptions extends Options {

    public PlanetOptions(PlanetState state){
        super(state);

        state.statusImages.resetPreviewAll();
        state.statusImages.resetColorAll();
        String[] dialogue = {"EXPLORE BLACK MAMBA 24 AND FIND ARTIFACTS"};
        state.dialogueImages.setDialogues(dialogue);
        setIncrementPreview(state.hero.getExplorer(), 1, Stats.EXPLORER.getValue());
        setIncrementPreview(state.hero.getArtifacts(), 1 + (state.hero.getExplorer() / 10), Stats.ARTIFACTS.getValue());
        if(state.hero.getEnergy() - 20 <= 0)
            setDecrementPreview(state.hero.getHunger(), 30, Stats.HUNGER.getValue());
        else
            setDecrementPreview(state.hero.getHunger(), 10, Stats.HUNGER.getValue());
        setDecrementPreview(state.hero.getEnergy(), 40, Stats.ENERGY.getValue());

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
        else if(currentOption.elementAt(0) == 3) {
            state.music.getAtlas("hostile").stop();
            state.music.getAtlas("hostile").dispose();
            state.getGSM().set(new ShipState(state.getGSM()));
        }
        else if(currentOption.elementAt(0) == 4){
            currentOption.clear();
            state.getGSM().push(new OptionState(state.getGSM()));
        }

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
                setIncrementPreview(state.hero.getExplorer(), 1, Stats.EXPLORER.getValue());
                setIncrementPreview(state.hero.getArtifacts(), 1 + (state.hero.getExplorer() / 10), Stats.ARTIFACTS.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 30, Stats.HUNGER.getValue());
                else
                    setDecrementPreview(state.hero.getHunger(), 10, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Stats.ENERGY.getValue());
            }
            else if(i == 1) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"HUNT FOR FOOD"};
                state.dialogueImages.setDialogues(dialogue);
                setIncrementPreview(state.hero.getHunter(), 1, Stats.HUNTER.getValue());
                setIncrementPreview(state.hero.getFood(), (state.hero.getHunter() / 15), Stats.FOOD.getValue());
                setDecrementPreview(state.hero.getHealth(), 50, Stats.HEALTH.getValue());
                if(state.hero.getEnergy() - 20 <= 0)
                    setDecrementPreview(state.hero.getHunger(), 20, Stats.HUNGER.getValue());
                setDecrementPreview(state.hero.getEnergy(), 40, Stats.ENERGY.getValue());
            }
            else if(i == 2) {
                state.statusImages.resetPreviewAll();
                state.statusImages.resetColorAll();
                String[] dialogue = {"CAMP TO MAINTAIN THE HERO"};
                state.dialogueImages.setDialogues(dialogue);
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

        // Preview At index
        // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
        // ship health = 6, ship fuel = 7, ship level = 8;
        // food = 9, artifacts = 10, gold = 11
        // setPercentagePreview: hero's status, value, colourAt Index, previewAt Index
        else if(currentOption.get(0) == 2) { // Hero options
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
    }

    private void exploreOptions(){
        changeStatsAt(Stats.HUNGER.getValue(), -10);
        changeStatsAt(Stats.ENERGY.getValue(), -40);
        changeStatsAt(Stats.EXPLORER.getValue(), 1);
        changeStatsAt(Stats.ARTIFACTS.getValue(), (1 + (state.hero.getExplorer()/10)));
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
        changeStatsAt(Stats.HEALTH.getValue(), -50);
        changeStatsAt(Stats.ENERGY.getValue(), -40);
        changeStatsAt(Stats.HUNTER.getValue(), 1);
        changeStatsAt(Stats.FOOD.getValue(), (state.hero.getHunter()/15));
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
                state.useTurn(4);
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
                state.useTurn(4);
                currentOption.clear();
            }
            else if(currentOption.elementAt(1) == 3){
                currentOption.clear();
            }
        }
    }
}
