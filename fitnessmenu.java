
//Fitnessmenu.java


import javax.swing.*;								
import java.awt.*; 								
import java.awt.event.*; 								
public class Fitnessmenu extends JFrame implements ActionListener			
{
        private JPanel pnlCategory=new JPanel();
        private JPanel pnlButtons=new JPanel();

	private JLabel Hill = new JLabel("Hill", JLabel.CENTER);
	private JLabel Cardio = new JLabel("Cardio", JLabel.CENTER);
	private JLabel Strength = new JLabel("Strength", JLabel.CENTER);
	private JLabel Change User = new JLabel("Change User", JLabel.CENTER);
              private JLabel Manual = new JLabel("Manual", JLabel.CENTER);
	
	private Font labelFont=new Font("Ariel", Font.BOLD, 18); 

	private JButton[] btn1=new JButton[5]; 
	private JButton[] btn2=new JButton[5]; 
	private JButton[] btn3=new JButton[5]; 
	private JButton[] btn4=new JButton[5]; 
	private JButton[] btn5=new JButton[5]; 	

	private Font buttonFont=new Font("Courier", Font.BOLD, 25);  	

	private String HillSelect1="";
	private String HillRun1="";
	

	private String CardioSelect1="";
	private String CardioRun1="";
	

	private String StrengthSelect1="";
	private String StrengthRun1="";
	

	private String Change UserSelect1="";
	private String Change UserRun1="";
	

	private String ManualSelect1="";
	private String ManualRun1="";
	

	


