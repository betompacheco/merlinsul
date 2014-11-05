
var NoOffFirstLineMenus = 7;			// Number of first level items
var LowBgColor = '#98BBD2';			// Background color when mouse is not over
var LowSubBgColor = '#98BBD2';			// Background color when mouse is not over on subs
var HighBgColor = '#236BA7';			// Background color when mouse is over
var HighSubBgColor = '#236BA7';			// Background color when mouse is over on subs
var FontLowColor = '#0076A3';			// Font color when mouse is not over
var FontSubLowColor = '#0076A3';		// Font color subs when mouse is not over
var FontHighColor = '#BAE3EC';			// Font color when mouse is over
var FontSubHighColor = '#BAE3EC';	     	// Font color subs when mouse is over
var BorderColor = '#ffffff';			// Border color
var BorderSubColor = '#ffffff';			// Border color for subs
var BorderWidth = 1;				// Border width
var BorderBtwnElmnts = 1;			// Border between elements 1 or 0
var FontFamily = "verdana,arial,sans-serif";	// Font family menu items
var FontSize = 11;				// Font size menu items
var FontBold = 0;				// Bold menu items 1 or 0
var FontItalic = 0;				// Italic menu items 1 or 0
var MenuTextCentered = 'left';			// Item text position 'left', 'center' or 'right'
var MenuCentered = 'left';			// Menu horizontal position 'left', 'center' or 'right'
var MenuVerticalCentered = 'top';		// Menu vertical position 'top', 'middle','bottom' or static
var ChildOverlap = .2;				// horizontal overlap child/ parent
var ChildVerticalOverlap = .2;			// vertical overlap child/ parent
var StartTop = 150;			        // Menu offset x coordinate
var StartLeft = 5;				// Menu offset y coordinate
var VerCorrect = 0;				// Multiple frames y correction
var HorCorrect = 0;				// Multiple frames x correction
var LeftPaddng = 3;				// Left padding
var TopPaddng = 2;				// Top padding
var FirstLineHorizontal = 0;			// SET TO 1 FOR HORIZONTAL MENU, 0 FOR VERTICAL
var MenuFramesVertical = 1;			// Frames in cols or rows 1 or 0
var DissapearDelay = 100; 			// delay before menu folds in
var TakeOverBgColor = 1;			// Menu frame takes over background color subitem frame
var FirstLineFrame = 'navig';			// Frame where first level appears
var SecLineFrame = 'space';			// Frame where sub levels appear
var DocTargetFrame = 'space';			// Frame where target documents appear
var TargetLoc = '';				// span id for relative positioning
var HideTop = 0;				// Hide first level when loading new document 1 or 0
var MenuWrap = 1;				// enables/ disables menu wrap 1 or 0
var RightToLeft = 0;				// enables/ disables right to left unfold 1 or 0
var UnfoldsOnClick = 0;				// Level 1 unfolds onclick/ onmouseover
var WebMasterCheck = 0;				// menu tree checking on or off 1 or 0
var ShowArrow = 1;				// Uses arrow gifs when 1
var KeepHilite = 1;				// Keep selected path highligthed
var Arrws = ['imagens/seta.png', 15, 15, 'tridown.gif', 10, 5, 'trileft.gif', 5, 10];	// Arrow source, width and height

function BeforeStart() {
    return;
}
function AfterBuild() {
    return;
}
function BeforeFirstOpen() {
    return;
}
function AfterCloseAll() {
    return;
}


// Menu tree
// MenuX=new Array(Text to show, Link, background image (optional), number of sub elements, height, width);
// For rollover images set "Text to show" to:  "rollover:Image1.jpg:Image2.jpg"

Menu1 = new Array("P�gina Inicial", "menu.faces", "", 0, 25, 125);

