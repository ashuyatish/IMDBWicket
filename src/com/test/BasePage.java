package com.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public abstract class BasePage extends WebPage{
	
	public BasePage(){
		Link moviesLink=new Link("movies"){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(Movies.class);
			}
			
		};
		
		Link eventsLink=new Link("events"){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(Events.class);
			}
			
		};
		
		Link tvLink=new Link("tv"){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(Tv.class);
			}
			
		};
		
		Link loginpage=new Link("loginpage"){

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				setResponsePage(Login.class);
			}
			
		};
		
		add(moviesLink);
		add(tvLink);
		add(eventsLink);
		add(loginpage);
	}

}
