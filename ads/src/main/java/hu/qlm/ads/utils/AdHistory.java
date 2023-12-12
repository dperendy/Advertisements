package hu.qlm.ads.utils;

import hu.qlm.ads.advertisement.Advertisement;

import java.util.LinkedHashMap;
import java.util.Map;

public enum AdHistory {
	INSTANCE;

	/*
	Egy Ad csak egyszer szerepel benne.
	 */
	private final Map<Advertisement, Integer> history = new LinkedHashMap<>(16, .75f, true);
	private Advertisement lastPlayed;


	public void recordAdAppearance(Advertisement ad) {
		history.merge(ad, 1, (o, n) -> ++o);
		lastPlayed = ad;
	}

	public Map<Advertisement, Integer> getHistory() {
		return history;
	}

	public boolean adAppeared(Advertisement ad) {
		return history.containsKey(ad);
	}

	public Advertisement getLastPlayed() {
		return lastPlayed;
	}

}
