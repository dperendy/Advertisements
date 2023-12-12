package hu.qlm.ads;

import hu.qlm.ads.advertisement.BaseAdvertisement;
import hu.qlm.ads.sys.BaseAdvertisementSystem;
import hu.qlm.ads.utils.AdHistory;
import hu.qlm.ads.utils.AdvertisementRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdAppearanceTests {

	private static final Logger LOG = LoggerFactory.getLogger(AdAppearanceTests.class);
	private final BaseAdvertisementSystem sys;

	@Autowired
	public AdAppearanceTests(BaseAdvertisementSystem sys) {
		this.sys = sys;
	}

	@Test
	void givenEqualAppearanceAndEqualWeightThenAppearancesEqual() {
		var ad1 = new BaseAdvertisement(2, 0.1d);
		var ad2 = new BaseAdvertisement(2, 0.1d);
		sys.registerAdvertisement(ad1);
		sys.registerAdvertisement(ad2);
		playAds(1, 1000);
		assertEquals(ad1.getAllAppearancesSum(), ad2.getAllAppearancesSum());
	}

	@Test
	void givenEqualAppearanceAndDifferentWeightThenAppearancesEqual() {
		var ad1 = new BaseAdvertisement(2, 0.2d);
		var ad2 = new BaseAdvertisement(2, 0.1d);
		sys.registerAdvertisement(ad1);
		sys.registerAdvertisement(ad2);
		playAds(1, 1000);
		assertEquals(ad1.getAllAppearancesSum(), ad2.getAllAppearancesSum());
	}

	@Test
	void givenDoubleAppearanceAndEqualWeightThenAppearancesDouble() {
		var ad1 = new BaseAdvertisement(4, 0.1d);
		var ad2 = new BaseAdvertisement(2, 0.1d);
		sys.registerAdvertisement(ad1);
		sys.registerAdvertisement(ad2);
		playAds(1, 1000);
		assertTrue(ad1.getAllAppearancesSum() == ad2.getAllAppearancesSum() * 2);
	}

	@BeforeEach
	private void clearSystem() {
		AdvertisementRegistry.INSTANCE.getAllAds().clear();
		AdHistory.INSTANCE.getHistory().clear();
		sys.setNumberOfDays(1);
	}


	private void playAds(int maxDay, int maxAdsInDay) {
		for (int day = 1; day <= maxDay; day++) {
			System.out.println("Day: " + day);
			for (int adCount = 1; adCount <= maxAdsInDay; adCount++) {
				sys.showNextAdvertisement(day);
			}
		}
	}

}
