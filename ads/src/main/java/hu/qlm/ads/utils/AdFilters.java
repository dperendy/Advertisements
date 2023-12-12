package hu.qlm.ads.utils;

import hu.qlm.ads.advertisement.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdFilters {

	/**
	 * A reklam lejatszasainak szama kisebb mint a beallitott maxAppearance erteke.
	 *
	 * @param ad
	 * @param dayIndex
	 * @param numberOfDays
	 * @return
	 */
	public boolean byMaxAppearance(Advertisement ad, int dayIndex, int numberOfDays) {
		return ad.getMaxAppearance() > ad.lastAppearence(dayIndex, numberOfDays);
	}

	public boolean byNotLastPlayed(Advertisement ad) {
		return ad != AdHistory.INSTANCE.getLastPlayed();
	}

//	public boolean byNotInLastNAds(Advertisement ad) {
//		return AdHistory.INSTANCE.getHistory().keySet().stream()
//				.skip(Math.max(0, AdHistory.INSTANCE.getHistory().size() - filterConfigs.getLastNAdLimit()))
//				.noneMatch(a -> a == ad);
//	}
}
