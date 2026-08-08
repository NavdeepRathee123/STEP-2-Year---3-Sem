import java.util.*;
class Mini_Arcade
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the no. of rounds you want to play");
        int n=sc.nextInt();
        Random r=new Random();
        int win=0 , lose=0 , draw=0;
        System.out.println("Please Choose The move you want to play");
        System.out.println("1.Rock , 2.Paper , 3.Scissor");
        String choice[]= {"Rock ","Paper","Scissor"};
        int ro=0;
        String user_move = sc.nextLine();
        while(n>0)
        {
            ro=ro+1;
            int i=r.nextInt(choice.length);
            String comp_move = choice[i];
            user_move = sc.nextLine();
            if(comp_move.equals(user_move))
            {
                System.out.println("Round No. "+ro);
                System.out.println("Your Move : "+user_move);
                System.out.println("Comuter's move : "+comp_move);
                System.out.println("It is a draw");
                draw++;
            }
            else if((comp_move.equals("Rock") && user_move.equals("Paper"))||(comp_move.equals("Scissor") && user_move.equals("Rock"))||(comp_move.equals("Paper") && user_move.equals("Scissor")))
            {
                System.out.println("Round No. "+ro);
                System.out.println("Your Move : "+user_move);
                System.out.println("Comuter's move : "+comp_move);
                System.out.println("You win");
                win++;
            }
            else if((comp_move.equals("Rock") && user_move.equals("Scissor"))||(comp_move.equals("Scissor") && user_move.equals("Paper"))||(comp_move.equals("Paper") && user_move.equals("Rock")))
            {
                System.out.println("Round No. "+ro);
                System.out.println("Your Move : "+user_move);
                System.out.println("Comuter's move : "+comp_move);
                System.out.println("You Loses");
                lose++;
            }
            else
            {
                System.out.println("Invalid Input");
            }
            n--;
        }
        System.out.println("----- Game Summary --------");
        System.out.println("Total wins : "+win);
        System.out.println("Total Loses : "+lose);
        System.out.println("Total Draws : "+draw);
        float percentage=(win*100)/ro;
        System.out.println("Player's Win Percentage : "+percentage);
    }
}