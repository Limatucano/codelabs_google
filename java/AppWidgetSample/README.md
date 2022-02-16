- Um Aplicativo widget é uma miniatura que aparece na tela inicial ou tela de bloqueio. 
  Os Apps widgets podem ser atualizados periodicamente com novos dados. Para adicionar um app Widget ao seu app no Android Studio, use File > New > Widget > AppWidget.
  Android Studio irá gerar todos arquivos de template que você precisa para desenvolver seu app widget.
  
- O Aplicativo widget provê um arquivo de infos. Esse arquivo define as propriedades gerais do seu widget, incluindo layout, default size, configuração da activity se tiver,
  e periodo de atualização.
  
- O provedor widget é uma classe java que extende de AppWidgetProvider, e implementa os comportamentos de seu pai. A classe AppWidgetProvider herda de BroadcastReceiver.
  Já que widgets são recebedores de boadcast, o provedor widget é definido como um broadcast receiver no Android Manifest com a tag receiver.
  
- Os layouts do widget são baseados em remote views, que possuem hierarquias de view que podem ser exibidas fora do seu app.
  
  |  numeros de colunas | minimo de largura e altura  |
  |---------------------|-----------------------------|
  |  1                  |  40 dp                      |
  |  2                  |  110 dp                     |
  |  3                  |  180 dp                     |
  |  4                  |  250 dp                     |
  
- O usuário pode ter multiplas instancias de seu widget instalado. O método onUpdate() possui uma lista de widget id e atualiza um por um.

- As ações são feitas por pending intents.

- Os widgets podem ser atualizados com a action AppWidgetManager.ACTION_APPWIDGET_UPDATE. Um extra, AppWidgetManager.EXTRA_APPWIDGET_IDS contem uma lista de id dos widgets que serão atualizados.