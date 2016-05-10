01-16-2015
============================
* Release tags of components for 15.1

1-6-2015
==================
* test commit

12-22-2014
===================
*Testing CI during holidays! really!!!

1.3.0 / 2014-07-09
==================
* Upgrading to bootstrap 3.x

0.7.0 / 2013-08-23
===================
* Complies with IIDS 1.0.x project structure in source
* Updated documentation to include additional components and patterns from GE Bootstrap
* Now uses GE Boostrap 1.0.3
* Considerable LESS cleanups
    * Changed structure of LESS files (Lights-on theme now resides in the root 'less' directory. Lights-off theme resides in the 'less/hds-lights-off' directory)
    * Removed all HDx-specific variables and mixins
    * Removed unnecessary and redundant LESS rules
    * Converted hard-coded color and unit values to Brandkit LESS variables where applicable
    * Minor color adjustments made to conform with GE Bootstrap/Brandkit color conventions
* Broke out the following HDx-specific UI elements into Dx components
    * sparkline
    * hds-nav
    * hds-modules
    * hds-tables
    * slider
    * rich-popover
      *  The component's main method name has changed from rich_popover() to richPopover()
      *  Added functionality to properly support multiple rich-popovers on a page
    * directional-pad
      * The path to the required SVG images can now be dynamically set during plugin instantiation via a new 'imagesUrl' config variable
    * filechooser
* Removed most HDx-native JavaScript. Most JavaScript now resides in new Dx components
* Checkbox, radio and select form components are now browser-native UI components

0.6.0 / 2013-04-24
==================
* Updated d3 to version 3.
* Updated require-jquery to latest version. jQuery is now 1.9.1 and requireJS is 2.1.5
* Added charts-more.js which includes the new spider graph chart.
* Updated to work with ge-bootstrap 0.2.3. New ge-bootstrap.css file added.
* Removed directional pad gradients and improved support for IE9
* Improved slider API
* Improved rich_popover documentation and js
* Added favicons and touch icons for mobile

0.5.2 / 2012-12-03
==================
* Use new IIDS 0.9.0 project structure in source
* Swapped require-config.js to common.js

0.5.1 / 2012-10-29
==================
* Data grid containers
* Sparklines
* Navigation
* Search utilities
* Lists & accordions
* Filter controls
* Containers
* Minor enhancements and bug fixes

0.5.0 / 2012-09-29
==================
* Core hds styles (type, color, etc)
* Overrides of standard iids components
* Mega buttons & mega button groups
* Directional pads
* Sliders
* Popovers w/ rich content
* Lights-off version
