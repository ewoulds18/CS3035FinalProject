package Sketcher;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class CreateMenuBar extends GridPane{
	private MenuBar menuBar;
	
	private String[] file = {"Save", "Open", "Clear"};
	private String[] help = {"About me", "Help"};
	
	private MenuItem[] allItems;
	
	CreateMenuBar(){
		MenuBar mb = new MenuBar();
		Menu fileMenu = new Menu("File");
		
		MenuItem save = new MenuItem(file[0]);
		MenuItem open = new MenuItem(file[1]);
		MenuItem clear = new MenuItem(file[2]);
		fileMenu.getItems().addAll(save,open, clear);
		
		Menu helpMenu = new Menu("Help");
		MenuItem about = new MenuItem(help[0]);
		MenuItem helpItem = new MenuItem(help[1]);
		helpMenu.getItems().addAll(about,helpItem);
		
		allItems = new MenuItem[]{save, open, about, helpItem, clear};
		
		mb.getMenus().addAll(fileMenu,helpMenu);
		this.menuBar = mb;
		menuBar.setPrefHeight(25);
		menuBar.setPrefWidth(1200);
		this.getChildren().add(menuBar);
		
	}
	
	public MenuBar getMenuBar() {return menuBar;}
	
	public MenuItem[] getAllItems(){ return allItems;}
}
