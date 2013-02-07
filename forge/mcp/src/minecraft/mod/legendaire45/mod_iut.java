package mod.legendaire45;

import mod.legendaire45.blocks.BlockCarottes;
import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.items.ItemToolPelleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod; 
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "mod_iut", name = "mod iut", version = "1.0.0")

@NetworkMod(clientSideRequired = false, serverSideRequired = true)

public class mod_iut
{	
	    @Instance("Mod")
		public static mod_iut modInstance;
		@SidedProxy(clientSide="mod.legendaire45.client.ClientProxy", serverSide="mod.legendaire45.common.CommonProxy", bukkitSide="mod.legendaire45.common.CommonProxy") //Vous remarquerez que Server et Bukkit partagent la même classe.
		public static CommonProxy proxy;
		private static String textureBlock = CommonProxy.textureBlock; //Ici une façon d'appeler une texture par exemple afin de plus bas pouvoir écrire "textureblock"
		private static String textureItem = CommonProxy.textureItem;
		
		@PreInit
		public void initConfig(FMLPreInitializationEvent event)
		{
			//MinecraftForge.setToolClass(this.piocheTool, "pickaxe", 2);
			MinecraftForge.setToolClass(this.pelleTool, "shovel", 2);
			//MinecraftForge.setToolClass(this.hacheTool, "axe", 2);
		}
		
		@Init
		public void load(FMLInitializationEvent event)
		{	
			proxy.registerRenderThings(); //Et oui, il faut bien dire de charger les proxy :)
			/**Enregistre le bloc**/

			GameRegistry.registerBlock(carottes);


			/** D�fini le nom IN-GAME des items/blocs**/

			LanguageRegistry.addName(pelleTool, "Pelle en Emeraude");
			LanguageRegistry.addName(carottes, "Block Carottes");
	    }	

		static EnumToolMaterial toolMaterial= net.minecraftforge.common.EnumHelper.addToolMaterial("MATERIAL", 2, 500, 7F, 3, 9);
		public static final Block carottes = (new BlockCarottes(170, 0, Material.ground)).setTextureFile(textureBlock).setBlockName("Carottes Block").setCreativeTab(CreativeTabs.tabBlock);
		public static final Item pelleTool= (new ItemToolPelleMod(500, toolMaterial )).setTextureFile(textureItem).setItemName("tool_pelle").setIconIndex(3);/*
		public static final Item piocheTool= (new itemToolPiocheMod(, )).setIconIndex().setItemName("tool_pioche");
		public static final Item hacheTool= (new itemToolHacheMod(, )).setIconIndex().setItemName("tool_hache");
		public static final Item �p�eTool= (new itemToolEpeeMod(, )).setIconIndex().setItemName("tool_epee");
		public static final Item fauxTool= (new itemToolFauxMod(, )).setIconIndex().setItemName("tool_faux");*/
}