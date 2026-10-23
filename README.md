# 🍅 Pomonai — API de Dúvidas

> API REST em **Spring Boot** para receber e armazenar as dúvidas dos alunos do curso de Java, enviadas por um aplicativo **React Native**.

![Java](https://img.shields.io/badge/Java-26-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-API%20REST-brightgreen)
![React Native](https://img.shields.io/badge/React%20Native-App-61dafb)
![Banco](https://img.shields.io/badge/Banco-Relacional-blue)

Para acessar a documentação acesse:
http://localhost:8080/swagger-ui/index.html
---

## 📌 Sobre o projeto

O **Pomonai** conecta o aluno ao professor de forma simples: o aluno digita uma dúvida no aplicativo, e a API guarda tudo em um banco de dados relacional para o professor consultar depois.

```
┌────────────────────┐   HTTP / JSON    ┌────────────────────┐    JPA     ┌──────────────────┐
│  App React Native  │ ───────────────► │  API Spring Boot   │ ─────────► │ Banco relacional │
│    (porta 8081)    │ ◄─────────────── │    (porta 8080)    │ ◄───────── │                  │
└────────────────────┘                  └────────────────────┘            └──────────────────┘
```

| Componente        | Tecnologia            | Porta local |
|-------------------|-----------------------|-------------|
| Aplicativo        | React Native (Metro)  | **8081**    |
| API de Dúvidas    | Spring Boot           | **8080**    |
| Persistência      | Banco relacional      | conforme o banco escolhido |

---

## 🧰 Pré-requisitos

- **JDK** instalado (a versão usada no curso) — confira com `java -version`
- **Git** — confira com `git --version`
- **Maven** (ou use o `mvnw` que acompanha o projeto)
- Um **banco de dados relacional** configurado (ou o banco em memória, para testes)
- **VS Code** (ou outra IDE de sua preferência)

---

## 📥 Como obter a API

O código da API está no **perfil público do professor no GitHub**.

1. Acesse o perfil público: `https://github.com/<SEU-USUARIO>` *(substitua pelo usuário correto)*
2. Abra o repositório da API de dúvidas: `https://github.com/<SEU-USUARIO>/<NOME-DO-REPOSITORIO>`
3. Clique no botão verde **Code** e copie a URL HTTPS.
4. No terminal, clone o projeto:

```bash
git clone https://github.com/romulodf-df/pomonai-app.git
cd pomonai-app
```

> 💡 Sem Git? Use **Code ▸ Download ZIP** no GitHub, extraia a pasta e abra no VS Code.

---

## ⚙️ Configuração

Edite o arquivo `src/main/resources/application.properties` com os dados do **seu** banco relacional.

Exemplo (ajuste driver, URL, usuário e senha conforme o banco usado):

```properties
# Porta da API
server.port=8080

# Conexão com o banco
spring.datasource.url=jdbc:<banco>://localhost:<porta>/<nome_do_banco>
spring.datasource.username=<usuario>
spring.datasource.password=<senha>

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Com `ddl-auto=update`, a tabela da entidade `Duvida` é criada automaticamente na primeira execução.

---

## ▶️ Como executar

A classe principal da aplicação é a `DuvidasApplication`:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DuvidasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuvidasApplication.class, args);
	}

}
```

**Pelo VS Code:** abra a classe `DuvidasApplication.java` e clique em **Run** acima do método `main`.

**Pelo terminal:**

```bash
./mvnw spring-boot:run
```

*(no Windows: `mvnw.cmd spring-boot:run`)*

Quando o console mostrar algo como `Tomcat started on port 8080`, a API está no ar em:

```
http://localhost:8080
```

---

## 📡 Endpoints

Recurso principal: **`/duvida`**

| Método   | Rota            | Descrição                    |
|----------|-----------------|------------------------------|
| `POST`   | `/duvida`       | Cadastra uma nova dúvida     |
| `GET`    | `/duvida`       | Lista todas as dúvidas       |
| `GET`    | `/duvida/{id}`  | Busca uma dúvida pelo id     |
| `PUT`    | `/duvida/{id}`  | Atualiza uma dúvida          |
| `DELETE` | `/duvida/{id}`  | Remove uma dúvida            |

### Modelo `Duvida`

| Campo      | Tipo            | Descrição                        |
|------------|-----------------|----------------------------------|
| `id`       | número          | Identificador (gerado pelo banco)|
| `mensagem` | texto           | A dúvida escrita pelo aluno      |
| `datahora` | data e hora     | Momento do envio                 |

### Exemplo de requisição

```bash
curl -X POST http://localhost:8080/duvida \
  -H "Content-Type: application/json" \
  -d '{"mensagem": "Qual a diferença entre ArrayList e HashMap?"}'
```

> Ajuste o corpo conforme a sua entidade (por exemplo, se o `datahora` é enviado pelo app ou preenchido pela API).

---

## 📱 Conectando o aplicativo React Native

O app roda na porta **8081** (servidor Metro) e envia as dúvidas por `fetch` para a API na porta **8080**:

```javascript
await fetch("http://localhost:8080/duvida", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ mensagem }),
});
```

O endereço `localhost` muda conforme onde o app está rodando:

| Onde o app roda            | URL da API                     |
|----------------------------|--------------------------------|
| Navegador (Expo Web)       | `http://localhost:8080`        |
| Emulador **Android**       | `http://10.0.2.2:8080`         |
| Simulador **iOS**          | `http://localhost:8080`        |
| Celular físico (mesma rede Wi-Fi) | `http://<IP-DO-SEU-PC>:8080` |

### ⚠️ CORS

Como o app (8081) e a API (8080) usam portas diferentes, o navegador pode bloquear as chamadas. Se isso acontecer, libere a origem no controller:

```java
@CrossOrigin(origins = "http://localhost:8081")
```

---

## 🧪 Testando rapidamente

1. Suba a API (`DuvidasApplication`).
2. Abra `http://localhost:8080/duvida` no navegador — deve retornar uma lista (vazia no início).
3. Suba o app React Native e envie uma dúvida.
4. Recarregue a rota acima: a dúvida enviada deve aparecer.

---

## 🛠️ Problemas comuns

| Sintoma | Possível causa | Solução |
|---------|----------------|---------|
| `Port 8080 was already in use` | Outro programa usando a porta | Encerre o programa ou mude `server.port` |
| Erro de conexão com o banco | URL, usuário ou senha incorretos | Revise o `application.properties` |
| `Network request failed` no app | Uso de `localhost` no emulador/celular | Use `10.0.2.2` (Android) ou o IP da máquina |
| Erro de CORS no navegador | Origem 8081 não liberada | Adicione `@CrossOrigin` |

---

## 📚 Tecnologias

- Java + Spring Boot (Spring Web, Spring Data JPA)
- Banco de dados relacional
- React Native

---

## 👨‍🏫 Autor

**Prof. Rômulo**
Neuroeducador Tech — SENAI Taguatinga

---

