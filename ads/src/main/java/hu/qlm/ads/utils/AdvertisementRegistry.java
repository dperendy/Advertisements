package hu.qlm.ads.utils;

import hu.qlm.ads.advertisement.Advertisement;
import hu.qlm.ads.advertisement.BaseAdvertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public enum AdvertisementRegistry {
	INSTANCE;

	private static final Logger LOG = LoggerFactory.getLogger(AdvertisementRegistry.class);

	private List<Advertisement> registry = new ArrayList<>();

	public void registerAd(Advertisement ad) {
//		LOG.info("Ad added to registry: {}", ad);
		registry.add(ad);
	}

	public List<Advertisement> getAllAds() {
		return registry;
	}
}
