package hu.qlm.ads;

import java.util.ArrayList;
import java.util.List;

public enum AdvertisementRegistry {
	INSTANCE;

	private List<Advertisement> registry = new ArrayList<>();

	public void registerAd(Advertisement ad) {
		System.out.println("Ad added to registry: " + ad);
		registry.add(ad);
	}

	public List<Advertisement> getAllAds() {
		return registry;
	}
}
