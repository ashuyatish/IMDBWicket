package com.test;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MyClass extends WebApplication{

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return HomePage.class;
	}

}
