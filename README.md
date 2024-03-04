<h1 align="center">OrderParkingBR</h1>

<p align="center">Projeto responsável por gerenciar reservas de estacionamento em parquímetros</p>

Sobre
=================

	Projeto responsável por gerenciar reservas de estacionamento em parquímetros, utilizando mongodb com estrutura de dados desnormalizada;




Tabela de conteúdo
=================
<!--ts-->
   * [Sobre](#Sobre)
   * [Tabela de Conteúdo](#tabela-de-conteúdo)
   * [Iniciar App localmente](#Iniciar-App-localmente)
   * [Iniciar App apenas com docker compose](#Iniciar-App-apenas-com-docker-compose)
   * [Utilização-documentação](#Utilização-documentação)
   * [Arquitetura](#Arquitetura)
<!--te-->

Iniciar App localmente
=================

	Iniciar banco de dados com docker-compose
        no diretório dockerdb
        - executar docker-compose -f dc-mongodb-cluster.yml up -d
        - executar apenas no primeiro start em um terminal git ou bash: sh initdatabase.sh

	Iniciar aplicação
       - executar mvn spring-boot:run no diretório da aplicação (java 17)
       - ou startar pela IDE

    Para finalizar os containers
        no diretório dockerdb
       - executar mantendo volumes: docker-compose -f dc-mongodb-cluster.yml down -d
       - executar removendo volumes: docker-compose -f dc-mongodb-cluster.yml down -d -v

Iniciar App apenas com docker compose
=================

	Iniciar banco de dados com docker-compose
        na raiz do projeto
        - executar docker-compose -f dc-init.yml up -d
        no diretório dockerdb
        - executar apenas no primeiro start em um terminal git ou bash: sh initdatabase.sh

    Para finalizar os containers
        no diretório raiz
       - executar mantendo volumes: docker-compose -f dc-init.yml down -d
       - executar removendo volumes: docker-compose -f dc-init.yml down -d -v


Utilização-documentação
=================

    - Swagger acessível em http://localhost:8002/swagger-ui.html

	- Exemplo de curl para criar reserva de estacionamento:
        curl --location 'http://localhost:8002/parking/order' \
        --header 'Content-Type: application/json' \
        --data '{
        "vehiclePlate": "GAL2A33",
        "parkingTime": 120,
        "payment": "DEBIT",
        "parkingmeterCode": "f679af614f1810e505df08eac609c16c"
        }'
		
    - Exemplo de curl para adicionar tempo a uma reserva de estacionamento
        curl --location --request PUT 'http://localhost:8002/parking/order/65e1b02211a579207490b88d' \
        --header 'Content-Type: application/json' \
        --data '{
        "parkingTime": 60,
        "payment": "CREDIT"
        }'

	- Exemplo de curl para consulta de reservas de estacionamento paginada, default size = 10 e page = 0
		curl --location 'http://localhost:8002/parking/order?page=0&size=25'
		
	- Exemplo de curl para consulta de reservas de estacionamento por data de expiração maior que data atual e código do parquímetro
        curl --location 'http://localhost:8002/parking/order/f679af614f1810e505df08eac609c16c?dateFinal=2024-03-01T18%3A33%3A00'

	- Exemplo de curl para consulta de reservas de estacionamento por data de expiração maior que data atual e placa do veívulo
        curl --location 'http://localhost:8002/parking/order/active/GAL2A69?dateFinal=2024-03-01T18%3A33%3A00'

	- Exemplo de curl para consulta de reservas de estacionamento por data de expiração maior que data atual e nome da rua
        curl --location 'http://localhost:8002/parking/order/active/parkingmeter/leblon?dateFinal=2024-03-01T18%3A33%3A00'
	
	

Arquitetura
=================

<img src="arq_orderparkingbr.jpg">
