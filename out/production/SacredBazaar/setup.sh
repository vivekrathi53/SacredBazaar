#!/bin/bash
echo "Prerequisites for running the project are - Java Runtime Environment (JRE) enabled PC and SQL Server preinstalled in PC (any Windows/Linux machine is supported)."
echo "Do You Want To Continue?(Y/N) "
read reply
if [ $reply == "Y" ]
then
    echo "username and password for admin is admin (both)."
    output=`java ServerSetup`
    check=`echo $?`
    if [ $check == "0" ]
    then
        echo "Now Application is ready to use. Provide any feedback at vivekrathi53@gmail.com and everybody is free to contribute to this project via this repo. Soon I will upload full stack self installable version util... Happy Coding"
    else
        echo "There was an error. Contact Admin or send feedback with screenshots at vivekrathi53@gmail.com"
        echo "error details = " $output
    fi
fi