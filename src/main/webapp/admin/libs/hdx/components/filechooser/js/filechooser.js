'use strict';

define(['jquery'], function ()
{
  $.fn.filechooser = function ()
  {
    return this.each(function ()
    {
      var $source = $(this).wrap('<div class="filechooser"></div>')
      var $container = $source.parent()
      var $label = $('<label></label>').prependTo($container)
      var $button = $('<button class="btn">Choose File</button>').prependTo($container)
      $source
        .css('right', $container.outerWidth() - $button.outerWidth())
        .hover(
          function () { $button.addClass('hover') },
          function () { $button.removeClass('hover') }
        )
        .mousedown(function () { $button.addClass('active') })
        .mouseup(function () { $button.removeClass('active') })
        .change(function ()
        {
          $label.text($source.val().split(/[\/\\]/).pop())
        })
      var label_width = $container.width() - $button.outerWidth() - 10
      $label
        .css({
          width: label_width,
          left: $container.width() - label_width
        })
    })
  }

  $('input[type=file]').filechooser()
})
