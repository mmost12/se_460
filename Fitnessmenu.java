
//Fitnessmenu.java


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Fitnessmenu extends JFrame implements ActionListener
{
   private JPanel pnlCategory = new JPanel();
   private JPanel pnlButtons  = new JPanel();

	private JLabel Hill        = new JLabel("Hill", JLabel.CENTER);
	private JLabel Cardio      = new JLabel("Cardio", JLabel.CENTER);
	private JLabel Strength    = new JLabel("Strength", JLabel.CENTER);
	private JLabel Change_User = new JLabel("Change_User", JLabel.CENTER);
   private JLabel Manual      = new JLabel("Manual", JLabel.CENTER);

	private Font labelFont     = new Font("Ariel", Font.BOLD, 18);

	private JButton hill_btn   = new JButton();
	private JButton cardio_btn = new JButton();
	private JButton stren_btn  = new JButton();
	private JButton man_btn    = new JButton();
	private JButton usr_btn    = new JButton();

	private Font buttonFont    = new Font("Courier", Font. BOLD, 25);

	private String HillSelect="";
	private String HillRun="";

	private String CardioSelect="";
	private String CardioRun="";

	private String StrengthSelect="";
	private String StrengthRun="";

	private String Change_UserSelect="";
	private String Change_UserRun="";

	private String ManualSelect="";
	private String ManualRun="";

	public Fitnessmenu()
	{
      hill_btn   = new JButton("Hill");
		cardio_btn = new JButton("Cardio");
		stren_btn  = new JButton("Strength");
		man_btn    = new JButton("Manual");
		usr_btn    = new JButton("User");

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
      pnlCategory.add(Change_User);
      pnlCategory.add(Manual);
   }

	private void pnlButtonsAddControls()
	{
      pnlButtons.setLayout(new GridLayout(5,5,5,5));
		pnlButtons.add(hill_btn);
		pnlButtons.add(cardio_btn);
		pnlButtons.add(stren_btn);
		pnlButtons.add(man_btn);
		pnlButtons.add(usr_btn);
	}

	public void setLabelFontColor()
	{
		Hill.setForeground(Color.pink);
		Cardio.setForeground(Color.pink);
		Strength.setForeground(Color.pink);
		Change_User.setForeground(Color.pink);
		Manual.setForeground(Color.pink);

		Hill.setOpaque(true);
		Cardio.setOpaque(true);
		Strength.setOpaque(true);
		Change_User.setOpaque(true);
		Manual.setOpaque(true);

		Hill.setBackground(Color.blue);
		Cardio.setBackground(Color.blue);
		Strength.setBackground(Color.blue);
		Change_User.setBackground(Color.blue);
		Manual.setBackground(Color.blue);

		Hill.setFont(labelFont);
		Cardio.setFont(labelFont);
		Strength.setFont(labelFont);
		Change_User.setFont(labelFont);
		Manual.setFont(labelFont);
	}

	public void setButtonFontColor()
	{
		hill_btn.setFont(buttonFont);
		hill_btn.setBackground(Color.yellow);
		cardio_btn.setFont(buttonFont);
		cardio_btn.setBackground(Color.yellow);
		stren_btn.setFont(buttonFont);
		stren_btn.setBackground(Color.yellow);
		man_btn.setFont(buttonFont);
		man_btn.setBackground(Color.yellow);
		usr_btn.setFont(buttonFont);
		usr_btn.setBackground(Color.yellow);
	}

	public void registerListeners()
	{
      hill_btn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            Workout wo = new Workout();

            Thread thread = new Thread(new Runnable() {

               @Override
               public void run() {

                  wo.start_wo();
                  wo.run_wo();
                  wo.finish_wo();
               }
            });
            thread.start();
         }
      });

		cardio_btn.addActionListener(this);
		stren_btn.addActionListener(this);
		man_btn.addActionListener(this);
		usr_btn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
   {

      // switch (choice) {
      //    case 1:  wo = new Workout(); break;
      //    case 2:  wo = new Hill_Workout(); break;
      //    case 3:  wo = new Cardio_Workout(); break;
      //    case 4:  wo = new Strength_Workout(); break;
      //    case 5:  wo = new Workout(); break;
      //    default: System.out.println("Invalid selection!");
      //             wo = new Workout();
      // }

		// if (e.getSource() == hill_btn) {
		// 	// MyJFrame f= new MyJFrame(HillSelect, HillRun);
      //    Workout wo = new Workout();
      //
      //    wo.start_wo();
      //    wo.run_wo();
      //    wo.finish_wo();
		// }
      //
		// if (e.getSource() == cardio_btn) {
		// 	MyJFrame f= new MyJFrame(CardioSelect, CardioRun);
      // }
      //
		// if (e.getSource() == stren_btn) {
		// 	MyJFrame f= new MyJFrame(StrengthSelect, StrengthRun);
		// }
      //
		// if (e.getSource() == man_btn) {
      //    MyJFrame f= new MyJFrame(ManualSelect, ManualRun);
		// }
      //
		// if (e.getSource() == usr_btn) {
      //    MyJFrame f= new MyJFrame(Change_UserSelect, Change_UserRun);
		// }


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
