package net.binaryaura.stamina;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Stamina.MODID, name = Stamina.NAME, version = Stamina.VERSION)
public class Stamina {
	
	public static final String MODID = "stamina";
	public static final String NAME = "Stamina";
	public static final String VERSION = "1G0-2";
	public static final String CLIENTPROXY = "net.binaryaura.stamina.client.";
	public static final String COMMONPROXY = "net.binaryaura.stamina.";
	
	@Instance(MODID)
	public static Stamina instance;
	
	@SidedProxy(clientSide = CLIENTPROXY + "ClientProxy", serverSide = COMMONPROXY + "CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
}
