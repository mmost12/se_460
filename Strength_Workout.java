import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Strength_Workout extends Workout {

   int strength_workout[] = new int[10];
   int difficulty;

   JFrame input = new JFrame("Input");
   private JButton dl_btn_1 = new JButton("1");
   private JButton dl_btn_2 = new JButton("2");
   private JButton dl_btn_3 = new JButton("3");
   private JButton dl_btn_4 = new JButton("4");

   private JPanel dl_pnlButtons = new JPanel();
   private JPanel dl_pnlInfo    = new JPanel();

   private JLabel dl_Info_label = new JLabel();

   public Strength_Workout() {
      // This constructor has one parameter, name.
      wo_name          = "Strength";
      wo_date          = new Date();
      cals_burned      = 0;
      watts_made       = 0;
      wo_distance      = 0;
      resistence_level = 0;
      difficulty       = 0;
      wo_manager       = new Workout_manager();

      // Set the "warm up" part of the strength workout
      for(int i=0; i < strength_workout.length; i++) {
         strength_workout[i] = 3;
      }

      JFrame.setDefaultLookAndFeelDecorated(true);
      input.setSize(400,250);
      input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      input.setLayout(new GridLayout(2, 1));
      input.getContentPane().setBackground(Color.black);

      addMoreControls();
      registerMoreListeners();
   }

   public void addMoreControls()
	{
      dl_Info_label.setBackground(Color.white);
      dl_Info_label.setForeground(Color.black);
      dl_Info_label.setFont(new Font("sans-serif", Font.PLAIN, 30));
      dl_Info_label.setText("Select a difficulty level");

      dl_pnlInfo.add(dl_Info_label, BorderLayout.CENTER);

		input.add(dl_pnlInfo, BorderLayout.CENTER);
		input.add(dl_pnlButtons, BorderLayout.CENTER);

      dl_pnlButtons.setLayout(new GridLayout(1,4,3,3));
		dl_pnlButtons.add(dl_btn_1);
		dl_pnlButtons.add(dl_btn_2);
		dl_pnlButtons.add(dl_btn_3);
		dl_pnlButtons.add(dl_btn_4);
	}

	public void registerMoreListeners()
	{
      dl_btn_1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  difficulty = 1;
               }
            });
            thread.start();
         }
      });

      dl_btn_2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  difficulty = 2;
               }
            });
            thread.start();
         }
      });

      dl_btn_3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  difficulty = 3;
               }
            });
            thread.start();
         }
      });

      dl_btn_4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  difficulty = 4;
               }
            });
            thread.start();
         }
      });
   }

   @Override
   public void start_wo() {
      input.setVisible(true);

      while(difficulty == 0) {
         // Wait for the user to input their selection
         try {
            Thread.sleep(40);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      input.setVisible(false);
      input.dispose();

      f.setSize(900,150);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLayout(new GridLayout(2, 3));
      f.add(wo_manager);
      f.getContentPane().setBackground(Color.black);

      addControls();
      registerListeners();

      f.setVisible(true);

      wo_manager.start();
   }

   @Override
   public void run_wo() {
      Boolean keep_going = true;
      int i = 0;

      while(keep_going) {

         try {
            resistence_level = strength_workout[i];
            wo_manager.set_resistence(resistence_level);
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         } catch (IndexOutOfBoundsException e) {

            // Make a new interval
            strength_workout = set_strength_workout(difficulty);
            i = 0;
         }
         i++;
      }
   }

   private int[] set_strength_workout(int difficulty) {

      int min = 0;
      int max = 0;

      switch (difficulty) {
         case 1: min = 8;
                 max = 11;
                 break;
         case 2: min = 11;
                 max = 14;
                 break;
         case 3: min = 14;
                 max = 17;
                 break;
         case 4: min = 17;
                 max = 20;
                 break;
      }

      int height  = ThreadLocalRandom.current().nextInt(min, max + 1);
      int steps[] = new int[20];

      // The work interval
      for(int i = 0; i < 10; i++) {
         steps[i] = height;
      }

      // The recover interval
      for(int i = 10; i < 20; i++) {
         steps[i] = 2;
      }

      return steps;
   }
}
