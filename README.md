# consignado

Api para emprestimo consignado, desenvolvida com a libguagem 
de programação Java.

### Estrutura pacostes e padrão
 ````
br.com.consignado/
├── api/
│   ├── controller/
│   │   └── ClienteController.java
│   ├── dto/
│   │   ├── LoanDTO.java
│   └── exception/
│       ├── AffiliationInvalidException.java
│       └── CustomerNotFoundException.java
├── data/
│   ├── entity/
│   │   └── Contract.java
|	|	└── Customer.java
|	|	└── Loan.java
│   ├── repository/
│   │   └── LoanRepository.java
|	|	└── ContractRepository.java
|	|	└── CustomerRepository.java
│   |
├── domain/
│   └── service/
│       ├── CustomerService.java
|		└── Simulate.java
│       └── impl/
│           └── CustomerServiceImpl.java
|			└── SimulateServiceImpl.java
└── App.java
````
Dentro temos uam divisão de lógica e responsabilidades, que esta contido dentro de cada módulo.

### Responsabilidade de cada pacote

No pacote da API contem as classes relacionadas à camadas de api.
- **controller**: responsável por lidar com as requisições HTTP
- **dto**: são a classes de transferências de dados, usadas para receber e enviar dados pela api. 
- **exception**: são as classes de exceções personalizadas.

No pacote de data é responsável pela camada de entidades, repositorio de banco de dados.
- **entity**: contém as entidades do domínio.
- **repository**:contém as interfaces de repositorio para acesso aos dados.

No pacote de dominio esta abstraido toda as regras de negocio do sistema.
- **impl**: são os serviçoes que implementam as interfaces, esse serviçoes executam 
operações como criar usuário, validar informações e realizar simulações de empréstimos.
- **service**: são as interfaces utilizadas na implementação.

As funcinalidades desta API incluem:
- **Criação usuário**
- **Validação de informação**
- **Simulação de empréstimo consignado**

### Como utilizar o projeto
Como a api ainda não tem um front, podemos executala localmente na maquina.
para isso precisamos seguir esses passos:
- **clonar projeto ou baixa-lo para seu ambiente de desenvolvimento.**
- **certifique-se que JDK e IDE estejam instalados. Em seguida, importe
o projeto sua IDE**
- **configurar banco de dados no application.properties que são as configurações de banco de dados para garantier que a aplicação suba sem problemas.
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.jpa.hibernate.ddl-auto=update`

então você pode começar a enviar requisições HTTP para as rotas definidas
nos controladores para interagir com api.

- **Utilize o postman para fazer as requisições**
