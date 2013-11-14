echo Starting GameEngine server...
glassfish4-GameEngine\bin\asadmin start-domain 
echo GameEngine running
echo Please visit http://localhost:8080/GameEngine for a sample output client

echo Starting Input Server...
glassfish4-Input\bin\asadmin start-domain 
echo Input component running
echo Please visit http://localhost:22501/BrowserClient for a sample input client