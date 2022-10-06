import java.util.*;
import java.io.*;

class Point{

    private int x,y;
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    int getX(){
        return this.x;
    }

    int getY(){
        return this.y;
    }

    void setX(int value){
        this.x=value;
    }

    void setY(int value){
        this.y=value;
    }
}

class SnakeGame{
    
    private int boardSize;
    private char[][] board;

    private Point[] snakeMovements;

    SnakeGame(int boardSize){
        this.boardSize=boardSize;
        this.initializeBoard();
    }

    void clearBoard(){

        for(int i=0;i<this.boardSize;i++){
            for(int j=0;j<this.boardSize;j++)
                this.board[i][j]='-';
        }

    }

    void initializeBoard(){

        this.board=new char[boardSize][boardSize];

        this.clearBoard();

        this.snakeMovements=new Point[this.boardSize-2];

        // setting up head
        this.board[1][1]='H';
        this.snakeMovements[0]=new Point(1,1);

        // setting up body
        for(int j=2;j<this.snakeMovements.length+1;j++){
            this.board[1][j]='0';
            this.snakeMovements[j-1]=new Point(1,j);
        }

        // setting up tail
        this.board[1][this.boardSize-3]='T';
        this.snakeMovements[this.boardSize-3]=new Point(1,this.boardSize-2);

    }

    // - - - - -
    // - H o o T
    // - - - - -
    // - - - - -
    // - - - - - 

    void displayBoard(){

        for(int i=0;i<this.boardSize;i++){
            for(int j=0;j<this.boardSize;j++){
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }

    boolean isSnakeCrashed(){

        for(int i=1;i<this.snakeMovements.length;i++){
            if(this.snakeMovements[i].getX()==this.snakeMovements[0].getX() && this.snakeMovements[i].getY()==this.snakeMovements[0].getY())
                return true;
        }   

        return false;

    }

    void drawSnake(){

        this.clearBoard();

        for(int i=1;i<this.snakeMovements.length-1;i++){
            this.board[this.snakeMovements[i].getX()][this.snakeMovements[i].getY()]=
            '0';
        }
        this.board[this.snakeMovements[this.boardSize-3].getX()][this.snakeMovements[this.boardSize-3].getY()]=
            'T';
        this.board[this.snakeMovements[0].getX()][this.snakeMovements[0].getY()]=
            'H';
        
        if(this.isSnakeCrashed()){
            this.displayBoard();
            System.out.println("Crashed! Game Over!!!");
            System.exit(0);

        }
        

    }
    void startGame(){

        System.out.print("Shall We Start ? (Y/N) :  ");        
        Scanner sc =new Scanner(System.in);
        char choice=sc.nextLine().charAt(0);
        if(choice=='Y' || choice=='y'){
            this.displayBoard();
            char keyPress='a';
            do{

                System.out.print("Enter Your Movement (W=up, A=left, S=down, D=right | E=Exit) : ");        
                keyPress = sc.next().charAt(0);

                switch(keyPress){
                    case 'W': case 'w':
                                        if(this.snakeMovements[0].getX()==this.snakeMovements[1].getX()+1)
                                            System.out.println("Cannot Move Upwards when facing down");
                                        else{
                                            for(int j=1;j<this.snakeMovements.length-1;j++){
                                                System.out.print(this.snakeMovements[j].getX());
                                                System.out.print(","+this.snakeMovements[j].getY()+" ");
                                            }
                                            for(int i=this.snakeMovements.length-1;i>0;i--){
                                                this.snakeMovements[i].setX(this.snakeMovements[i-1].getX());
                                                this.snakeMovements[i].setY(this.snakeMovements[i-1].getY());
                                            }
                                            System.out.println();
                                            for(int j=1;j<this.snakeMovements.length-1;j++){
                                                System.out.print(this.snakeMovements[j].getX());
                                                System.out.print(","+this.snakeMovements[j].getY()+" ");
                                            }
                                            System.out.println();
                                            if(this.snakeMovements[0].getX()==0)
                                                this.snakeMovements[0].setX(this.boardSize-1);

                                            this.snakeMovements[0].setX(this.snakeMovements[0].getX()-1);
                                        }
                                        this.drawSnake();
                                        this.displayBoard();
                                        break;
                    case 'S': case 's':
                                        if(this.snakeMovements[0].getX()==this.snakeMovements[1].getX()-1)
                                            System.out.println("Cannot Move Downwards when facing up");
                                        else{
                                            for(int i=this.snakeMovements.length-1;i>0;i--){
                                                this.snakeMovements[i].setX(this.snakeMovements[i-1].getX());
                                                this.snakeMovements[i].setY(this.snakeMovements[i-1].getY());
                                            }
                                            if(this.snakeMovements[0].getX()==this.boardSize-1)
                                                this.snakeMovements[0].setX(0);

                                            this.snakeMovements[0].setX(this.snakeMovements[0].getX()+1);
                                        }
                                        this.drawSnake();
                                        this.displayBoard();
                                        break;
                    case 'A': case 'a':
                                        if(this.snakeMovements[0].getY()==this.snakeMovements[1].getY()+1)
                                            System.out.println("Cannot Move Left when facing right");
                                        else{
                                            for(int i=this.snakeMovements.length-1;i>0;i--){
                                                this.snakeMovements[i].setX(this.snakeMovements[i-1].getX());
                                                this.snakeMovements[i].setY(this.snakeMovements[i-1].getY());
                                            }
                                            if(this.snakeMovements[0].getY()==0)
                                                this.snakeMovements[0].setY(this.boardSize-1);

                                            this.snakeMovements[0].setY(this.snakeMovements[0].getY()-1);
                                        }
                                        this.drawSnake();
                                        this.displayBoard();
                                        break;
                    case 'D': case 'd':
                                        if(this.snakeMovements[0].getY()==this.snakeMovements[1].getY()-1)
                                            System.out.println("Cannot Move Right when facing left");
                                        else{
                                            for(int i=this.snakeMovements.length-1;i>0;i--){
                                                this.snakeMovements[i].setX(this.snakeMovements[i-1].getX());
                                                this.snakeMovements[i].setY(this.snakeMovements[i-1].getY());
                                            }
                                            if(this.snakeMovements[0].getY()==this.boardSize-1)
                                                this.snakeMovements[0].setY(0);

                                            this.snakeMovements[0].setY(this.snakeMovements[0].getY()+1);
                                        }
                                        this.drawSnake();
                                        this.displayBoard();
                                        break;
                    case 'E' : case 'e':
                                        System.out.println("Game Over!");
                                        System.exit(0);
                                        break;
                    default:
                                        System.out.println("Enter Valid Keys");
                }

            }while(keyPress!='e' && keyPress!='E');

        }
        else{
            System.out.println("Run Me When You are Ready");
        }

        sc.close();

    }



}


class Main{

    public static void main(String args[]) {
 
        System.out.print("Enter Board Size (>4): ");  
        Scanner sc= new Scanner(System.in);
        int boardSize=sc.nextInt();

        SnakeGame snakeGame = new SnakeGame(boardSize);
        snakeGame.startGame();
        sc.close();
    }
    
}