package hu.qlm.ads;

import hu.qlm.ads.advertisement.BaseAdvertisement;
import hu.qlm.ads.sys.BaseAdvertisementSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SystemFunctionsTests {

	private final BaseAdvertisementSystem sys;

	@Autowired
	public SystemFunctionsTests(BaseAdvertisementSystem sys) {
		this.sys = sys;
	}

	@Test
	void testAdRegistered() {
		var ad1 = new BaseAdvertisement(2, 0.1d);
		sys.registerAdvertisement(ad1);
		assertTrue(sys.getAdvertisementList().contains(ad1));
	}


}
