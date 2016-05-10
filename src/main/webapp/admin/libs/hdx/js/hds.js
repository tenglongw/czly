/*
 * hds.js
 *
 * Copyright (c) 2015 by General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

'use strict';

/*
 This file is a good starting point when you're first developing
 with the HDx. It enables the bootstrap plugins.
 By no means is hds.js a required file,
 it's just here so you can start coding right away. If you would like to
 define your own application files which pull in pieces of the HDx ala carte
 that's strongly encouraged. For instance, if you're not using navbar you can
 delete it from the section below.
 */

define(
	[
        'jquery',
        'navbar',
        'ge-bootstrap'
	], function($) {

		// Never try and talk to elements including the body until the page is ready.
		$(document).ready(
			function() {


				var isTouchDevice = 'ontouchstart' in document.documentElement;

				// Register tooltips but only on non-mobile devices
				if(!isTouchDevice)
				{
					$('.tooltip-target').tooltip({ container: 'body' });
				}
				else
				{
					var $body = $(document.body);
					if(!$body.attr("ontouchstart"))
						$body.attr("ontouchstart", "");
				}


				//IE10 detect to fix issue of ignoring styling rules in CSS files.
				//noinspection ConstantIfStatementJS
				if(/*@cc_on!@*/false)
				{
					var style;
					$('.dropdown-menu, .dropdown-menu.mega-dropdown .nav').each(
						function() {
							style = $(this).attr('style');
							style = ( style ) ? style + ' list-style-type:none !important;' : 'list-style-type:none !important;';
							$(this).attr('style', style);
						});
				}
                // Initialize navbars.
                $('.navbar').navbar();


			});
	});
