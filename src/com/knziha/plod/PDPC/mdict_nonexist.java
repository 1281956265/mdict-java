package com.knziha.plod.PDPC;

import java.io.File;

import com.knziha.plod.dictionary.mdict;


/*
 ui side of mdict
 data:2018.07.30
 author:KnIfER
*/


public class mdict_nonexist extends mdict {	
	//构造
	public mdict_nonexist(String fn) {
		fn = new File(fn).getAbsolutePath();
		f = new File(fn);
        _Dictionary_fName = f.getName();
    	int tmpIdx = _Dictionary_fName.lastIndexOf(".");
    	if(tmpIdx!=-1) {
	    	_Dictionary_fSuffix = _Dictionary_fName.substring(tmpIdx+1);
	    	_Dictionary_fName = _Dictionary_fName.substring(0, tmpIdx);
    	}
        String fnTMP = f.getName();
	}
	
	
}
