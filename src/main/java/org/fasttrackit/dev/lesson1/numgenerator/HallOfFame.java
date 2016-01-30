package org.fasttrackit.dev.lesson1.numgenerator;

/**
 * Created by condor on April 04, 2015
 * FastTrackIT, 2015
 */
public class HallOfFame {

    private long id;
    private double score;

    HallOfFame(long id, double value) {
        this.id=id;
        this.score=value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }



}
