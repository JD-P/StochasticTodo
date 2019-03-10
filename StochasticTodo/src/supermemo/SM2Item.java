package supermemo;

/*
 * Supermemo-2 Implementation in Java
 * See: https://supermemo.guru/wiki/SuperMemo_1.0_for_DOS_(1987)#Algorithm_SM-2
 * for a description of the algorithm and its purpose.
 *
 * In our context, we use the Supermemo-2 algorithm to help determine how often
 * we should show our 
 */

public abstract class SM2Item {
    private int interRepetitionInterval; // Days until you show item again in SM
    private double easeFactor;

    private void updateEaseFactor(int quality) {
	this.easeFactor = this.easeFactor + (0.1 - (5-quality)
					     * (0.08 + (5-quality) * 0.02));
    }
}