Menu2 = new Array("Inclus�es", "#", "", 6, 25, 125);
Menu2_1 = new Array("Incluir Condom�nio", "inclusaocondominio.faces", "", 0, 25, 180);
Menu2_2 = new Array("Incluir Apartamento", "inclusaoapartamento.faces", "", 0, 25, 180);
Menu2_3 = new Array("Incluir Propriet�rio", "inclusaoproprietario.faces", "", 0, 25, 180);
Menu2_4 = new Array("Incluir Endere�o", "inclusaoendereco.faces", "", 0, 25, 180);
Menu2_5 = new Array("Incluir Ve�culo", "inclusaoveiculo.faces", "", 0, 25, 180);
Menu2_6 = new Array("Incluir Servi�o Utilizado", "inclusaoservicoutilizado.faces", "", 0, 25, 180);

Menu3 = new Array("Tarefas", "#", "", 3, 25, 125);
Menu3_1 = new Array("Gerar Cobran�a", "inclusaocobranca.faces", "", 0, 25, 180);
Menu3_2 = new Array("Gerar arquivo Remessa", "inclusaoremessa.faces", "", 0, 25, 180);
Menu3_3 = new Array("Importar arquivo Retorno", "importacaoarquivoretorno.faces", "", 0, 25, 180);

Menu4 = new Array("Consultas", "#", "", 10, 25, 125);
Menu4_1 = new Array("Consultar Condom�nio", "consultacondominio.faces", "", 0, 25, 180);
Menu4_2 = new Array("Consultar Apartamento", "consultaapartamento.faces", "", 0, 25, 180);
Menu4_3 = new Array("Consultar Propriet�rio", "consultaproprietario.faces", "", 0, 25, 180);
Menu4_4 = new Array("Consultar Endere�o", "consultaendereco.faces", "", 0, 25, 180);
Menu4_5 = new Array("Consultar Ve�culo", "consultaveiculo.faces", "", 0, 25, 180);
Menu4_6 = new Array("Consultar Cobran�a", "consultacobranca.faces", "", 0, 25, 180);
Menu4_7 = new Array("Consultar Or�amento", "consultaorcamento.faces", "", 0, 25, 180);
Menu4_8 = new Array("Consultar Mensagem", "consultamensagem.faces", "", 0, 25, 180);
Menu4_9 = new Array("Consultar Servi�o", "consultaservico.faces", "", 0, 25, 180);
Menu4_10 = new Array("Consultar Servi�o Utilizado", "consultaservicoutilizado.faces", "", 0, 25, 180);

Menu5 = new Array("Relatorios", "#", "", 6, 25, 125);
Menu5_1 = new Array("Emitir Boleto Banc�rio", "imprimirboleto.faces", "", 0, 25, 180);
Menu5_2 = new Array("Emitir Ve�culos", "imprimirveiculos.faces", "", 0, 25, 180);
Menu5_3 = new Array("Emitir Inadimplentes", "imprimirinadimplentes.faces", "", 0, 25, 180);
Menu5_4 = new Array("Emitir Servi�os Utilizados", "imprimirservicosutilizados.faces", "", 0, 25, 180);
Menu5_5 = new Array("Emitir Propriet�rios", "imprimirproprietarios.faces", "", 0, 25, 180);
Menu5_6 = new Array("Emitir Hist�rico de Movimento", "imprimirmovimentos.faces", "", 0, 25, 180);

Menu6 = new Array("Parametriza��o", "#", "", 3, 25, 125);
Menu6_1 = new Array("Incluir Or�amento", "inclusaoorcamento.faces", "", 0, 25, 180);
Menu6_2 = new Array("Incluir Mensagem", "inclusaomensagem.faces", "", 0, 25, 180);
Menu6_3 = new Array("Incluir Servi�o", "inclusaoservico.faces", "", 0, 25, 180);

Menu7 = new Array("Manuten��o", "#", "", 1, 25, 125);
Menu7_1 = new Array("Alterar Sequencial da Remessa", "atualizasequencialremessa.faces", "", 0, 25, 200);