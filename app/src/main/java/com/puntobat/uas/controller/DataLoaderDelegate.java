package com.puntobat.uas.controller;

public interface DataLoaderDelegate {
	public void didStartLoadingData();
	public void didFinishLoadingData(Object data);
}
