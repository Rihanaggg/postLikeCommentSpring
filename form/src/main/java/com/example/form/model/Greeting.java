package com.example.form.model;


public class Greeting {

  private long score;
  private String name;
  private int rank;
  private long id;

  public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public long getScore() {
    return score;
  }

  public void setScore(long id) {
    this.score = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String content) {
    this.name = content;
  }
  
  public int getRank() {
	return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

}
