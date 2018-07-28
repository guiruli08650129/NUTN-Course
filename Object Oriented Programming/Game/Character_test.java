import java.util.Random;

public class Character_test {

	public static void main(String[] args) {
		
		boolean isDead = false;
		Random r = new Random();
		Warrior W_arr[] = new Warrior[3];
		W_arr[0] = new Warrior("Sam");
		W_arr[1] = new Warrior("Bob");
		W_arr[2] = new Warrior("Tony");
		
		Master M_arr[] = new Master[3];
		M_arr[0] = new Master("Amy");
		M_arr[1] = new Master("Rose");
		M_arr[2] = new Master("Kate");
		
		System.out.println("================Game Start================");
		while( isDead == false )
		{
			int x1 = r.nextInt(3);
			int y1 = r.nextInt(3);
			System.out.println("Warrior " + W_arr[x1].Name + " attack master " + M_arr[y1].Name);
			W_arr[x1].NewMoon( M_arr[y1] );
			W_arr[x1].detail();
			M_arr[y1].detail();
			isDead = M_arr[y1].dead();
			System.out.println("\n_________________________________________");
			if( isDead == true )
				break;
			
			int x2 = r.nextInt(3);
			int y2 = r.nextInt(3);
			System.out.println("Master " + M_arr[x2].Name + " attack warrior " + W_arr[y2].Name);
			M_arr[x2].SmallFire( W_arr[y2] );
			W_arr[x2].detail();
			M_arr[y2].detail();
			isDead =  W_arr[y2].dead();
			System.out.println("\n_________________________________________");
		}
		
		System.out.println("Game Over");
	}

}
