/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import au.edu.newcastle.SENG48002013.game.engine.connections.input.IInput;
import au.edu.newcastle.SENG48002013.game.engine.connections.input.InputManager;

/**
 *
 * @author Ross
 */
public class Player {

    long id;
    long inputId;
    double score;

    public Player(long id) {
        this.id = id;
        this.inputId = -1;
        this.score = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInputId() {
        return inputId;
    }

    public void setInputId(long inputId) {
        this.inputId = inputId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public IInput getInput() {
        return InputManager.getInput(inputId);
    }

    public boolean isInitialized() {
        return (inputId != -1);
    }
}