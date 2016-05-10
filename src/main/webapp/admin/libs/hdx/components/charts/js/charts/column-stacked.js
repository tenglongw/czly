define([
	'charts/theme',
  	'charts/theme-dark',
  	'highstock'
], function (theme) {
  'use strict';
  Highcharts.setOptions(theme);
  return function (el, options) {
    var cfg = {
      chart: {
        renderTo: el,
        type: 'column'
      },
      plotOptions: {
        series: {
          stacking: 'normal'
        }
      }
    };
    return new Highcharts.Chart($.extend(true, {}, cfg, options));
  };
});
