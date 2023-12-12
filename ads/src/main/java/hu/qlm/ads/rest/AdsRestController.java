package hu.qlm.ads.rest;

import hu.qlm.ads.advertisement.Advertisement;
import hu.qlm.ads.sys.AdvertisementSystem;
import hu.qlm.ads.advertisement.BaseAdvertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdsRestController {

	private final AdvertisementSystem sys;

	// TODO: exception handling
	@PostMapping(path = "/registerAd")
	public ResponseEntity<String> registerAd(@RequestBody BaseAdvertisement ad) {
		sys.registerAdvertisement(ad);
		return ResponseEntity.accepted().build();
	}

	@PostMapping(path = "/registerAds")
	public ResponseEntity<String> registerAds(@RequestBody List<BaseAdvertisement> ads) {
		ads.forEach(sys::registerAdvertisement);
		return ResponseEntity.accepted().build();
	}

	@GetMapping(path = "/getAllAds")
	public ResponseEntity<List<Advertisement>> getAllAds() {
		List<Advertisement> advertisementList = sys.getAdvertisementList();
		return advertisementList.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<List<Advertisement>>(advertisementList, HttpStatus.OK);
	}

	@PostMapping(path = "/showNextAd")
	public ResponseEntity<String> showNextAd(@RequestParam int dayIndex) {
		sys.showNextAdvertisement(dayIndex);
		return ResponseEntity.ok().build();
	}

}
