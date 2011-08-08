/**
 * @Description Ext.ux.LoginWindow for ExtJS 2.x and 3.x
 * @author  Wemerson Januario (Brazil - Goi�nia)
 * @author  Albert Varaksin
 * @author  Sumit Madan
 * @license LGPLv3 http://www.opensource.org/licenses/lgpl-3.0.html
 * @version 1.0, 09/05/2009
 */
Ext.namespace('Ext.ux');
/**
 * Construtor da janela de login
 *
 * @param {Object} config
 * @extends {Ext.util.Observable}
 */
      
Ext.ux.LoginWindow = function(config) {
  Ext.apply(this, config); 
  // Css necess�rio para criar a extens�o.
  var css = "#login-logo .x-plain-body {background:#f9f9f9 url('" + this.basePath + "/" + this.winBanner + "') no-repeat;}" + "#login-form  {background: " + this.formBgcolor + " none;}" + ".ux-auth-header-icon {background: url('" + this.basePath + "/locked.gif') 0 4px no-repeat !important;}" + ".ux-auth-form {padding:10px;}"+ ".ux-auth-login {background-image: url('" + this.basePath + "/key.gif') !important}"
            + ".ux-auth-close {background-image: url('" + this.basePath + "/close.gif') !important}";
    
  Ext.util.CSS.createStyleSheet(css, this._cssId); 
  // Eventos do LoginWindow
  this.addEvents({
    'show': true,
    'reset': true,
    'submit': true,
    'submitpass': true
  });
  Ext.ux.LoginWindow.superclass.constructor.call(this, config);
	  
  //Painel topo (Logotipo do sistema)
  this._logoPanel = new Ext.Panel({
    baseCls: 'x-plain',
    id: 'login-logo',
    region: 'center'
  }); 
  // Seta id para o elementos
  this.usernameId = Ext.id();
  this.passwordId = Ext.id();
  this.emailId = Ext.id();
  this.emailFieldsetId = Ext.id();
  this._loginButtonId = Ext.id();
  this._resetButtonId = Ext.id();
  this._passwordButtonId = Ext.id();
  this._WinPasswordButtonId = Ext.id(); //Painel do formul�rio  
  this._formPanel = new Ext.form.FormPanel({
    region: 'south',
    border: false,
    bodyStyle: "padding: 5px;",
    baseCls: 'x-plain',
    id: 'login-form',
    waitMsgTarget: true,    
    labelWidth: 80,
    defaults: {
      width: 300
    },
    baseParams: {
      task: 'login'
    },
    listeners: {
      'actioncomplete': {
        fn: this.onSuccess,
        scope: this
      },
      'actionfailed': {
        fn: this.onFailure,
        scope: this
      }
    },
    height: 110,
    items: [{
      xtype: 'textfield',
      id: this.usernameId,
      name: this.usernameField,
      fieldLabel: this.usernameLabel,
      vtype: this.usernameVtype,
      validateOnBlur: false,
      allowBlank: false
    },
    {
      xtype: 'textfield',
      inputType: 'password',
      id: this.passwordId,
      name: this.passwordField,
      fieldLabel: this.passwordLabel,
      vtype: this.passwordVtype,
      validateOnBlur: false,
      allowBlank: false
    }]
  });
  //Painel do formul�rio de recupera��o de senha
  this._formPasswordPanel = new Ext.form.FormPanel({
    bodyStyle: "padding: 5px;",
    id: 'password-form',
    waitMsgTarget: true,
    labelWidth: 60,
    autoHeight: true,
    buttonAlign: 'center',
    baseParams: {
      task: 'forgotPassword'
    },
    items: [{
      layout: 'form',
      border: false,
      items: [{
        xtype: 'fieldset',
        title: this.emailFieldset,
        id: this.emailFieldsetId,
        autoHeight: true,
        items: [{
          xtype: 'textfield',
          vtype: this.emailVtype,
          id: this.emailId,
          name: this.emailField,
          fieldLabel: this.emailLabel,
          vtype: this.emailVtype,
          validateOnBlur: false,
          anchor: '98%',
          allowBlank: false
        }]
      }]
    }],
    buttons: [{
      text: this.passwordButton,
      id: this._WinPasswordButtonId,
      width: 100,
      handler	: this.Passwordsubmit,
      scope: this
    }]
  }); 
  // Bot�es padr�es
  var buttons = [{
    id: this._loginButtonId,
    text: this.loginButton,
    handler: this.submit,
    scale: 'medium',
    scope: this
  }];
  var keys = [{
    key: [10, 13],
    //Tecla ENTER
    handler: this.submit,
    scope: this
  }]; 
  //Testa se o bot�o que recupera senha existe
  if (typeof this.passwordButton == 'string') {
    buttons.push({
      id: this._passwordButtonId,
      text: this.passwordButton,      
      handler: this.password,
      scale: 'medium',
      scope: this
    });
  } 
  //Testa se o bot�o que reseta o formul�rio existe
  if (typeof this.resetButton == 'string') {
    buttons.push({
      id: this._resetButtonId,
      text: this.resetButton,      
      handler: this.reset,
      scale: 'medium',
      scope: this
    });
    keys.push({
      key: [27],
      //Tecla ESC
      handler: this.reset,
      scope: this
    });
  } 
  //Cria a janela principal de login
  this._window = new Ext.Window({
    width: 429,
    height: 270,
    closable: false,
    resizable: false,
    draggable: true,
    modal: this.modal,
    iconCls: 'ux-auth-header-icon',
    title: this.title,
    layout: 'border',
    bodyStyle: 'padding:5px;',
    buttons: buttons,
    buttonAlign: 'center',
    keys: keys,
    plain: false,
    items: [this._logoPanel, this._formPanel]
  }); 
  //Cria a janela de recupera��o de senha
  this._windowPassword = new Ext.Window({
    width: 350,
    height: 160,
    closable: true,
    resizable: false,
    draggable: true,
    modal: this.modal,
    iconCls: 'ux-auth-header-icon',
    title: this.Passwordtitle,
    bodyStyle: 'padding:5px;',
    keys: keys,
    closeAction: 'hide',
    items: [this._formPasswordPanel]
  }); 
  //Seta foco no campo username quando a janela principal � exibida
  // Dispara o evento "show"
  this._window.on('show',
  function() {
    this.setlanguage();
    Ext.getCmp(this.usernameId).focus(false, true);    
    this.fireEvent('show', this);
  },
  this);
}; 
// Extende a classe Ext.util.Observable
Ext.extend(Ext.ux.LoginWindow, Ext.util.Observable, {
  /**
     * T�tulo da janela principal
     *
     * @type {String}
     */
  title: '',
  /**
     * T�tulo da janela de recupera��o de senha
     *
     * @type {String}
     */
  Passwordtitle: '',  
    /**
     * T�tulo do fieldset da janela de recupera��o de senha
     *
     * @type {String}
     */
  emailFieldset: '',  
  /**
     * Mensagem de espera ao enviar os dados
     *
     * @type {String}
     */
  waitMessage: '',
  /**
     * Texto do bot�o de login
     *
     * @type {String}
     */
  //loginButton : Ext.lang.us.login,
  loginButton: '',
  /**
     * Texto do bot�o de recupera��o de senha
     *
     * @type {String}
     */
  passwordButton: '',
  /**
     * Texto do bot�o limpar
     *
     * @type {String}
     */
  resetButton: '',
  /**
     * T�tulo do campo usu�rio
     *
     * @type {String}
     */
  usernameLabel: '',
  /**
     * Nome do campo usu�rio
     *
     * @type {String}
     */
  usernameField: 'username',
  /**
     * Valida��o do campo usu�rio
     *
     * @type {String}
     */
  usernameVtype: 'alphanum',
  /**
     * T�tulo do campo email
     *
     * @type {String}
     */
  emailLabel: '',
  /**
     * Nome do campo email
     *
     * @type {String}
     */
  emailField: 'email',
  /**
     * Valida��o do campo email
     *
     * @type {String}
     */
  emailVtype: 'email',
  /**
     * T�tulo do campo senha
     *
     * @type {String}
     */
  passwordLabel: '',
  /**
     * Nome do campo senha
     *
     * @type {String}
     */
  passwordField: 'password',
  /**
     * Valida��o do campo senha
     *
     * @type {String}
     */
  passwordVtype: 'alphanum',
  /**
     * Nome do combo idioma
     *
     * @type {String}
     */
  languageField: 'lang',
  /**
     * T�tulo do iconcombobox idioma
     *
     * @type {String}
     */
  languageLabel: '',
  /**
     * Url de requisi��o de login
     *
     * @type {String}
     */
  url: '',
  /**
     * Url de requisi��o de recupera��o de senha
     *
     * @type {String}
     */
  emailUrl: '',  
  /**
     * Url de destino caso login seja efetivado
     *
     * @type {String}
     */
  locationUrl: '',
  /**
     * Diret�rio das imagens
     *
     * @type {String}
     */
  basePath: 'img',
  /**
     * Logotipo do sistema (Banner)
     *
     * @type {String}
     */
  winBanner: '',
  /**
     * Cor de fundo do formul�rio
     *
     * @type {String}
     */
  formBgcolor: '',
  /**
     * M�todo de envio do formul�rio
     *
     * @type {String}
     */
  method: 'post',
  /**
     * Abrir janela modal
     *
     * @type {Bool}
     */
  modal: false,
  /**
     * Identificador do CSS
     *
     * @type {String}
     */
  _cssId: 'ux-LoginWindow-css',
  /**
     * Painel topo (Logotipo do sistema)
     *
     * @type {Ext.Panel}
     */
  _logoPanel: null,
  /**
     * Painel do formul�rio
     *
     * @type {Ext.form.FormPanel}
     */
  _formPanel: null,
  /**
     * Objeto da janela principal
     *
     * @type {Ext.Window}
     */
  _window: null,
  /**
     * Objeto da janela de recupera��o de senha
     *
     * @type {Ext.Window}
     */
  _windowPassword: null,
  /**
     * Exibe a  janela principal
     *
     * @param {Ext.Element} el
     */
  show: function(el) {
    this._window.show(el);
  },
  /**
     * Exibe a  janela de recupera��o de senha
     *
     * @param {Ext.Element} el
     */
  password: function(el) {
    this._windowPassword.show(el);
  },
  /**
     * Limpa o formul�rio
     */
  reset: function() {
    if (this.fireEvent('reset', this)) {
      this._formPanel.getForm().reset();
    }
  },  
  /**
     * Idioma padr�o do formul�rio
     */
  defaultLanguage: 'ptbr',
  /**
     * Seleciona o idioma
     */
  setlanguage: function() {
    Ext.override(Ext.form.Field, {
      setFieldLabel: function(text) {
        if (this.rendered) {
          this.el.up('.x-form-item', 10, true).child('.x-form-item-label').update(text);
        } else {
          this.fieldLabel = text;
        }
      }
    });
    this._window.setTitle('登录认证');
    this._windowPassword.setTitle('找回密码');
    Ext.getCmp(this._loginButtonId).setText('登录');
    Ext.getCmp(this._passwordButtonId).setText('找回密码');
    Ext.getCmp(this._resetButtonId).setText('清除');
    Ext.getCmp(this._WinPasswordButtonId).setText('找回密码');
    Ext.getCmp(this.emailId).setFieldLabel('电子邮件');
    Ext.getCmp(this.emailFieldsetId).setTitle('请输入您的电子邮件');
    Ext.getCmp(this.usernameId).setFieldLabel('用户名:');
    Ext.getCmp(this.passwordId).setFieldLabel('密码:');
    this.waitMessage = '正在发送数据...';     	 
  },
  
  /**
     * Envia a requisi��o de login
     */
    submit : function () {
        var form = this._formPanel.getForm();

        if (form.isValid())
        {
            Ext.getCmp(this._loginButtonId).disable();
            if(Ext.getCmp(this._cancelButtonId)) {
                Ext.getCmp(this._cancelButtonId).disable();
            }
            if (this.fireEvent('submit', this, form.getValues()))
            {
                form.submit ({
                    url     : this.url,
                    method  : this.method,
                    waitMsg : this.waitMessage,
                    success : this.onSuccess,
                    failure : this.onFailure,
                    scope   : this
                });
            }
        }
    },
    
    /**
     * Envia a requisi��o de recupera��o de senha 
     */
    Passwordsubmit : function () {
        var form = this._formPasswordPanel.getForm();

        if (form.isValid())
        {
            Ext.getCmp(this._WinPasswordButtonId).disable();
            if (this.fireEvent('submitpass', this, form.getValues()))
            {
                form.submit ({
                    url     : this.emailUrl,
                    method  : this.method,
                    waitMsg : this.waitMessage,
                    success : this.onEmailSuccess,
                    failure : this.onEmailFailure,
                    scope   : this
                });
            }
        }
    },
    
  /**
     * Se receber sucesso
     *
     * @param {Ext.form.BasicForm} form
     * @param {Ext.form.Action} action
     */
  onSuccess: function(form, action) {
    if (action && action.result) {
      window.location = action.result.redirect;
      window.fullscreen=true;
      //window.open(action.result.redirect,'newwindow','height='+screen.height+',width='+screen.width+',top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=yes, location=no,status=no');
      //window.close();
    }
  },
  /**
     * Se receber falha
     *
     * @param {Ext.form.BasicForm} form
     * @param {Ext.form.Action} action
     */
  onFailure: function(form, action) { // enable buttons
    Ext.getCmp(this._loginButtonId).enable();
    if (Ext.getCmp(this._resetButtonId)) {
      Ext.getCmp(this._resetButtonId).enable();
    }
    Ext.MessageBox.show({
							title: '登录失败',
							msg: action.result.message,
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.ERROR
						});
  },  
  /**
     * Se receber sucesso
     *
     * @param {Ext.form.BasicForm} form
     * @param {Ext.form.Action} action
     */
  onEmailSuccess: function(form, action) {
    if (action && action.result) {
      Ext.MessageBox.show({
							title: 'We love ExtJs',
							msg: 'ExtJs Brasil - visite www.extjs.com.br',
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.INFO
						});
    }
  },
  /**
     * Se receber falha
     *
     * @param {Ext.form.BasicForm} form
     * @param {Ext.form.Action} action
     */
  onEmailFailure: function(form, action) { 
  	// Ativa os bot�es
    Ext.getCmp(this._WinPasswordButtonId).enable();
    Ext.MessageBox.show({
							title: 'We love ExtJs',
							msg: 'ExtJs Brasil - visite www.extjs.com.br',
							buttons: Ext.MessageBox.OK,
							icon: Ext.MessageBox.INFO
						});
  }
});
