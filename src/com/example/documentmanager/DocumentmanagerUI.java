package com.example.documentmanager;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import org.omg.CORBA.ValueBaseHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("docUI")
public class DocumentmanagerUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DocumentmanagerUI.class)
	public static class Servlet extends VaadinServlet {
	}

	FilesystemContainer docs=new FilesystemContainer(new File("C:\\Users\\lidon\\Documents\\tmp"));
	ComboBox list=new ComboBox("Document",docs);
	//Label docView=new Label("",ContentMode.HTML);
	DocEditor docView=new DocEditor();
	Table listTable=new Table("Document",docs);
	@Override
	protected void init(VaadinRequest request) {
		HorizontalSplitPanel split=new HorizontalSplitPanel();
		setContent(split);
		//split.addComponent(list);
		split.addComponent(docView);
		split.addComponent(listTable);
		listTable.setSizeFull();
		

		listTable.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				docView.setPropertyDataSource(new TextFileProperty((File)event.getProperty().getValue()));
			}
		});
		docView.setImmediate(true);
		listTable.setSelectable(true);
	}

}