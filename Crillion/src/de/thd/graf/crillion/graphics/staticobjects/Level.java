package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

/**
 * Class which indicates the level in which is the player
 */
public class Level extends ScoreObjects {

 /**
  * Create the Level object
  * @param gameView get important Code from GameView
  */
 public Level(GameView gameView){
  super(gameView);
  this.position = new Position(0, 0);
  this.scoreNum = 0;
  this.name = "Level:" + this.scoreNum;
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public void addToCanvas() {
  gameView.addTextToCanvas(this.name, this.position.x, this.position.y,this.size, this.color, this.rotation);
 }

 /**
  * {@inheritDoc}
  */
 @Override
 public void updateStatus() {
 }

}
