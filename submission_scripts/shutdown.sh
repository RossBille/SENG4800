echo Stopping GameEngine server...
glassfish4-GameEngine/bin/asadmin stop-domain > /dev/null
echo GameEngine Stopped

echo Stopping Input Server...
glassfish4-Input/bin/asadmin stop-domain > /dev/null
echo Input component Stopped
