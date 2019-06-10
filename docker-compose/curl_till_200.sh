#!/usr/bin/env bash
bash -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' http://authoring:8080/cms/ping/)" != "200" ]]; do sleep 5; done'