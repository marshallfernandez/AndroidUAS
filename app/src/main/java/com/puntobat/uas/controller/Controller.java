package com.puntobat.uas.controller;

import android.content.Context;

import com.puntobat.uas.controller.intent.HTTPConnector;

import java.io.InputStream;

public abstract class Controller {

	protected Context context;
	private DataLoaderDelegate delegate;  
	public DataLoaderDelegate getDelegate() {
		return delegate;
	}
	public void setDelegate(DataLoaderDelegate delegate) {
		this.delegate = delegate;
	}
	
	public Controller(Context context){
		this.context = context;
	}
	
	public static InputStream getBinaryfromService(String URL) {
		return HTTPConnector.getBinaryFromService(URL);
	}
	
	public static InputStream getBinaryfromPOST(String URL, String parameters) {
		HTTPConnector poster = new HTTPConnector();
		return poster.getBinaryPOST(URL, parameters, poster.JSON_TYPE);
	}
}
