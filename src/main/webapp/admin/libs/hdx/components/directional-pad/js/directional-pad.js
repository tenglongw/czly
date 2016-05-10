define(['jquery'], function ($) {
  'use strict';

  $.fn.directionalPad = function (userOptions) {

    // Default options
    var options = $.extend({
      imagesUrl: '../img/hds/svg',
      imagePrefix: 'directional-pad'
    }, userOptions || {});

    return this.each(function () {
      setupPad($(this), options);
    });
  };

  var svg = {},
    gradientReq = null,
    copyNode = function (node) {
      var clone = document.createElementNS(node.namespaceURI, node.nodeName);

      for (var i = 0; i < node.attributes.length; i++) {
        var attr = node.attributes[i];

        if (/^xmlns\b/.test(attr.nodeName)) {
          continue;
        }

        clone.setAttributeNS(attr.namespaceURI, attr.nodeName, attr.nodeValue);
      }

      for (var j = 0; j < node.childNodes.length; j++) {
        var child = node.childNodes[j];
        clone.appendChild(
          child.nodeType === 1 ? copyNode(child) : document.createTextNode(child.nodeValue));
      }

      return clone;
    };

  var setupPad = function ($container, options) {
    if ($container.data('directional-pad')) {
      return;
    }

    $container.data('directional-pad', true);

    var $buttons = $container.find('a').hide(),
      numButtons = $buttons.length,
      style = null;

    var buttonDown = function () {
      var $elm = $(this);

      // Add '.pressed' as the last class. This is so we can style a proper
      // depressed state since IE9 doesn't support :active properly.

      $elm.attr('class', $elm.attr('class') + ' pressed');

      // Add a single fire listener to mouseup to the document. This way
      // if the user clicks and drags outside the directional pad we'll still
      // capture the event and we can remove the pressed class.
      $(document).one('mouseup touchend', function () {
        $elm.attr('class', $elm.attr('class').replace('pressed', ''));
      });
    };

    var buttonClick = function () {
      var $elm = $(this),
        button;

      if ($elm.is('.up')) {
        button = '.up';
      }
      else if ($elm.is('.down')) {
        button = '.down';
      }
      else if ($elm.is('.left')) {
        button = '.left';
      }
      else if ($elm.is('.right')) {
        button = '.right';
      }

      $buttons.filter(button).trigger('click');
    };

    var src = options.imagesUrl + '/' + options.imagePrefix;

    if ($container.is('.mega')) {
      src += '-mega';
    }

    if (numButtons === 2) {
      if ($container.find('a.up').length > 0) {
        src += '-vertical';
      }
      else {
        src += '-horizontal';
      }
    }

    src += '-' + numButtons + '.svg';

    var req;
    if (svg[src]) {
      req = svg[src];
    }
    else {
      req = $.get(src);
    }

    req.done(function (doc) {
      // Internet Explorer will throw a HIERARCHY_REQUEST_ERR: DOM Exception 3 if
      // you try to clone the svg element into the page.
      // To avoid browser sniffing we put the offending code in a try/catch and
      // use a workaround if it fails in IE.
      // A more in-depth discussion is available here:
      // https://groups.google.com/forum/#!msg/d3-js/-qYDy71c_lA/w8szly0-eZgJ
      var $svg;
      try {
        $svg = $(doc).find('svg').clone();
        $container.append($svg);
      }
      catch (e) {
        $svg = $(copyNode($(doc).find('svg').get(0)));
        $container.append($svg);
      }

      var buttons = $svg.find('.button').get();

      if ('ontouchstart' in window) {
        for (var i = 0; i < buttons.length; i++) {
          buttons[i].addEventListener('touchstart', buttonDown, false);
          buttons[i].addEventListener('touchend', buttonClick, false);
        }
      }
      else {
        for (var j = 0; j < buttons.length; j++) {
          buttons[j].addEventListener('mousedown', buttonDown, false);
          buttons[j].addEventListener('click', buttonClick, false);
        }
      }
    });
  };
});
