# 🧑‍💻 service-user

O `service-user` é um microsserviço desenvolvido em Java 21 utilizando Spring WebFlux, responsável pelo **cadastro de usuários** e **geração de tokens de autenticação (JWT)**. Este serviço é parte do ecossistema de microsserviços do projeto **FIAP X - Sistema de Processamento de Vídeos**.

---

## ⚙️ Tecnologias utilizadas

- ✅ Java 21
- ✅ Spring WebFlux
- ✅ Spring Security (JWT)
- ✅ Docker + Kubernetes
- ✅ Terraform
- ✅ AWS
- ✅ Lombok
- ✅ MongoDB
- ✅ Swagger (OpenAPI)
- ✅ Testes Unitários (JUnit + Mockito)
- ✅ Maven

---

## 🚀 Funcionalidades

- 📥 Cadastro de usuários
- 🔐 Autenticação com retorno de token JWT
- 🧪 Testes automatizados unitários

---

## 📦 Como executar localmente

### Pré-requisitos

- Java 21
- Maven 3.8+
- Docker (opcional para execução em container)

### 📁 Documentação da API

- Após a aplicação estar em execução, a documentação estará disponível em:

```
http://<url_service>/swagger-ui/index.html
```

### 🐳 Docker

```
docker build -t service-user .
```

## 📝 Considerações Finais

O ```service-user``` desempenha um papel central na arquitetura do projeto FIAP X - Sistema de Processamento de Vídeos, sendo responsável tanto pelo ```cadastro de novos usuários``` quanto pela ```autenticação``` e ```emissão de tokens JWT```.

Implementado com Java 21 e o paradigma reativo do Spring WebFlux, o serviço foi projetado para lidar com alta concorrência, mantendo performance e escalabilidade. O cadastro de usuários é tratado com validações e persistência segura, preparando a base de dados para uma comunicação consistente com os demais microsserviços.

A autenticação baseada em JWT garante uma estrutura moderna de segurança, permitindo que apenas usuários autorizados possam interagir com funcionalidades protegidas do sistema. O uso de Swagger facilita a integração e testes via documentação interativa, enquanto Docker, Kubernetes e Terraform asseguram uma implantação automatizada, versionada e padronizada.