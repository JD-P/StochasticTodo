package supermemo;

import java.util.Comparator;
import java.util.Date;

/*
 * Supermemo-2 Implementation in Java
 * See: https://supermemo.guru/wiki/SuperMemo_1.0_for_DOS_(1987)#Algorithm_SM-2
 * for a description of the algorithm and its purpose.
 *
 * In our context, we use the Supermemo-2 algorithm to help determine how often
 * we should show our 
 */

public abstract class SM2Item implements Comparable<SM2Item> {
	protected String ID;
    protected double interRepetitionInterval = 1; // Days between repetition in SM
    protected double easeFactor = 2.5;
    protected int reviews; // Number of reviews performed
    protected Date lastReviewDate;
    protected Date nextReviewDate;

    @Override
    public abstract String toString();
    
    public abstract SM2Item deserializeItem(String itemString);
    
    public abstract String serializeItem(SM2Item item);
    
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
    
    public void reviewed(int quality) {
    	this.reviews++;
    	this.updateEaseFactor(quality);
    	this.updateInterRepetitionInterval(this.reviews);
    	// TODO: Add history
    	this.lastReviewDate = new Date();
    	this.nextReviewDate = this.calculateNextReviewDate();
    }
    
    @Override
    public int compareTo(SM2Item item) {
    	/*
    	 * We compare items by their next review date
    	 */
    	return this.nextReviewDate.compareTo(item.nextReviewDate);
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
    
    private Date calculateNextReviewDate() {
    	Date nextReviewDate = new Date();
    	long newTime = nextReviewDate.getTime() + (long) (1000 * (60 * 60 * 24 * this.interRepetitionInterval));
    	return new Date(newTime);
    }
    
    private void updateInterRepetitionInterval(int nthRepetition) {
    	this.interRepetitionInterval = calculateInterRepetitionInterval(nthRepetition);
    }
}

