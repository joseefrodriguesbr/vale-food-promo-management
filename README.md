# Projeto Final da Disciplina

# Pós-Graduação em Desenvolvimento Mobile e Cloud Computing – Inatel
## Desenvolvimento de Web Services com segurança em Java no Google App Engine

## Projeto Final da Disciplina
Implementação de uma aplicação Spring boot para cadastros de promoções;

### 👤 Autor: 
José Enderson Ferreira Rodrigues   
jose.rodrigues@pg.inatel.br, jose.e.f.rodrigues.br@gmail.com

## 📌 Implementação
Microserviço em Java/Spring Boot que expõe um CRUD (create, read, update e delete) para a entidade Promotion.

### Requisitos atendidos:
✅ CRUD (create, read, update e delete) para a entidade Promotion

✅ Operações expostas via REST seguindo os padrões HTTP para request e response

✅ Rotas do CRUD exigindo o token gerado pelo auth

✅ Projeto seguindo o padrão de rotas e controllers para as operações

✅ Um restaurante poderá capacidade de registrar, listar, atualizar e deletar uma promoção

✅ O cliente poderá listar todas as promoções e uma opção para listar apenas aquelas promoções que sejam do seu interesse, isto é, promoções que contenham produtos cuja categoria
batem com as categorias preferidas do usuário.

✅ Envio de requições com promoção para user e restaurant, a fim de cache das informações de promoções

✅ Cache de user e restaurant recebidos a partir de requisições oriundas de User e Restaurant 

✅ O serviço de users expondo API para que os usuários consigam buscar as "novidades"

## 📌 Detalhamento da solução

### ⚙️ Porta da API: 8083

### ⚙️ Variáveis importantes em application.properties:
```
spring.application.name=vfp
spring.profiles.active=test
vale-food.restaurant.url=http://localhost:8081/valefood/promotions
vale-food.user.url=http://localhost:8080/valefood/promotions
server.port=8083
```

#### 📂 Estrutura de pastas do serviço VFP (Vale Food Promotion)
```
📦vale-food-promo-management
 ┗📂br.inatel.pos.dm111.vfp
   ┣📂api                          
   ┃ ┣📂core                      
   ┃ ┃ ┗📂interceptor              
   ┃ ┣📂promo          
   ┃ ┃ ┣📂controller
   ┃ ┃ ┗📂service 
   ┃ ┣📂restaurant         
   ┃ ┃ ┣📂controller
   ┃ ┃ ┗📂service 
   ┃ ┗📂user       
   ┃   ┣📂controller
   ┃   ┗📂service 
   ┣📂config             		 
   ┣📂consumer
   ┣📂persistence                      
   ┃ ┣📂promo          
   ┃ ┣📂restaurant          
   ┃ ┗📂user       
   ┗📂publisher           	 

```


### ⚙️ Rotas:

🔐 **Rotas protegidas** (com JWT via AuthController.verificaJWT):  
As rotas abaixo estão dentro do prefixo /estoque, e requerem autenticação JWT.  

🌐 **POST /valefood/promotions**  
* **Descrição:** Insere uma nova promoção.  
* **Body esperado(exemplo):**
```
{
  "name": "Descontos do Feriado",
  "description": "Descontos especiais em pratos selecionados para Feriado!",
  "restaurantId": "daeb779e-b415-4793-86a4-385607a03385",
  "product": {
                "productId": "3ea7f265-a80e-4c73-9af4-df0c2df51627",
                "promotionalPrice": 5
            }
}
```

🌐 **PUT /valefood/promotions/:IdPromocao**  
* **Descrição:** Atualiza uma promoção.
* * **Parâmetro de rota:**  
  * **:IdPromocao** : Id da promoção a ser atualizada  
* **Body esperado(exemplo):**
```
{
  "name": "Descontos do Feriado prorrogado",
  "description": "Descontos especiais em pratos selecionados para Feriado!",
  "restaurantId": "53fcba1c-595b-495f-9d9a-71b67d3e1bd2",
  "product": {
                "productId": "5678e7cb-715e-4121-987a-05a0e021cac6",
                "promotionalPrice": 1.5
            }
}
```

🌐 **DELETE /valefood/promotions/:IdPromocao**  
* **Descrição:** deleta uma promoção.
* * **Parâmetro de rota:**  
  * **:IdPromocao** : Id da promoção a ser deletada

