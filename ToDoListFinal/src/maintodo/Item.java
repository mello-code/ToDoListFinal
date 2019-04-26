package maintodo;

import java.io.Serializable;

import maintodo.Status;

public class Item implements Serializable
{
	private Status status;
	private String description;
	private String dueDate;
	private String optionalDate;

	public Item()
	{
		status = Status.NOT_STARTED;
		description = "Error! This text should not be seen.";
		dueDate = "Error! This text should not be seen.";
		optionalDate = "XXX";
	}

	public Item(Status a, String b, String c)
	{
		status = a;
		description = b;
		dueDate = c;
		optionalDate = "-";
	}

	public Status getStatus()
	{
		return status;
	}

	public String getDescription()
	{
		return description;
	}

	public String getDueDate()
	{
		return dueDate;
	}

	public String getOptionalDate()
	{
		return optionalDate;
	}

	public void setStatus(Status input)
	{
		status = input;
	}

	public void setDescription(String input)
	{
		description = input;
	}

	public void setDueDate(String input)
	{
		dueDate = input;
	}

	public void setOptionalDate(String input)
	{
		optionalDate = input;
	}
}
