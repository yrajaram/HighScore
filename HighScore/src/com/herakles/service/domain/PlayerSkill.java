package com.herakles.service.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerSkill implements Comparable<PlayerSkill>{
	String name;
	int skill;

	public PlayerSkill(){
		super();
	}
	public PlayerSkill(String n, int r){
		this.name =n;
		this.skill = r;
	}

	@Override
	public String toString() {
		return "PlayerSkill [name=" + name + ", skill=" + skill + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int rank) {
		this.skill = rank;
	}

	public int compareTo(PlayerSkill o) { // to do descending order
		if ((skill) > (o.getSkill()))
			return -1;
		else if (skill<o.getSkill())
			return 1;
		else
			return 0;	
		
	}
}
