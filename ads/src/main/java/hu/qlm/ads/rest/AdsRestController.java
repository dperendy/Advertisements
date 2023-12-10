package hu.qlm.ads.rest;

import hu.qlm.ads.Advertisement;
import hu.qlm.ads.AdvertisementSystem;
import hu.qlm.ads.BaseAdvertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdsRestController {

	private final AdvertisementSystem sys;

	// TODO: exception handling
	@PostMapping(path = "/registerAd")
	public ResponseEntity<String> registerAd(@RequestBody BaseAdvertisement ad) {
		sys.registerAdvertisement(ad);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/registerAds")
	public ResponseEntity<String> registerAds(@RequestBody List<BaseAdvertisement> ads) {
		ads.forEach(sys::registerAdvertisement);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/getAllAds")
	public ResponseEntity<List<Advertisement>> getAllAds() {
		return new ResponseEntity<List<Advertisement>>(sys.getAdvertisementList(), HttpStatus.OK);
	}

	@PostMapping(path = "/showNextAd")
	public ResponseEntity<String> showNextAd(@RequestParam int dayIndex) {
		sys.showNextAdvertisement(dayIndex);
		return ResponseEntity.ok().build();
	}

}
