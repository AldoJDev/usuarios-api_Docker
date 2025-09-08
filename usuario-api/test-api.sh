#!/bin/bash

echo "=== Testando API de Usuários ==="
echo ""

# URL base da API
BASE_URL="http://localhost:8080/api/usuarios"

echo "1. Testando criação de usuário..."
echo "POST $BASE_URL"
curl -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"login":"user001","password":"pass123"}' \
  -w "\nStatus: %{http_code}\n\n"

echo "2. Testando criação do mesmo usuário (deve dar erro)..."
echo "POST $BASE_URL"
curl -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"login":"user001","password":"pass456"}' \
  -w "\nStatus: %{http_code}\n\n"

echo "3. Verificando se usuário existe..."
echo "GET $BASE_URL/user001"
curl -X GET $BASE_URL/user001 \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "4. Verificando usuário que não existe..."
echo "GET $BASE_URL/user999"
curl -X GET $BASE_URL/user999 \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "5. Verificando apenas se usuário existe (endpoint check)..."
echo "GET $BASE_URL/check/user001"
curl -X GET $BASE_URL/check/user001 \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "6. Verificando usuário inexistente (endpoint check)..."
echo "GET $BASE_URL/check/user999"
curl -X GET $BASE_URL/check/user999 \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "7. Testando validação - login muito longo..."
echo "POST $BASE_URL"
curl -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"login":"user001234","password":"pass123"}' \
  -w "\nStatus: %{http_code}\n\n"

echo "8. Testando validação - password muito longo..."
echo "POST $BASE_URL"
curl -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"login":"user002","password":"pass123456789"}' \
  -w "\nStatus: %{http_code}\n\n"

echo "=== Testes concluídos ==="