package com.labrat.actors;

import com.labrat.actorinventory.Inventory;
import com.labrat.actorstates.ActorState;
import com.labrat.actorstates.ActorStateType;
import com.labrat.actorstates.ExploreState;
import com.labrat.rooms.Room;
import com.labrat.view.ResultText;

public class MainCharacter implements Actor {
    private ActorState actorState;
    private Room currentRoom;
    private ResultText previousResult;
    private final Inventory inventory;
    private boolean isQuitting;

    public MainCharacter() {
        this.actorState = new ExploreState(this);
        this.previousResult = new ResultText("");
        this.isQuitting = false;
        this.inventory = new Inventory();
    }

    // ActorState
    public void changeState(ActorState newState) { actorState = newState; }
    public ActorState getActorState(){
        return actorState;
    }
    public ActorStateType getActorStateType() { return actorState.getActorStateType(); }

    // ResultText
    public void setResultText(ResultText result) {
        this.previousResult = result;
    }
    public ResultText getResultText() {
        return previousResult;
    }

    // Room Management
    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Inventory Management
    public Inventory getInventory() {
        return inventory;
    }

    // Hiding Logic
    public boolean isHidden(){
        return this.getActorState().getActorStateType() == ActorStateType.HIDE;
    }

    // Quit Flag
    public void quit() { isQuitting = true; }
    public boolean isQuitting() {
        return isQuitting;
    }
}