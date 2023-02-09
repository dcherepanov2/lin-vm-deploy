# lin-vm-deploy
A program for deploying a Spring boot application using maven, docker and ngrok on a vm machine using SSH connect.

For correct operation, you need: an installed iso image in a virtual machine with open access via SSH.

![Безымянный2](https://user-images.githubusercontent.com/82049934/217823089-0440423c-0354-48ed-a245-7dc658526879.png)

![Безымянный](https://user-images.githubusercontent.com/82049934/217822418-26047da0-b61f-4d8d-8b89-5e4dbeb20ce3.png)

Commands to install SSH-client for Ubuntu:

sudo apt-get update

sudo apt-get upgrade

sudo apt-get install openssh-client

For correct operation, you need to make changes to the deploy-commands.properties, deploy-machine.properties, specifying the data for connecting your local machine via SSH, as well as settings for deploying the server, respectively.

ngrok http {number port your local server}
