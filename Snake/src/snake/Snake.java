package snake;
import java.awt.Color; 
import java.awt.Container; 
import java.awt.Graphics; 
import java.awt.Toolkit; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.InputEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import javax.swing.*;
public class Snake extends JFrame implements ActionListener, KeyListener,Runnable 
{ 
	private JMenuBar menuBar;
	private JMenu youXiMenu,nanDuMenu,fenShuMenu,guanYuMenu;
	private JMenuItem kaiShiYouXi,exitItem,zuoZheItem,fenShuItem;
	private JMenuItem cJianDan,cPuTong,cKunNan;
	private int length = 6;
	private Toolkit toolkit;
	private int i,
				//ÒòÎªÐÂÔö¼ÓµÄ Í·²¿ÊÇÌí¼ÓÔÚ Ê³ÎïµÄÎ»ÖÃ£¬ËùÒÔÒªÏÈ±£´æÐÂµÄÍ·²¿Î»ÖÃ
				//Ô­ÏÈµÄÉíÌåÐèÒªÒÆ¶¯µ½ºóÃæ
				NewHeadLeftTopX,
				NewHeadLeftTopY,
				
				//ÒÆ¶¯µÄ·½Ïò
				m_SnakeDirection,
				//Ëæ»úµÄÊ³Îï ·½¸ñµÄ ×óÉÏ½ÇµÄ×ø±ê
				FoodLeftTopX,
				FoodLeftTopY,
				
				//ÊÇ·ñÐèÒªËæ»úÐÂµÄÊ³Îï?
				IsNeedNewFood=1,
				//Éß³Ùµ½ÁËÐÂµÄÊ³ÎïÁËÃ´?
				m_IsNeedEnLength=0,
				time;//bojectX,YÎªÊ³Îï×ø±ê,m_SnakeDirectionÎªÉßÇ°½ø·½Ïò
	private int difficult=2;
	//x·½Ïò50¸ö¸ñ×Ó£¬m n,´æ·ÅÁËÉßÉíÌåµÄÃ¿¸ö¸ñ×ÓµÄ×óÉÏ½ÇµÄ×ø±ê
	private int BlockLeftTopX[]=new int[50];
	//y·½Ïò51¸ö¸ñ×Ó
	private int BlockTopLeftY[]=new int[50];
	private Thread m_Thread = null;
	private int CurGrade=0;
	private int foods = 0;
	private int MaxGrade=0;
	//ÉßµÄÒÆ¶¯·½Ïò
	public static int MOVE_UP=1;//ÉÏ
	public static int MOVE_DOWN=2;//ÏÂ
	public static int MOVE_LEFT=3;//×ó
	public static int MOVE_RIGHT=4;//ÓÒ
	
	//Ïß³Ì 500msÖ´ÐÐÒ»´Î
	public void run() 
	{ 
	   for(i=0;i<=length-1;i++) 
	   { 
	    BlockLeftTopX[i]=90-i*10;
	    BlockTopLeftY[i]=60; 
	   } 
	   NewHeadLeftTopX=BlockLeftTopX[0]; 
	   NewHeadLeftTopY=BlockTopLeftY[0]; 
	   m_SnakeDirection=4;
	   while(m_Thread!=null) 
	   { 
	    Step(); 
	    try 
	    { 
	     m_Thread.sleep(time); 
	    } 
	    catch(Exception ee) 
	    { 
	      System.out.println(m_SnakeDirection+""); 
	    } 
	   } 
	} 
	