🌐 **GET /valefood/promotions**
* **Descrição:** Lista todas as promoções cadastradas
* **Body esperado(exemplo):**
```
[
    {
        "id": "e9203869-da78-4e09-8237-8c9bbc7aeb60",
        "name": "Descontos do Feriado",
        "description": "Descontos especiais em pratos selecionados para Feriado!",
        "restaurantId": "52a2c945-11d0-40b9-85fd-739f537e02bd",
        "restaurantName": "Restaurante Cozinha da Fazenda",
        "product": {
            "productId": "622fc20a-c7c4-4c3a-a266-9845481e69eb",
            "promotionalPrice": 2.5,
            "category": "Carnes",
            "productName": "Linguiça"
        }
    },
    {
        "id": "0a4f48ed-4a05-4084-9a52-2eb82a643f91",
        "name": "Descontos do Feriado",
        "description": "Descontos especiais em pratos selecionados para Feriado!",
        "restaurantId": "cad9771d-29e6-4d8c-8dfb-bb678e32d088",
        "restaurantName": "Pizzaria",
        "product": {
            "productId": "713980f5-2e6f-410a-a064-ea457b3da38e",
            "promotionalPrice": 2.5,
            "category": "Massas",
            "productName": "Macarronada"
        }
    },
    {
        "id": "06bb002f-1f64-486d-a5e3-4e5f30b26983",
        "name": "Descontos do Final de semana",
        "description": "Descontos especiais em pratos selecionados para Final de semana!",
        "restaurantId": "52a2c945-11d0-40b9-85fd-739f537e02bd",
        "restaurantName": "Restaurante Cozinha da Fazenda",
        "product": {
            "productId": "8974a7c6-7046-40ac-8690-17e4a087b969",
            "promotionalPrice": 1.5,
            "category": "Carnes",
            "productName": "File"
        }
    }
]
```

🌐 **GET /valefood/promotions/:IdPromocao**  
* **Descrição:** Consulta uma promoção por Id.
* * **Parâmetro de rota:**  
  * **:IdPromocao** : Id da promoção a ser consultada  
* **Body esperado(exemplo):**
```
{
    "id": "0a4f48ed-4a05-4084-9a52-2eb82a643f91",
    "name": "Descontos do Feriado",
    "description": "Descontos especiais em pratos selecionados para Feriado!",
    "restaurantId": "cad9771d-29e6-4d8c-8dfb-bb678e32d088",
    "restaurantName": "Pizzaria",
    "product": {
        "productId": "713980f5-2e6f-410a-a064-ea457b3da38e",
        "promotionalPrice": 2.5,
        "category": "Massas",
        "productName": "Macarronada"
    }
}
```

🌐 **GET /valefood/promotions/user/:IdUser**  
* **Descrição:** Consulta promoções recomendadas para usuário informado.
* * **Parâmetro de rota:**  
  * **:IdUser** : Id do usuário a filtrar promoções recomendadas 
* **Body esperado(exemplo):**
``` 
[
    {
        "id": "e9203869-da78-4e09-8237-8c9bbc7aeb60",
        "name": "Descontos do Feriado",
        "description": "Descontos especiais em pratos selecionados para Feriado!",
        "restaurantId": "52a2c945-11d0-40b9-85fd-739f537e02bd",
        "restaurantName": "Restaurante Cozinha da Fazenda",
        "product": {
            "productId": "622fc20a-c7c4-4c3a-a266-9845481e69eb",
            "promotionalPrice": 2.5,
            "category": "Carnes",
            "productName": "Linguiça"
        }
    },
    {
        "id": "0a4f48ed-4a05-4084-9a52-2eb82a643f91",
        "name": "Descontos do Feriado",
        "description": "Descontos especiais em pratos selecionados para Feriado!",
        "restaurantId": "cad9771d-29e6-4d8c-8dfb-bb678e32d088",
        "restaurantName": "Pizzaria",
        "product": {
            "productId": "713980f5-2e6f-410a-a064-ea457b3da38e",
            "promotionalPrice": 2.5,
            "category": "Massas",
            "productName": "Macarronada"
        }
    }
]
``` 


🌐 **GET /valefood/promotions/restaurant/:IdRestaurant**  
* **Descrição:** Consulta promoções cadastradas para um restaurante informado
* * **Parâmetro de rota:**  
  * **:IdUser** : Id do restaurante
* **Body esperado(exemplo):**
``` 
[
    {
        "id": "e9203869-da78-4e09-8237-8c9bbc7aeb60",
        "name": "Descontos do Feriado",
        "description": "Descontos especiais em pratos selecionados para Feriado!",
        "restaurantId": "52a2c945-11d0-40b9-85fd-739f537e02bd",
        "restaurantName": "Restaurante Cozinha da Fazenda",
        "product": {
            "productId": "622fc20a-c7c4-4c3a-a266-9845481e69eb",
            "promotionalPrice": 2.5,
            "category": "Carnes",
            "productName": "Linguiça"
        }
    },
    {
        "id": "06bb002f-1f64-486d-a5e3-4e5f30b26983",
        "name": "Descontos do Final de semana",
        "description": "Descontos especiais em pratos selecionados para Final de semana!",
        "restaurantId": "52a2c945-11d0-40b9-85fd-739f537e02bd",
        "restaurantName": "Restaurante Cozinha da Fazenda",
        "product": {
            "productId": "8974a7c6-7046-40ac-8690-17e4a087b969",
            "promotionalPrice": 1.5,
            "category": "Carnes",
            "productName": "File"
        }
    }
]
```


## 🛠️ IDE
- **Eclipse IDE for Enterprise Java and Web Developers - Version: 2025-03 (4.35.0) Build id: 20250306-0812**

## 💻 Linguagem
- **Java(Spring Boot)**
