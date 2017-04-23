
import java.util.*;
import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.concurrent.TimeUnit;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Workout {
   Date wo_date;
   float watts_made;
   float wo_distance;
   int cals_burned;
   int resistence_level;
   String wo_name;
   Workout_manager wo_manager;

   JFrame f;
   JButton up_btn;
   JButton down_btn;
   JButton stop_btn;
   JPanel pnlButtons;

   public Workout() {
      wo_name          = "Manual";
      wo_date          = new Date();
      cals_burned      = 0;
      watts_made       = 0;
      wo_distance      = 0;
      resistence_level = 0;
      wo_manager       = new Workout_manager();
      f                = new JFrame("Clock");
      up_btn           = new JButton("up");
      down_btn         = new JButton("down");
      stop_btn         = new JButton("stop");
      pnlButtons       = new JPanel();
   }

	public void addControls()
	{
		f.add(pnlButtons, BorderLayout.CENTER);

      pnlButtons.setLayout(new GridLayout(1,3,3,3));
		pnlButtons.add(up_btn);
		pnlButtons.add(down_btn);
		pnlButtons.add(stop_btn);
	}

	public void registerListeners()
	{
      up_btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  if(resistence_level < 20) {
                     resistence_level++;
                     wo_manager.set_resistence(resistence_level);
                  }
               }
            });
            thread.start();
         }
      });

      down_btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  if(resistence_level > 0) {
                     resistence_level--;
                     wo_manager.set_resistence(resistence_level);
                  }
               }
            });
            thread.start();
         }
      });

      stop_btn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
               @Override
               public void run() {
                  finish_wo();
               }
            });
            thread.start();
         }
      });
	}

   public void start_wo() {

      JFrame.setDefaultLookAndFeelDecorated(true);
      f.setSize(900,150);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLayout(new GridLayout(2, 3));
      f.add(wo_manager);
      f.getContentPane().setBackground(Color.black);

      addControls();
      registerListeners();

      resistence_level = 1;
      wo_manager.set_resistence(resistence_level);

      f.setVisible(true);

      wo_manager.start();
   }

   public void run_wo() {
      Boolean keep_going = true;
      Scanner reader     = new Scanner(System.in);

      while(keep_going) {

         resistence_level = reader.nextInt();

         if(resistence_level == 0) {
            keep_going = false;
         }
         else {
            wo_manager.set_resistence(resistence_level);
         }
      }
   }

   public void finish_wo() {
      int total_time       = wo_manager.get_time();
      float total_distance = wo_manager.get_distance();
      String display_time;
      String summary_display;

      f.remove(wo_manager);
      f.remove(pnlButtons);

      f.setLayout(new GridLayout(1, 1));

      JLabel summary  = new JLabel();
      summary.setForeground(Color.green);
      f.add(summary);

      display_time = String.format("%2d:%2d",
         TimeUnit.SECONDS.toMinutes(total_time),
         total_time - TimeUnit.SECONDS.toMinutes(total_time) * 60
      );

      summary_display = String.format(
                        "<html>Workout summary:<br><br>" +
                        "  Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%s<br>" +
                        "  Distance:&nbsp;&nbsp;&nbsp;&nbsp;%.2f<br>" +
                        "  Time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%s<br>" +
                        "  Diff Lvl:&nbsp;&nbsp;&nbsp;&nbsp;%d<br>" +
                        "  Calories:&nbsp;&nbsp;&nbsp;&nbsp;%d<br>" +
                        "  Watts:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%f<br></html>",
                        wo_date, total_distance, display_time, resistence_level, cals_burned, watts_made);

     summary.setFont(new Font("sans-serif", Font.PLAIN, 30));
     summary.setHorizontalAlignment(SwingConstants.LEFT);
     summary.setText(summary_display);

     f.setSize(800,400);
     f.repaint();

     try {
        Thread.sleep(5000);
     }
     catch(InterruptedException e) {
        e.printStackTrace();
     }

     f.setVisible(false);
     f.dispose();
   }

// ----------------------------- setter methods ----------------------------- //
   public void set_wo_name( String name ) {wo_name = name;}
   public void set_wo_date( Date date ) {wo_date = date;}
   public void set_cals_burned( int cals ) {cals_burned = cals;}
   public void set_watts_made( float watts ) {watts_made = watts;}
   public void set_distance( float distance ) {wo_distance = distance;}
   public void set_resistence( int resistence ) {resistence_level = resistence;}
// -------------------------- End setter methods ---------------------------- //

// ----------------------------- getter methods ----------------------------- //
   public String get_wo_name() {return wo_name;}
   public Date get_wo_date() {return wo_date;}
   public int get_cals_burned() {return cals_burned;}
   public float get_watts_made() {return watts_made;}
   public float get_distance() {return wo_distance;}
   public int get_resistence_level() {return resistence_level;}
