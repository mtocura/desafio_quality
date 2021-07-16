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

