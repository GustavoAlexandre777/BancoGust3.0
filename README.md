BancoGust 3.0 
Um sistema bancário simples desenvolvido em Java para simular operações de contas correntes, poupanças e jurídicas.

 Sobre o Projeto
Este é o meu primeiro projeto individual em Java, desenvolvido do zero para explorar conceitos de Programação Orientada a Objetos (POO) e simular um sistema bancário básico. O objetivo principal é criar uma plataforma onde os usuários possam gerenciar diferentes tipos de contas (Pessoa Física e Pessoa Jurídica) e realizar operações como depósitos, saques, uso de crédito, pagamento de faturas e controle de impostos.

Atualmente, o projeto já consolidou todas as funcionalidades em uma interface main unificada e mais robusta, permitindo que o usuário crie sua própria conta e escolha o tipo desejado.

Este é um projeto em constante evolução, e estou focado em expandir e aprimorar o código-fonte, adicionando mais recursos e refinando a arquitetura.

🛠 Tecnologias Utilizadas
Java: Linguagem de programação principal.

 Funcionalidades Atuais
O sistema oferece as seguintes operações para diferentes tipos de contas:

Contas de Pessoa Física (PF)
Abertura de Conta: Criação de contas básicas com nome e CPF, com validação de idade mínima.

Depósito: Adicionar fundos à conta.

Saque: Retirar fundos da conta, com validação de saldo e estado da conta.

Controle de Crédito:

Uso de limite de crédito.

Pagamento de fatura de crédito.

Tipos de Conta: Classificação automática da conta (BÁSICO, PREFERENCIAL, PREMIUM, PRIVATE) baseada no valor do primeiro depósito.

Gestão de Conta: Bloquear, reativar e inativar conta.

Histórico de Transações: Visualizar depósitos e saques realizados.

Alteração de Dados: (Acesso restrito por senha "Admin")

Mudar nome do cliente.

Mudar número da conta.

Alterar estado da conta (ativo, inativo, bloqueado).

Mudar tipo de cliente (Básico, Preferencial, Premium, Private).

Contas de Pessoa Jurídica (PJ)
Abertura de Conta: Criação de contas com nome, CNPJ e Razão Social.

Depósito: Adicionar fundos à conta.

Saque: Retirar fundos da conta, com aplicação de taxas baseadas no tipo de PJ.

Classificação de PJ: Definir tipo (MEI, LTDA, SA) e calcular imposto anual com base no faturamento.

Pagamento de Impostos: Parcelamento do imposto de renda anual em até 12 vezes.

🚀 Próximos Passos e Melhorias Futuras
Persistência de Dados: Implementar a gravação de dados em arquivos ou banco de dados de forma mais robusta, garantindo que as informações das contas não sejam perdidas ao fechar o programa.

Validação de Entradas: Aprimorar a validação de entradas do usuário para evitar erros e exceções, tornando o sistema mais resiliente.

Segurança: Reforçar as validações e implementar conceitos básicos de segurança (como senhas mais robustas, criptografia de dados sensíveis).

Testes Unitários: Adicionar testes automatizados para garantir a corretude das funcionalidades e facilitar futuras expansões.

Boas Práticas: Continuar refatorando o código para seguir as melhores práticas de POO e organização.

Interface Gráfica (Opcional): No futuro, talvez implementar uma interface gráfica (GUI) para o sistema, tornando-o mais amigável.

 Como Rodar o Projeto Localmente
Para testar o projeto em sua máquina, siga os passos abaixo:

Clone o repositório:

Bash

git clone https://github.com/GustavoAlexandre777/BancoGust3.0.git
Navegue até a pasta do projeto:

Bash

cd BancoGust3.0
Compile as classes Java:
Se você estiver usando uma IDE como IntelliJ IDEA, Eclipse ou VS Code com Java Extension Pack, a compilação geralmente é automática. Caso esteja usando o terminal, execute na raiz do projeto:

Bash

javac br/com/bancoGust/ui/*.java br/com/bancoGust/core/*.java
Execute o programa:
O sistema agora possui uma interface main unificada. Execute a partir da raiz do projeto:

Bash

java br.com.bancoGust.ui.Main
 Como Contribuir
Contribuições são sempre bem-vindas! Se você tiver alguma ideia, sugestão de melhoria ou encontrar algum bug, sinta-se à vontade para:

Abrir uma Issue: Descreva o bug ou a funcionalidade desejada em detalhes.

Criar um Pull Request (PR): Se você implementou uma melhoria ou correção, envie um PR. Por favor, siga as boas práticas de código Java.

 Autor
Gustavo Alexandre

GitHub = https://github.com/GustavoAlexandre777  

LinkedIn = https://www.linkedin.com/in/gustavo-alexandre-609151312/

E-mail = gustavoalexandre1799@gmail.com
