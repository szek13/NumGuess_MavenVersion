package org.fasttrackit.dev.lesson1.numgenerator;



import java.util.*;

/**
 * Created by condor on April 04, 2015
 * FastTrackIT, 2015
 */
public class MyListOfHallOfFame {

    private static MyListOfHallOfFame m;

    public static MyListOfHallOfFame getInstance() {

        if(m==null) {
            m=new MyListOfHallOfFame();
            m.generateInitialList();
        }
       return m;
    }


    private List<HallOfFame> hallOfFames = new ArrayList<HallOfFame>();
    private long id;

    private void generateInitialList() {

        for(int i=0;i<1000;i++) {
            hallOfFames.add(new HallOfFame(i, Math.abs(new Random().nextInt() % 20) + 3 + new Random().nextDouble()));
        }
        id=1000;

    };

   public long addItem(double value) {
      id++;
      hallOfFames.add(new HallOfFame(id, value));
       return id;
        }

    public HallOfFame getMinScore() {
        double min=Double.MAX_VALUE;
        long lid=0;
        for (ListIterator<HallOfFame> iter = hallOfFames.listIterator(); iter.hasNext(); ) {
            HallOfFame element = iter.next();

            if (element.getScore()<min) {
                min=element.getScore();
                lid=element.getId();
            }
        }
        return new HallOfFame(lid,min);
    }



    public List getList() {
        return hallOfFames;
    }

}