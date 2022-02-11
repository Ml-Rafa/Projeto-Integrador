# Projeto-Integrador

## Objetivo
O objetivo deste projeto final é implementar uma API REST no âmbito do slogan e aplicar
os conteúdos trabalhados durante o BOOTCAMP MELI. (Git, Java, Spring, Armazenamento,
Qualidade e testes).

![Anurag's GitHub stats](https://github-readme-stats.vercel.app/api?username=Ml-Rafa&show_icons=true&theme=radical)



## Requisitos

O desafio foi divido em 6 requisitos listados abaixo <img src="https://img.icons8.com/office/16/000000/menu--v1.png"/>

### 1: Insira o lote no armazém de atendimento

    CENÁRIO 1: O produto de um vendedor é registrado.
    DESDE o produto de um Vendedor é registrado
    E que o armazém é válido
    E que o representante pertence ao armazém
    E que o setor é válido
    E que o setor corresponde ao tipo de produto
    E que o setor tenha espaço disponível
    QUANDO o representante entra no lote
    ENTÃO o registro de compra é criado
    E o lote é atribuído a um setor
    E o representante é associado ao registro de estoque
    
### 2: Registrar Venda: Adicione o produto ao carrinho de compras

    CENÁRIO 1: O produto de um vendedor é registrado.
    DESDE o produto de um Vendedor é registrado
    E que o comprador esteja cadastrado
    E que o produto tem estoque
    E que o prazo de validade do produto não seja inferior a 3 semanas
    QUANDO o comprador adiciona o produto com a quantidade ao carrinho
    ENTÃO, um produto é adicionado ao carrinho de compras
    E atualiza o estoque atual do produto
    
### 3: Verifique a localização de um produto no armazém

    CENÁRIO 1: um produto do vendedor é registrado
    DESDE o produto de um Vendedor é registrado
    E que o armazém é válido
    E que o representante pertence ao armazém
    E que o setor é válido
    E que o setor corresponde ao tipo de produto
    E que o setor é dono do lote
    E que o lote possui o produto.
    QUANDO o representante insere o código do produto
    ENTÃO a localização do produto em um setor é exibida
    E que o produto tem um número de lote
    E que o produto é filtrado (ordenado) por número de lote
    E que o produto é filtrado (ordenado) pela quantidade atual do lote (menor para maior)
    E que o produto é filtrado (ordenado) por data de validade (mais antigo para o mais novo)
    
### 4: Consultar o estoque de um produto em todos os armazéns

    CENÁRIO 1: um produto do vendedor é registrado
    DESDE o produto de um Vendedor é registrado
    E que o armazém é válido
    QUANDO o representante insere o código do produto
    ENTÃO a quantidade do produto é exibida em cada armazém     

### 5: Consultar a data de validade por lote
  
     CENÁRIO 1: um produto do vendedor é registrado
     DESDE o produto de um Vendedor é registrado
     E que o armazém é válido
     E que o representante pertence ao armazém
     QUANDO o representante insere o número de dias
     ENTÃO, uma lista de produtos é exibida com uma data de validade entre a data atual e a data futura
     (data atual + dias inseridos)
     E que o produto tem um número de lote
     E que o produto é filtrado por número de lote
     E que o produto é filtrado por data de validade
     E que o produto seja filtrado de acordo com a categoria dos produtos (frescos, congelados,
     refrigerados)
    
### 6: Este requisito é feito individualmente e é obrigatório

    A instrução consiste em criar/adicionar uma nova User Story ao Projeto
    Final, mas desta vez sem especificar o problema a ser resolvido pelo
    Product Owner (PO), ou seja, de natureza GRATUITA. O participante terá
    a possibilidade de explorar criativamente diferentes alternativas de
    possíveis problemas enquanto estes se inserem no universo e na lógica
    de negócio proposta pela cadeira no Projecto Final, para depois
    desenvolver e implementar a solução que considere mais adequada.
    Por sua vez, o requisito terá dois níveis de dificuldade para sua
    resolução:
    - Nível 1: Base, que inclui o desenvolvimento de entregas que serão
    a base para o próximo nível.
    - Nível 2: Bônus, que inclui o desenvolvimento de produtos com
    especificações avançadas.
    Os objetivos gerais e as entregas correspondentes para cada nível são
    especificados abaixo.
    
    
 ### Link do repositório das user stories
 
 ##### Felipe Conceição:
        https://github.com/Ml-Rafa/Projeto-Integrador/tree/req_6
 ##### Gabriela da Rocha:
        https://github.com/Ml-Rafa/Projeto-Integrador/tree/US6-gabrieladarocha
 ##### Marcelo Nader:
        https://github.com/Ml-Rafa/Projeto-Integrador/tree/requisito-6-marcelonader
 ##### Paula Rodrigues:
        https://github.com/Ml-Rafa/Projeto-Integrador/tree/requisito-6-paularodrigues
 ##### Rafael Oliveira:
        https://github.com/Ml-Rafa/Projeto-Integrador/tree/requisito-6-rafael-oliveira
        
 [![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=Ml-Rafa&layout=compact)](https://github.com/Ml-Rafa/github-readme-stats)
 
 ## Como fazer as requisições

 A documentação oficial da API por meio do SWAGGER encontra-se no seguinte link: 

 http://localhost:8081/swagger-ui/index.html

 ## Coleção de casos de teste do Postman.

 https://www.getpostman.com/collections/860f27688c6e4436ce79
    
 ## Colaboradores 
 
 <img src="https://img.icons8.com/office/40/000000/user-male.png"/> https://github.com/fsconceicao
 
 <img src="https://img.icons8.com/office/40/000000/user-female.png"/> https://github.com/gabrielarocha21
 
 <img src="https://img.icons8.com/office/40/000000/user-male.png"/> https://github.com/marcelonader-meli
 
 <img src="https://img.icons8.com/office/40/000000/user-female.png"/> https://github.com/paularodriguesmeli
 
 <img src="https://img.icons8.com/office/40/000000/user-male.png"/> https://github.com/Ml-Rafa
 
