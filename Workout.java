
import java.util.*;
import javax.swing.JFrame;

public class Workout {
   String wo_name;
   Date wo_date;
   int cals_burned;
   float watts_made;
   int avg_hbm;
   float wo_distance;
   int wo_duration;
   int resistence_level;
   Workout_timer wo_timer;


   public Workout(String name) {
      // This constructor has one parameter, name.
      System.out.println("Name chosen is :" + name );
      wo_name          = name;
      wo_date          = new Date();
      cals_burned      = 0;
      watts_made       = 0;
      avg_hbm          = 0;
      wo_distance      = 0;
      wo_duration      = 0;
      resistence_level = 1;
      wo_timer         = new Workout_timer();

      wo_timer.setDaemon(true);
   }

   public void start_wo() {
      System.out.println("Begin workout!");
      wo_timer.start();
   }

   public void shout_out() {
      wo_timer.speak();
   }

   public void finish() {
      int total_time = wo_timer.get_time();

      System.out.println("Workout summary:\n" +
                         "Time:     " + total_time + "\n" +
                         "Diff Lvl: " + resistence_level);
   }

   public Boolean check_finished() {
      int current_time = wo_timer.get_time();

      if(current_time < wo_duration) {
         return true;
      }
      else {
         return false;
      }
   }

// ---------------------------- setter functions ---------------------------- //
   public void set_wo_name( String name ) {
      wo_name = name;
   }

   public void set_wo_date( Date date ) {
      wo_date = date;
   }

   public void set_cals_burned( int cals ) {
      cals_burned = cals;
   }

   public void set_watts_made( float watts ) {
      watts_made = watts;
   }

   public void set_avg_hbm( int hbm ) {
      avg_hbm = hbm;
   }

   public void set_distance( float distance ) {
      wo_distance = distance;
   }

   public void set_duration( int duration ) {
      wo_duration = duration;
   }

   public void set_resistence_level( int resistence ) {
      resistence_level = resistence;
   }
// ------------------------- End setter functions --------------------------- //

// ---------------------------- getter functions ---------------------------- //
   public String get_wo_name() {
      System.out.println("Workout name: " + wo_name);
      return wo_name;
   }

   public Date get_wo_date() {
      System.out.println("Workout date: " + wo_date);
      return wo_date;
   }

   public int get_cals_burned() {
      System.out.println("Calories burned: " + cals_burned);
      return cals_burned;
   }

   public float get_watts_made() {
      System.out.println("Watts generated: " + watts_made);
      return watts_made;
   }

   public int get_avg_hbm() {
      System.out.println("Average heart rate: " + avg_hbm);
      return avg_hbm;
   }

   public float get_distance() {
      System.out.println("Distance: " + wo_distance);
      return wo_distance;
   }

   public int get_duration() {
      System.out.println("Duration: " + wo_duration);
      return wo_duration;
   }

   public int get_resistence_level() {
      System.out.println("Resistence level: " + resistence_level);
      return resistence_level;
   }
// ------------------------- End getter functions --------------------------- //

// finish()
// pause()
// reset()


   public static void main(String []args) {

      Workout wo1    = new Workout( "Workout #1" );
      Scanner reader = new Scanner(System.in);
      int choice;
      int duration;
      int difficulty;

      System.out.println("Please select a workout mode from the list:\n" +
                         "0 - Manual\n" +
                         "1 - Hill\n" +
                         "2 - Cardio\n" +
                         "3 - Strength");
      choice = reader.nextInt();

      System.out.println("Please select a duration\n");
      duration = reader.nextInt();

      System.out.println("Please select a difficulty level\n");
      difficulty = reader.nextInt();

      wo1.set_duration(duration);
      wo1.set_resistence_level(difficulty);

      wo1.start_wo();

      int n = reader.nextInt();

      while(wo1.check_finished()) {
          wo1.shout_out();
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }

      wo1.finish();

      // while(true) {
      //
      //    if(n == 2) {
      //       wo1.shout_out();
      //    }
      //    else if(n == 1) {
      //       wo1.finish();
      //       break;
      //    }
      //    else {
      //       System.out.println("Try again");
      //    }
      //    n = reader.nextInt();
      // }
   }

    public static class Workout_timer extends Thread {

       int time     = 0;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
            }
        }

        public void speak() {
           System.out.print("\r" + time);
        }

        public int get_time() {
           return time;
        }

    }
}
