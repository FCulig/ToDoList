package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import database.Database;
import sort.ToDoItemSorter;
import utils.LDTconverter;
import utils.ToDoItem;

public class Main {
	
	public static void insertTask() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Name of the task:");
		String name = sc.nextLine();
		System.out.println("Description of the task:");
		String desc = sc.nextLine();
		System.out.println("Expiry of the task (dd.MM.yyyy. HH:mm):");
	
		boolean validFormat = false;
		LocalDateTime ldtExp = null;
		while(!validFormat) {
			try {
				String exp = sc.nextLine();
				ldtExp = LDTconverter.StringToLocalDateTime(exp);
				validFormat = true;
			}catch (Exception e){
				System.out.println("Format of date and time needs to be like dd.MM.yyyy. HH:mm");
			}
		}
		
		boolean expired;
		if(ldtExp.compareTo(LocalDateTime.now()) > 0){
			expired = false;
		}else {
			expired = true;
		}
		
		Database db = new Database(); 
		
		db.insertToDoItem(new ToDoItem(name, desc, ldtExp, expired));
	}
	
	public static void printTasks(List<ToDoItem> todo) {
		if(todo.size() == 0) {
			System.out.println("There are no tasks!");
		}else {
			for(ToDoItem it : todo) {
				System.out.println(it);
			}
		}
	}
	
	public static List<ToDoItem> getItems(){
		Database db = new Database(); 
		List<ToDoItem> items = new ArrayList<>();
		
		items = db.getAllToDoItems();
		
		return items;
	}
	
	public static List<ToDoItem> getItems(int expired){
		Database db = new Database(); 
		List<ToDoItem> items = new ArrayList<>();
		
		if(expired == 1) {
			items = db.getAllExpiredTasks();
		}
		
		
		return items;
	}
	
	public static void deleteTask() {
		Scanner sc = new Scanner(System.in);
		Database db = new Database(); 
		
		System.out.println("Enter id of the task you want to delete:");
		db.deleteToDoItem(sc.nextLong());
	}
	
	public static void help() {
		System.out.println("? - List of all commands");
		System.out.println("0 - Exit program");
		System.out.println("1 - Enter new task");
		System.out.println("2 - Print all tasks");
		System.out.println("3 - Sort all tasks by expiry date and time");
		System.out.println("4 - Get all expired tasks");
		System.out.println("5 - Get all active tasks");
		System.out.println("6 - Remove item from list");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		
		help();
		do{
			
			input = sc.nextLine();
			
			if(input.equals("1")) {
				insertTask();
			}else if(input.equals("2")) {
				printTasks(getItems());
			}else if(input.equals("3")) {
				Database db = new Database(); 
				List<ToDoItem> todo = new ArrayList<>();
				
				todo = db.getAllToDoItems();
				Collections.sort(todo, new ToDoItemSorter());
				
				printTasks(todo);
			}else if(input.equals("4")) {
				List<ToDoItem> todo = new ArrayList<>();
				
				todo = getItems(1);
				
				printTasks(todo);
			}else if(input.equals("5")) {
				List<ToDoItem> todo = new ArrayList<>();
				
				todo = getItems(0);
				
				printTasks(todo);
			}else if(input.equals("6")) {
				deleteTask();
			}else if(input.equals("?")) {
				help();
			}else{
				System.out.println("Wrong number!");
			}
			
		}while(!input.equals("0"));
		
		
	}

}
