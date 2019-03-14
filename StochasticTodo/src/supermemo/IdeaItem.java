package supermemo;

import java.util.Date;

public class IdeaItem extends SM2Item {

	String ideaText = "";
	double ideaGoodness;
	
	public IdeaItem(String ID, String ideaText) {
		/*
		 * Constructor for a new IdeaItem
		 * 
		 */
		this.ID = ID;
		this.ideaText = ideaText;
	}
	
	public IdeaItem(String ID, String ideaText, double interRepetitionInterval, 
			double easeFactor,  int reviews, Date lastReviewDate, Date nextReviewDate) {
		this.ID = ID;
		this.ideaText = ideaText;
		this.interRepetitionInterval = interRepetitionInterval;
		this.easeFactor = easeFactor;
		this.reviews = reviews;
		this.lastReviewDate = lastReviewDate;
		this.nextReviewDate = nextReviewDate;
	}
	
	@Override
	public String toString() {
		/*
		 * Returns the textual representation of the item that is shown in the
		 * review panel
		 */
		return ideaText;
	}

	@Override
	public SM2Item deserializeItem(String itemString) {
		/*
		 * Deserialize a string representation of a IdeaItem
		 */
		String[] itemStrings = itemString.split("\t");
		String ID = itemStrings[0];
		String ideaText = itemStrings[1];
		double interRepetitionInterval = Double.parseDouble(itemStrings[2]);
		double easeFactor = Double.parseDouble(itemStrings[3]);
		int reviews = Integer.parseInt(itemStrings[4]);
		Date lastReviewDate = new Date(Long.parseLong(itemStrings[5]));
		Date nextReviewDate = new Date(Long.parseLong(itemStrings[6]));
		return new IdeaItem(ID, ideaText, interRepetitionInterval,
				easeFactor, reviews, lastReviewDate, nextReviewDate);
		
		
	}

	@Override
	public String serializeItem() {
		/*
		 * Serialize the IdeaItem to a tab separated value line
		 */
		String sanitizedIdeaText = ideaText.replace("\t", "    "); // Prevent parse errors on deserialization
		return String.format("%s\t%s\t%f\t%f\t%d\t%d\t%d\t\n", 
				this.ID, sanitizedIdeaText, this.interRepetitionInterval, 
				this.easeFactor,  this.reviews, this.lastReviewDate.getTime(), 
				this.nextReviewDate.getTime());
	}
	
	@Override
    protected double calculateInterRepetitionInterval(int nthRepetition) {
		/*
		 * Calculates the interval between item repetitions 
		 * 
		 * We override the repetition interval because we want the user to *forget*
		 * instead of remember.
		 */
    	if (nthRepetition == 1) {
    		return 3;
    	}
    	else if (nthRepetition == 2) {
    		return 9;
    	}
    	else {
    		return calculateInterRepetitionInterval(nthRepetition - 1) 
    				* this.easeFactor; 
    	}
    }
	
}
