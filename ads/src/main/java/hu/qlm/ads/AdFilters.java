package hu.qlm.ads;

public class AdFilters {

	private static final int LAST_N_AD_LIMIT = 2;

	public static boolean byMaxAppearance(Advertisement ad, int dayIndex) {
		return ad.getMaxAppearance() > ad.lastAppearence(dayIndex, AdvertisementSystem.DAY_LIMIT);
	}

	public static boolean byNotLastPlayed(Advertisement ad) {
		return ad != AdHistory.INSTANCE.getLastPlayed();
	}

	public static boolean byNotInLastNAds(Advertisement ad) {
		return AdHistory.INSTANCE.getHistory().keySet().stream()
				.skip(Math.max(0, AdHistory.INSTANCE.getHistory().size() - LAST_N_AD_LIMIT))
				.noneMatch(a -> a == ad);
	}
}
