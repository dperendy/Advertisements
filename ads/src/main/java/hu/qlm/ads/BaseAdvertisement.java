package hu.qlm.ads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Hashtable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseAdvertisement implements Advertisement{

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
		System.out.println("Ad playing [" + this.hashCode() + "]");
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
}
