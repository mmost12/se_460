
//Fitnessmenu.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Fitnessmenu extends JFrame implements ActionListener
{
   private JPanel pnlButtons  = new JPanel();

	private JLabel Hill        = new JLabel("Hill", JLabel.CENTER);
	private JLabel Cardio      = new JLabel("Cardio", JLabel.CENTER);
	private JLabel Strength    = new JLabel("Strength", JLabel.CENTER);
	private JLabel Change_User = new JLabel("Change_User", JLabel.CENTER);
   private JLabel Manual      = new JLabel("Manual", JLabel.CENTER);

	private JButton hill_btn   = new JButton();
	private JButton cardio_btn = new JButton();
	private JButton stren_btn  = new JButton();
	private JButton man_btn    = new JButton();
	private JButton usr_btn    = new JButton();

	private Font buttonFont    = new Font("Courier", Font. BOLD, 25);
   private Font labelFont     = new Font("Ariel", Font.BOLD, 18);

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
		add(pnlButtons, BorderLayout.CENTER);
      pnlButtonsAddControls();
		setButtonFontColor();
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
            Workout wo = new Hill_Workout();

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

		cardio_btn.addActionListener(new ActionListener() {

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

		stren_btn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            Workout wo = new Strength_Workout();

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

		man_btn.addActionListener(new ActionListener() {

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

		usr_btn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
   {
      // Defualt action is to do nothing
	}
}
