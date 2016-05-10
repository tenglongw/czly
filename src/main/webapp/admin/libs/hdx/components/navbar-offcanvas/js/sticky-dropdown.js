/* ========================================================================
 * Modified to allow dropdowns to remain open from:
 * Bootstrap: dropdown.js v3.2.0
 * http://getbootstrap.com/javascript/#dropdowns
 * ========================================================================
 * Copyright 2011-2014 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 * ======================================================================== */

define(
	['jquery'], function($) {

		'use strict';

		// DROPDOWN CLASS DEFINITION
		// =========================
		var backdrop = '.stickydropdown-backdrop';
		var toggle = '[data-toggle="stickydropdown"]';
		var StickyDropdown = function(element) {
			$(element).on('click.bs.stickydropdown', this.toggle)
		};

		StickyDropdown.VERSION = '1.0.0';

		StickyDropdown.prototype.closeAll = function() {
			$(".navmenu.offcanvas a[data-toggle='stickydropdown']").parent().removeClass('open');
		};

		StickyDropdown.prototype.toggle = function(e) {
			var $this = $(this);

			if($this.is('.disabled, :disabled')) return;

			var $parent = getParent($this);
			var isActive = $parent.hasClass('open');

			if(this.isSticky)
			{
				$parent.removeClass('open');
			}
			else
			{
				clearMenus();
			}

			if(!isActive)
			{
				if('ontouchstart' in document.documentElement && !$parent.closest('.navbar-nav').length)
				{
					// if mobile we use a backdrop because click events don't delegate
					$('<div class="stickydropdown-backdrop"></div>').insertAfter($(this)).on('click', clearMenus)
				}

				var relatedTarget = { relatedTarget: this };
				$parent.trigger(e = $.Event('show.bs.stickydropdown', relatedTarget));

				if(e.isDefaultPrevented()) return;

				$this.trigger('focus');

				$parent
					.toggleClass('open')
					.trigger('shown.bs.stickydropdown', relatedTarget)
			}

			return false;
		};

		StickyDropdown.prototype.keydown = function(e) {
			if(!/(38|40|27)/.test(e.keyCode)) return;

			var $this = $(this);

			e.preventDefault();
			e.stopPropagation();

			if($this.is('.disabled, :disabled')) return;

			var $parent = getParent($this);
			var isActive = $parent.hasClass('open');

			if(!isActive || (isActive && e.keyCode==27))
			{
				if(e.which==27) $parent.find(toggle).trigger('focus');
				return $this.trigger('click')
			}

			var desc = ' li:not(.divider):visible a';
			var $items = $parent.find('[role="menu"]' + desc + ', [role="listbox"]' + desc);

			if(!$items.length) return;

			var index = $items.index($items.filter(':focus'));

			if(e.keyCode==38 && index>0)                 index--;                        // up
			if(e.keyCode==40 && index<$items.length - 1) index++;                        // down
			if(!~index)                                      index = 0;

			$items.eq(index).trigger('focus');
		};

		function clearMenus(e) {
			if(e && e.which===3) return;
			$(backdrop).remove();

			$(toggle).each(
				function() {
					var $parent = getParent($(this));
					var relatedTarget = { relatedTarget: this };
					if(!$parent.hasClass('open')) return;
					$parent.trigger(e = $.Event('hide.bs.dropdown', relatedTarget));
					if(e.isDefaultPrevented()) return;
					$parent.removeClass('open').trigger('hidden.bs.dropdown', relatedTarget)
				})
		}

		function getParent($this) {
			var selector = $this.attr('data-target');

			if(!selector)
			{
				selector = $this.attr('href');
				selector = selector && /#[A-Za-z]/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, ''); // strip for ie7
			}

			var $parent = selector && $(selector);

			return $parent && $parent.length ? $parent : $this.parent();
		}


		// DROPDOWN PLUGIN DEFINITION
		// ==========================

		function Plugin(option) {
			return this.each(
				function() {
					var $this = $(this);
					var data = $this.data('bs.stickydropdown');

					if(!data) $this.data('bs.stickydropdown', (data = new StickyDropdown(this)));
					if(typeof option=='string') data[option].call($this)
				});
		}

		var old = $.fn.stickydropdown;

		$.fn.stickydropdown = Plugin;
		$.fn.stickydropdown.Constructor = StickyDropdown;


		// DROPDOWN NO CONFLICT
		// ====================

		$.fn.stickydropdown.noConflict = function() {
			$.fn.stickydropdown = old;
			return this;
		};


		// APPLY TO STANDARD DROPDOWN ELEMENTS
		// ===================================

		$(document)
			.on('click.bs.offcanvasnav.respondtobreakpoint', StickyDropdown.prototype.closeAll)
			.on('click.bs.stickydropdown.data-api', '.stickydropdown form', function(e) { e.stopPropagation() })
			.on('click.bs.stickydropdown.data-api', toggle, StickyDropdown.prototype.toggle)
			.on('keydown.bs.stickydropdown.data-api', toggle + ', [role="menu"], [role="listbox"]', StickyDropdown.prototype.keydown)

	});
