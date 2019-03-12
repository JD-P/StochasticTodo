package supermemo;

import java.util.Date;

/*
 * Supermemo-2 Implementation in Java
 * See: https://supermemo.guru/wiki/SuperMemo_1.0_for_DOS_(1987)#Algorithm_SM-2
 * for a description of the algorithm and its purpose.
 *
 * In our context, we use the Supermemo-2 algorithm to help determine how often
 * we should show our 
 */

public abstract class SM2Item {
    private double interRepetitionInterval = 1; // Days between repetition in SM
    private double easeFactor = 2.5;
    private Date lastReviewDate;
    private Date nextReviewDate;

    
    public boolean reviewNow() {
    	/*
    	 * Return whether it is currently time to review the SM Item
    	 */
    	Date currentDate = new Date();
    	if (nextReviewDate.before(currentDate)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private void updateEaseFactor(int quality) {
	this.easeFactor = this.easeFactor + (0.1 - (5-quality)
					     * (0.08 + (5-quality) * 0.02));
	if (this.easeFactor < 1.3) {
		this.easeFactor = 1.3;
	}
		
    }
    
    private double calculateInterRepetitionInterval(int nthRepetition) {
    	if (nthRepetition == 1) {
    		return 1;
    	}
    	else if (nthRepetition == 2) {
    		return 6;
    	}
    	else {
    		return calculateInterRepetitionInterval(nthRepetition - 1) 
    				* this.easeFactor; 
    	}
    }
    
    private void updateInterRepetitionInterval(int nthRepetition) {
    	this.interRepetitionInterval = calculateInterRepetitionInterval(nthRepetition);
    }
}

