package hu.qlm.ads;

import java.util.Comparator;
import java.util.Optional;

public final class AdComparators {

	public static Comparator<Advertisement> byWeight() {
		return Comparator.comparing(Advertisement::getWeight);
	}

	public static Comparator<Advertisement> byPlayHistory() {
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
//				Optional<Advertisement> first = AdHistory.INSTANCE.getHistory().keySet().stream()
//						.filter(ad -> ad1 == ad || ad2 == ad)
//						.findFirst();
//				return first.isPresent() ?
//						first.get() == ad1 ? -1 : 1
//						: 0;
		};
	}

	public static Comparator<Advertisement> byRegistryPosition() {
		return (ad1, ad2) -> Integer.valueOf(AdvertisementRegistry.INSTANCE.getAllAds().indexOf(ad1))
				.compareTo(AdvertisementRegistry.INSTANCE.getAllAds().indexOf(ad2));
	}
}
