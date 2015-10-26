package net.binaryaura.stamina.entity.player;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * 
 * @author BinaryAura
 *
 * The StaminaPlayer Class implements the {@link IExtendedEntityProperties} interface,
 * which creates additional properties for entities to adopt. The StaminaProperties
 * has four variables: max, energy, stamina, and adren which represent the values for
 * the Maximum Stamina, Energy Bar, Stamina Bar, and Adrenaline Bar respectively. 
 * 
 */
public class StaminaPlayer implements IExtendedEntityProperties {
	
	/**
	 * 
	 * StaminaType enum allows for a specific set of variables to
	 * be used when specifying a specific bar.
	 *
	 */
	public static enum StaminaType {
		MAX(),				// Maximum amount of stamina possible
		ENERGY(20),			// Energy for the day, Stamina can't exceed this value
		STAMINA(21),		// Stamina that can be used for actions at any given moment
		ADREN(22);			// Adrenaline. Allows for limit-less stamina for a given amount of time.
		
		/**
		 *	StaminaType constructer that isn't to use a {@link DataWatcher}.
		 */
		private StaminaType() {
			this(-1);
		}
		
		/**
		 * Constructer gives an int meta for the use of a {@link DataWatcher}.
		 * 
		 * @param meta		Metadata value for {@link DataWatcher} value. This should be -1 if it's not to be used.
		 */
		private StaminaType(int meta) {
			this.meta = meta;
		}
		
		/**
		 * Returns the meta value for {@link DataWatcher}.
		 * 
		 * @return	meta
		 */
		public int getMeta() {
			return this.meta;
		}
		
		private final int meta;		// Value for {@link DataWatcher}.
	}
	
	public static final float DEFAULT_MAX = 2000.0F;		// Default Value for the Maximum Value to be used
	public static final String NAME = "StaminaPlayer";		// Internal name of the property set
	
	/**
	 * Gets this Extended Property Class for the player Argument.
	 * 
	 * @param 	player		Player the Property Set is assigned to.
	 * @return  {@link EntityPlayer} player instance of this class.
	 */
	public static final StaminaPlayer get(EntityPlayer player) {
		return (StaminaPlayer)player.getExtendedProperties(NAME);
	}
	
	/**
	 * Registers this Extended Property Class for the player Argument.
	 * 
	 * @param player		Player to assign the Property Set
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new StaminaPlayer(player));
	}
	
	/**
	 * Constructor initiates the player the property set is associated to, gets
	 * that player's {@link DataWatcher}, and initiates the stamina variables.
	 * 
	 * @param player		Player to assign the Property Set
	 */
	public StaminaPlayer(EntityPlayer player) {
		this.player = player;
		this.dw = this.player.getDataWatcher();
		this.stamina = this.energy = this.max = DEFAULT_MAX;
		this.adren = 0;
		
		this.dw.addObject(StaminaType.ENERGY.getMeta(), this.max);
		this.dw.addObject(StaminaType.STAMINA.getMeta(), this.stamina);
		this.dw.addObject(StaminaType.ADREN.getMeta(), this.adren);
	}
	
	/**
	 * Saves the staminaProperty variable.
	 */
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound staminaProperties = new NBTTagCompound();
		staminaProperties.setFloat("max", this.max);
		staminaProperties.setFloat("energy", this.energy);
		staminaProperties.setFloat("stamina", this.stamina);
		staminaProperties.setFloat("adren", this.adren);
		
