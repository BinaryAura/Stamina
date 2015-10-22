package net.binaryaura.stamina.entity.player;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class StaminaPlayer implements IExtendedEntityProperties {

	public static enum StaminaType {
		MAX(20),
		ENERGY(21),
		STAMINA(22),
		ADREN(23);
		
		private StaminaType(int meta) {
			this.meta = meta;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		private final int meta;
	}
	
	public final float DEFAULT_MAX = 2000.0F;
	public final static String NAME = "StaminaPlayer";
	
	public final static void register(EntityPlayer player) {
		player.registerExtendedProperties(NAME, new StaminaPlayer(player));
	}
	
	public StaminaPlayer(EntityPlayer player) {
		this.player = player;
		this.dw = this.player.getDataWatcher();
		this.stamina = this.energy = this.stamina = DEFAULT_MAX;
		this.adren = 0;
		
		this.dw.addObject(StaminaType.STAMINA.getMeta(), this.stamina);
		this.dw.addObject(StaminaType.MAX.getMeta(), this.max);
		this.dw.addObject(StaminaType.STAMINA.getMeta(), this.stamina);
		this.dw.addObject(StaminaType.ADREN.getMeta(), this.adren);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub

	}
	
	private float max;
	private float energy;
	private float stamina;
	private float adren;
	
	private final DataWatcher dw;
	private final EntityPlayer player;

}