	public Snake() 
	{ 
	   //Ä¬ÈÏtime = 500;
	   time = 500;
	   //***************´´½¨ÐÂ¶ÔÏó************** 
	   setVisible(true); 
	   menuBar = new JMenuBar(); 
	   Container con=getContentPane(); 
	   toolkit=getToolkit(); 
	  
	   //**************ÓÎÏ·²Ëµ¥¶ÔÏó***************** 
	   youXiMenu = new JMenu("Menu"); 
	   kaiShiYouXi = new JMenuItem("Start"); 
	   exitItem = new JMenuItem("Exit");
	   //***************À§ÄÑ³Ì¶È¶ÔÏó**************** 
	   nanDuMenu = new JMenu("Level"); 
	   cJianDan = new JMenuItem("Easy"); 
	   cPuTong = new JMenuItem("Middle"); 
	   cKunNan = new JMenuItem("Hard");
	   //*****************·ÖÊý²Ëµ¥¶ÔÏó**************** 
	   fenShuMenu = new JMenu("Score"); 
	   fenShuItem = new JMenuItem("fenshuItem"); 
	  
	   //****************¹ØÓÚ¶ÔÏó********************* 
	   guanYuMenu = new JMenu("About"); 
	   zuoZheItem = new JMenuItem("Author");
	   //***************ÉèÖÃ¹ØÓÚ²Ëµ¥******************* 
	   guanYuMenu.add(zuoZheItem);
	   //****************ÉèÖÃÀ§ÄÑ³Ì¶È²Ëµ¥************** 
	   nanDuMenu.add(cJianDan); 
	   nanDuMenu.add(cPuTong); 
	   nanDuMenu.add(cKunNan);
	   //******************ÉèÖÃ·ÖÊý²Ëµ¥*************** 
	   fenShuMenu.add(fenShuItem);
	   //*****************ÉèÖÃÓÎÏ·²Ëµ¥**************** 
	   youXiMenu.add(kaiShiYouXi); 
	   youXiMenu.add(exitItem);
	   //******************ÉèÖÃÖ÷²Ëµ¥********************
	   menuBar.add(youXiMenu); 
	   menuBar.add(nanDuMenu); 
	   menuBar.add(fenShuMenu); 
	   menuBar.add(guanYuMenu);
	   //*********************¼àÌý×¢²á***************** 
	   zuoZheItem.addActionListener(this); 
	   kaiShiYouXi.addActionListener(this); 
	   cJianDan.addActionListener(this);
	   cPuTong.addActionListener(this);
	   cKunNan.addActionListener(this);
	   exitItem.addActionListener(this); 
	   addKeyListener(this);   //Ìí¼ÓÖ¸¶¨µÄ°´¼üÕìÌýÆ÷£¬ÒÔ½ÓÊÕ·¢×Ô´Ë×é¼þµÄ°´¼üÊÂ¼þ
	   fenShuItem.addActionListener(this); 
	
	   //*********************¼Ó¿ì½Ý¼ü******************** 
	   KeyStroke keyOpen = KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK); 
	   kaiShiYouXi.setAccelerator(keyOpen); 
	   KeyStroke keyExit = KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK); 
	   exitItem.setAccelerator(keyExit);
	   //*****************ÉèÖÃ¿ò¼Ü********************** 
	   setJMenuBar(menuBar); 
	   setTitle("Snake"); 
	   setResizable(false); 
	   setBounds(300,200,400,400); 
	   validate();          //ÓÐÐ§ÐÔÑéÖ¤ 
	   setDefaultCloseOperation(EXIT_ON_CLOSE); 
	  
	   this.repaint();
	}
	public static void main(String args[]) 
	{ 
	   new Snake(); 
	}
	//******************²Ëµ¥¼àÌý****************************** 
	public void actionPerformed(ActionEvent e) 
	{ 
		//ÏÈ»ñÈ¡µ½²Ëµ¥µÄÑ¡Ïî
		Object obj = e.getSource();
	   if(obj==kaiShiYouXi) 
	   { 
		    length = 6; 
		    CurGrade = 0; 
		    foods = 0; 
		    if(m_Thread==null) 
		    { 
		     m_Thread=new Thread(this); 
		     m_Thread.start(); 
		    } 
		    else if(m_Thread!=null) 
		    { 
		     m_Thread=null; 
		     m_Thread= new Thread(this); 
		     m_Thread.start(); 
		    } 
	   }
	   //¼òµ¥
	   else if(obj==cJianDan){
		  time = 500;
	   }
	   else if(obj==cPuTong){
		   time=300;
	   }
	   else if(obj==cKunNan){
		   time = 100;
	   }
	   else if(obj==exitItem) 
	   { 
		   System.exit(0); 
	   } 
	   else if(obj==zuoZheItem) 
	   { 
	    JOptionPane.showMessageDialog(this, "XiqianHan"); 
	   } 
	   else if(obj==fenShuItem) 
	   { 
	    JOptionPane.showMessageDialog(this," "+MaxGrade+" "); 
	   } 
	} 
	
	//**************¼ì²éÒ»ÏÂ******************************* 
	public void Step() 
	{ 
	   isDead(); 
	   if(m_Thread!=null) 
	   { 
			 //ÊÇ·ñ³Ôµ½ÁË¶«Î÷£¬ÐèÒªÔö¼Ó³¤¶ÈÃ´
		    if(m_IsNeedEnLength==0) 
		    { 
		       ReCalcSnakeBodyPos(); 
		    } 
		    else 
		    { 
		      EnLength(); //growth==1£¬ÐèÒªÈÃÉß±ä³¤
		    } 
		    //³Ôµ½ÁË¶«Î÷£¬³É³¤+1 growth=1; 
		    if(BlockLeftTopX[0] == FoodLeftTopX && BlockTopLeftY[0] == FoodLeftTopY) 
		    { 
		    //¼ÌÐø²úÉúÊ³ÎïµÄ±ê¼Ç
		     IsNeedNewFood=1; 
		     m_IsNeedEnLength=1; 
		     //³Ôµ½ÁË¶«Î÷ÏìÒ»ÏÂ
		     toolkit.beep(); 
	    } 
	   
	    //****************²úÉúÊ³Îï×ø±ê********************** 
	    if(IsNeedNewFood==1) 
	    { 
	     IsNeedNewFood=0; 
	     FoodLeftTopX=(int)Math.floor(Math.random()*39)*10; 
	     FoodLeftTopY=(int)Math.floor(Math.random()*29)*10+50; 
	    } 
	    this.repaint(); //ÖØ»æ 
	   } 
	}
	void isDead() 
	{ 
	   //¸ù¾Ý·½ÏòÏÈÖ´ÐÐ×ø±êµÄ¼ÆËã
	   if(m_SnakeDirection==Snake.MOVE_RIGHT) 
	   { 
		  NewHeadLeftTopX+=10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_LEFT) 
	   { 
		   NewHeadLeftTopX-=10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_DOWN) 
	   { 
		   NewHeadLeftTopY+=10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_UP) 
	   { 
		   NewHeadLeftTopY-=10; 
	   } 
	   if(NewHeadLeftTopX<0||NewHeadLeftTopX>390||NewHeadLeftTopY<50||NewHeadLeftTopY>390) 
	   { 
	    m_Thread=null; 
	    JOptionPane.showMessageDialog(this,"Game Over"); 
	   } 
	   for(i = 1; i < length; i++) 
	   { 
	    if(BlockLeftTopX[i] == NewHeadLeftTopX && BlockTopLeftY[i] == NewHeadLeftTopY) 
	    { 
	     m_Thread = null; 
	     JOptionPane.showMessageDialog(this,"Game Over"); 
	    } 
	   } 
	} 
	
	//³¤¶ÈÔö¼ÓÒ»¸ö
	public void EnLength() 
	{ 
	   //µ±Éß³Ôµ½¶«Î÷Ê±µÄ·½·¨ 
	   if(length<50) 
	   { 
	    length++; 
	   } 
	   m_IsNeedEnLength=0; 
	  //±ä»»ËÙ¶È
	   time=time-5;
	   ReCalcSnakeBodyPos(); 
	   CurGrade+=100; 
	   if(MaxGrade<CurGrade) 
	   { 
	    MaxGrade = CurGrade; 
	   } 
	   foods++; 
	} 
	
	//¸Ä±äÉßµÄÎ»ÖÃ
	public void ReCalcSnakeBodyPos() 
	{ 
	   int i=0;
	   //ÉßµÄÉíÌåÇ°½ø£¬Í·²¿µÄ×ø±ê×îºó±ä»»
	   for(i=length-1;i>0;i--) 
	   { 
	    BlockLeftTopX[i]=BlockLeftTopX[i-1]; 
	    BlockTopLeftY[i]=BlockTopLeftY[i-1]; 
	   } 
	   
	   //¸Ä±äÍ·²¿µÄ×ø±ê
	   if(m_SnakeDirection==Snake.MOVE_RIGHT) 
	   { 
	    BlockLeftTopX[0]=BlockLeftTopX[0]+10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_LEFT) 
	   { 
	    BlockLeftTopX[0]=BlockLeftTopX[0]-10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_DOWN) 
	   { 
	    BlockTopLeftY[0]=BlockTopLeftY[0]+10; 
	   } 
	   else if(m_SnakeDirection==Snake.MOVE_UP) 
	   { 
	    BlockTopLeftY[0]=BlockTopLeftY[0]-10; 
	   } 
	    
	}
	public void keyPressed(KeyEvent e) 
	{ 
		//ÒªÖªµÀ:ÉßÍù×ó±ßÔË¶¯µÄÊ±ºò°´ÏÂ·½Ïò¼üÓÒÊÇÎÞÐ§µÄ
	   if(m_Thread!=null) 
	   { 
	    if(e.getKeyCode()==KeyEvent.VK_UP) 
	    { 
	     if(m_SnakeDirection!=Snake.MOVE_DOWN) 
	     { 
	      m_SnakeDirection=Snake.MOVE_UP; 
	      Step(); 
	     } 
	    } 
	    else if(e.getKeyCode()==KeyEvent.VK_DOWN) 
	    { 
	     if(m_SnakeDirection!=Snake.MOVE_UP) 
	     { 
	      m_SnakeDirection=Snake.MOVE_DOWN;
	      Step(); 
	     } 
	    } 
	    else if(e.getKeyCode()==KeyEvent.VK_LEFT) 
	    { 
	     if(m_SnakeDirection!=Snake.MOVE_RIGHT) 
	     { 
	      m_SnakeDirection=Snake.MOVE_LEFT; 
	      Step(); 
	     } 
	    } 
	    else if(e.getKeyCode()==KeyEvent.VK_RIGHT) 
	    { 
	     if(m_SnakeDirection!=Snake.MOVE_LEFT) 
	     { 
	      m_SnakeDirection=Snake.MOVE_RIGHT;
	      Step(); 
	     }
	    } 
	   } 
	}
	public void keyReleased(KeyEvent e) 
	{ 
	   // TODO ×Ô¶¯Éú³É·½·¨´æ¸ù 
	}
	public void keyTyped(KeyEvent e) 
	{ 
	   // TODO ×Ô¶¯Éú³É·½·¨´æ¸ù 
	}
	public void paint(Graphics g) 
	{ 
		super.paint(g);
	   //*******************»­Í¼********************** 
	   g.setColor(Color.black); //ÉèÖÃ±³¾° 
	   g.fillRect(0,50,400,400); 
	   g.setColor(Color.pink); 
	   for(i=0;i<=length-1;i++) 
	   { 
	    g.fillRect(BlockLeftTopX[i],BlockTopLeftY[i],10,10); 
	   } 
	   g.setColor(Color.green); //ÉßµÄÊ³Îï 
	   g.fillRect(FoodLeftTopX,FoodLeftTopY,10,10); 
	   g.setColor(Color.white); 
	   g.drawString("Score "+this.CurGrade,6,60); 
	   g.drawString("Food "+this.foods,6,72); 
	} 
	
}
