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
	Boolean whiteTurn;


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

	    whiteTurn = true;
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
		This is a method to check if a piece is a White piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			oponent = true;
			// Message to show checkmate has been achieved
			if(tmp1.contains("King")){
				JOptionPane.showMessageDialog(null,"Game over, White has achieved checkmate");
				System.exit(0);
			}
		}
		else{
			oponent = false;
		}
		return oponent;
	}
		/*
				This is a method to check if a piece is a Black piece.
	*/
		private Boolean checkBlackOponent(int newX, int newY){
			Boolean oponent;
			Component c1 = chessBoard.findComponentAt(newX, newY);
			JLabel awaitingPiece = (JLabel)c1;
			String tmp1 = awaitingPiece.getIcon().toString();
			if(((tmp1.contains("White")))){
				oponent = true;
				// Message to show checkmate has been achieved
				if(tmp1.contains("King")){
					JOptionPane.showMessageDialog(null,"Game over, Black has achieved checkmate");
					System.exit(0);
				}
			}
			else{
				oponent = false;
			}
			return oponent;
	}
	 private String getPieceName(int x, int y){
		 // similar to the piece present method but instead of returning a boolean in is returing the name of the piece on the tile we want to check
	    Component c = chessBoard.findComponentAt(x, y);
	    if((c instanceof JLabel)){
	      JLabel awaitingPiece = (JLabel)c;
	      String tmp1 = awaitingPiece.getIcon().toString();
	      return tmp1;
	    }
	    else{
	      return "";
	    }
	  }
	  private Boolean KingNear(int x, int y){
	    if(((piecePresent(x, y + 75)) && getPieceName(x, y + 75).contains("King")) ||
	    //Checking the square ahead while moving down the board
	    ((piecePresent(x, y - 75)) && getPieceName(x, y - 75).contains("King")) ||
	    //Checking the square ahead while moving up the board
	    ((piecePresent(x + 75, y)) && getPieceName(x + 75, y).contains("King")) ||
	    //Checking the square ahead while moving to the right
	    ((piecePresent(x - 75, y)) && getPieceName(x - 75, y).contains("King")) ||
	    //Checking the square ahead while moving to the left
	    ((piecePresent(x + 75, y + 75)) && getPieceName(x + 75, y + 75).contains("King")) ||
	    //Checking the southwest path ahead
	    ((piecePresent(x + 75, y - 75)) && getPieceName(x + 75, y - 75).contains("King")) ||
	    // Checking the northwest path ahead
	    ((piecePresent(x - 75, y + 75)) && getPieceName(x - 75, y + 75).contains("King")) ||
	    // Checking the souteast path ahead
	    ((piecePresent(x - 75, y - 75)) && getPieceName(x - 75, y - 75).contains("King"))){
			// checking the northeast path ahead
	      return true;
	      //if true we know the piece ahead of the next tile in each direction is a King
	    }
	    else{
	      return false;
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
		Boolean progression = false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;

			/* information from the users actions */
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

			/* information from the users actions */



		if(!whiteTurn){
	//Black Queen
	if (pieceName.equals("BlackQueen")){
		int newY = e.getY() /75; //new e.get variable with the value Y
		int newX = e.getX() /75; // the same as above only with the value X
		boolean inTheWay = false; //inTheWay equals falses means dont do it
		int distance = Math.abs(startX-newX); //calculating the distance from start to end point
		if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
			//checking to see if the movement is less than 0 or greater than 7
			validMove = false;
		}
		else {
			validMove = true;
			whiteTurn = true;
			// this method allows to check if there is a piece in the way
			// using the after mousePressed(MouseEvent e) method to calculate
			// the boolean inTheWay set as true, it means that there is a piece there and we can move there or skip over the piece
			// allows queen to move diagonally

			if (Math.abs(startX - landingX) == Math.abs(startY - landingY)){
				// if the length of the movement on axis is equal, which is needed to move diagonally
				if ((startX-landingX <0)&&(startY-landingY <0)){
					for (int i =0; i < distance; i++){
						// for loop to check the distance on possible movement
						if(piecePresent((initialX + (i*75)), (initialY + (i*75)))){
							// check if the piece is on the square in line of the queen that,
							// could move up in the X&Y direction
						inTheWay = true;
						}
					}
				}
				else if ((startX -landingX <0)&& (startY - landingY > 0)){
					for (int i =0; i < distance; i++){
						if(piecePresent((initialX + (i*75)), (initialY - (i*75)))){
							// check if the piece is on the square in line of the queen that,
							// could move up in the X&Y direction
							inTheWay = true;
						}
					}
				}
				else if ((startX - landingX > 0) && (startY - landingY >0)){
					for (int i =0; i < distance; i++){
						if(piecePresent((initialX - (i*75)), (initialY - (i*75)))){
							// check if the piece is on the square in line of the queen that,
							// could move up the x direction and down the y direction
							inTheWay = true;
						}
					}
				}
				else if ((startX - landingX > 0) && (startY - landingY <0)){
					for (int i =0; i < distance; i++){
						if(piecePresent((initialX - (i*75)), (initialY +(i*75)))){
							// check if the piece is on the square in line of the queen that,
							// could move down the x direction and up the y direction
							inTheWay = true;
						}
					}
				}
				if(inTheWay){
					validMove = false;
					// if there is a piece is in the way, dont move on the piece or skip it
				}
				// this checks to see if the piece is an oponate or not
				//if an oponate it allows the queen to take the piece
				// if its one of its own pieces it goes back to orginal position
				else {
					if(piecePresent(e.getX(),(e.getY()))){
						if (pieceName.contains("Black")){
							if(checkBlackOponent(e.getX(),e.getY())){
								validMove = true;
								whiteTurn = true;
							}
							else {
								validMove = false;
							}
						}
						else {
							if (checkWhiteOponent(e.getX(),e.getY())){
								validMove = true;
								whiteTurn = true;
							}
							else {
								validMove = false;
							}
						}
					}
					else {
						validMove = true;
						whiteTurn = true;
					}
				}
			}
			//checking when the queen moves up and down back and forward
			//there there is no piece in the way
			//that you can go to that square or skip the piece in the square

			else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0)&&(Math.abs(startY - landingY) != 0))) {
			//is from where the queen starts to land is not 0 on x, = 0 on y
				//is from where the queen starts to land is not 0 on Y. = 0 on X

				if (Math.abs(startX - landingX) !=0){
					if (startX - landingX > 0){

						for (int i = 0; i < xMovement; i++){
							if(piecePresent(initialX - (i*75), e.getY())){
								//checking to see if there is a piece on the square
								inTheWay = true;
								// something on the square, cant move
								break;
							}
							else {
								inTheWay = false;
								//there is nothing on the square, move
							}
						}
					}
					else {
						//checking the oposite direction
						// same calculation as above
						for(int i = 0; i < xMovement; i++){
							if(piecePresent(initialX +(i*75), e.getY())){
								inTheWay = true;
								break;
							}
							else {
								inTheWay = false;
							}
						}
					}
				}
					else{

						if (startY - newY >0){
							//moving on the y axis greater than 0

							for(int i =0; i<yMovement; i++){
								//check the path
								if(piecePresent(e.getX(), initialY - (i * 75))){
									// checking the for piece going down
									inTheWay = true;
									// there is a piece in the way, no move
									break;
								}
								else {
									inTheWay = false;
									//there is no piece in the way, move
								}
							}
						}
						else {
							for(int i = 0; i <yMovement; i++){
								//same code as above but checking in the oposite direction
								if(piecePresent(e.getX(), initialY + (i *75))){
									inTheWay = true;
									break;
								}
								else {
									inTheWay = false;
								}
							}
						}
					}
					if(inTheWay){
						validMove = false;
					}
					else {
						if(piecePresent(e.getX(), (e.getY()))){
							// if there is a piece on the square you want to move to
							if(pieceName.equals("BlackQueen")){
								//the piece your moving
							if(checkBlackOponent(e.getX(), e.getY())){
								//checking if its an oponents piece
								validMove = true;
								//if oponents piece, move
								whiteTurn = true;
							}
							else {
								validMove = false;
								//if own piece, no move
							}
						}
						else {
							if (checkWhiteOponent(e.getX(), e.getY())){
								validMove = true;
								whiteTurn = true;
							}
							else {
								validMove = false;
						}
					}
				}
				else {
					validMove = true;
					whiteTurn = true;
				}
			}
		}
		else {
			validMove = false;
		}
	}
}
						// black king code
			else if (pieceName.equals("BlackKing")){
				if(((landingX < 0) || (landingX > 7))||((landingX<0) || (landingY >7))){
					validMove = false;
				}
				else {
					if((xMovement<=1)&& (yMovement<=1)){
						// this allows the king to only move 1 space at a time
						if(!KingNear(e.getX(), e.getY())){
							// if the other king isnt near
							if(!piecePresent(e.getX(),e.getY())){
								// if another piece isnt in that square
								validMove = true;
								whiteTurn = true;

							}
							else {
								//if there is a piece in the square
								if(checkBlackOponent(e.getX(),e.getY())){
									//check if it an oponent
									validMove = true;
									//if it is an oponent, take piece
									whiteTurn = true;
								}
								else {
									validMove = false;
									//if its own piece, king will return to orignal position
							}
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}


// The Knight code, the Knight can move in a L shape

	//BlackKnight
	else if (pieceName.equals("BlackKnight")){
				if(((xMovement ==1)&&(yMovement== 2))||((xMovement ==2)&&(yMovement==1))){
				// this allows the knife to move in a L shape
				if((!piecePresent(e.getX(), e.getY()))){
					//check to see if theres a piece in the spot
					validMove = true;
					whiteTurn = true;
				}
				else {
					if(pieceName.contains("White")){
						//moving your piece
					if(checkWhiteOponent(e.getX(),e.getY())){
						//check piece is Black
						validMove = false;
						//dont take your own piece
						}
					}

				else{
					if(checkBlackOponent(e.getX(),e.getY())){
						//checking to see if the piece is White
						validMove = true;
						// take oposition
						whiteTurn = true;
						}

					}

				}

			}
	}
	//Black Rook - moves up & down, side to side
			    else if(pieceName.equals("BlackRook")){
			          Boolean inTheWay = false;
			          //inTheWay equals false, no move
			          if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
			            //checking to see if the movement is less than 0 or greater 7
			              validMove = false;
			          }
			          else{
			              if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) ==0))|| ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
			                //is from where the Rook starts to land is not 0 on x = 0 on y
			                //is where the Rook starts to land is not 0 on y = 0 on x

			                  if(Math.abs(startX-landingX)!=0){
								  //calculation on starting x to landing x is not 0
			                      xMovement = Math.abs(startX-landingX);
			                      //new xMovemnet variable = this move
			                      if(startX-landingX > 0){
									  //if move > 0 on x axis
			                          for(int i=0;i < xMovement;i++){
										  // loop through to find possible path
			                              if(piecePresent(initialX-(i*75),e.getY())){
											  //checking to see if there is a piece on the square
			                              inTheWay = true;
			                              //somethhing on the square, no move
			                              break;
			                              }
			                              else{
			                                  inTheWay = false;
			                                  //there is nothing on the square, move
			                              }
			                          }
			                      }
			                      else{
									  // checking the oposite direction
									  //same calculation as above
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
			                  else{
								  //same calculations as above but for yaxis
			                      yMovement = Math.abs(startY-landingY);
			                      if(startY-landingY > 0){
									  //move on yaxis greater than 0
			                          for(int i=0;i < yMovement;i++){
										  //check path
			                              if(piecePresent(e.getX(), initialY-(i*75))){
											  //checking the for piece is going down
			                              inTheWay = true;
			                              //there is a piece in the way, no move
			                              break;
			                              }
			                          else{
			                              inTheWay = false;
			                              // no piece, move
			                          }
			                      }
			                  }
			                  else{
								  //same code as above but checking the oposite direction
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
			                  if(piecePresent(e.getX(), (e.getY()))){
								  //if there is a piece on the square you are moving too
			                      if(pieceName.equals("BlackRook")){
									  //name of piece your moving
			                          if(checkBlackOponent(e.getX(), e.getY())){
										  //checking to see if its a white piece
			                              validMove = true;
			                              //if its white piece, take piece
			                              whiteTurn = true;
			                          }
			                          else{
			                              validMove = false;
			                              //if its black piece, no move
			                          }
			                      }
			                      else{
			                          if(checkWhiteOponent(e.getX(), e.getY())){
			                              validMove = true;
			                              whiteTurn = true;
			                          }
			                          else{
			                              validMove = false;
			                          }
			                      }
			                  }
			                  else{
			                      validMove = true;
			                      whiteTurn = true;
			                  }
			              }
			          }
			          else{
			              validMove = false;
			          }
			      }
			    }



		// Black Bishop

		else if (pieceName.equals("BlackBishup")){
			//checked to make sure it was the bishop we were using
			Boolean inTheWay = false;
			int distance = Math.abs(startX-landingX);
			//calculates the distance from where the bishop started to where it lands
			if(((landingX <0) || (landingX > 7))|| ((landingY < 0) || (landingY >7))){
				// if the bishop moves more the 7 or less than 0 it is not a valid move
				validMove = false;
			}
			else{
				/*
				This method checks to make sure there isnt any pieces in the way of the diagonal movement
				it calculates after the mousePressed(MouseEvent e) method.
				using the boolen intheway set to be true, this means that there is a piece on the square
				and we can not move on that square or skip the square with the piece on it
				*/
				validMove = true;
				whiteTurn = true;

				if(Math.abs(startX-landingX)== Math.abs(startY-landingY)){
					//check to make sure the length of the movement is on each axis is equal to each other, so the bishop to move in diagonal
					if ((startX-landingX <0)&&(startY-landingY <0)){
						//if the length of the movement on each axis is less then 0
						for (int i = 0; i <distance; i++){
							// for loop, to check the distance on possible movement along the <0 direction
							if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
								// checked if there was a piece present
								inTheWay = true;
							}
						}
					}
 					else if ((startX-landingX < 0)&&(startY-landingY > 0)){
						for(int i=0;i < distance; i++){
							if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
								//check if the piece is on the square in life of the bishop that
								//could move up in the X&Y direction
								inTheWay = true;
							}
						}
					}
					else if ((startX-landingX > 0)&&(startY-landingY >0)){
						for(int i=0;i < distance; i++){
							if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
								//check if the piece is on the square in life of the bishop that
								//could move down in the X&Y direction
								inTheWay = true;
							}
						}
					}
					else if ((startX-landingX > 0)&&(startY-landingY < 0)){
						for(int i=0;i < distance; i++){
							if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
								//check if the piece is on the square in life of the bishop that
								//could move down the X and up the Y axis
								inTheWay = true;
							}
						}
					}
					if(inTheWay){
						validMove = false;
						//if there is a piece in the way, dont move on the piece or skip it
					}
					//this checks to see if the piece is an oponent or not
					// if oponent it allows the bishop to take that piece
					// if not oponent it returns bishop to orignal place
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("BlackBishup")){
								if(checkBlackOponent(e.getX(), e.getY())){
									//checking to see if your oponent is on the square
									validMove = true;
									whiteTurn = true;
								}
								else {
									validMove = false;
								}
							}
							else{
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true;
									whiteTurn = true;
								}
								else {
									validMove = false;
								}
							}
						}
						else {
							validMove = true;
							whiteTurn = true;
						}
					}
				}
				else {
					validMove = false;
				}
			}
		}

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/

		else if (pieceName.equals("BlackPawn")){
			//pawn starting code
			if (startY == 6){ //move black pawn from start 6

				/*
				if the pawn is making its first movement,
				it can either move one square or two squares.. in the y direction
				as long as we are moving up the board, we cant move back down
				no movement in the x Direction i.e back down
				*/

				if(((yMovement ==1)||(yMovement == 2))&&(startY > landingY)&&(xMovement == 0)){
					// this allows the pawn to move one or two squares while making sure it moves in the right direction
					if(yMovement == 2){
						//if pawn moves 2 squares
						if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), e.getY()+75))){
							// its true to move if no piece is present on the sqauare ahead
						validMove= true;
						whiteTurn = true;
						}

					}
					else{
						if(!piecePresent(e.getX(), e.getY())){
							//makes sure there are no pieces on the coordinates you are moving too

					validMove = true;
					whiteTurn = true;
				}
				}
			}
			else if ((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
				// if the pawn wants to move 1 space
				if (piecePresent(e.getX(),e.getY())){
					// checking to see if piece present
					if(checkBlackOponent(e.getX(), e.getY())){
						// check to make sure its a white pawn and not a black pawn being taken
						validMove = true;
						whiteTurn = true;
						}
					}
				}
			}
			//pawn starting code

			else { // this is where the pawn is making all subsequence moves
				if(((yMovement ==1))&&(startY > landingY)&&(xMovement == 0)){
					if(!piecePresent(e.getX(), e.getY())){
					validMove = true;
					whiteTurn = true;
					if(landingY == 0) {
						//if the black pawn has made it to the other players front row of the board
					success = true;
					//pawn turns into queen
				}
				}
			}
			else if ((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
				if (piecePresent(e.getX(),e.getY())){
					//if piece is there on cooradates
				if(checkBlackOponent(e.getX(), e.getY())){
					//check to see if its white
					validMove = true;
					//take place if white piece
					whiteTurn = true;
					if(landingY == 0) {
						success = true;
						}
					 }
					 else{
					validMove = false; //if not these conditions it wont move
				  }
			  	}
            }

       	 }
	}
			} // this closes the not white turn
 else {
	 //this is if its not a black piece do this
// White Queen
	if (pieceName.equals("WhiteQueen")){
			int newY = e.getY() /75; //new e.get variable with the value Y
			int newX = e.getX() /75; // the same as above only with the value X
			boolean inTheWay = false; //inTheWay equals falses means dont do it
			int distance = Math.abs(startX-newX); //calculating the distance from start to end point
			if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
				//checking to see if the movement is less than 0 or greater than 7
				validMove = false;
			}
			else {
				validMove = true;
				whiteTurn = false;

				// this method allows to check if there is a piece in the way
				// using the after mousePressed(MouseEvent e) method to calculate
				// the boolean inTheWay set as true, it means that there is a piece there and we can move there or skip over the piece
				// allows queen to move diagonally

				if (Math.abs(startX - landingX) == Math.abs(startY - landingY)){
					// if the length of the movement on axis is equal, which is needed to move diagonally
					if ((startX-landingX <0)&&(startY-landingY <0)){
						for (int i =0; i < distance; i++){
							// for loop to check the distance on possible movement
							if(piecePresent((initialX + (i*75)), (initialY + (i*75)))){
								// check if the piece is on the square in line of the queen that,
								// could move up in the X&Y direction
							inTheWay = true;
							}
						}
					}
					else if ((startX -landingX <0)&& (startY - landingY > 0)){
						for (int i =0; i < distance; i++){
							if(piecePresent((initialX + (i*75)), (initialY - (i*75)))){
								// check if the piece is on the square in line of the queen that,
								// could move up in the X&Y direction
								inTheWay = true;
							}
						}
					}
					else if ((startX - landingX > 0) && (startY - landingY >0)){
						for (int i =0; i < distance; i++){
							if(piecePresent((initialX - (i*75)), (initialY - (i*75)))){
								// check if the piece is on the square in line of the queen that,
								// could move up the x direction and down the y direction
								inTheWay = true;
							}
						}
					}
					else if ((startX - landingX > 0) && (startY - landingY <0)){
						for (int i =0; i < distance; i++){
							if(piecePresent((initialX - (i*75)), (initialY +(i*75)))){
								// check if the piece is on the square in line of the queen that,
								// could move down the x direction and up the y direction
								inTheWay = true;
							}
						}
					}
					if(inTheWay){
						validMove = false;
						// if there is a piece is in the way, dont move on the piece or skip it
					}
					// this checks to see if the piece is an oponate or not
					//if an oponate it allows the queen to take the piece
					// if its one of its own pieces it goes back to orginal position
					else {
						if(piecePresent(e.getX(),(e.getY()))){
							if (pieceName.contains("White")){ //checking if its, your own piece
								if(checkWhiteOponent(e.getX(),e.getY())){
									validMove = true;
									// if its is, no move
									whiteTurn = false;
								}
								else {
									validMove = false;
									// if its not, move
								}
							}
							else {
								if (checkBlackOponent(e.getX(),e.getY())){
									validMove = true;
									whiteTurn = false;
								}
								else {
									validMove = false;
								}
							}
						}
						else {
							validMove = true;
							whiteTurn = false;
						}
					}
				}
				//checking when the queen moves up and down back and forward
				//there there is no piece in the way
				//that you can go to that square or skip the piece in the square

				else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0)&&(Math.abs(startY - landingY) != 0))) {
				//is from where the queen starts to land is not 0 on x, = 0 on y
					//is from where the queen starts to land is not 0 on Y. = 0 on X

					if (Math.abs(startX - landingX) !=0){
						if (startX - landingX > 0){

							for (int i = 0; i < xMovement; i++){
								if(piecePresent(initialX - (i*75), e.getY())){
									//checking to see if there is a piece on the square
									inTheWay = true;
									// something on the square, cant move
									break;
								}
								else {
									inTheWay = false;
									//there is nothing on the square, move
								}
							}
						}
						else {
							//checking the oposite direction
							// same calculation as above
							for(int i = 0; i < xMovement; i++){
								if(piecePresent(initialX +(i*75), e.getY())){
									inTheWay = true;
									break;
								}
								else {
									inTheWay = false;
								}
							}
						}
					}
						else{

							if (startY - newY >0){
								//moving on the y axis greater than 0

								for(int i =0; i<yMovement; i++){
									//check the path
									if(piecePresent(e.getX(), initialY - (i * 75))){
										// checking the for piece going down
										inTheWay = true;
										// there is a piece in the way, no move
										break;
									}
									else {
										inTheWay = false;
										//there is no piece in the way, move
									}
								}
							}
							else {
								for(int i = 0; i <yMovement; i++){
									//same code as above but checking in the oposite direction
									if(piecePresent(e.getX(), initialY + (i *75))){
										inTheWay = true;
										break;
									}
									else {
										inTheWay = false;
									}
								}
							}
						}
						if(inTheWay){
							validMove = false;
						}
						else {
							if(piecePresent(e.getX(), (e.getY()))){
								// if there is a piece on the square you want to move to
								if(pieceName.equals("WhiteQueen")){
									//the piece your moving
								if(checkWhiteOponent(e.getX(), e.getY())){
									//checking if its an oponents piece
									validMove = true;
									//if oponents piece, move
									whiteTurn = false;
								}
								else {
									validMove = false;
									//if own piece, no move
								}
							}
							else {
								if (checkBlackOponent(e.getX(), e.getY())){
									validMove = true;
									whiteTurn = false;
								}
								else {
									validMove = false;
							}
						}
					}
					else {
						validMove = true;
						whiteTurn = false;
					}
				}
			}
			else {
				validMove = false;
			}
		}
	}

	//White King

	else if (pieceName.equals("WhiteKing")){
		if(((landingX < 0) || (landingX > 7))||((landingX<0) || (landingY >7))){
							validMove = false;
						}
						else {
							if((xMovement<=1)&& (yMovement<=1)){
								// this allows the king to only move 1 space at a time
								if(!KingNear(e.getX(), e.getY())){
									// if the other king isnt near
									if(!piecePresent(e.getX(),e.getY())){
										// if another piece isnt in that square
										validMove = true;
										whiteTurn = false;

									}
									else {
										//if there is a piece in the square
										if(checkWhiteOponent(e.getX(),e.getY())){
											//check if it an oponent
											validMove = true;
											//if it is an oponent, take piece
											whiteTurn = false;
										}
										else {
											validMove = false;
											//if its own piece, king will return to orignal position
									}
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}



	//White Knight
			else if (pieceName.equals("WhiteKnight")){
				if(((xMovement ==1)&&(yMovement== 2))||((xMovement ==2)&&(yMovement==1))){
				// this allows the knife to move in a L shape
				if((!piecePresent(e.getX(), e.getY()))){
					//check to see if theres a piece in the spot
					validMove = true;
					whiteTurn = false;
				}
				else {
					if(pieceName.contains("Black")){
						//moving your piece
					if(checkBlackOponent(e.getX(),e.getY())){
						//check piece is white
						validMove = false;
						//dont take your own piece
						}
					}

				else{
					if(checkWhiteOponent(e.getX(),e.getY())){
						//checking to see if the piece is black
						validMove = true;
						// take oposition
						whiteTurn = false;
						}

					}

				}

			}
	}

			//Rook moves up and down, side to side
			else if(pieceName.equals("WhiteRook")){
			          Boolean inTheWay = false;
			          //inTheWay equals false, no move
			          if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
			              //if the movement is less then 0 or greater then 7 rows/columns
			              validMove = false;
			          }
			          else{
			              if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) ==0))|| ((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
			                //if from where the Rook starts to land is not 0 on x = 0 on y
			                // is where the queen starts to land is not 0 on y = 0 on x
			                  if(Math.abs(startX-landingX)!=0){
								  //calculation on starting x to landing x is not 0
			                      xMovement = Math.abs(startX-landingX);
			                      //new xMovemnet variable = this move
			                      if(startX-landingX > 0){
									  //if move > 0 on x axis
			                          for(int i=0;i < xMovement;i++){
										  // loop through to find possible path
			                              if(piecePresent(initialX-(i*75),e.getY())){
											  //checking to see if there is a piece on the square
			                              inTheWay = true;
			                              //something on the square, no move
			                              break;
			                              }
			                              else{
			                                  inTheWay = false;
			                                  //nothing on the square, move
			                              }
			                          }
			                      }
			                      else{
									  // checking the oposite direction
									  //same calculation as above
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
			                  else{
								  //same calculations as above but for yaxis
			                      yMovement = Math.abs(startY-landingY);
			                      if(startY-landingY > 0){
									  //move on yaxis greater than 0
			                          for(int i=0;i < yMovement;i++){
										  //check path
			                              if(piecePresent(e.getX(), initialY-(i*75))){
											  //checking the piece is going down
			                              inTheWay = true;
			                              //there is a piece in the way, no move
			                              break;
			                              }
			                          else{
			                              inTheWay = false;
			                              //no piece, move
			                          }
			                      }
			                  }
			                  else{
								  //same code as above but check the oposite direction
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
			                  if(piecePresent(e.getX(), (e.getY()))){
								  //if there is a piece on the square you are moving too
			                      if(pieceName.equals("WhiteRook")){
									  //name of piece your moving
			                          if(checkWhiteOponent(e.getX(), e.getY())){
										  //checking to see if its a black piece
			                              validMove = true;
			                              //if its a black piece, take piece
			                              whiteTurn = false;
			                          }
			                          else{
			                              validMove = false;
			                              //if its white, no move
			                          }
			                      }
			                      else{
			                          if(checkBlackOponent(e.getX(), e.getY())){
			                              validMove = true;
			                              whiteTurn = false;
			                          }
			                          else{
			                              validMove = false;
			                          }
			                      }
			                  }
			                  else{
			                      validMove = true;
			                      whiteTurn = false;
			                  }
			              }
			          }
			          else{
			              validMove = false;
			          }
			      }
		    }
		    // White Bishop
						else if (pieceName.equals("WhiteBishup")){
									//checked to make sure it was the bishop we were using
									Boolean inTheWay = false;
									int distance = Math.abs(startX-landingX);
									//calculates the distance from where the bishop started to where it lands
									if(((landingX <0) || (landingX > 7))|| ((landingY < 0) || (landingY>7))){
										// if the bishop moves more the 7 or less than 0 it is not a valid move
										validMove = false;
									}
									else{
										/*
										This method checks to make sure there isnt any pieces in the way of the diagonal movement
										it calculates after the mousePressed(MouseEvent e) method.
										using the boolen intheway set to be true, this means that there is a piece on the square
										and we can not move on that square or skip the square with the piece on it
										*/
										validMove = true;
										whiteTurn = false;

										if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
											//check to make sure the length of the movement is on each axis is equal to each other, so the bishop to move in diagonal
											if ((startX-landingX <0)&&(startY-landingY <0)){
												//if the length of the movement on each axis is less then 0
												for (int i = 0; i <distance; i++){
													// for loop, to check the distance on possible movement along the <0 direction
													if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
														//check if the piece is on the square in life of the bishop that
														//could move up in the X&Y direction
														inTheWay = true;
													}
												}
											}
						 					else if ((startX-landingX < 0)&&(startY-landingY > 0)){
												for(int i=0;i < distance; i++){
													if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
														//check if the piece is on the square in life of the bishop that
											           // could move up X and down the Y axis
														inTheWay = true;
													}
												}
											}
											else if ((startX-landingX > 0)&&(startY-landingY >0)){
												for(int i=0;i < distance; i++){
													if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
														//check if the piece is on the square in life of the bishop that
														// could move down the x&y axis
														inTheWay = true;
													}
												}
											}
											else if ((startX-landingX > 0)&&(startY-landingY < 0)){
												for(int i=0;i < distance; i++){
													if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
														//check if the piece is on the square in life of the bishop that
														//could move down the x and up the y axis
														inTheWay = true;
													}
												}
											}
											if(inTheWay){
												validMove = false;
												//if there is a piece in the way, dont move or skip it
											}
											//checks to see if the piece is an oponent or not
											// if an oponent it allows the bishop to take the piece
											// if own piece, returns bishop to orignal position
											else{
												if(piecePresent(e.getX(), (e.getY()))){
													if(pieceName.contains("WhiteBishup")){
														//the name of the piece your moving
														if(checkWhiteOponent(e.getX(), e.getY())){
															//checking to see if your oponent is on the square
															validMove = true;
															whiteTurn = false;
														}
														else {
															validMove = false;
														}
													}
													else{
														if(checkBlackOponent(e.getX(), e.getY())){
															validMove = true;
															whiteTurn = false;
														}
														else {
															validMove = false;
														}
													}
												}
												else {
													validMove = true;
													whiteTurn = false;
												}
											}
										}
										else {
											validMove = false;
										}
									}
					}


    //White Pawn
		else if(pieceName.equals("WhitePawn")){

		        if(startY == 1){
		          if(((yMovement==1)||(yMovement == 2))&&(startY < landingY)&&(xMovement ==0)){ //moving the piece only 1 or 2 steps
		            if(yMovement == 2){
		                if((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()-75)))){
		                validMove = true;
		                whiteTurn = false;
		                }
		                else{
		    							validMove = false;
		    						}
		            }
		            else{
		                if(!piecePresent(e.getX(), e.getY())){
		                 validMove = true;
		                 whiteTurn = false;
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
		                   whiteTurn = false;

		               }
		            }
		        }
		    }
		    else{
		            if((yMovement==1)&&(startY < landingY)&&(xMovement ==0)){
		                if(!piecePresent(e.getX(), e.getY())){
		                validMove = true;
		                whiteTurn = false;
		                   if(landingY ==7){
						    // if pawn lands on the last row allow to change piece
						  	   progression = true;
						   }
		                }
		            }
		            else if((yMovement == 1)&&(startY < landingY)&&(xMovement == 1)){
		              if(piecePresent(e.getX(), e.getY())){
		               if(checkWhiteOponent(e.getX(), e.getY())){
		                   validMove = true;
		                   whiteTurn = false;
		                    if(landingY == 7){ // if the landing place is the last square of the board it allow the piece to change
		                     progression = true;
		                   }
		                 }
		                }
		            }
		          }
      }
  } // this ends white pieces turn
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
			if(progression){
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
				if(progression = true){
					success = false;
				}
			}
			else if (success){
				//the pawn changes to a queen
				int location = 0 + (e.getX()/75);
				//if the piece is at the top or bottom of the board
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
					if(progression = true){
					success = false;
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


