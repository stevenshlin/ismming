#!/usr/bin/env bash

function start_jetty(){
    check_jetty_out_file_exist
    if [ $? -eq 0 ]; then
        nohup java -jar target/api.jar > target/jetty.out 2>&1 &
    fi
}

function stop_jetty(){
    curl -X POST http://localhost:9090/shutdown
}

function check_jetty_out_file_exist(){
    file="target/jetty.out"
    if [ -f "$file" ]
    then
        echo "$file found."
    else
        echo "$file not found. Create a new one."
        touch "$file"
    fi
}

function install_app(){
    mvn clean install
}
