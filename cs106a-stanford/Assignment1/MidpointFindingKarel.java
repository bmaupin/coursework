/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

/* Note: this fails if the world is only one street wide. I'm not sure if that's 
 * permissible or not.
 */
public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		setup();
		while (frontIsBlocked()) {
			turnAround();
			moveToFirstBeeper();
			moveBeeper();
		}
	}
	
	private void setup() {
		putBeeper();
		moveToWall();
		putBeeper();
	}
	
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}
	
	private void moveBeeper() {
		if (beepersPresent()) {
			pickBeeper();
			move();
			if (noBeepersPresent()) {
				putBeeper();
				moveToWall();
			}
		}
	}
	
	private void moveToFirstBeeper() {
		while (noBeepersPresent()) {
			move();
		}
	}
}
