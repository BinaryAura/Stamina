package net.binaryaura.stamina.entity.player;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class StaminaPlayer implements IExtendedEntityProperties {

	public static enum StaminaType {
		MAX(-1),
		ENERGY(20),
		STAMINA(21),
		ADREN(22);
		
		private StaminaType(int meta) {
			this.meta = meta;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		private final int meta;
	}
	
	public static final float DEFAULT_MAX = 2000.0F;
	public static final String NAME = "StaminaPlayer";
	
	public static final StaminaPlayer get(EntityPlayer player) {
		return (StaminaPlayer)player.getExtendedProperties(NAME);
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new StaminaPlayer(player));
	}
	
	public StaminaPlayer(EntityPlayer player) {
		this.player = player;
		this.dw = this.player.getDataWatcher();
		this.stamina = this.energy = this.max = DEFAULT_MAX;
		this.adren = 0;
		
		this.dw.addObject(StaminaType.ENERGY.getMeta(), this.max);
		this.dw.addObject(StaminaType.STAMINA.getMeta(), this.stamina);
		this.dw.addObject(StaminaType.ADREN.getMeta(), this.adren);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound staminaProperties = new NBTTagCompound();
		staminaProperties.setFloat("max", this.max);
		staminaProperties.setFloat("energy", this.energy);
		staminaProperties.setFloat("stamina", this.stamina);
		staminaProperties.setFloat("adren", this.adren);
		
		compound.setTag(NAME, staminaProperties);
	}

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
				throw new IllegalArgumentException("Invalid StaminaType. This shouldn't happen.");
		}
	}
	
	public void drainStamina(final StaminaType type) {
		if(type != StaminaType.MAX)
			setStamina(type, 0.0F);
		else throw new IllegalArgumentException("Can't drain the Maximum");
	}
	
	public void refillStamina(final StaminaType type) {
		if(type != StaminaType.MAX)
			setStamina(type, this.max);
		else throw new IllegalArgumentException("Can't refill the Maximum");
	}
	
	public void resetMax() {
		this.max = DEFAULT_MAX;
	}
	
	public void setStamina(final StaminaType type, float value) {
		if(value < 0) throw new IllegalArgumentException("Attempted to set " + type + " to a negative value: " + value);
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
	
	public void substractStamina(final StaminaType type, final float value) {
		addStamina(type, -value); 
	}
	
	private float max;
	private float energy;
	private float stamina;
	private float adren;
	
	private final DataWatcher dw;
	private final EntityPlayer player;

}
