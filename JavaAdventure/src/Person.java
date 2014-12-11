import java.util.function.Function;

/**
 * A person class stores the Name and the Response function.
 * @author Artem Los
 *
 */
public class Person {
	private String name;
	
	private Function <String, String> responses;
	
	/**
	 * The initialisation of Person.
	 * @param name The name of the person.
	 * @param respons The function that should interpret responses.
	 */
	public Person(String name, Function<String,String> response)
	{
		this.name=name;
		responses = response; 
	}
	
	/**
	 * Changes the response function.
	 * @param response The response function.
	 */
	public void changeResponse(Function <String, String> response)
	{
		//this.responses.add(response);
		this.responses = response;
	}
	
	/**
	 * Returns the name of the person.
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns a response that corresponds to a given input.
	 * @param message The input.
	 * @return A response based on input.
	 */
	public String getResponse(String message)
	{
		return this.responses.apply(message);
	}
	
}
