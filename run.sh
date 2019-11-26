gnome-terminal --command="./start-server.sh"
sleep 10s
gnome-terminal -e '/bin/bash -c "cd ~/dsl-project/client/src; node testclient.js"'
gnome-terminal -e '/bin/bash -c "cd ~/dsl-project/client/src; npm start"'
