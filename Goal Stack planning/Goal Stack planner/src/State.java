
public class State {
    int blocks;
	int on[][];
	int ontable[];
	int clear[];
	int hold[];
	int arm;
	
	State(int n,String desc)
	{
		blocks =n;
		on = new int[n][n];
		ontable = new int[n];
		clear = new int[n];
		hold = new int[n];
		arm =-1;
		setState(desc);
	}
	
	void setState(String desc)
	{
		int i;
		//System.out.println(desc);
		String subG[] = desc.split("['^']+");
		for(i=0;i<subG.length;i++)
		{
			String ele[] = subG[i].split("[() ]+");
			//System.out.println(i+" "+subG[i]);
			
			/*for(int j=0;j<ele.length;j++)
			{
				System.out.print(ele[j] + " ");
			}
			System.out.println();*/
			if(ele[1].equals("on"))
			{
				//System.out.println("on["+ele[2].charAt(0)%97+"]["+(int)ele[3].charAt(0)%97 +"]");
				on[(int)ele[2].charAt(0)%97][(int)ele[3].charAt(0)%97] = 1;
			}
			else if(ele[1].equals("ontable"))
			{
				ontable[ele[2].charAt(0)%97] =1;
			}
			else if(ele[1].equals("clear"))
			{
				clear[ele[2].charAt(0)%97] =1;
			}
			else if(ele[1].equals("hold"))
			{
				hold[ele[2].charAt(0)%97] =1;
			}
			else if(ele[1].equals("AE"))
			{
				arm =1;
			}
			
		}
		
		/*for(i=0;i<blocks;i++)
		{
			for(int j=0;j<blocks;j++)
			{
				System.out.print(on[i][j] + " ");
			}
			System.out.println();
		}*/
	}
    
	public int check(String state)
	{
		int i;
		String subG[] = state.split("['^']+");
		int flag=1;
		for(i=0;i<subG.length;i++)
		{
			String ele[] = subG[i].split("[() ]+");
	
			if(ele[1].equals("on") && on[(int)ele[2].charAt(0)%97][(int)ele[3].charAt(0)%97] == 1)
			{
				flag=1;
			}
			else if(ele[1].equals("ontable") && ontable[ele[2].charAt(0)%97] ==1)
			{
				flag =1;
			}
			else if(ele[1].equals("clear") && clear[ele[2].charAt(0)%97] ==1)
			{
				flag= 1;
			}
			else if(ele[1].equals("hold") && hold[ele[2].charAt(0)%97] ==1)
			{
				flag= 1;
			}
			else if(ele[1].equals("AE") && arm==1)
			{
				flag= 1;
			}
			else
			  return 0;
		}
		return flag;
	}
	
	public int checktop(char c)
	{
		int i=0;
		for(i=0;i<blocks;i++)
		{
			if(on[i][c%97]==1)
				return i; 
		}
		return -1;
	}
	
	public void performAction(String act)
	{
		String ele[] = act.split("[() ]+");
		if(act.contains("pick"))
		  {
			 ontable[ele[2].charAt(0)%97]=0;
			 clear[ele[2].charAt(0)%97]=0;
			 hold[ele[2].charAt(0)%97]=0;
			 arm=0;
	  	  }
		else if(act.contains("unstack"))
		{
			hold[ele[2].charAt(0)%97]=1;
			clear[ele[2].charAt(0)%97]=0;
			clear[ele[3].charAt(0)%97]=1;
			on[ele[2].charAt(0)%97][ele[3].charAt(0)%97]=0;
			arm=0;
		}
		else if(act.contains("release"))
		{
			ontable[ele[2].charAt(0)%97]=1;
			clear[ele[2].charAt(0)%97]=1;
			hold[ele[2].charAt(0)%97]=0;
			arm=1;
		}
		else if(act.contains("stack"))
		{
			hold[ele[2].charAt(0)%97]=0;
			clear[ele[2].charAt(0)%97]=1;
			clear[ele[3].charAt(0)%97]=0;
			on[ele[2].charAt(0)%97][ele[3].charAt(0)%97]=1;
			arm=1;
		}
	}
	
	/*public static void main(String args[])
	{
		State s = new State(3,"(on a b)^(clear c)\n");
		
	}*/
	
}
