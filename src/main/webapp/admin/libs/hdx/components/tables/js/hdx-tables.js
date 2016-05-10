define(['jquery'], function ($) {
  'use strict';

  $.fn.table = function () {
    return this.filter('.table.table-arrow-selection').each(function () {
      var $container = $(this);

      if ($container.data('table-arrow')) {
        return;
      }

      $container.data('table-arrow', true);

      $container.find('tbody td:first-child').each(function () {
        var $cell = $(this);

        $cell
          .wrapInner('<div class="arrow-container"><div class="content"></div></div>')
          .find('.arrow-container')
          .css({
            // We need to alter the container margin to account for the
            // cell padding.
            marginTop: '-' + $cell.css('padding-top'),
            marginRight: '-' + $cell.css('padding-right'),
            marginBottom: '-' + $cell.css('padding-bottom'),
            marginLeft: '-' + $cell.css('padding-left'),
            // and alsoadd that padding back to the container
            paddingTop: $cell.css('padding-top'),
            paddingRight: $cell.css('padding-right'),
            paddingBottom: $cell.css('padding-bottom'),
            paddingLeft: $cell.css('padding-left')
          })
        // We add the arrow element
        .append('<span class="arrow"><span class="point"></span></span>');
      });
    });
  };

  $(function () {
    $('table.table-arrow-selection').table();
  });
});
