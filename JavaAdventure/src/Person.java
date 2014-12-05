import java.util.function.Function;

public class Person {
	private String name;
	
	private Function <String, String> responses;
	
	public Person(String name, Function<String,String> respons)
	{
		this.name=name;
		responses = respons; 
	}
	public void addResponse(Function <String, String> response)
	{
		//this.responses.add(response);
		this.responses = response;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getResponse(String message)
	{
		return this.responses.apply(message);
	}
	
}
