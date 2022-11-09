# avaliacao-games
Repositório da equipe do projeto "Avaliação de Games"

## Instruções Git ##

### Preparação ###

1 - Abra o git bash

ou abra o VSCode e clique no menu "Terminal"

2 - Execute os seguintes comandos para configurar o nome do seu usuário e o seu e-mail

`git config --global user.name "Seu Nome"`

`git config --global user.email "seu_email@qualquercoisa.com"`

3 - Execute os comandos abaixo para configurar os caracteres de fim-de-linha

`git config --global core.autocrlf true`

`git config --global core.safecrlf warn`

### Baixando o projeto ###

1. Execute o comando abaixo para se mover para o seu diretório 

`cd /d/<caminho do seu diretório do seu workspace>`

2. Execute o comando abaixo para baixar o projeto do GitHub

`git clone https://github.com/ProfNpc/avaliacao-games.git`

3. Mova para o diretório do projeto

`cd avaliacao-games`

Com isso será criada uma pasta com o seu projeto

>Obs.: Se você executar o comando abaixo verá que o seu projeto já está apontando para repositório remoto correto.

`git remote -v`
	

### Como baixar as atualizações do repositório remoto: ###

`git pull origin main`

ou

`git pull`

### Como subir suas alterações para o repositório remoto: ###

1. Execute o comando abaixo para verificar os arquivos alterados

`git status`

2. Execute o comando abaixo para verificar o que foi alterado em cada arquivo

`git diff`

3. Uma vez que você se certificou que as alterações estão corretas, execute o comando a seguir para preparar um pacote de alterações para serem preservadas no repositório "estacionando" essas alterações na área conhecida como "stage".

`git add .`

4. Adicione o pacote de alterações ao seu repositório local com o comando abaixo:

`git commit -m "<descrição sucinta das alterações incluídas neste commit>"`

5. Por fim, envie as alterações para o repositório remoto com

`git push origin main`

ou

`git push`

## Como abrir o projeto no Spring

1. No Spring, clique em File > Import

2. Procure a pasta Maven, abra-a e clique duas vezes em "Existing Maven Projects"

3. Em Root Directory, clique em Browse, navegue até a pasta do repositório e clique em "Selecionar pasta"

4. Marque a caixinha que aparecer na seção "Projects" e clique em "Finish"

5. Pronto. O Spring vai estar aberto diretamente na pasta do projeto dentro do repositório. Qualquer alteração que você fizer no projeto do Spring será diretamente salva no repositório.