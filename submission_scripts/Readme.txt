The final submission consists of 4 web applications.
	- The Game Engine and a sample output client which are bundled in the GameEngine.war for ease of running 
	- The Input component is bundled in Input.war
	- The sample input client is bundled in BrowserClient.war
	- The GameCreator 

To make things easier we have submitted 2 preconfigured instance of glassfish4.
	- Glassfish4-GameEnine should be run first and contains the GameEngine.war
	- Glassfish4-Input should be run second and contains both the Input.war and BrowserClient.war
and a preconfigured instance of tomcat7 for the GameCreator (note without nodejs installed this will load without access to game sprites)

Again to make things easier there is a startup script for running.
	UNIX:
	- Navigate to the directory with startup.sh and shutdown.sh and grant execution permissions to these.
		 chmod +x *.sh
	 - Run startup.sh to run both servers and then visit:
	 	http://localhost:8080/GameEngine
 	 and:
 	 	http://localhost:22501/BrowserClient
 	in 2 seperate browser windows to see view the sample output and sample input (both configured to run in a browser on the local machine at this point in time with a game preloaded as was seen in the presentation)

 	WINDOWS
 	- Run startup.bat to run both servers and then visit:
	 	http://localhost:8080/GameEngine
 	 and:
 	 	http://localhost:22501/BrowserClient
 	in 2 seperate browser windows to see view the sample output and sample input (both configured to run in a browser on the local machine at this point in time with a game preloaded as was seen in the presentation)

 The GameCreator needs access to a server that runs on Node.js and is not as simple to preload (note: it will still work without this server but it will not load any game sprites).
 To run the GameCreator please follow the following steps:
 	- install node.js
 	- in a terminal navigate to builder_ui/server directory of this submission
 	- run: 
 		npm install
 		node server
	- navigate back to the submission directory and run the GameCreator-startup script
 	- Visit http://localhost:37333/ in another browser window


When finished run the appropriate shutdown scripts

NOTE: all source is included in this submission and if you would like the option to build it yourself then simply install Maven and run:
	mvn install
in the SENG4800-submission directory.


If there are any problems please contact me at 
rossjbille@gmail.com