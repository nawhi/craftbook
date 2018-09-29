package craftbook;

public abstract class Command {

	protected final User targetUser;
	
	public Command(User u) {
		targetUser = u;
	}
	
	public abstract void execute();
	

}
