package com.donation.donationmanager.util;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class Resource implements Closeable {
@Override
public void close() throws IOException {
	// TODO Auto-generated method stub
    	
}

int i;

public int getI() {
	return i;
}

public void setI(int i) {
	this.i = i;
}

}

