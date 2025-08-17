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
🔓 **Rota pública** (sem autenticação):  
🌐 **GET /**  
* **Descrição:** Rota raiz, responde com uma mensagem simples JSON.  
* **Resposta:** { msg: "Olá mundo!" }  

🔐 **Rotas protegidas** (com JWT via AuthController.verificaJWT):  
As rotas abaixo estão dentro do prefixo /estoque, e requerem autenticação JWT.  

🌐 **POST /estoque**  
* **Descrição:** Insere um novo produto no estoque.  
* **Middlewares:**
  * **validaProduto:** valida os dados do produto.
  * **inserir:** insere o produto no banco de dados.
* **Body esperado(exemplo):**
```
{
    "nome" : "Feijao",
    "fornecedor" : "Camil",
    "quantidade" : 200,
    "preco" : 50.32
}
```

🌐 **PATCH /estoque/:nome**  
* **Descrição:** Atualiza a quantidade e preço de um produto existente, identificado por seu nome.
* **Middlewares:**  
  * **validaProduto:** valida os dados fornecidos para a atualização.
  * **atualizar:** aplica as alterações ao produto.
* **Parâmetro de rota:**  
  * **:nome** : nome do produto a ser atualizado.  
* **Body esperado(exemplo):**
```
{
    "quantidade" : 450,
    "preco" : 18.20
}
```

🌐 **GET /estoque**
* **Descrição:** Busca e retorna a lista de produtos em estoque.
* **Middleware:** buscar
* **Resposta esperada(exemplo):**
```
{
    "count": 2,
    "produtoList": [
        {
            "_id": "68828bea90f6cf8f252d44f7",
            "nome": "Carne",
            "fornecedor": "Friboi",
            "quantidade": 200,
            "preco": 85.76,
            "createdAt": "2025-07-24T19:39:22.597Z",
            "updatedAt": "2025-07-24T19:39:22.597Z",
            "__v": 0
        },
        {
            "_id": "68828c0b90f6cf8f252d44fa",
            "nome": "Arroz",
            "fornecedor": "Joao",
            "quantidade": 3,
            "preco": 40.55,
            "createdAt": "2025-07-24T19:39:55.387Z",
            "updatedAt": "2025-07-24T19:42:43.114Z",
            "__v": 0
        }
    ]
}
```

* **Suporte a rotas com query params:**
  * 📥 Exemplos de requisição:
    * Buscar por nome e fornecedor:
    ```
    GET http://localhost:3003/estoque?nome=Carne&fornecedor=Friboi
    ```
    * Buscar apenas por nome:
    ```
    GET http://localhost:3003/estoque?nome=Carne
    ```
    * Buscar apenas por fornecedor:
    ```
    GET http://localhost:3003/estoque?fornecedor=Friboi
    ```
    
🌐 **DELETE /estoque/:nome**
* **Descrição:** Remove um produto do estoque com base no nome fornecido.
* **Middleware:** excluir
* **Parâmetro de rota:**
  * **:nome** – nome do produto a ser removido.        

## 🛠️ IDE
- **Eclipse IDE for Enterprise Java and Web Developers - Version: 2025-03 (4.35.0) Build id: 20250306-0812**

## 💻 Linguagem
- **Java(Spring Boot)**
