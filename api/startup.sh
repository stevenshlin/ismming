#!/usr/bin/env bash

. service.sh

install_app
if [ $? -eq 0 ]; then
    start_jetty
fi