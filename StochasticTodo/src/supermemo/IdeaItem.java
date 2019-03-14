package supermemo;

public class IdeaItem extends SM2Item {

	String ideaText = "";
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serializeItem(SM2Item item) {
		// TODO Auto-generated method stub
		return String.format("%s\t%d\t%d\t%d\t%d\t%d\t\n", this.ID, this.easeFactor,
				this.lastReviewDate.getTime(), this.nextReviewDate.getTime(),
				this.interRepetitionInterval, this.reviews);
	}
	
}
