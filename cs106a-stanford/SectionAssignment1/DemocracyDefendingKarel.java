import stanford.karel.*;

public class DemocracyDefendingKarel extends SuperKarel {
	public void run() {
		moveToFirstBallotEntry();
		while (frontIsClear()) {
			cleanBallotEntry();
			moveToNextBallotEntry();
		}
	}
	
	private void moveToFirstBallotEntry() {
		move();
	}
	
	private void cleanBallotEntry() {
		if (noBeepersPresent()) {
			turnLeft();
			move();
			removeAllBeepers();
			turnAround();
			move();
			move();
			removeAllBeepers();
			turnAround();
			move();
			turnRight();
		}
	}
	
	private void removeAllBeepers() {
		while (beepersPresent()) {
			pickBeeper();
		}
	}
	
	private void moveToNextBallotEntry() {
		safeMove();
		safeMove();
	}
	
	private void safeMove() {
		if (frontIsClear()) {
			move();
		}
	}
}