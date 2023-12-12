package hu.qlm.ads.sys;

import hu.qlm.ads.advertisement.Advertisement;
import hu.qlm.ads.utils.AdComparators;
import hu.qlm.ads.utils.AdFilters;
import hu.qlm.ads.utils.AdHistory;
import hu.qlm.ads.utils.AdvertisementRegistry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseAdvertisementSystem implements AdvertisementSystem {

	private static final Logger LOG = LoggerFactory.getLogger(BaseAdvertisementSystem.class);

	private final AdFilters adFilters;
	private final AdComparators adComparators;
	/**
	 * Hany napra vonatkozik egy @Advertisement maxAppearance-e.
	 */
	private int numberOfDays = 1;

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	@Override
	public void registerAdvertisement(Advertisement ad) {
		AdvertisementRegistry.INSTANCE.registerAd(ad);
	}

	/**
	 * 1. Azon reklamok kiszurese, amiket mar a maxAppearance miatt nem lehet jatszani.
	 * 2. A bent marado reklamok sorbarendezese sulyozott ertekkel, ami:
	 * fennmarado lejatszasok * suly
	 * 3. Holtverseny eseten a reklamok regisztalasi sorrendje szerint.
	 *
	 * @param dayIndex
	 */
	@Override
	public void showNextAdvertisement(int dayIndex) {
		var toBePlayed = getAdvertisementList().stream()
				.filter(ad -> adFilters.byMaxAppearance(ad, dayIndex, numberOfDays))
				.sorted(adComparators.byWeight(dayIndex, numberOfDays)
						.thenComparing(adComparators.byRegistryPosition()))
				.findFirst();
		if (toBePlayed.isPresent()) {
			Advertisement ad = toBePlayed.get();
			ad.showAdvertisement();
			ad.getAllAppearences().merge(dayIndex, 1, (o, n) -> ++o);
		}
//		else {
//			 LOG.info("No eligible ad found");
//		}
	}

	@Override
	public List<Advertisement> getAdvertisementList() {
		return AdvertisementRegistry.INSTANCE.getAllAds();
	}
}
