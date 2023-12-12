package hu.qlm.ads.utils;

import hu.qlm.ads.advertisement.Advertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class AdComparators {

	/**
	 * Sulyozott ertek alapu comparator.
	 *
	 * @param dayIndex
	 * @param numberOfDays
	 * @return
	 */
	public Comparator<Advertisement> byWeight(int dayIndex, int numberOfDays) {
		return Comparator.comparing(ad -> weightedValue(ad, dayIndex, numberOfDays), Comparator.reverseOrder());
	}

	private Double weightedValue(Advertisement ad, int dayIndex, int numberOfDays) {
		return ad.getWeight() * (ad.getMaxAppearance() - ad.lastAppearence(dayIndex, numberOfDays));
	}

	/**
	 * Regisztalasi sorrend alapu Comparator.
	 *
	 * @return
	 */
	public Comparator<Advertisement> byRegistryPosition() {
		return (ad1, ad2) -> Integer.valueOf(AdvertisementRegistry.INSTANCE.getAllAds().indexOf(ad1))
				.compareTo(AdvertisementRegistry.INSTANCE.getAllAds().indexOf(ad2));
	}
	
	/*
	Ha valamelyik meg nem volt lejatszva akkor az lesz az elso, egyebkent a regebben lejatszott.
	 */
	public Comparator<Advertisement> byPlayHistory() {
		return (ad1, ad2) -> {
			boolean ad1Appeared = AdHistory.INSTANCE.adAppeared(ad1);
			boolean ad2Appeared = AdHistory.INSTANCE.adAppeared(ad2);
			if (ad1Appeared && !ad2Appeared) {
				return 1;
			} else if (!ad1Appeared && ad2Appeared) {
				return -1;
			} else if (!ad1Appeared && !ad2Appeared) {
				return 0;
			} else {
				Optional<Advertisement> first = AdHistory.INSTANCE.getHistory().keySet().stream()
						.filter(ad -> ad1 == ad || ad2 == ad)
						.findFirst();
				return first.get() == ad1 ? -1 : 1;
			}
		};
	}
}
