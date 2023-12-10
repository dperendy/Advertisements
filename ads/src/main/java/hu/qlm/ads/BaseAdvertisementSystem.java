package hu.qlm.ads;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BaseAdvertisementSystem implements AdvertisementSystem{
	@Override
	public void registerAdvertisement(Advertisement ad) {
		AdvertisementRegistry.INSTANCE.registerAd(ad);
	}

	/*
	1. filter(elmult D napban lejatszasai) < maxAppear
	2. compare weight
	3. compare history
	4. ha nincs benne historyban -> AdvertisementRegistry sorrendben az elso
	 */
	@Override
	public void showNextAdvertisement(int dayIndex) {
		Optional<Advertisement> toBePlayed = getAdvertisementList().stream()
				.filter(ad -> AdFilters.byMaxAppearance(ad, dayIndex))
				.filter(AdFilters::byNotLastPlayed)
				.sorted(AdComparators.byWeight()
						.thenComparing(AdComparators.byPlayHistory())
						.thenComparing(AdComparators.byRegistryPosition()))
				.findFirst();
		if (toBePlayed.isPresent()) {
			Advertisement ad = toBePlayed.get();
			ad.showAdvertisement();
			recordAdAppearance(ad, dayIndex);
		} else {
			System.out.println("No eligible ad found");
		}


	}

	private void recordAdAppearance(Advertisement ad, int dayIndex) {
		ad.getAllAppearences().merge(dayIndex, 1, (o,n) -> ++o);
		AdHistory.INSTANCE.recordAdAppearance(ad);
	}


	@Override
	public List<Advertisement> getAdvertisementList() {
		return AdvertisementRegistry.INSTANCE.getAllAds();
	}
}
