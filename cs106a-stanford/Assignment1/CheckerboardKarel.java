/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		while (notFacingNorth()) {
			putBeeper();
			moveToNextSpot();
		}
	}
	
	private void moveToNextSpot() {
		for (int i = 0; i < 2; i ++) {
			if (frontIsClear()) {
				move();
			} else {
				moveToNextLine();
			}
		}
	}
	
	private void moveToNextLine() {
		if (facingEast()) {
			turnLeft();
			if (frontIsClear()) {
				move();
				turnLeft();
			}
		} else {
			if (facingWest()) {
				turnRight();
				if (frontIsClear()) {
					move();
					turnRight();
				}
			}
		}
	}
}
