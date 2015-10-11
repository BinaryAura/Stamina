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
	
	final static String MODID = "stamina";
	final static String NAME = "Stamina";
	final static String VERSION = "1G0";
	final static String CLIENTPROXY = "net.binaryaura.stamina.client.";
	final static String COMMONPROXY = "net.binaryaura.stamina.";
	
	@Instance(MODID)
	public static Stamina instance;
	
	@SidedProxy(clientSide = CLIENTPROXY + "ClientProxy", serverSide = COMMONPROXY + "CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
