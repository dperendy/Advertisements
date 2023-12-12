package hu.qlm.ads.advertisement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.qlm.ads.sys.BaseAdvertisementSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseAdvertisement implements Advertisement {

	private static final Logger LOG = LoggerFactory.getLogger(BaseAdvertisement.class);

	private static final String AD_PLAYING_TEMPL = "Ad playing [%s] weight [%s]";
	private int maxAppearance;
	private double weight;
	private final Hashtable<Integer, Integer> allAppearances = new Hashtable<>();

	public BaseAdvertisement(int maxAppearance, double weight) {
		this.maxAppearance = maxAppearance;
		this.weight = weight;
	}

	@Override
	public int getMaxAppearance() {
		return maxAppearance;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public int lastAppearence(int dayIndex, int numberOfDays) {
		return allAppearances.entrySet()
				.stream()
				.filter(e -> e.getKey() <= dayIndex && e.getKey() >= dayIndex - numberOfDays)
				.mapToInt(Map.Entry::getValue)
				.sum();
	}

	@Override
	public void showAdvertisement() {
		// LOG.info(AD_PLAYING_TEMPL.formatted(this.hashCode(), this.weight));
	}

	@Override
	public Hashtable<Integer, Integer> getAllAppearences() {
		return allAppearances;
	}

	public void setMaxAppearance(int maxAppearance) {
		this.maxAppearance = maxAppearance;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Fennmarado lejatszhatosagok szama.
	 *
	 * @param dayIndex
	 * @param dayLimit
	 * @return
	 */
	public int remainingAppearancesByDay(int dayIndex, int dayLimit) {
		return maxAppearance - lastAppearence(dayIndex, dayLimit);
	}

	/**
	 * Adott reklam eddigi osszes lejatszasanak szama.
	 *
	 * @return
	 */
	public int getAllAppearancesSum() {
		return allAppearances.values().stream()
				.mapToInt(Integer::intValue)
				.sum();
	}
}
