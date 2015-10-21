#Stamina Mod

###Description
  
  Minecraft is know for it's unrealism. The Stamina Mod is the first of many mods that will attempt to remedy this fantastic game. Stamina addresses the ability for players to stay up forever and to work forever with outbreaks.
  
  At the mod's core will a Stamina Bar. It in itself consists of three bars: The Energy, Stamina, and Adrenaline Bars:
  
  The Energy bar slowly depletes throughout the day and slightly faster with strenuous activity (e.g., Running, Mining, Jumping). When this depletes the player will pass out. This presents some issues with multiplier. The player would be "sleeping" while other players were still active. A possible solution to this would be a dream world, possibly tailored to the players recent experiences and, even more unlikely, the players fears--reflecting real life dreams.
  The Stamina bar is what most players must be most cautious of. This bar drains solely with exertion however, the bar will never be higher than the energy bar. This means the more tired a player is the less work they can do at a time. Each action has a "cost" which it would take from this bar. When the player doesn't have enough stamina to preform the action, the player simply doesn't. 
  If the Stamina bar is a curse, the Adrenaline bar is the saving grace. When confronted with danger, this bar activates and gives the player free reign on actions, giving him time to "fight or fly." As of now this bar will take 5 seconds to fill and then 30 seconds to drain. When it drains, the bar will drag down the Stamina bar aswell, resulting in exhaustion after depletion. This bar however, has a cooldown period, and the player must have "full" Stamina in order for it to activate, that is, the Stamina bar must be the same level as the energy bar.
  
  One final idea for this mod is for weight to effect player speed and energy depletion. This would require a massive list of item and block weights. A config file would be added to adjust these values if desired.
  
###Development

  To set up this mod, download the forge source files and copy all the files __*save*__ the src file in the forge zip with the src file in this repo, allow the os to overwrite all files. Open the command prompt and navigate to the project (or shift click and select Open Command Prompt here, if your system supports it). Type the command: "gradlew setupDecompWorkspace --refresh-dependencies". Wait for the program to finish and then type the following command for eclipse: "gradlew eclipse". If you're using IntelliJ, open up IntelliJ and import a project. Navigate to the build.gradle file and click ok. Wait for IntelliJ to finish building the project then, in the Terminal type "gradlew genIntellijRuns". Then the project should be ready to go.
