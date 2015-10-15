@echo off

set eclipse="C:/Program Files/Eclipse/eclipsec.exe"
set intellij="C:/Program Files (x86)/JetBrains/IntelliJ IDEA Community Edition 14.1.5/bin/idea64.exe"
set defaultide=eclipse

echo Building Forge API
Start /w gradlew setupDecompWorkspace --refresh-dependencies

:DefaultIDE
	if /i %defaultide% == eclipse (
		goto Eclipse
	) else if /i %defaultide% == intellij (
		goto IntelliJ
	) else if /i %defaultide% == none (
		echo No Default IDE
	) else if /i %defaultide% == "" (
		echo No Default IDE
	) else (
		echo Invalid IDE: %defaultide%
	)

:SetupIDE
	echo Choose an IDE
	echo
	echo 1-Eclipse
	echo 2-IntelliJ
	set /p ide=
	
	if %ide%==1 (
		goto Eclipse
	) else if %ide%==2 (
		goto IntelliJ
	) else (
		echo Invalid input: %ide%
		goto SetupIDE
	)

:Eclipse
	echo Setting up Eclipse IDE
	Start /w gradlew eclipse
	echo Setup Complete
	if exist %eclipse% (
		echo Opening Eclipse
		%eclipse% -data %cd%/eclipse
	) else (
		echo Invalid Eclipse Path: %eclipse%
	)
	goto EOF

:IntelliJ
	if not exist %intellij% (
		echo Invalid IntelliJ Path: %intellij%
		goto InvalidIntelliJ
	)
	%intellij% %cd%/build.gradle
	:IntelliJLoop
	if exist .idea/libraries (
		echo Setting up IntelliJ
		start /w gradlew genIntellijRuns
		echo Setup Complete
		echo Opening IntelliJ
		%intellij% %cd%/%ModName%.iml
		goto EOF
	)
	goto IntelliJLoop
	
:InvalidIntelliJ
	echo In the IDEA, click on "Import Project."
	echo Navigate to the build.gradle file in the the project directory.
	echo Click OK. Wait for IDEA to Build the Project.
	echo Close IDEA.
	
:InvalidIntelliJLoop
	if exist .idea/libraries (
		echo Setting up IntelliJ
		Start /w gradlew genIntellijRuns
		goto EOF
	)
	goto IntelliJLoop

:EOF

	echo DONE
	exit