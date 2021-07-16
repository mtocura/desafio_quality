# Desafio Quality

Este repositório contém a solução para o desafio quality.

## Bairros
| Parâmetro | Tipo | Descrição |
| --------- | ---- | ----------------- |
| prop_district | String | Nome do bairro |
| value_district_m2 | BigDecimal | Valor do m2 no bairro |
| id | Long | ID do bairro |
| name | String | Nome do bairro |
| value_m2 | BigDecimal | Valor do m2 no bairro |
| message | String | Mensagem de erro |
| ValidationError | Map | Lista de chave-valor com as validações do payload |
- ### POST ````/api/districts````
    - Endpoint responsável por cadastrar um bairro no banco de dados
    - Body request:
    ````
    {
        "prop_district": "Vila Olímpia",
        "value_district_m2": 12500.50
    }
    ````
    - Caso o body passado esteja de acordo com as regras de validações o bairro é
    cadastrado no sistema e como retorno temos o código ``201`` e no header temos
    a url de onde o bairro está cadastrado
    - Caso o body não cumpra as validações de payload, é retornado o código `400`
    com o seguinte body:
    ```
    {
      "ValidationError": {
          campo: mensagem de erro
      }
    }
    ```  
      
- ### GET `/api/districts`
    - Endpoint responsável por retornar uma lista com todos os bairros cadastrados
    - Body response (``200``):
    ```
    [
        {
            "id": 1,
            "name": "Vila Olímpia",
            "value_m2": 12500.50
        }
    ]
    ```

- ### GET ``/api/districts/{id}``
    - Endpoint responsável por retornar um bairro em específico
    - Body response (``200``):
    ```
    {
        "id": 1,
        "name": "Vila Olímpia",
        "value_m2": 12500.50
    }
    ```
    - Caso o id passado como parâmetro não seja válido, retornamos um status code
    `404` com o seguinte body response:
    ```
    {
        "message": "Distrito não encontrado"
    }
    ```  
  
- ### PUT `/api/districts/{id}`
    - Endpoint responsável por atualizar um bairro específico
    - Body request:
    ```
    {
        "prop_district": "Vila Moraes",
        "value_district_m2": 12500.50
    }
    ```
    - Se o id do bairro existir no banco, o status de retorno é `200`
    - Caso o id não exista o código é `404` e é retornado o seguinte body:
    ```
    {
        "message": "Distrito não encontrado"
    }
    ```  
  
- ### DELETE `/api/districts/{id}`
    - Endpoint responsável por remover o registro de um bairro do banco de dados
    - Caso o id passado como parâmetro exista, o status de retorno é `200`
    - Caso contrário é retornado o status ``404`` e um body com a mensagem:
    ```
    {
        "message": "Distrito não encontrado"
    }
    ```
  
## Propriedades
| Parâmetro | Tipo | Descrição |
| --------- | ---- | ----------------- |
| id | Long | ID de uma propriedade |
| prop_name | String | Nome da propriedade |
| districtId | Long | Id do bairro |
| rooms | Array de Room | Lista de cômodos de uma propriedade |
| prop_m2 | Double | Tamanho em m2 de uma propriedade |
| value | BigDecimal | Valor total de uma propriedade baseada em seu tamanho e bairro |

### Cômodos
| Parâmetro | Tipo | Descrição |
| --------- | ---- | --------- |
| id | Long | ID de um cômodo |
| room_name | String | Nome do cômodo |
| room_width | Double | Largura de um cômodo |
| room_length | Double | Comprimento de um cômodo |
| room_m2 | Double | Tamanho em m2 de um cômodo |

- ### POST ``/api/houses``
  - Endpoint responsável por cadastrar uma propriedade no banco de dados
  - Body request:
  ```
  {
    "prop_name": "Casa",
    "districtId": 1,
    "rooms": [
        {
            "room_name": "Sala",
            "room_width": 15,
            "room_length": 20
        },
        {
            "room_name": "Quarto",
            "room_width": 10,
            "room_length": 20
        },
        {
            "room_name": "Cozinha",
            "room_width": 20,
            "room_length": 20
        },
        {
            "room_name": "Banheiro",
            "room_width": 5,
            "room_length": 10
        }
    ]
  }
  ```
  - Caso o body passado esteja de acordo com as regras de validações a propriedade
  é cadastrada no sistema e como retorno temos o código ``201`` e no header temos
  a url de onde a propriedade está cadastrada
  - Caso o body não cumpra as validações de payload, é retornado o código `400`
  com o seguinte body:
  ```
  {
    "ValidationError": {
      campo: mensagem de erro
    }
  }
  ```  
  - Caso o id do bairro (``districtId``) passado no payload não esteja cadastrado
  na tabela de bairros, então o código de retorno é `404` com o seguinte body:
  ```
  {
    "message": "Distrito não encontrado"
  }
  ``` 
  
- ### GET `/api/houses`
  - Endpoint responsável por retornar uma lista de propriedades cadastradas no
  banco
  - Body response (`200`):
  ```
  [
    {
        "id": 1,
        "name": "Casa",
        "district": {
            "id": 1,
            "value_m2": 13000.00
        },
        "rooms": [
            {
                "id": 1,
                "name": "Sala",
                "width": 15.0,
                "length": 20.0
            },
            {
                "id": 2,
                "name": "Quarto",
                "width": 10.0,
                "length": 20.0
            },
            {
                "id": 3,
                "name": "Cozinha",
                "width": 20.0,
                "length": 20.0
            },
            {
                "id": 4,
                "name": "Banheiro",
                "width": 5.0,
                "length": 10.0
            }
        ]
    }
  ]
  ```
  
- ### GET `/api/hosues/{id}`
  - Endpoint responsável por retornar uma propriedade específica
  - Body response (`200`):
  ```
  {
    "id": 1,
    "name": "Casa",
    "districtId": 1,
    "rooms": [
        {
            "id": 1,
            "name": "Sala",
            "width": 15.0,
            "length": 20.0
        },
        {
            "id": 2,
            "name": "Quarto",
            "width": 10.0,
            "length": 20.0
        },
        {
            "id": 3,
            "name": "Cozinha",
            "width": 20.0,
            "length": 20.0
        },
        {
            "id": 4,
            "name": "Banheiro",
            "width": 5.0,
            "length": 10.0
        }
    ]
  }
  ```
  - Caso o id passado como parâmetro o código de retono é `404` com o seguinte body:
  ```
  {
    "message": "Propriedade não encontrada."
  }
  ```