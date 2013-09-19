/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

public class Background extends Sprite {

    private PositionType positionType;

    public Background(long id) {
        super(id);
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public enum PositionType {

        TILED,
        STRETCH,
        FILL,
        CENTER
    }
}