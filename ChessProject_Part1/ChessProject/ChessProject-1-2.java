import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}


	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}

  private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}

  private Boolean checkKingNear(int x , int y)
  {
    if(
    ((piecePresent(x, y + 75)) && getSquarePieceName(x, y +75).contains("king"))||
    ((piecePresent(x, y - 75)) && getSquarePieceName(x, y -75).contains("king"))||
    ((piecePresent(x + 75, y )) && getSquarePieceName(x +75, y).contains("king"))||
    ((piecePresent(x - 75, y )) && getSquarePieceName(x -75, y).contains("king"))||
    ((piecePresent(x + 75, y + 75 )) && getSquarePieceName(x + 75, y + 75).contains("king"))||
    ((piecePresent(x + 75, y - 75 )) && getSquarePieceName(x + 75, y - 75).contains("king"))||
    ((piecePresent(x - 75, y + 75 )) && getSquarePieceName(x - 75, y + 75).contains("king"))||
    ((piecePresent(x - 75, y - 75 )) && getSquarePieceName(x - 75, y - 75).contains("king"))
    )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  private String getSquarePieceName(int x , int y)
  {
    Component c = chessBoard.findComponentAt(x, y);
      if((c instanceof JLabel))
      {
        JLabel awaitingPiece = (JLabel)c;
        String tmpl = awaitingPiece.getIcon().toString();
        return tmpl;
      }
      else
      {
        return "";
      }
  }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

    chessPiece.setVisible(false);
		Boolean success =false;
    Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
    /*
      Getting information of the user actions
    */
    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("-----------------------------------------------");
    System.out.println("The piece that is being moved is : " +pieceName);
    System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
    System.out.println("The xMovement is : "+xMovement);
    System.out.println("The yMovement is : "+yMovement);
    System.out.println("The landing coordinates are : "+"( "+landingX+","+landingY+")");
    System.out.println("-----------------------------------------------");
    /*
      Getting information of the user actions
    */
    Boolean validMove = false;

    //Black Queen code
    if (pieceName.contains("BlackQueen")) {// game creates conditions for this piece if pieceName = ""
  		int newY = e.getY() / 75;//new e.get variable value Y
  		int newX = e.getX() / 75;// same X
  		boolean inTheWay = false;//variable intheway = false .. dont do
  		int distance = Math.abs(startX - newX);//calculation distance of starting position to where landed
  		if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
        //if the movement is less then 0 or greater then 7 rows/columns
  			validMove = false;
  		}
      else
  		{
  			validMove = true;
            //the following methods checking if there is a piece along the line of a potential move
            //these methods calculate after mousePressed(MouseEvent	e) method. the Boolean intheway is set to true
            // meaning yes there is a piece in the way and we cannot move on that square or after it
  		if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
        //if the lenght of the movement on each axis =eachother as they have to be = since diagonal movement
  			if ((startX - landingX < 0) && (startY - landingY < 0)) {
  				for (int i = 0; i < distance; i++) {//for loop .. checking distance on possible movement along <0 direction
  					if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
              //if piece is on a square of the line in which bishup could move higher on x&y on board
  						inTheWay = true;
  					}
  				}
  			}
        else if ((startX - landingX < 0) && (startY - landingY > 0)) {
  				for (int i = 0; i < distance; i++) {
  					if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
              //if piece is on a square of the line in which bishup could move..higher on x lower on y on board
  						inTheWay = true;
  					}
  				}
  			} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
  				for (int i = 0; i < distance; i++) {
  					if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
              //if piece is on a square of the line in which bishup could move.. lower oon x&y on board
  						inTheWay = true;
  					}
  				}
  			} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
  				for (int i = 0; i < distance; i++) {
  					if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
              //if piece is on a square of the line in which bishup could move.. lower x higher y on board
  						inTheWay = true;
  					}
  				}
  			}
  				if (inTheWay) {
  					validMove = false;// if something on the way dont move there or past
  				}

          else {
  					if (piecePresent(e.getX(), (e.getY()))) {
  						if (pieceName.contains("Black")) { //piece name your moving
  							if (checkBlackOponent(e.getX(), e.getY())) { //checking if the white oponent on the square
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						} else {
  							if (checkWhiteOponent(e.getX(), e.getY())) { //checking if the black oponent on the square
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						}
  					} else {
  						validMove = true;
  					}
  				}
  			}
  			else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0) && (Math.abs(landingY - startY) !=0))) {
  				//is from where rook starts to lands is not 0 on x , = 0 on y ..or.. = 0 on x and not y
          if (Math.abs(startX - landingX) != 0) {
          //calculation on starting x to landing x is not 0
  					if (startX - landingX > 0) {//if move > 0 on x axis

  						for (int i = 0; i < xMovement; i++) {
  							if (piecePresent(initialX - (i * 75), e.getY())) {//if there a piece on a square along the path going onboard
  								inTheWay = true;//somethhing there no move
  								break;
  							} else {
  								inTheWay = false;//else go ahead
  							}
  						}
  					} else {// same calculation as above except going opposite direction
  						for (int i = 0; i < xMovement; i++) {
  							if (piecePresent(initialX + (i * 75), e.getY())) {
  								inTheWay = true;
  								break;
  							} else {
  								inTheWay = false;
  							}
  						}
  					}
  				} else {

  					if (startY - newY > 0) {//move on yaxis greater than 0

  						for (int i = 0; i < yMovement; i++) {//check path
  							if (piecePresent(e.getX(), initialY - (i * 75))) {//if piece on path goig up
  								inTheWay = true;//no move
  								break;
  							} else {
  								inTheWay = false;// go ahead
  							}
  						}
  					} else {
  						for (int i = 0; i < yMovement; i++) {//same as above opposite direction
  							if (piecePresent(e.getX(), initialY + (i * 75))) {
  								inTheWay = true;
  								break;
  							} else {
  								inTheWay = false;
  							}
  						}
  					}
  				}
  				if (inTheWay) {
  					validMove = false;
  				} else {
  					if (piecePresent(e.getX(), (e.getY()))) {//piece on square tyour moving to
  						if (pieceName.contains("Black")) {//piece yourmoving
  							if (checkBlackOponent(e.getX(), e.getY())) {//use check white method..oponet piece black
  								validMove = true;//if piece white then go
  							} else {
  								validMove = false;//else no move
  							}
  						} else {
  							if (checkWhiteOponent(e.getX(), e.getY())) {
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						}
  					} else {
  						validMove = true;
  					}
  				}
  				} else {
  				validMove = false;
  				}
  		}
		}
    //Black Queen

    //White Queen
    else if (pieceName.contains("WhiteQueen")) {
  		int newY = e.getY() / 75;//new e.get variable value Y
  		int newX = e.getX() / 75;// same X
  		boolean inTheWay = false;//variable intheway = false .. dont do
  		int distance = Math.abs(startX - newX);//calculation distance of starting position to where landed
  		if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
        //if the movement is less then 0 or greater then 7 rows/columns
  			validMove = false;
  		} else
  		{
  			validMove = true;

        //thefollowing methods checking if there is a piece along th line of a potential move
            //these methods calculate after mousePressed(MouseEvent	e) method. the Boolean intheway is set to true
            // meaning yes there is a piece in the way and we cannot move on that square or after it
  		if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
        //if the lenght of the movement on each axis =eachother as they have to be = since diagonal movement

  			if ((startX - landingX < 0) && (startY - landingY < 0)) {
  				for (int i = 0; i < distance; i++) {//for loop .. checking distance on possible movement along <0 direction
  					if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
              //if piece is on a square of the line in which bishup could move higher on x&y on board
  						inTheWay = true;
  					}
  				}
  			} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
  				for (int i = 0; i < distance; i++) {//for loop .. checking distance on possible movement along <0 direction
  					if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
              //if piece is on a square of the line in which bishup could move..higher on x lower on y on board
  						inTheWay = true;
  					}
  				}
  			} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
  				for (int i = 0; i < distance; i++) {//for loop .. checking distance on possible movement along <0 direction
  					if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                //if piece is on a square of the line in which bishup could move.. lower oon x&y on board
  						inTheWay = true;
  					}
  				}
  			} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
  				for (int i = 0; i < distance; i++) {//for loop .. checking distance on possible movement along <0 direction
  					if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                //if piece is on a square of the line in which bishup could move.. lower x higher y on board
  						inTheWay = true;
  					}
  				}
  			}
  				if (inTheWay) {
  					validMove = false;// if something on the way dont move there or past
  				}
          else {
  					if (piecePresent(e.getX(), (e.getY()))) {
  						if (pieceName.contains("White")) {//piece nme your moving
  							if (checkWhiteOponent(e.getX(), e.getY())) {//checking if oponet black on square
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						} else {
  							if (checkBlackOponent(e.getX(), e.getY())) {//checking if oponet white on square
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						}
  					} else {
  						validMove = true;//if nothing there then move
  					}
  				}
  			}
  			else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0) && (Math.abs(landingY - startY) !=0))) {
            //is from where rook starts to lands is not 0 on x , = 0 on y ..or.. = 0 on x and not y
  				if (Math.abs(startX - landingX) != 0) {//calculation on starting x to landing x is not 0

  					if (startX - landingX > 0) {//if move > 0 on x axis

  						for (int i = 0; i < xMovement; i++) {// loop through to find possible path
  							if (piecePresent(initialX - (i * 75), e.getY())) {//if there a piece on a square along the path going onboard
  								inTheWay = true;//somethhing there no move
  								break;
  							} else {
  								inTheWay = false;//else go ahead
  							}
  						}
  					} else {// same calculation as above except going opposite direction
  						for (int i = 0; i < xMovement; i++) {
  							if (piecePresent(initialX + (i * 75), e.getY())) {
  								inTheWay = true;
  								break;
  							} else {
  								inTheWay = false;
  							}
  						}
  					}
  				} else {//same calculations as above but for yaxis
  					if (startY - newY > 0) {//move on yaxis greater than 0

  						for (int i = 0; i < yMovement; i++) {//check path
  							if (piecePresent(e.getX(), initialY - (i * 75))) {//if piece on path goig up
  								inTheWay = true;//no move
  								break;
  							} else {
  								inTheWay = false;// go ahead
  							}
  						}
  					} else {//same as above opposite direction
  						for (int i = 0; i < yMovement; i++) {
  							if (piecePresent(e.getX(), initialY + (i * 75))) {
  								inTheWay = true;
  								break;
  							} else {
  								inTheWay = false;
  							}
  						}
  					}
  				}
  				if (inTheWay) {
  					validMove = false;
  				} else {
  					if (piecePresent(e.getX(), (e.getY()))) {//piece on square tyour moving to
  						if (pieceName.contains("White")) {//piece yourmoving
  							if (checkWhiteOponent(e.getX(), e.getY())) {//use check white method..oponet piece black
  								validMove = true;//if piece white then go
  							} else {
  								validMove = false;//else no move
  							}
  						} else {
  							if (checkBlackOponent(e.getX(), e.getY())) {
  								validMove = true;
  							} else {
  								validMove = false;
  							}
  						}
  					} else {
  						validMove = true;
  					}
  				}
  				} else {
  				validMove = false;
  				}
  		}
		}
    //White Queen

    //White King
    else if(pieceName.equals("WhiteKing")){
      if((yMovement<=1)&&(xMovement <= 1)){//if movement is <=1 in any direction then ok
        if(!piecePresent(e.getX(), e.getY())){//once no piece is on that square
            validMove = true;//go ahead
          }
          else{//if piece on square
                if (checkWhiteOponent(e.getX(), e.getY())) {//check if its oponet piece
                  validMove = true;//take piece
                }
                else {
                  validMove = false;//no move
          }
        }
  }
}
    //White King

    //Black King
    if(pieceName.equals("BlackKing")){
                if(((landingX < 0) || (landingX > 7))||((landingX<0) || (landingY > 7))){
                  validMove = false;
                }

                else{

                  if((xMovement<=1) && (yMovement<=1)){
                    if(!KingNear(e.getX(), e.getY())){
                    if(!piecePresent(e.getX(),e.getY())){
                      whiteTurn = true;
                      validMove = true;
                    }
                    else{
                          if(checkBlackOpponent(e.getX(), e.getY())){
                           whiteTurn = true;
                           validMove = true;
                          }
                          else{
                            validMove = false;
                          }
                        }
                    }
                  }

                  else{
                    validMove = false;
                  }
                }
              }
    //Black king


    //White Knight
    else if(pieceName.equals("WhiteKnight")){
      if(((xMovement == 1)&&(yMovement ==2))||((xMovement==2)&&(yMovement==1))){
        //movement on X axis is 1 and 2 squares on y..or..2 on x and 1 square on y
          if((!piecePresent(e.getX(),e.getY()))){//if no piece there then move
              validMove = true;
          }
          else{
              if(pieceName.contains("Black")){//piece your moving
                  if(checkBlackOponent(e.getX(), e.getY())){//checkingpopnet is white
                  validMove = false;//dont take
              }
          }
              else{
                  if(checkWhiteOponent(e.getX(), e.getY())){//checking oponet is black
                      validMove = true;//take oponet
                  }
              }
          }
      }
    }
    //White Knight

    //Black Knight
    else if(pieceName.equals("BlackKnight")){
      if(((xMovement == 1)&&(yMovement ==2))||((xMovement==2)&&(yMovement==1))){
          //movement on X axis is 1 and 2 squares on y..or..2 on x and 1 square on y
          if((!piecePresent(e.getX(),e.getY()))){//if no piece there then move
              validMove = true;
          }
          else{
              if(pieceName.contains("White")){//piece your moving
                  if(checkWhiteOponent(e.getX(), e.getY())){//checking oponet is black
                  validMove = false;//dont take
              }
          }
              else{
                  if(checkBlackOponent(e.getX(), e.getY())){//checkingpopnet is white
                      validMove = true;//take oponet
                  }
              }
          }
      }
    }
    //Black Knight

    //White Rook
    else if(pieceName.equals("WhiteRook")){
          Boolean inTheWay = false;//variable intheway = false .. dont do
          if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
              //if the movement is less then 0 or greater then 7 rows/columns
              validMove = false;
          }
          else{
              if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) ==0))|| ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
                //is from where rook starts to lands is not 0 on x ,but = 0 on y ..or.. = 0 on x and not y
                  if(Math.abs(startX-landingX)!=0){//calculation on starting x to landing x is not 0
                      xMovement = Math.abs(startX-landingX);//new xMovemnet variable = this move
                      if(startX-landingX > 0){//if move > 0 on x axis
                          for(int i=0;i < xMovement;i++){// loop through to find possible path
                              if(piecePresent(initialX-(i*75),e.getY())){//if there a piece on a square along the path going onboard
                              inTheWay = true;//somethhing there no move
                              break;
                              }
                              else{
                                  inTheWay = false;//else go ahead
                              }
                          }
                      }
                      else{// same calculation as above except going opposite direction
                          for(int i=0;i < xMovement;i++){
                              if(piecePresent(initialX+(i*75), e.getY())){
                                  inTheWay = true;
                                  break;
                              }
                              else{
                                  inTheWay = false;
                              }
                          }
                      }
                  }
                  else{//same calculations as above but for yaxis
                      yMovement = Math.abs(startY-landingY);
                      if(startY-landingY > 0){//move on yaxis greater than 0
                          for(int i=0;i < yMovement;i++){//check path
                              if(piecePresent(e.getX(), initialY-(i*75))){//if piece on path goig up
                              inTheWay = true;//no move
                              break;
                              }
                          else{
                              inTheWay = false;//go ahead
                          }
                      }
                  }
                  else{
                      for(int i=0;i < yMovement;i++){//same as above opposite direction
                          if(piecePresent(e.getX(),initialY+(i*75))){
                              inTheWay = true;
                              break;
                          }
                          else{
                              inTheWay = false;
                          }
                      }
                  }
              }

              if(inTheWay){
                  validMove = false;
              }
              else{
                  if(piecePresent(e.getX(), (e.getY()))){//piece on square tyour moving to
                      if(pieceName.equals("WhiteRook")){//piece yourmoving
                          if(checkWhiteOponent(e.getX(), e.getY())){//use check white method
                              validMove = true;//if piece white then go
                          }
                          else{
                              validMove = false;//else no move
                          }
                      }
                      else{
                          if(checkBlackOponent(e.getX(), e.getY())){
                              validMove = true;
                          }
                          else{
                              validMove = false;
                          }
                      }
                  }
                  else{
                      validMove = true;
                  }
              }
          }
          else{
              validMove = false;
          }
      }
    }
    //White Rook

    //Black Rook
    else if(pieceName.equals("BlackRook")){
          Boolean inTheWay = false;//variable intheway = false .. dont do
          if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
            //if the movement is less then 0 or greater then 7 rows/columns
              validMove = false;
          }
          else{
              if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) ==0))|| ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
                //is from where rook starts to lands is not 0 on x , = 0 on y ..or.. = 0 on x and not y

                  if(Math.abs(startX-landingX)!=0){//calculation on starting x to landing x is not 0
                      xMovement = Math.abs(startX-landingX); //new xMovemnet variable = this move
                      if(startX-landingX > 0){//if move > 0 on x axis
                          for(int i=0;i < xMovement;i++){ // loop through to find possible path
                              if(piecePresent(initialX-(i*75),e.getY())){//if there a piece on a square along the path going onboard
                              inTheWay = true;//somethhing there no move
                              break;
                              }
                              else{
                                  inTheWay = false;//else go ahead
                              }
                          }
                      }
                      else{// same calculation as above except going opposite direction
                          for(int i=0;i < xMovement;i++){
                              if(piecePresent(initialX+(i*75), e.getY())){
                                  inTheWay = true;
                                  break;
                              }
                              else{
                                  inTheWay = false;
                              }
                          }
                      }
                  }
                  else{//same calculations as above but for yaxis
                      yMovement = Math.abs(startY-landingY);
                      if(startY-landingY > 0){//move on yaxis greater than 0
                          for(int i=0;i < yMovement;i++){//check path
                              if(piecePresent(e.getX(), initialY-(i*75))){//if piece on path goig up
                              inTheWay = true;//no move
                              break;
                              }
                          else{
                              inTheWay = false;// go ahead
                          }
                      }
                  }
                  else{//same as above opposite direction
                      for(int i=0;i < yMovement;i++){
                          if(piecePresent(e.getX(),initialY+(i*75))){
                              inTheWay = true;
                              break;
                          }
                          else{
                              inTheWay = false;
                          }
                      }
                  }
              }

              if(inTheWay){
                  validMove = false;
              }
              else{
                  if(piecePresent(e.getX(), (e.getY()))){//piece on square tyour moving to
                      if(pieceName.equals("BlackRook")){//piece yourmoving
                          if(checkBlackOponent(e.getX(), e.getY())){//use check black method
                              validMove = true;//if piece white then go
                          }
                          else{
                              validMove = false;//else no move
                          }
                      }
                      else{
                          if(checkWhiteOponent(e.getX(), e.getY())){
                              validMove = true;
                          }
                          else{
                              validMove = false;
                          }
                      }
                  }
                  else{
                      validMove = true;
                  }
              }
          }
          else{
              validMove = false;
          }
      }
    }
    //Black Rook

    //White Bishup
    else if(pieceName.equals("WhiteBishup")){
              Boolean inTheWay = false;//variable intheway = false .. dont do
              int distance = Math.abs(startX-landingX);//calculation distance of starting position to where landed
              if(((landingX < 0) || (landingX > 7))||((landingY < 0) ||(landingY > 7))){
                //if the movement is less then 0 or greater then 7 rows/columns
              validMove = false;
          }
    else{
              validMove = true;

              //thefollowing methods checking if there is a piece along th line of a potential bishup move
            //these methods calculate after mousePressed(MouseEvent	e) method. the Boolean intheway is set to true
            // meaning yes there is a piece in the way and we cannot move on that square or after it
              if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
                  //if the lenght of the movement on each axis =eachother as they have to be = since diagonal movement
                if((startX-landingX < 0 )&&(startY-landingY < 0)){//if length of movement on each axis is lessthan 0
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX+(i*75)),(initialY+(i*75)))){
                              //if piece is on a square of the line in which bishup could move higher on x&y on board
                              inTheWay = true;
                          }
                      }
                  }
                  else if((startX-landingX < 0)&&(startY-landingY > 0)){
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
                              //if piece is on a square of the line in which bishup could move..higher on x lower on y on board
                              inTheWay = true;
                          }
                      }
                  }
                  else if((startX-landingX > 0)&&(startY-landingY > 0)){
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
                                //if piece is on a square of the line in which bishup could move.. lower oon x&y on board
                              inTheWay = true;
                          }
                      }
                  }
                  else if((startX-landingX > 0)&&(startY-landingY < 0)){
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
                              //if piece is on a square of the line in which bishup could move.. lower x higher y on board
                              inTheWay = true;
                          }
                      }
                  }
                  if(inTheWay){
                      validMove = false;// if something on the way dont move there or past
                  }
                  else{
                      if(piecePresent(e.getX(),(e.getY()))){
                          if(pieceName.equals("WhiteBishup")){//piece nme your moving
                              if(checkWhiteOponent(e.getX(), e.getY())){//checking if oponet black on square
                                  validMove = true;
                              }
                              else{
                                  validMove = false;
                              }
                          }
                          else{
                              if(checkBlackOponent(e.getX(), e.getY())){
                                  validMove = true;
                              }
                              else{
                                  validMove = false;
                              }
                          }
                      }
                      else{
                          validMove = true;
                      }
                  }
              }
              else{
                  validMove= false;
              }
          }
    }
    //White Bishop

    //Black Bishup
    else if(pieceName.equals("BlackBishup")){//if piece name black bishup
              Boolean inTheWay = false; //variable intheway = false .. dont do
              int distance = Math.abs(startX-landingX);//calculation distance of starting position to where landed
              if(((landingX < 0) || (landingX > 7))||((landingY < 0) ||(landingY > 7))){
                //if the movement is less then 0 or greater then 7 rows/columns
              validMove = false;//then this move is false
          }
    else{//else do this
              validMove = true;

              //thefollowing methods checking if there is a piece along th line of a potential bishup move
              //these methods calculate after mousePressed(MouseEvent	e) method. the Boolean intheway is set to true
              // meaning yes there is a piece in the way and we cannot move on that square or after it

              if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
                //if the lenght of the movement on each axis =eachother as they have to be = since diagonal movement

                if((startX-landingX < 0 )&&(startY-landingY < 0)){//if length of movement on each axis is lessthan 0
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX+(i*75)),(initialY+(i*75)))){
                            //if piece is on a square of the line in which bishup could move higher on x&y on board
                              inTheWay = true;//..piece in the way
                          }
                      }
                  }
                  else if((startX-landingX < 0)&&(startY-landingY > 0)){//movement on x axis less than0 & yaxis greater 0
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
                            //if piece is on a square of the line in which bishup could move..higher on x lower on y on board
                              inTheWay = true;
                          }
                      }
                  }
                  else if((startX-landingX > 0)&&(startY-landingY > 0)){//movement on x axis greater than0 & yaxis greater 0
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
                            //if piece is on a square of the line in which bishup could move.. lower oon x&y on board
                              inTheWay = true;
                          }
                      }
                  }
                  else if((startX-landingX > 0)&&(startY-landingY < 0)){//movement on x axis greater than0 & yaxis less 0
                      for(int i=0; i < distance;i++){//for loop .. checking distance on possible movement along <0 direction
                          if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
                            //if piece is on a square of the line in which bishup could move.. lower x higher y on board
                              inTheWay = true;
                          }
                      }
                  }
                  if(inTheWay){
                      validMove = false;// if something on the way dont move there or past
                  }
                  else{
                      if(piecePresent(e.getX(),(e.getY()))){
                          if(pieceName.equals("BlackBishup")){//piece nme your moving
                              if(checkBlackOponent(e.getX(), e.getY())){//checking if oponet white on square
                                  validMove = true;
                              }
                              else{
                                  validMove = false;
                              }
                          }
                          else{
                              if(checkWhiteOponent(e.getX(), e.getY())){
                                  validMove = true;
                              }
                              else{
                                  validMove = false;
                              }
                          }
                      }
                      else{
                          validMove = true;
                      }
                  }
              }
              else{
                  validMove= false;
              }
          }
    }
    //Black Bishup

    //Black Pawn
    else if(pieceName.equals("BlackPawn")){
        if(startY == 6){ //if starting poisiton is in the 6th row .. so if first move

        if(((yMovement==1)||(yMovement == 2))&&(startY > landingY)&&(xMovement ==0)){ // if black pawn is moving one or 2
          //squares forward then do these actions
            if(yMovement == 2){//if moved 2 squares
                if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()+75)))){
                  //if not piece present on square ahead and not on another square ahead (+75) then true to move
                validMove = true;
                }
            }
            else{
                if(!piecePresent(e.getX(), e.getY())){ // if there isnt a piece on the coordinates you are moving onto
                 validMove = true; // move equals true
                }
            }
        }
        else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){ //1 up the y axis and the landing makes sure the pawn has moved by changing yaxis0 to >, if its 1 on x axis also
           if(piecePresent(e.getX(), e.getY())){ // we then check if a piece is present on the square 1 up y axis and 1 up x
               if(checkBlackOponent(e.getX(), e.getY())){ //checking if the white piece is on square
                   validMove = true; // takes the oponent piece
               }
            }
        }
    }
    else{
            if((yMovement==1)&&(startY > landingY)&&(xMovement ==0)){ //1 up y axis and none on x
                if(!piecePresent(e.getX(), e.getY())){ //once no piece there on cordinates of move
                 validMove = true;//move there
                 if(landingY == 0){ //if the piece reaches the oponent's first row of the board
                   success = true; //the pawn turns to queen at the top/ bottom of the board
                 }
                }
            }
             else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){//if we move1 x and y axis
               if(piecePresent(e.getX(), e.getY())){ //if the piece is present there on coordinates
               if(checkBlackOponent(e.getX(), e.getY())){ //check its white piece on coordinates
                   validMove = true; //take this piece
                   if(landingY == 0){ //if the piece reaches the oponent's first row of the board
                     success = true; //the pawn turns to queen at the top/ bottom of the board
                   }
                 }
                   else{
                     validMove = false; //if not these conditions it wont move
                   }
                }
            }
            if(!validMove){ //if not valid move
        			int location=0;
        			if(startY ==0){
        				location = startX;
        			}
        			else{
        				location  = (startY*8)+startX;
        			}
        			String pieceLocation = pieceName+".png"; // This takes piece name of location
        			pieces = new JLabel( new ImageIcon(pieceLocation) );
        			panels = (JPanel)chessBoard.getComponent(location);//get component for panel on board
        		    panels.add(pieces);//this this new piece
        		}
        		else{
        			if(success){ // the pwan changes into a queen
        				int location = 0 + (e.getX()/75); // the location of the piece at the top or bottom of the board
        				if (c instanceof JLabel){
        	            	Container parent = c.getParent();
        	            	parent.remove(0);
        					pieces = new JLabel( new ImageIcon("BlackQueen.png") );
        					parent = (JPanel)chessBoard.getComponent(location);
        			    	parent.add(pieces);
        				}
        				else{
        					Container parent = (Container)c;
        	            	pieces = new JLabel( new ImageIcon("BlackQueen.png") );
        					parent = (JPanel)chessBoard.getComponent(location);
        			    	parent.add(pieces);
        				}
        			}
        			else{
        				if (c instanceof JLabel){
        	            	Container parent = c.getParent();
        	            	parent.remove(0);
        	            	parent.add( chessPiece );
        	        	}
        	        	else {
        	            	Container parent = (Container)c;
        	            	parent.add( chessPiece );
        	        	}
        	    		chessPiece.setVisible(true);
        			}
        		}
        }
    }
    //Black Pawn


    //White Pawn code
  		else if(pieceName.equals("WhitePawn")){
        if(startY == 1){
          if(((yMovement==1)||(yMovement == 2))&&(startY < landingY)&&(xMovement ==0)){ //moving the piece only 1 or 2 steps
            if(yMovement == 2){
                if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()-75)))){
                validMove = true;
                }
                else{
    							validMove = false;
    						}
            }
            else{
                if(!piecePresent(e.getX(), e.getY())){
                 validMove = true;
                }
                else{
    							validMove = false;
    						}
            }
        }
        else if((yMovement == 1)&&(startY < landingY)&&(xMovement == 1)){
           if(piecePresent(e.getX(), e.getY())){
               if(checkWhiteOponent(e.getX(), e.getY())){
                   validMove = true;
               }
            }
        }
    }
    else{
            if((yMovement==1)&&(startY < landingY)&&(xMovement ==0)){
                if(!piecePresent(e.getX(), e.getY())){
                validMove = true;
                  if(landingY == 7){
                   success = true;
                  }
                }
            }
            else if((yMovement == 1)&&(startY < landingY)&&(xMovement == 1)){
              if(piecePresent(e.getX(), e.getY())){
               if(checkWhiteOponent(e.getX(), e.getY())){
                   validMove = true;
                    if(landingY == 7){ // if the landing place is the last square of the board it allow the piece to change
                     success = true;
                   }
                 }
                }
            }
          }
      }

		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
		if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
			}
		else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
			}
		}
    }
    //White Pawn

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }

}
