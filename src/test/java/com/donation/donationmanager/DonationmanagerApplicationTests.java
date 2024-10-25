package com.donation.donationmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.donation.donationmanager.util.Resource;

@SpringBootTest
class DonationmanagerApplicationTests {

	@Autowired
	Resource y;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void doTest() {
		y.setI(0);
		int t = y.getI();
		assertEquals(0, t);
	}

}