		compound.setTag(NAME, staminaProperties);
	}

	/**
	 * Loads the staminaProperty variable.
	 */
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound staminaProperties = (NBTTagCompound)compound.getTag(NAME);
		this.max = staminaProperties.getFloat("max");
		this.energy = staminaProperties.getFloat("energy");
		this.stamina = staminaProperties.getFloat("stamina");
		this.adren = staminaProperties.getFloat("adren");

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Tests whether the {@link StaminaType} type at least value points.
	 * 
	 * @param type		StaminaType to be check.
	 * @param value		Amount to be checked against.
	 * @return			True if type has a value of at least of value, False otherwise.
	 */
	public boolean isEnough(final StaminaType type, final float value) {
		switch(type) {
			case MAX:
				return this.max > value;
			case ENERGY:
				return this.energy > value;
			case STAMINA:
				return this.stamina > value;
			case ADREN:
				return this.adren > value;
			default:
				throw new IllegalArgumentException("InvalidStaminaType. This shouldn't happen.");
		}
	}
	
	/**
	 * Adds value amount of stamina to {@link StaminaType} type.
	 * This method calls setStamina() after calculating
	 * the new value, passing 0, if the result is less than zero.
	 * 
	 * @param type			StaminaType to add to. 
	 * @param value			Amount to add.
	 */
	public void addStamina(final StaminaType type, final float value) {
		switch(type) {
			case MAX:
				if(this.max + value > 0) {
					setStamina(type, this.max + value);
				} else {
					setStamina(type, 0);
				}
				break;
			case ENERGY:
				if(this.energy + value > 0) {
					setStamina(type, this.energy + value);
				} else {
					setStamina(type, 0);
				}
				break;
			case STAMINA:
				if(this.stamina + value > 0) {
					setStamina(type, this.stamina + value);
				} else {
					setStamina(type, 0);
				}
				break;
			case ADREN:
				if(this.adren + value > 0) {
					setStamina(type, this.adren + value);
				} else {
					setStamina(type, 0);
				}
				break;
			default:
				// All enums are covers the code should never reach here unless type == null.
				throw new NullPointerException("Cannot add to nothing.");
		}
	}
	
	/**
	 * Drains the {@link StaminaType} type. This method calls
	 * setStamina() and passes 0.
	 * 
	 * @param type			StaminaType to drain. Energy/Stamina/Adren
	 */
	public void drainStamina(final StaminaType type) {
		if(type == null) throw new NullPointerException("Cannot drain nothing.");
		if(type != StaminaType.MAX)
			setStamina(type, 0.0F);
		else throw new IllegalArgumentException("Can't drain the Maximum");
	}
	
	/**
	 * Refills the {@link StaminaType} type. This method calls
	 * setStamina() and passes value of StaminaType max.
	 * 
	 * @param type			StaminaType to refill. Energy/Stamina/Adren
	 */
	public void refillStamina(final StaminaType type) {
		if(type == null) throw new NullPointerException("Cannot refill nothing.");
		if(type != StaminaType.MAX)
			setStamina(type, this.max);
		else throw new IllegalArgumentException("Can't refill the Maximum");
	}
	
	/**
	 * Resets the max to the default. Calls setStamina() and passes the
	 * static variable: DEFAULT_MAX.
	 */
	public void resetMax() {
		setStamina(StaminaType.MAX, DEFAULT_MAX);
	}
	
	/**
	 * Sets the Stamina of {@link StaminaType} to value.
	 * 
	 * @param type				StaminaType to be set.
	 * @param value				Amount to set.
	 */
	public void setStamina(final StaminaType type, float value) {
		if(type == null) throw new NullPointerException("Cannot set nothing.");
		if(value < 0) throw new IllegalArgumentException("Attempted to set " + type + " to a negative value: " + value);
		/*
		 * The following cases decided which actions need to be performed in
		 * order to keep the following rules:
		 * 		1. Nothing can be higher than Max.
		 * 		2. Stamina cannot be higher than Energy.
		 */
		switch(type) {
			case MAX:
				if(value < this.adren) this.adren = value;
				if(value < this.energy) this.energy = value;
				if(this.energy < this.stamina) this.stamina = this.energy;
				this.max = value;
				break;
			case ENERGY:
				if(value > this.max) value = this.max;
				this.energy = value;
				if(value < this.stamina) this.stamina = this.energy;
				break;
			case STAMINA:
				if(value > this.max) value = this.max;
				if(value > this.energy) value = this.energy;
				this.stamina = value;
				break;
			case ADREN:
				if(value > this.max) value = this.max;
				this.adren = value;
				break;
			default:
				throw new IllegalArgumentException("Invalid StaminaType. This shouldn't happen.");
		}
	}
	
	/**
	 * Subtracts value amount of stamina from {@link StaminaType} type.
	 * This method calls addStamina() with a negated value. This method
	 * is purely for convenience.
	 * 
	 * @param type				StaminaType to be subtracted from.
	 * @param value				Amount to be subtracted.
	 */
	public void substractStamina(final StaminaType type, final float value) {
		addStamina(type, -value); 
	}
	
	private float max;						// Maximum amount for all Stamina.
	private float energy;					// Daily Energy. This value effects the amount of straight work the player can perform. When this runs out the player passes out.
	private float stamina;					// Stamina of the player. When this runs out the player cannot do certain actions.
	private float adren;					// Special Stamina that allows for limit-less use of stamina for an amount of time.
	
	private final DataWatcher dw;			// Player DataWatcher that allows for more streamlined data control.
	private final EntityPlayer player;		// Player the Property Set is associated with.

}
