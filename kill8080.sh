#!/bin/bash
kill $(lsof -t -i:8080)
