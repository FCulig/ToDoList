package utils;

import java.time.LocalDateTime;

public class ToDoItem {
	private long id;
	private String name;
	private String description;
	private LocalDateTime expiry;
	private boolean expired;
	
	public ToDoItem(long id, String name, String description, LocalDateTime expiry, boolean expired) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.expiry = expiry;
		this.expired = expired;
	}

	public ToDoItem(String name, String description, LocalDateTime expiry, boolean expired) {
		super();
		this.name = name;
		this.description = description;
		this.expiry = expiry;
		
		if(expiry.compareTo(LocalDateTime.now()) > 0){
			this.expired = false;
		}else {
			this.expired = true;
		}
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the expiry date and time
	 */
	public LocalDateTime getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry the expiry to set
	 */
	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "ToDoItem [id=" + id + ", name=" + name + ", description=" + description + ", expiry=" + expiry
				+ ", expired=" + expired + "]";
	}
	
}
