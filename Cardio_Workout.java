import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Cardio_Workout extends Workout {

   int cardio_workout[] = new int[0];
   int difficulty;

   public Cardio_Workout() {
      // This constructor has one parameter, name.
      wo_name          = "Cardio";
      wo_date          = new Date();
      cals_burned      = 0;
      watts_made       = 0;
      avg_hbm          = 0;
      wo_distance      = 0;
      resistence_level = 0;
      difficulty       = 0;
      wo_manager       = new Workout_manager();

      // Set the "warm up" part of the cardio workout
      for(int i=0; i < cardio_workout.length; i++) {
         cardio_workout[i] = 3;
      }

      wo_manager.setDaemon(true);
   }

   @Override
   public void start_wo() {

      cardio_workout = set_cardio_workout(resistence_level);
      wo_manager.start();
   }

   @Override
   public void run_wo() {
      Boolean keep_going = true;
      Scanner reader     = new Scanner(System.in);
      int i = 0;

      while(keep_going) {

         try {
            resistence_level = cardio_workout[i];
            wo_manager.set_resistence(resistence_level);
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         } catch (IndexOutOfBoundsException e) {
            keep_going = false;
         }
         i++;
      }
   }

   private int[] set_cardio_workout(int difficulty) {

      Tuple<Integer,Integer> a = new Tuple<>(3,0);
      Tuple<Integer,Integer> b = new Tuple<>(3,10);
      Tuple<Integer,Integer> c = new Tuple<>(difficulty,20);
      Tuple<Integer,Integer> d = new Tuple<>(difficulty,30);
      Tuple<Integer,Integer> e = new Tuple<>(0,50);
      float up_rate;
      float down_rate;
      int warm_up = 3;
      int length  = e.right();
      int steps[] = new int[length];

      up_rate   = (float)(c.left()-b.left())/(c.right()-b.right());
      down_rate = (float)(e.left()-d.left())/(e.right()-d.right());

      System.out.println(up_rate);
      System.out.println(down_rate);

      // Increase to tempo workout
      for(int i = 0; i < b.right(); i++) {
         steps[i] = warm_up;
      }

      // Increase to tempo workout
      for(int i = b.right(); i < c.right(); i++) {
         steps[i] = warm_up + (int) Math.ceil((float)((i-b.right())*up_rate));
      }

      // Maintain tempo
      for(int i = c.right(); i < d.right(); i++) {
         steps[i] = difficulty;
      }

      // Decrease for cool down
      for(int i = d.right(); i < e.right(); i++) {
         steps[i] = difficulty + (int) Math.ceil((float)((i-d.right())*down_rate));
      }

      for(int i = 0; i < steps.length; i++) {
         System.out.println("steps[" + i + "] " + steps[i]);
      }

      return steps;
   }
}
