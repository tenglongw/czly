define(['jquery', 'ge-bootstrap'], function ($) {
  'use strict';

  // Create a new function for popovers with a different style
  // that pull their content from an element on the page
  $.fn.richPopover = function (option) {
    return this.each(function () {
      var $this = $(this),
        options = $.extend({}, option);

      // If the popover has already been initialized then ignore
      // future calls
      if ($this.data('rich-popover')) {
        return;
      }

      $this.data('rich-popover', true);

      options = $.extend({
        trigger: 'click'
      }, options || {});

      options.template = '<div class="popover popover-rich"><div class="arrow"></div><div class="popover-inner"><div class="popover-content"><div></div></div></div></div>';

      if (!options.content)
      {
      	    options.content = function () {
	        var selector = $this.data('content-selector');
	
	        if (!selector) {
	          return;
	        }
	
	        return $(selector).html();
	      };
      }

      var isClickTriggered = options.trigger === 'click';

      if (isClickTriggered) {
        options.trigger = 'manual';
      }

      $this.popover(options);

      if (isClickTriggered) {
        var popover = $this.data('bs.popover'),
          stopClicks = function (e) {
            e.preventDefault();
            e.stopPropagation();
          };

        $this.click(function (e) {
          e.preventDefault();
          e.stopPropagation();

          // Hide any other open rich popovers
          var $currentButton = $(this);
          $('.popover-target[data-popover-type=rich]').each(function () {
            if ($currentButton[0] !== $(this)[0]) {
              if ($(this).data('bs.popover').tip().hasClass('in')) {
                $(this).data('bs.popover').tip().unbind('click', stopClicks);
                $(this).data('bs.popover').hide();
              }
            }
          });

          var $tip = popover.tip();

          if ($tip.hasClass('in')) {
            $tip.unbind('click', stopClicks);
            popover.hide();
          }
          else {
            popover.show();
            $tip.bind('click', stopClicks);
          }

        });

        $(document).click(function (e) {
          popover.hide();
        });
      }
    });
  }; // END: richPopover

});
