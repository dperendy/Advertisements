package hu.qlm.ads.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * application.properties based configurations.
 */
@Configuration
@Getter
public class FilterConfigs {

	/**
	 * Specifies the look back period from a certain day for calculating ad appearances.
	 */
	private final int dayLimit;

	/**
	 * Specifies how many ads have to appear before a certain ad can appear again.
	 */
	private final int lastNAdLimit;

	public FilterConfigs(@Value("${ads.daylimit}") int dayLimit, @Value("${ads.lastnadlimit}") int lastNAdLimit) {
		this.dayLimit = dayLimit;
		this.lastNAdLimit = lastNAdLimit;
	}
}
