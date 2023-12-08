package hu.qlm.ads;

import java.util.Hashtable;

public class BaseAdvertisement implements Advertisement{

	private int maxAppearance;

	@Override
	public int getMaxAppearance() {
		return 0;
	}

	@Override
	public double getWeight() {
		return 0;
	}

	@Override
	public int lastAppearence(int dayIndex, int numberOfDays) {
		return 0;
	}

	@Override
	public void showAdvertisement() {

	}

	@Override
	public Hashtable<Integer, Integer> getAllAppearences() {
		return null;
	}
}
