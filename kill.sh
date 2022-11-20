#!/bin/bash

DISCOVERY_SERVER_PORT=8761

ORCID_SERVICE_PORT=8181
ORG_SERVICE_PORT=8281
EDU_SERVICE_PORT=8381

do_local() {
  for PORT in ${ORCID_SERVICE_PORT} ${ORG_SERVICE_PORT} ${EDU_SERVICE_PORT} ${DISCOVERY_SERVER_PORT}
  do
      kill $(lsof -t -i:${PORT})
  done
}

do_docker() {
  docker compose down --rmi local
}

# shellcheck disable=SC2068
. ./do.sh Start $@