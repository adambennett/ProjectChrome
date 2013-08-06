package com.cvgstudios.pokemonchrome.gamelogic;

import java.util.Vector;

import com.cvgstudios.pokemonchrome.javascript.JSExecutor;

public class PokemonCreature {

	protected PokemonType theBaseType;
	protected String type;

	protected String givenName;

	protected Vector<Boolean> actionsAvailable;

	protected int ivHP;
	protected int ivAttack;
	protected int ivDefense;
	protected int ivSpAttack;
	protected int ivSpDefense;
	protected int ivSpeed;

	private PokemonCreature(PokemonType pokemonType) {
		theBaseType = pokemonType;
		actionsAvailable = new Vector<Boolean>();
		for (String s : theBaseType.actions) {
			actionsAvailable.add(false);
		}
		JSExecutor.setThisScope(this);
	}

	public void setActionAvailability(String actionName, boolean availability) {
		if (theBaseType.actions.contains(actionName)) {
			actionsAvailable.set(theBaseType.actions.indexOf(actionName),
					availability);
		}
	}

	public void setName(String newName) {
		givenName = newName;
	}

	public String getName() {
		return givenName;
	}

	public void setIvHP(int hp) {
		ivHP = hp;
	}

	public void setIvAttack(int attack) {
		ivAttack = attack;
	}

	public void setIvDefense(int defense) {
		ivDefense = defense;
	}

	public void setIvSpAttack(int spAttack) {
		ivSpAttack = spAttack;
	}

	public void setIvSpDefense(int spDefense) {
		ivSpDefense = spDefense;
	}

	public void setIvSpeed(int speed) {
		ivSpeed = speed;
	}

	public void setType(String theType) {
		type = theType;
	}

	public PokemonType getType() {
		return theBaseType;
	}

	public boolean getActionAvailability(int actionIndex) {
		return actionsAvailable.get(actionIndex);
	}

	public boolean getActionAvailability(String actionCodeName) {
		return actionsAvailable
				.get(theBaseType.actions.indexOf(actionCodeName));
	}

	public static PokemonCreature makePokemon(PokemonType pokemonType) {
		PokemonCreature newPokemon = new PokemonCreature(pokemonType);
		JSExecutor.setThisScope(newPokemon);
		JSExecutor.evaluate(pokemonType.baseName.concat(".on_create()"));
		return newPokemon;
	}

}