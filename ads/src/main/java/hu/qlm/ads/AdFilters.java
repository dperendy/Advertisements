package hu.qlm.ads;

public class AdFilters {

	public static boolean byMaxAppearance(Advertisement ad, int dayIndex) {
		return ad.getMaxAppearance() > ad.lastAppearence(dayIndex, AdvertisementSystem.DAY_LIMIT);
	}

	public static boolean byNotLastPlayed(Advertisement ad) {
		return ad != AdHistory.INSTANCE.getLastPlayed();
	}
}
