package com.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class Login extends BasePage{

	public Login(){
		ModalWindow helpWindow=new ModalWindow("help");
		helpWindow.setPageCreator(new ModalWindow.PageCreator() {
			
			@Override
			public Page createPage() {
				// TODO Auto-generated method stub
				return new Copyright();
			}
		});
		helpWindow.setTitle(new Model("Help"));
		helpWindow.setOutputMarkupId(true);
		ModalWindow modal=new ModalWindow("modal_window");
		modal.setOutputMarkupId(true);
		
		AjaxLink help=new AjaxLink("helplink"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				helpWindow.show(target);
			}
			
		};
		User user=new User();
		FeedbackPanel feedbackPanel=new FeedbackPanel("error_msgs");
		Form loginForm=new Form("loginform");
		Label username=new Label("username","username");
		Label password=new Label("password","password");
		
		TextField<String> usernameField=new TextField<String>("utextfield",new PropertyModel(user,"username"));
		usernameField.add(new UsernameValidator());
		usernameField.setRequired(true);
		PasswordTextField passwordField=new PasswordTextField("ptextfield",new PropertyModel(user,"password"));
		passwordField.setRequired(true);
		
		Button login=new Button("loginbtn"){
			@Override
			public void onSubmit(){
				super.onSubmit();
				System.out.println(user.getUsername());
				System.out.println(user.getPassword());
				getApplication().getSessionStore().setAttribute(getRequest(),"logged_in", "logged_in");
			    setResponsePage(HomePage.class);
			}
		};
		loginForm.add(username);
		loginForm.add(password);
		loginForm.add(usernameField);
		loginForm.add(passwordField);
		loginForm.add(login);
		add(help);
		add(helpWindow);
		add(loginForm);
		add(feedbackPanel);
		
		IColumn[] columns2 = new IColumn[2];
		columns2[0]=new PropertyColumn(new Model("Username"),"Username","Username");
		columns2[1]=new PropertyColumn(new Model("Password"),"Password","Password");
		
		DefaultDataTable table=new DefaultDataTable("datatable",columns2,new UserDataProvider(),10);
		add(table);
		
		List<ICellPopulator<User>> columns= new ArrayList<>();
		columns.add(new PropertyPopulator<User>("Username"));
		columns.add(new PropertyPopulator<User>("Password"));
		DataGridView view=new DataGridView("rows",columns,new UserDataProvider());
		add(view);
		
	}
}
