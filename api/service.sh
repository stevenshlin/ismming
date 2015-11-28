#!/usr/bin/env bash

BASE_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/

function start_jetty(){
    java -jar target/api.jar &
    echo $! > $BASE_DIR/target/jetty.pid
}

function stop_jetty(){
    kill -9 $(cat $BASE_DIR/target/jetty.pid)
}

function install_app(){
    mvn clean install
}
