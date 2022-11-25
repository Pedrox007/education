#!/bin/bash

curl --parallel \
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'\
  -o /dev/null -s -w '\nTotal: %{time_total}s - Status Code: %{http_code}\n' --request GET 'http://localhost:8381/api/v1/edu/organization/ufrn.br'
