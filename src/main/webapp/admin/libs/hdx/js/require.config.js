require.config(
	{
		'waitSeconds': 30, //30seconds added to fix loading issue in IE 9
		'paths': {
			'hds': 'js/hds',
			'kurento': 'components/kurento/kurento-utils',
			'consultation': 'components/consultation/consultation',
			'bootstrap': 'components/bootstrap/js',
			'brandkit': 'components/brandkit/js/hdx-brandkit',
			'cascading-list': 'components/cascading-list/js/cascading-list',
			'charts': 'components/charts/js/charts',
			'charts-theme': 'components/charts/js/charts/theme-hdx',
			"col-reorder-amd": "components/datatables-col-reorder/index",
			'd3-amd': 'components/d3-amd/d3',
			'datagrids': 'components/datagrids/js/datagrids',
			'datatables': 'components/datatables/dist/media/js/jquery.dataTables',
			'datepicker': 'components/datepicker/js/datepicker',
			'declarative-visualizations': 'components/declarative-visualizations/js/declarative-visualizations',
			'hdx-declarative-visualizations': 'components/declarative-visualizations/js/hdx-declarative-visualizations',
			'ge-bootstrap': 'components/ge-bootstrap/js/ge-bootstrap',
			'tables': 'components/tables/js/hdx-tables',
			'jquery': 'components/jquery/jquery',
			'jqueryui-sortable-amd': 'components/jqueryui-sortable-amd/js/jquery-ui-1.10.2.custom',
			'modernizr': 'components/modernizr/modernizr',
			'modules': 'components/modules/js/modules',
			'prettify': 'components/prettify/prettify',
			'respond': 'components/respond/respond.src',
			'responsive-emitter': 'components/responsive-emitter/js/responsive-emitter',
			'responsive-tables': 'components/responsive-tables/js/responsive-tables',
			'spinner': 'components/spinner/js/spinner',
			'trays': 'components/trays/js/trays',
			'spin': 'components/spin.js/spin',
			'holder': 'components/holderjs',
			'highcharts': 'components/highcharts-amd/js/highcharts.src',
			'highstock': 'components/highcharts-amd/js/highstock.src',
			'highcharts-more': 'components/highcharts-amd/js/highcharts-more.src',
			'slider': 'components/slider/js/slider',
			'signin': 'components/signin/js/signin',
			'simpleslider': 'components/simpleslider/js/simpleslider',
			'sparkline': 'components/sparkline/js/sparkline',
			'filechooser': 'components/filechooser/js/filechooser',
			'directional-pad': 'components/directional-pad/js/directional-pad',
			'rich-popover': 'components/rich-popover/js/rich-popover',
			'popover': 'components/popover/js/popover',
			'navbar': 'components/navbar/js/navbar',
      'navbar-offcanvas': 'components/navbar-offcanvas/js/navbar-offcanvas',
      'sticky-dropdown': 'components/navbar-offcanvas/js/sticky-dropdown',
			'requirejs': 'components/requirejs/require',
			'highcharts.src': 'components/highcharts-amd/js/highcharts.src',
			'highstock.src': 'components/highcharts-amd/js/highstock.src',
			'highcharts-more.src': 'components/highcharts-amd/js/highcharts-more.src',
			'underscore': 'components/underscore/underscore',
			'bootstrap-datepicker': 'components/bootstrap-datepicker/js/bootstrap-datepicker',
			'holderjs': 'components/holderjs/holder',
			'datatables-colreorder': 'components/datatables-col-reorder/index',
			'datatables-scroller': 'components/datatables-scroller/index',
			'sizzle': 'components/sizzle/dist/sizzle',
			'oo-utils': 'components/oo-utils/src/js/oo-utils',
			'bootstrap-timepicker': 'components/bootstrap-timepicker/js/bootstrap-timepicker',
			'timepicker': 'components/timepicker/js/timepicker',
			'inspector': 'components/inspector/js/inspector',
			'jumpnav': 'components/jumpnav/js/jumpnav',
			'multi-step-navigation': 'components/multi-step-navigation/js/multi-step-navigation',
			'twitter-bootstrap-wizard': 'components/twitter-bootstrap-wizard/jquery.bootstrap.wizard',
			"typeahead": "components/typeahead.js/dist/typeahead.jquery.min",
			"bloodhound": "components/typeahead.js/dist/bloodhound.min",
            "signalstrength":"components/signalstrength/js/signalstrength"

		},
		'shim': {
			'underscore': {
				'exports': '_'
			},
			'typeahead': {
				deps: ['jquery'],
				exports: 'Typeahead'
			},
			'bloodhound': {
				deps: ['jquery'],
				exports: 'Bloodhound'
			},
			'datatables-colreorder': {
				'deps': ['jquery', 'datatables']
			}
		}
	});
