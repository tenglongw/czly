'use strict';

define([
  'ge-bootstrap', 'ge-bootstrap/placeholder'
], function ($, placeholder) {
  // SIGNIN CONTAINER PUBLIC CLASS DEFINITION

  $.fn.signinconstruct = function (element) {
    var element = $('.signin');
    if (element.attr('header') && element.attr('header') !== "") {
      this.$signinHeader = element.attr('header');
    }
    if (element.attr('app-name') && element.attr('app-name') !== "") {
      this.$signinAppname = element.attr('app-name');
    }
    if (element.attr('forgot-pwd') && element.attr('forgot-pwd') !== "") {
      this.$signinPwd = element.attr('forgot-pwd');
    }
    if (element.attr('error-block') && element.attr('error-block') !== "") {
      this.$error = element.attr('error-block');
    }

    // forgotpwd flag to show/hide Forgot pwd link
    var pwd = "";
    if (this.$signinPwd && this.$signinPwd !== "" && this.$signinPwd != null && this.$signinPwd !== 'undefined') {
      if (this.$signinPwd === 'true') {
        pwd = '<div class="pwd"><h4><a href="#modal-forgot-pwd" data-toggle="modal">Forgot your password?</a> </h4></div>' +
        '<div id="modal-forgot-pwd" class="modal fade">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content"><div class="signin-left">' +
        '<div class="modal-header signin-margin20">' +
        '<button type="button" class="close btn btn-icon" data-dismiss="modal"><i class="icon-ico_cross_rnd_lg"></i> </button>' +
        '<h3 class="forgot-pwd-label">Forgot your password?</h3>' +
        '</div>' +
        '<div class="modal-body">' +
        '<input type="text" placeholder="Email address" class="forgot-pwd-email signin-margin20"/><br/>' +
        '<label class="forgot-pwd-label signin-margin20"><h4>We will send you a link to reset your password</h4></label>' +
        '</div>' +
        '<div class="modal-footer forgot-pwd-modal-footer">' +
        '<a href="#" class="forgot-pwd-btn btn btn-primary">Send</a>' +
        '</div>' +
        '</div></div>' +
        '</div>' +
        '</div>';
      }
    }

    //header flag - to show/hide GE logo, application name and GE healthcare
    //signinAppname variable - to make application name editable
    var headerContent;
    headerContent = '<div class="signin-noheader-margin"></div>';
    // check header for following exceptions
    // 1- if header attribute exists and not empty
    // 2- if header is not null
    // 3- if header is not undefined
    if (this.$signinHeader && this.$signinHeader !== "" && this.$signinHeader != null && this.$signinHeader !== 'undefined') {
      if (this.$signinHeader === 'true') {
        if (this.$signinAppname && this.$signinAppname !== "" && this.$signinAppname != null && this.$signinAppname !== 'undefined') {
          headerContent = '<div class="header text-center"><div>' +
          '<span class="ge-logo signin-margin20">General Electric</span>' +
          '<h1 class="title voice voice-brand name">' + this.$signinAppname + '</h1>' +
          '</div>' +
          '<div class="signin-margin40" >' +
          '</div></div>';
        } else {
          headerContent = '<div class="header text-center"><div>' +
          '<span class="ge-logo signin-margin20">General Electric</span>' +
          '</div>' +
          '<div class="signin-margin40" >' +
          '</div></div>';
        }
      }
    }

    // construct error block only if error is not null/ not empty/ not undefined
    var error = '';
    if (this.$error && this.$error !== "" && this.$error != null && this.$error !== 'undefined') {
      error = '<div><label class="error-block">' + this.$error + '</label></div><br/>';
    }

    // construct the main content of the sign in component
    var content;
    if (error !== '') {
      content = headerContent +
      '<div>' +
      '<form>' +
      '<input type="text" placeholder="Username" class="span2 span2-error signin-margin20"/> <br/>' +
      '<input type="password" placeholder="Password" class="span2 span2-error"/> <br/>' + error +
      '<button type="button" class="signin-btn btn btn-primary signin-margin20">Sign In</button>' +
      '</form>' + pwd +
      '</div></div>';
    } else {
      content = headerContent +
      '<div>' +
      '<form>' +
      '<input type="text" id="username" name="username" placeholder="Username" class="span2 signin-margin20"/> <br/>' +
      '<input type="password" id="password" name="password" placeholder="Password" class="span2 signin-margin40"/> <br/>' +
      '<button type="button" class="signin-btn btn btn-primary signin-margin20">Sign In</button>' +
      '</form>' + pwd +
      '</div></div>';
    }
    element.append(content);

    placeholder(element.find("input[placeholder]"));
  };
});
