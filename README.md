
# Trabalho-Sockets

Este repositório contém o projeto para o trabalho em grupo do segundo bimestre de programação.

## Estrutura do Projeto

- **lib**: Contém bibliotecas necessárias para a execução do projeto.
- **src**: Contém o código-fonte principal.
- **target**: Contém os arquivos compilados.

## Tecnologias Utilizadas

- Java 17
- Maven 3.8.1
- Gson 2.8.8

## Configuração do Ambiente no IntelliJ

1. Clone o repositório:
   ```sh
   git clone https://github.com/ChrisNoronha/Trabalho-Sockets.git
   ```

2. Abra o IntelliJ IDEA.

3. Selecione **Open** e navegue até o diretório do projeto clonado.

4. O IntelliJ IDEA irá detectar o arquivo `pom.xml` e solicitará para importar o projeto como um projeto Maven. Confirme a importação.

5. Certifique-se de que o JDK 17 está configurado no IntelliJ:
   - Vá para **File > Project Structure**.
   - Em **Project Settings > Project**, defina o **Project SDK** para **JDK 17**. Se o JDK 17 não estiver listado, você pode baixá-lo através do IntelliJ ou [baixá-lo diretamente do site da Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) e adicioná-lo manualmente.

6. Configure a versão do Maven:
   - Vá para **File > Settings**.
   - Em **Build, Execution, Deployment > Build Tools > Maven**, configure o **Maven home directory** para a versão 3.8.1. Se necessário, você pode [baixar o Maven 3.8.1 aqui](https://maven.apache.org/download.cgi).

7. Compile o projeto:
   - No painel lateral, abra a aba **Maven**.
   - Navegue até **Lifecycle** e clique duas vezes em **clean** e depois em **install**.

8. Execute o projeto:
   - No painel lateral, abra a aba **Project**.
   - Navegue até **src/main/java** e encontre a classe principal com o método `public static void main(String[] args)`.
   - Clique com o botão direito na classe e selecione **Run 'NomeDaClassePrincipal.main()'**.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a licença MIT.

---

Para mais detalhes, visite o [repositório no GitHub](https://github.com/ChrisNoronha/Trabalho-Sockets).