// -------------------------- End getter methods ---------------------------- //


    public class Workout_manager extends JLabel implements ActionListener {

       int cals_burned         = 0;
       int resistence_level    = 0;
       int time                = 0;
       int time_step           = 0;
       float cadance           = 0;
       float current_rate      = 0;
       float distance          = 0;
       float gear_ratio        = 0;
       float old_rate          = 0;
       float previous_distance = 0;
       float watts_made        = 0;
       String display_time;
       String display;

      //   @Override
        public Workout_manager() {
           setForeground(Color.green);
        }

        public void start() {
           Timer t = new Timer(1000, this);
           t.start();
        }

       public void actionPerformed(ActionEvent ae) {

         get_rpms();
         calculate_stats();

         display_time = String.format("%02d:%02d",
            TimeUnit.SECONDS.toMinutes(time),
            time - TimeUnit.SECONDS.toMinutes(time) * 60
         );

         display = String.format("%-5s   %-5.2fmi   %-4.1f mph   %-5d", display_time, distance, current_rate, resistence_level);

         setFont(new Font("sans-serif", Font.PLAIN, 50));
         setHorizontalAlignment(SwingConstants.LEFT);

         time++;
         time_step++;
         setText(display);
       }

// ----------------------------- setter methods ----------------------------- //
        public void set_resistence(int level) {resistence_level = level;}
// -------------------------- End setter methods ---------------------------- //

// ----------------------------- getter methods ----------------------------- //
        public int get_time() {return time;}
        public float get_distance() {return distance;}
// -------------------------- End getter methods ---------------------------- //

// --------------------------- Private methods ------------------------------ //
        private void calculate_stats() {
            switch (resistence_level) {
               case 1:  gear_ratio = (float) 39/23; set_gear_ratio(39,23); break;
               case 2:  gear_ratio = (float) 39/21; set_gear_ratio(39,21); break;
               case 3:  gear_ratio = (float) 39/19; set_gear_ratio(39,19); break;
               case 4:  gear_ratio = (float) 50/23; set_gear_ratio(50,23); break;
               case 5:  gear_ratio = (float) 39/18; set_gear_ratio(39,18); break;
               case 6:  gear_ratio = (float) 39/17; set_gear_ratio(39,17); break;
               case 7:  gear_ratio = (float) 50/21; set_gear_ratio(50,21); break;
               case 8:  gear_ratio = (float) 39/16; set_gear_ratio(39,16); break;
               case 9:  gear_ratio = (float) 39/15; set_gear_ratio(39,15); break;
               case 10: gear_ratio = (float) 50/19; set_gear_ratio(50,19); break;
               case 11: gear_ratio = (float) 50/18; set_gear_ratio(50,18); break;
               case 12: gear_ratio = (float) 39/14; set_gear_ratio(39,14); break;
               case 13: gear_ratio = (float) 50/17; set_gear_ratio(50,17); break;
               case 14: gear_ratio = (float) 39/13; set_gear_ratio(39,13); break;
               case 15: gear_ratio = (float) 50/16; set_gear_ratio(50,16); break;
               case 16: gear_ratio = (float) 39/12; set_gear_ratio(39,12); break;
               case 17: gear_ratio = (float) 50/15; set_gear_ratio(50,15); break;
               case 18: gear_ratio = (float) 50/14; set_gear_ratio(50,14); break;
               case 19: gear_ratio = (float) 50/13; set_gear_ratio(50,13); break;
               case 20: gear_ratio = (float) 50/12; set_gear_ratio(50,12); break;
           }

           // (Pi x diameter) x (gear ratio) x cadence
           current_rate = 0.079f * gear_ratio * cadance;

           // If the rate changes
           if(current_rate != old_rate) {

              // Restart the time_step clock
              time_step = 0;
              old_rate  = current_rate;
              previous_distance = distance;
           }

           distance = previous_distance + (current_rate * (time_step/3600f));
        }

        private void set_gear_ratio(int front_gear, int back_gear) {
            try {
               Runtime.getRuntime().exec("/home/mmost/Documents/test/se_460/change_gear_ratio " + front_gear + " " + back_gear);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void get_rpms() {
           JSONParser parser = new JSONParser();

           try {
               JSONObject car_inputs = (JSONObject) parser.parse(new FileReader("/home/mmost/Documents/test/se_460/car_inputs.json"));
               cadance = (long) car_inputs.get("rpms");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ParseException e) {
               e.printStackTrace();
           }
        }
    }

    public static void main(String []args) {

       int choice;
       Boolean continue_wo = true;
       Scanner reader      = new Scanner(System.in);
       Fitnessmenu j       = new Fitnessmenu();
    }
}
