package hu.qlm.ads;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdsApplicationTests {

	private final BaseAdvertisementSystem sys;

	@Autowired
	public AdsApplicationTests(BaseAdvertisementSystem sys) {
		this.sys = sys;
	}

	@Test
	void test1() {
		sys.registerAdvertisement(new BaseAdvertisement(10, 0.1d));
		sys.registerAdvertisement(new BaseAdvertisement(10, 0.1d));
		sys.registerAdvertisement(new BaseAdvertisement(10, 0.1d));
		sys.registerAdvertisement(new BaseAdvertisement(10, 0.6d));

		for (int day = 0; day <= 10; day++) {
			System.out.println("Day: " + day);
			for (int adCount = 0; adCount <= 4; adCount++) {
				sys.showNextAdvertisement(day);
			}
		}
		// assert something somehow
	}

}