	public Fitnessmenu()							
	{
                 for(int i=0; i<5; i++){
			btn1[i]=new JButton("click to select");}
                 for(int i=0; i<5; i++){
			btn2[i]=new JButton("click to select");}
                 for(int i=0; i<5; i++){
			btn3[i]=new JButton("click to select");}
                 for(int i=0; i<5; i++){
			btn4[i]=new JButton("click to select");}
                 for(int i=0; i<5; i++){
			btn5[i]=new JButton("click to select");}
		 addControls();
		 registerListeners();
		 setTitle("Fitnessmenu");	
		 setSize(750,500); 						
	         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );		
		 setVisible(true);							
	}									
	public void addControls()
	{
		add(pnlCategory, BorderLayout.NORTH);
		add(pnlButtons, BorderLayout.CENTER);
                pnlCategoryAddControls();
                pnlButtonsAddControls();
		setLabelFontColor();
		setButtonFontColor();
	}
        private void pnlCategoryAddControls()
        {
		pnlCategory.setLayout( new GridLayout(1,5,5,10));
                pnlCategory.add(Hill);
                pnlCategory.add(Cardio);
                pnlCategory.add(Strength);
                pnlCategory.add(Change User);
                pnlCategory.add(Manual);
                pnlCategory.add(People);
        }
	private void pnlButtonsAddControls()
	{
        	pnlButtons.setLayout(new GridLayout(5,5,5,5));
                for(int i=0; i<5; i++){
			pnlButtons.add(btn1[i]);}
                for(int i=0; i<5; i++){
			pnlButtons.add(btn2[i]);}
                for(int i=0; i<5; i++){
			pnlButtons.add(btn3[i]);}
                for(int i=0; i<5; i++){
			pnlButtons.add(btn4[i]);}
                for(int i=0; i<5; i++){
			pnlButtons.add(btn5[i]);}
	}
	public void setLabelFontColor()
	{
		Hill.setForeground(Color.pink);
		Cardio.setForeground(Color.pink);
		Strength.setForeground(Color.pink);
		Change User.setForeground(Color.pink);
		Manual.setForeground(Color.pink);
		

		Hill.setOpaque(true);
		Cardio.setOpaque(true);
		Strength.setOpaque(true);
		Change User.setOpaque(true);
		Manual.setOpaque(true);
		

		Hill.setBackground(Color.blue);
		Cardio.setBackground(Color.blue);
		Strength.setBackground(Color.blue);
		Change User.setBackground(Color.blue);
		Manual.setBackground(Color.blue);
		

		Hill.setFont(labelFont);
		Cardio.setFont(labelFont);
		Strength.setFont(labelFont);
		Change User.setFont(labelFont);
		Manual.setFont(labelFont);
		
		
	}
	public void setButtonFontColor()
	{
                for(int i=0; i<5; i++){
			btn1[i].setFont(buttonFont);
			btn1[i].setBackground(Color.yellow);}
                for(int i=0; i<5; i++){
			btn2[i].setFont(buttonFont);
			btn2[i].setBackground(Color.yellow);}
                for(int i=0; i<5; i++){
			btn3[i].setFont(buttonFont);
			btn3[i].setBackground(Color.yellow);}
                for(int i=0; i<5; i++){
			btn4[i].setFont(buttonFont);
			btn4[i].setBackground(Color.yellow);}
                for(int i=0; i<5; i++){
			btn5[i].setFont(buttonFont);
			btn5[i].setBackground(Color.yellow);}
	}
	public void registerListeners()
	{
		for(int i=0;i<5;i++)
		{
			btn1[i].addActionListener(this);
		}
		for(int i=0;i<5;i++)
		{
			btn2[i].addActionListener(this);
		}
		for(int i=0;i<5;i++)
		{
			btn3[i].addActionListener(this);
		}
		for(int i=0;i<5;i++)
		{
			btn4[i].addActionListener(this);
		}
		for(int i=0;i<5;i++)
		{
			btn5[i].addActionListener(this);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		for(int i=0;i<5;i++)
		{
			if (e.getSource() == btn1[i]) 
			{
				btn1[i].setEnabled(false);
	 			btn1[i].setBackground(Color.gray);
				if(i==0){
					MyJFrame f= new MyJFrame(HillSelect1, HillRun1);}
				else if(i==1){
					MyJFrame f= new MyJFrame(CardioSelect1, CardioRun1);}
				else if(i==2){
					MyJFrame f= new MyJFrame(StrengthSelect1, StrengthRun1);}
				else if(i==3){
					MyJFrame f= new MyJFrame(Change UserSelect1, Change UserRun1);}
				else if(i==4){
					MyJFrame f= new MyJFrame(ManualSelect1, ManualRun1);}
				else{
					MyJFrame f= new MyJFrame(PeopleSelect1, PeopleRun1);}
			}
			if (e.getSource() == btn2[i]) 
			{
				btn2[i].setEnabled(false);
	 			btn2[i].setBackground(Color.gray);
				if(i==0){
					MyJFrame f= new MyJFrame(HillSelect2, HillRun2);}
				else if(i==1){
					MyJFrame f= new MyJFrame(CardioSelect2, CardioRun2);}
				else if(i==2){
					MyJFrame f= new MyJFrame(StrengthSelect2, StrengthRun2);}
				else if(i==3){
					MyJFrame f= new MyJFrame(Change UserSelect2, Change UserRun2);}
				else if(i==4){
					MyJFrame f= new MyJFrame(ManualSelect2, ManualRun2);}
				else{
					MyJFrame f= new MyJFrame(PeopleSelect2, PeopleRun2);}
			}
			if (e.getSource() == btn3[i]) 
			{
				btn3[i].setEnabled(false);
	 			btn3[i].setBackground(Color.gray);
				if(i==0){
					MyJFrame f= new MyJFrame(HillSelect3, HillRun3);}
				else if(i==1){
					MyJFrame f= new MyJFrame(CardioSelect3, CardioRun3);}
				else if(i==2){
					MyJFrame f= new MyJFrame(StrengthSelect3, StrengthRun3);}
				else if(i==3){
					MyJFrame f= new MyJFrame(Change UserSelect3, Change UserRun3);}
				else if(i==4){
					MyJFrame f= new MyJFrame(ManualSelect3, ManualRun3);}
				else{
					MyJFrame f= new MyJFrame(PeopleSelect3, PeopleRun3);}
			}
			if (e.getSource() == btn4[i]) 
			{
				btn4[i].setEnabled(false);
	 			btn4[i].setBackground(Color.gray);
				if(i==0){
					MyJFrame f= new MyJFrame(HillSelect4, HillRun4);}
				else if(i==1){
					MyJFrame f= new MyJFrame(CardioSelect4, CardioRun4);}
				else if(i==2){
					MyJFrame f= new MyJFrame(StrengthSelect4, StrengthRun4);}
				else if(i==3){
					MyJFrame f= new MyJFrame(Change UserSelect4, Change UserRun4);}
				else if(i==4){
					MyJFrame f= new MyJFrame(ManualSelect4, ManualRun4);}
				else{
					MyJFrame f= new MyJFrame(PeopleSelect4, PeopleRun4);}
			}
			if (e.getSource() == btn5[i]) 
			{
				btn5[i].setEnabled(false);
	 			btn5[i].setBackground(Color.gray);
				if(i==0){
					MyJFrame f= new MyJFrame(HillSelect5, HillRun5);}
				else if(i==1){
					MyJFrame f= new MyJFrame(CardioSelect5, CardioRun5);}
				else if(i==2){
					MyJFrame f= new MyJFrame(StrengthSelect5, StrengthRun5);}
				else if(i==3){
					MyJFrame f= new MyJFrame(Change UserSelect5, Change UserRun5);}
				else if(i==4){
					MyJFrame f= new MyJFrame(ManualSelect5, ManualRun5);}
				else{
					MyJFrame f= new MyJFrame(PeopleSelect5, PeopleRun5);}
			}
		}

	}
	public static void main(String[] args)
   	{
		Fitnessmenu j=new Fitnessmenu();					
	}
}
class MyJFrame extends JFrame 
{
	private JTextArea txa= new JTextArea();
	private String run;
	private String select;
	private Boolean first=true;
	public MyJFrame(String ans, String ques)
   	{
		addWindowListener(new WindowAdapter()				
		{
			public void windowClosing(WindowEvent e)
			{
				if (first==true) 
				{
					txa.setText(run);  
					first=false;	
				}
				else setDefaultCloseOperation(
				JFrame.DISPOSE_ON_CLOSE );	
			}
		});
		run =ques;
		select =ans;
		addControls();								
		setSize(800, 500);
		setLocationRelativeTo(null);				
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		setVisible(true);							
	}
	public void addControls()							
	{
		add(txa);
		txa.setText(select);
		txa.setEditable(false);
		Font f=new Font("Helvetica", Font.BOLD, 50);  	
		txa.setFont(f);				
		txa.setForeground(Color.yellow);
		txa.setBackground(Color.blue);
		txa.setLineWrap(true);
		txa.setWrapStyleWord(true);
	}
}


