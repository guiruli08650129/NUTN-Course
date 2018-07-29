
public class myProtocol {
	private static final int WAITING = 0;
	private static final int SENTPROTOCOL = 1;
	private static final int ANOTHER = 2;
	
	private static final int NUMSTATE= 4;
	
	private int state = WAITING;
	private int currentstate = 0;
	
	public boolean isNum(String msg)
	{
		boolean check = true;
		
		for(int i = 0 ; i < msg.length() ; i++)
		{
			if(!java.lang.Character.isDigit(msg.charAt(i)))
			{
				if(msg.charAt(i) != '-')
				{
					check = false;
					break;
				}
			}
			//else if(msg.charAt(i) != '-' && !java.lang.Character.isDigit(msg.charAt(i+1)))
			{}
		}
		return check;
	}
	
	public String processInput(String theInput)
	{
		String theOutput = null;
		
		if(state == WAITING)
		{
			theOutput = "Please enter some numbers:";
			state = SENTPROTOCOL;
		}
		else if(state == SENTPROTOCOL)
		{
			if(isNum(theInput))
			{
				theOutput = theInput + ", continue?(y/n)";
				state = ANOTHER;
			}
			else
			{
				theOutput = "Enter error!! Try again!! Please enter some numbers:";
			}
		}
		else if(state == ANOTHER)
		{
			if(theInput.equalsIgnoreCase("y"))
			{
				theOutput = "Please enter some number:";
				if(currentstate == (NUMSTATE - 1))
					currentstate = 0;
				else
					currentstate++;
				state = SENTPROTOCOL;
			}
			else
			{
				theOutput = "BYE BYE!!";
				state = WAITING;
			}			
		}
		return theOutput;
	}
}
