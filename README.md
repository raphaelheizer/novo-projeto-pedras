# Desafio Stone - Elixir
Olá. Agradeço a atenção por antecipado. Tudo bem?
Abaixo explicarei detalhadamente meus passos, como cheguei às minhas conclusões
e por que tomei determinadas decisões.

Fiz o desafio com atenção total aos requisitos, e me esforcei bastante para
entregar um código limpo, documentado e o mais compreensível possível.

Ficarei satisfeito se a leitura de vocês for fácil e simplificada.

Tomei o máximo de cuidado pra obedecer os princípios SOLID, principalmente
o princípio da responsabilidade única, pois acredito ser um dos mais importantes
princípios.

A aplicação está em português, mas o desenvolvimento está em inglês (comentários e documentações).

*Caso prefira, pode ir direto às Instruções. Estão lá embaixo.*

## Como realizei?

O projeto foi construído em Java, com JDK_14 v.2, e disponibilizei um
repositório do GitHub com os arquivos. Não se preocupem, eu mudei o nome do
repositório para algo sem relação com o desafio para evitar que o código seja
encontrado facilmente pelo Google por outras pessoas.

No projeto, temos duas classes principais, a Main e a DueValueCalculator, que
é a classe central da aplicação, onde cumprem-se os requisitos apresentados
no desafio, que são:

Calcular a soma dos valores, ou seja, multiplicar o preço de cada item por sua
quantidade e somar todos os itens Dividir o valor de forma igual entre a quantidade de e-mails

Retornar um mapa/dicionário onde a chave será o e-mail e o valor será quanto ele deve pagar nessa conta

Aqui eu gostaria de fazer uma observação, pois fiz duas funções para calcular
a soma dos valores e dividir o resultado de forma igual entre os e-mails,
pois acredito que além de não causar nenhum impacto negativo no cumprimento
do requisito, ainda fará jus ao princípio que citei logo ali em cima.

### Como organizei?

A classe Main é majoritariamente composta de objetos e campos estáticos, propositalmente.
Como preferi fazer uma aplicação de console, me pareceu viável fazer desta forma.

Temos três modelos de Objeto. EmailList, Email e Item. Todos eles possuem
características especiais e possuem métodos próprios ou herdados de classes-pai
com relações necessárias entre si.

Muitas funções destes objetos foram criadas para cumprir regras de boa prática
e nem todas foram utilizadas na aplicação, mas não há nenhuma implementação
complexa ou algo que possa tornar o desenvolvimento complicado no futuro, mesmo
que a aplicação seja apenas para o desafio.

Todas as classes e métodos estão documentadas (javadoc) e também comentei
todas as linhas de código em que julguei necessário, tentando fazer de maneira
o mais clara possível.

## Como cheguei no resultado

Na divisão euclidiana (Divisão de inteiros com resto inteiro), o teorema da
divisão explicita que para dois números inteiros (o divisor e o dividendo),
existe um outro número inteiro maior ou igual a zero (o resto),
que é sempre menor que o divisor.

Em termos de programação, significa que podemos dividir dois Integers
e logo depois verificar se (int1 / int2) >= 0. Se for maior que zero, então,
da mesma forma, (int1 % int2) < int2.

Aplicando esta lógica, criei um _for each_ que verifica para cada ocorrência
se existe (int1 % int2) < int2. Se existir, o resultado de minha iteração
será igual ao valor da divisão do valor total pelo número de e-mails acrescido
de um.

Isso significa, também, que se o resto for igual a 5, então estas pessoas
terão um centavo a mais, enquanto as outras terão o valor da divisão sem
qualquer acréscimo. E isso ocorrerá por ordem de cadastro de e-mails, para que
tenhamos uma "ordem de chegada". Essa é minha estratégia para realizar a
divisão de maneira igualitária e, de certa forma, de maneira eficiente. Será
tão proporcional quanto o número de e-mails, pois o loop dependerá apenas disso.

## Instruções

O programa pode ser executado de diversas formas, mas preparei um arquivo
chamado desafio-elixir.stone.jar que pode ser executado diretamente do console
via JVM para facilitar um pouco mais.
Este arquivo está na pasta RELEASE_JAR logo na pasta principal.

Para executá-lo, certifique-se que a máquina pode rodar JDK_14.

No prompt, navegue até a pasta do arquivo e execute:

java -jar desafio-elixir-stone.jar

Abraços!
