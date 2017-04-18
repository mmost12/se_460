
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

public class Workout {
   Date wo_date;
   float watts_made;
   float wo_distance;
   int avg_hbm;
   int cals_burned;
   int resistence_level;
   String wo_name;
   Workout_manager wo_manager;


   // -------------------------
   //  ClockLabel dateLable = new ClockLabel("date");
   //  ClockLabel timeLable = new ClockLabel("time");
   //  ClockLabel dayLable = new ClockLabel("day");
    //
   //  JFrame f = new JFrame("Digital Clock");
   // ------------------------

   public Workout() {
      // This constructor has one parameter, name.
      wo_name          = "Manual";
      wo_date          = new Date();
      cals_burned      = 0;
      watts_made       = 0;
      avg_hbm          = 0;
      wo_distance      = 0;
      resistence_level = 0;
      wo_manager       = new Workout_manager();

      // ------------
      //  JFrame.setDefaultLookAndFeelDecorated(true);
      //  f.setSize(300,150);
      //  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  f.setLayout(new GridLayout(3, 1));
       //
      //  f.add(dateLable);
      //  f.add(timeLable);
      //  f.add(dayLable);
       //
      //  f.getContentPane().setBackground(Color.black);
       //
      //  f.setVisible(true);
      // ------------

      // wo_manager.setDaemon(true);
   }

   public void start_wo() {
      Scanner reader = new Scanner(System.in);

      System.out.print("Select resistence level: (1-20)\n-> ");
      resistence_level = reader.nextInt();

      try {
         System.out.print("3.. ");
         Thread.sleep(1000);
         System.out.print("2.. ");
         Thread.sleep(1000);
         System.out.print("1.. ");
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.print("\rBegin workout!\n");

      wo_manager.set_resistence(resistence_level);
      // wo_manager.start();
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
      // wo_manager.interrupt();

      display_time = String.format("%2d:%2d",
         TimeUnit.SECONDS.toMinutes(total_time),
         total_time - TimeUnit.SECONDS.toMinutes(total_time) * 60
      );

      System.out.print("\r\033[K\n-------------------------------------------------\n" +
                       "Workout summary:     " + wo_date + "\n\n");
      System.out.format("  Distance:   %.2f\n" +
                        "  Time:       %s\n" +
                        "  Diff Lvl:   %d\n" +
                        "  Calories:   %d\n" +
                        "  Watts:      %f\n" +
                        "  Heart rate: %d\n" +
                        "-------------------------------------------------\n",
                        total_distance, display_time, resistence_level, cals_burned, watts_made, avg_hbm);
   }

// ----------------------------- setter methods ----------------------------- //
   public void set_wo_name( String name ) {wo_name = name;}
   public void set_wo_date( Date date ) {wo_date = date;}
   public void set_cals_burned( int cals ) {cals_burned = cals;}
   public void set_watts_made( float watts ) {watts_made = watts;}
   public void set_avg_hbm( int hbm ) {avg_hbm = hbm;}
   public void set_distance( float distance ) {wo_distance = distance;}
   public void set_resistence( int resistence ) {resistence_level = resistence;}
// -------------------------- End setter methods ---------------------------- //

// ----------------------------- getter methods ----------------------------- //
   public String get_wo_name() {return wo_name;}
   public Date get_wo_date() {return wo_date;}
   public int get_cals_burned() {return cals_burned;}
   public float get_watts_made() {return watts_made;}
   public int get_avg_hbm() {return avg_hbm;}
   public float get_distance() {return wo_distance;}
   public int get_resistence_level() {return resistence_level;}
// -------------------------- End getter methods ---------------------------- //

   public static void main(String []args) {

      int choice;
      Boolean continue_wo = true;
      Workout wo          = new Workout();
      Scanner reader      = new Scanner(System.in);

      System.out.println("Please select a workout mode from the list:\n" +
                         "1 - Manual\n" +
                         "2 - Hill\n" +
                         "3 - Cardio\n" +
                         "4 - Strength\n" +
                         "5 - User\n");

      choice = reader.nextInt();

      switch (choice) {
         case 1:  wo = new Workout(); break;
         case 2:  wo = new Hill_Workout(); break;
         case 3:  wo = new Cardio_Workout(); break;
         case 4:  wo = new Strength_Workout(); break;
         case 5:  wo = new Workout(); break;
         default: System.out.println("Invalid selection!");
      }

      wo.start_wo();
      wo.run_wo();
      wo.finish_wo();
   }

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
       //  int avg_hbm;
       String display_time;

      //   @Override
        public void run() {
            while (true) {
                try {
                    get_rpms();
                    calculate_stats();

                    display_time = String.format("%2d:%2d",
                       TimeUnit.SECONDS.toMinutes(time),
                       time - TimeUnit.SECONDS.toMinutes(time) * 60
                    );

                    System.out.format("\r\033[K%-5s %-5.2f mi   %3.1f mph %-5d", display_time, distance, current_rate, resistence_level);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                   // Anticipating the inturruption for exit
                   System.out.print("\r\033[K");
                }
                time++;
                time_step++;

               Timer t = new Timer(1000, this);
               t.start();
            }
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

        public void actionPerformed(ActionEvent ae) {
          Date d = new Date();
         //  setText(sdf.format(d));
        }
    }

    class ClockLabel extends JLabel implements ActionListener {

      String type;
      SimpleDateFormat sdf;

      public ClockLabel(String type) {
        this.type = type;
        setForeground(Color.green);

        switch (type) {
          case "date" : sdf = new SimpleDateFormat("  MMMM dd yyyy");
                        setFont(new Font("sans-serif", Font.PLAIN, 12));
                        setHorizontalAlignment(SwingConstants.LEFT);
                        break;
          case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
                        setFont(new Font("sans-serif", Font.PLAIN, 40));
                        setHorizontalAlignment(SwingConstants.CENTER);
                        break;
          case "day"  : sdf = new SimpleDateFormat("EEEE  ");
                        setFont(new Font("sans-serif", Font.PLAIN, 16));
                        setHorizontalAlignment(SwingConstants.RIGHT);
                        break;
          default     : sdf = new SimpleDateFormat();
                        break;
        }

        Timer t = new Timer(1000, this);
        t.start();
      }

      public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));
      }
    }
}
