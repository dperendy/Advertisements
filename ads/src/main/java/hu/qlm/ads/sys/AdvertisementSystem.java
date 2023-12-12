package hu.qlm.ads.sys;

import hu.qlm.ads.advertisement.Advertisement;

import java.util.List;

public interface AdvertisementSystem {

	// Reklám regisztrálása.
	public void registerAdvertisement(Advertisement ad);

	// Következo reklám megjelenítése a megadott napon.
	public void showNextAdvertisement(int dayIndex);

	// Reklámok listája
	public List<Advertisement> getAdvertisementList();

}
