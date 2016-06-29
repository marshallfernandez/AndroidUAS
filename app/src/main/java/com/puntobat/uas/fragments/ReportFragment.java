package com.puntobat.uas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.puntobat.uas.R;
import com.puntobat.uas.constans.UAS;

/**
 * Created by edu24 on 26/06/2016.
 */
public class ReportFragment extends Fragment {

    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_report, container, false);

        mWebView = (WebView) rootView.findViewById(R.id.results_report_webview);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.loadDataWithBaseURL("", UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).HTMLREPORT, "text/html", "UTF-8", "");

        UAS.ABTITLE.setText("Resultados de Evaluaciones");

        return rootView;

    }
}
