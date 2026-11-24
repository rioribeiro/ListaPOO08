# Calculadora de Equação do 2º Grau

Aplicação Java com interface web Javelit para calcular as raízes e gerar pontos
de uma parábola.

## Requisitos

JDK 21 ou superior. Para executar, escolha uma das opções abaixo: JBang ou
Maven.

## Executar com JBang

Execute na raiz do projeto.

### PowerShell

```powershell
jbang run --deps io.javelit:javelit:0.89.0 -s src\model\Equacao.java -s src\service\EquacaoService.java src\template\EquacaoUI.java
```

### Bash

```bash
jbang run --deps io.javelit:javelit:0.89.0 -s src/model/Equacao.java -s src/service/EquacaoService.java src/template/EquacaoUI.java
```

## Executar com Maven

Com Maven instalado, execute na raiz do projeto:

### PowerShell

```powershell
mvn compile exec:java -Dexec.mainClass=template.EquacaoUI
```

### Bash

```bash
mvn compile exec:java -Dexec.mainClass=template.EquacaoUI
```

Em qualquer opção, abra http://localhost:8080 no navegador.